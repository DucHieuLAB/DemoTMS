package com.tms.api;

import java.lang.reflect.Field;
import java.util.List;

public class SqlFunctionUtils {
    private static final String GET_FINAL_INPUT_PARAMS = "out \"out_status\" int4, out \"out_fail_message\" varchar, out \"out_count_row\" int4, inout \"inout_rc_name\" refcursor";
    private static final String GET_FINAL_FUNCTION_INS_PARAMS = "out \"out_status\" int4, out \"out_fail_message\" varchar";
    private static final String GET_FINAL_FUNCTION_UPD_PARAMS = "out \"out_status\" int4, out \"out_fail_message\" varchar";

    private static final String FINAL_FUNCTION_STR = "EXCEPTION WHEN OTHERS THEN\n" +
            "\tout_status \t\t\t:=1;\n" +
            "\tout_fail_message := SQLERRM;\n" +
            "END;\n" +
            "$BODY$\n" +
            "  LANGUAGE plpgsql VOLATILE\n" +
            "  COST 100";
    private static final String AFTER_INPUT_PARAMS = ")\n" +
            "  RETURNS \"pg_catalog\".\"record\" AS $BODY$\n\n" +
            "BEGIN\n" +
            "\tout_status \t\t\t\t:= 0;\n" +
            "\tout_fail_message \t:= 'ok';\n";
    private static final String RETURN = "RETURNS \"pg_catalog\".\"record\" AS $BODY$\n\n";
    private static final String DECLARE = "DECLARE\n";
    private static final String BEGIN = "BEGIN\n\tout_status \t\\tt\t:= 0;\n\tout_fail_message \t:= 'ok';\n";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String CREATE_FUNCTION = "CREATE OR REPLACE FUNCTION ";
    private static final String SELECT_COUNT = "SELECT COUNT(*) INTO out_count_row";
    private static final String SELECT_COUNT_ROW = "SELECT COUNT(*) INTO count_row";

    public static void main(String[] args) {
//        List<String> date = new ArrayList<>();
//        date.add("updateDay");
//        date.add("createdDate");
//        date.add("modifiedDate");
//        System.out.println(getFunctionGet(GetScheduleUpdate.class, "cf_schedule_update", date));
    }

    public static String getFunctionGet(Class<?> config, String tableName) {
        return CREATE_FUNCTION +
                "\"public\".\"get_" + tableName + "\"(" +
                getInputParamsFunctionGet(config) +
                AFTER_INPUT_PARAMS +
                "OPEN inout_rc_name FOR\n" +
                "SELECT * FROM (\n" +
                "\tSELECT " +
                getParamsNoLimitOffset(config) +
                "\n\tFROM " + tableName + "\n" +
                ") AS " + tableName + "_table\nWHERE\n" +
                getWhereParamsNoLimitOffSet(config) +
                "\n\tORDER BY lead_id DESC\n" +
                "\tLIMIT in_limit OFFSET in_offset;\n\n" +
                SELECT_COUNT +
                "\nFROM (\n\tSELECT " +
                getParamsNoLimitOffset(config) +
                "\n\tFROM " + tableName + "\n" +
                ") AS " + tableName + "_table\nWHERE\n" +
                getWhereParamsNoLimitOffSet(config) +
                "\n;\n\n" +
                FINAL_FUNCTION_STR;
    }

    public static String getFunctionGet(Class<?> config, String tableName, List<String> dateBetween) {
        return CREATE_FUNCTION +
                "\"public\".\"get_" + tableName + "\"(" +
                getInputParamsFunctionGet(config) +
                AFTER_INPUT_PARAMS +
                "OPEN inout_rc_name FOR\n" +
                "SELECT * FROM (\n" +
                "\tSELECT " +
                getParamsNoLimitOffset(config) +
                "\n\tFROM " + tableName + "\n" +
                ") AS " + tableName + "_table\nWHERE\n" +
                getWhereParamsNoLimitOffSet(config, dateBetween) +
                "\n\tORDER BY lead_id DESC\n" +
                "\tLIMIT in_limit OFFSET in_offset;\n\n" +
                SELECT_COUNT +
                "\nFROM (\n\tSELECT " +
                getParamsNoLimitOffset(config) +
                "\n\tFROM " + tableName + "\n" +
                ") AS " + tableName + "_table\nWHERE\n" +
                getWhereParamsNoLimitOffSet(config, dateBetween) +
                "\n;\n\n" +
                FINAL_FUNCTION_STR;
    }

