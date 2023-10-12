package com.tms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jcsv.writer.CSVEntryConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.StringUtils;

import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public final class AppUtils {
    private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);

    private static String toRevertCamelCaseLowercase(String str) {
        if (StringUtils.hasText(str)) {
            String[] r = str.split("(?=\\p{Upper})|(?<=\\D)(?=\\d+\\b)");
            return String.join("_", r).toLowerCase();
        }
        return "";
    }


    private static String lowerCaseFirst(String str) {
        if (StringUtils.hasText(str)) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return "";
    }

    private static String upperCaseFirst(String str) {
        if (StringUtils.hasText(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return "";
    }

    public static void close(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (Exception e) {
                logger.error("[AppUtils] Error close writer");
            }
        }
    }

    public static <T> MapSqlParameterSource ob2SqlSource(T config) {
        return ob2SqlSource(config, false);
    }

    public static <T> MapSqlParameterSource ob2SqlSource(T config, boolean cursor) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        Field[] fields = config.getClass().getDeclaredFields();
        Method method;
        for (Field f : fields) {
            String name = f.getName();
            String reName = "in_" + toRevertCamelCaseLowercase(name);
            if (StringUtils.hasText(reName)) {
                try {
                    method = config.getClass()
                            .getMethod("get" + upperCaseFirst(name));

                    Object invoke = method.invoke(config);
                    in.addValue(reName, invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (cursor) {
            in.addValue("inout_rc_name", "c_cursor");
        }
        return in;
    }

    public static <T> List<SqlParameter> ob2SqlInParams(T config, boolean inOut) {
        return ob2SqlInParams(config, inOut, false);
    }

    public static <T> List<SqlParameter> ob2SqlInParams(T config, boolean inOut, boolean out) {
        Field[] fields = config.getClass().getDeclaredFields();
        List<SqlParameter> inParams = new ArrayList<>();
        for (Field f : fields) {
            String name = f.getName();
            String reName = "in _" + toRevertCamelCaseLowercase(name);
            if (StringUtils.hasText(reName)) {
                try {
                    SqlParameter sqlParameter = null;
                    if (f.getType().isAssignableFrom(Integer.class)) {
                        sqlParameter = new SqlParameter(reName, Types.INTEGER);
                    } else if (f.getType().isAssignableFrom(String.class) && (name.equals("attribute") || name.equals("jsonLog")
                            || name.equals("packageListItem") || name.equals("chatId") || name.equals("pbAll") || name.equals("requestJson") || name.equals("responseJson") || name.equals("json"))) {
                        sqlParameter = new SqlParameter(reName, Types.OTHER);
                    } else if (f.getType().isAssignableFrom(String.class)) {
                        sqlParameter = new SqlParameter(reName, Types.VARCHAR);
                    } else if (f.getType().isAssignableFrom(Double.class)) {
                        sqlParameter = new SqlParameter(reName, Types.NUMERIC);
                    } else if (f.getType().isAssignableFrom(Boolean.class)) {
                        sqlParameter = new SqlParameter(reName, Types.BOOLEAN);
                    }
                    if (sqlParameter != null) {
                        inParams.add(sqlParameter);
                    }
                } catch (Exception e) {
                    logger.info(e.getMessage(), e);
                }
            }
        }

        if (inOut) {
            inParams.add(new SqlParameter("inout_rc_name", Types.OTHER));
        }

        if (out) {
            inParams.add(new SqlOutParameter("out_status", Types.INTEGER));
            inParams.add(new SqlOutParameter("out_fail_message", Types.VARCHAR));
            inParams.add(new SqlOutParameter("out_count_row", Types.INTEGER));
        }

        return inParams;
    }

    public static <T> CSVEntryConverter<T> ob2Str(T config) {
        Field[] fields = config.getClass().getDeclaredFields();
        String[] res = new String[fields.length];
        Method method;
        int i = 0;
        for (Field f : fields) {
            try {
                method = config.getClass()
                        .getMethod("get" + upperCaseFirst(f.getName()));

                Object invoke = method.invoke(config);
                if (invoke != null) {
                    res[i++] = String.valueOf(invoke);
                } else {
                    res[i++] = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        final String[] result = res;
        return t -> result;
    }
}