    public static String getFunctionIns(Class<?> config, String tableName) {
        return CREATE_FUNCTION +
                "\"public\".\"" +
                "ins_" + tableName + "\"(" +
                getInputParamsFunctionIns(config) +
                AFTER_INPUT_PARAMS +
                INSERT_INTO + tableName +
                " (" +
                getParams(config) +
                ") VALUES ("
                + getParamsWithInFirst(config) +
                ");\n\n" + "SELECT CAST(currval('seq_lead_id') AS text) INTO out_fail_message;\n\n" +
                FINAL_FUNCTION_STR;
    }

    public static String getFunctionUpd(Class<?> config, String tableName) {
        return CREATE_FUNCTION +
                "\"public\".\"" +
                "upd_" + tableName + "\"(" +
                getInputParamsFunctionUpd(config) +
                ")\n" +
                RETURN + DECLARE +
                getParamsDeclareOld(config) +
                "\n\tcount_row integer;\n\n" +
                BEGIN + SELECT_COUNT_ROW +
                " FROM " + tableName +
                " WHERE lead_id = in_lead_id;\nIF count_row > 0 THEN\nSELECT " +
                getParams(config) +
                "\nINTO " +
                getParamsWithOldFirst(config) +
                "\nFROM " + tableName + " WHERE lead_id = in_lead_id;\n\n" +
                "UPDATE " + tableName + "\nSET\n" +
                getSetParams(config) +
                "WHERE lead_id = in_lead_id;\n" +
                "ELSE\n\tout_status \t\t\t:=1;\n" +
                "\tout_fail_message := 'There are no corresponding lead';\n" +
                "END IF;\n\n" +
                FINAL_FUNCTION_STR;
    }

    public static <T extends Class<?>> String getInputParamsFunctionGet(T config) {
        StringBuilder result = getInputParams(config).append(GET_FINAL_INPUT_PARAMS);
        return result.toString();
    }

    public static <T extends Class<?>> String getInputParamsFunctionIns(T config) {
        StringBuilder result = getInputParams(config).append(GET_FINAL_FUNCTION_INS_PARAMS);
        return result.toString();
    }

    public static String getInputParamsFunctionUpd(Class<?> config) {
        StringBuilder result = getInputParams(config).append(GET_FINAL_FUNCTION_UPD_PARAMS);
        return result.toString();
    }

    public static String getParams(Class<?> config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = toRevertCamelCaseLowercase(field.getName());
            result.append(name).append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    public static String getParamsNoLimitOffset(Class<?> config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = toRevertCamelCaseLowercase(field.getName());
            if (name.equals("limit") || name.equals("offset"))
                continue;
            result.append(name).append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    public static <T extends Class<?>> String getParamsWithInFirst(T config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = "in_" + toRevertCamelCaseLowercase(field.getName());
            result.append(name).append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    public static <T extends Class<?>> String getParamsWithInFirstNoLimitOffset(T config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = "in_" + toRevertCamelCaseLowercase(field.getName());
            if (name.equals("in_limit") || name.equals("in_offset"))
                continue;
            result.append(name).append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    public static <T extends Class<?>> String getParamsWithOldFirst(T config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = "old_" + toRevertCamelCaseLowercase(field.getName());
            result.append(name).append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    public static String getParamsDeclareOld(Class<?> config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = "old_" + toRevertCamelCaseLowercase(field.getName());
            result.append("\t")
                    .append(name)
                    .append(" ")
                    .append(getSqlTypeDeclare(field.getType().toString()))
                    .append(";\n");
        }

        return result.toString();
    }

    public static <T extends Class<?>> String getWhereParams(T config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = toRevertCamelCaseLowercase(field.getName());
            result.append("\tCASE WHEN in_")
                    .append(name)
                    .append(" IS NULL THEN 1=1 ELSE ")
                    .append(name)
                    .append(" = in_")
                    .append(name)
                    .append(" END AND\n");
        }

        return result.substring(0, result.length() - 5);
    }

    public static <T extends Class<?>> String getWhereParamsNoLimitOffSet(T config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = toRevertCamelCaseLowercase(field.getName());
            if (name.equals("limit") || name.equals("offset"))
                continue;
            result.append("\tCASE WHEN in_")
                    .append(name)
                    .append(" IS NULL THEN 1=1 ELSE ")
                    .append(name)
                    .append(" = in_")
                    .append(name)
                    .append(" END AND\n");
        }

        return result.substring(0, result.length() - 5);
    }

    public static <T extends Class<?>> String getWhereParamsNoLimitOffSet(T config, List<String> dateBetween) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = toRevertCamelCaseLowercase(field.getName());

            if (name.equals("limit") || name.equals("offset"))
                continue;

            if (dateBetween.contains(field.getName())) {
                result.append("\tCASE WHEN in_")
                        .append(name)
                        .append(" IS NULL THEN 1=1 ELSE (")
                        .append(name)
                        .append(" >= to_timestamp(split_part(in_")
                        .append(name)
                        .append(",'|',1),'yyyymmddhh24miss') AND ")
                        .append(name)
                        .append(" < to_timestamp(split_part(in_")
                        .append(name)
                        .append(",'|',2),'yyyymmddhh24miss'))")
                        .append(" END AND\n");
                continue;
            }
            result.append("\tCASE WHEN in_")
                    .append(name)
                    .append(" IS NULL THEN 1=1 ELSE ")
                    .append(name)
                    .append(" = in_")
                    .append(name)
                    .append(" END AND\n");
        }

        return result.substring(0, result.length() - 5);
    }

    public static String getSetParams(Class<?> config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            String name = toRevertCamelCaseLowercase(field.getName());
            result.append("\t")
                    .append(name)
                    .append(" = COALESCE (in_")
                    .append(name)
                    .append(", old_")
                    .append(name)
                    .append("),\n");
        }

        return result.substring(0, result.length() - 2) + "\n";
    }

    private static <T extends Class<?>> StringBuilder getDeclareOldParams(T config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            result.append("\t");
            String name = "old_" + toRevertCamelCaseLowercase(field.getName());
            result.append(name).append(" ");
            String type = getSqlTypeDeclare(field.getType().toString());
            result.append(type).append(";\n");
        }
        return result;
    }

    private static <T extends Class<?>> StringBuilder getInputParams(T config) {
        Field[] fields = config.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        for (Field field : fields) {
            result.append("IN \"");
            String name = "in_" + toRevertCamelCaseLowercase(field.getName());
            result.append(name).append("\" ");
            String type = getSqlType(field.getType().toString());
            result.append(type).append(", ");
        }
        return result;
    }

    private static String getSqlTypeDeclare(String type) {
        switch (type) {
            case "class java.lang.String":
            case "class java.util.Date":
                return "text";
            case "class java.lang.Integer":
            case "class java.lang.int":
                return "integer";
            case "class java.lang.Boolean":
                return "boolean";
            default:
                return null;
        }
    }

    private static String getSqlType(String type) {
        switch (type) {
            case "class java.lang.String":
            case "class java.util.Date":
                return "varchar";
            case "class java.lang.Integer":
            case "class java.lang.int":
                return "int4";
            case "class java.lang.Double":
                return "numeric";
            case "class java.lang.Boolean":
                return "boolean";
            default:
                return null;
        }
    }

    private static String toRevertCamelCaseLowercase(String str) {
        String[] r = str.split("(?=\\p{Upper})|(?<=\\D)(?=\\d+\\b)");
        return String.join("_", r).toLowerCase();
    }
}