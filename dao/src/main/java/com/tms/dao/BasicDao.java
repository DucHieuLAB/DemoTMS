package com.tms.dao;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.writer.CSVEntryConverter;
import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;
import com.tms.commons.DBResponse;
import com.tms.commons.TMSBeanPropertyRowMapper;
import com.tms.config.DBConfig;
import com.tms.config.DBConfigMap;
import com.tms.config.DataSourceConfig;
import com.tms.utils.AppUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Repository
public class BasicDao {
    public static final String C_CURSOR = "c_cursor";
    public static final String OUT_STATUS = "out_status";
    public static final String OUT_COUNT_ROW = "out_count_row";
    public static final String OUT_FAIL_MESSAGE = "out_fail_message";

    protected final JdbcTemplate dbTemplate;
    protected final JdbcTemplate mmTemplate;
    protected final DBConfigMap configMap;

    public BasicDao(@Qualifier(DataSourceConfig.DB_JDBC_TEMPLATE) JdbcTemplate dbTemplate,
                    @Qualifier(DataSourceConfig.MM_JDBC_TEMPLATE) JdbcTemplate mmTemplate,
                    DBConfigMap configMap) {
        this.dbTemplate = dbTemplate;
        this.mmTemplate = mmTemplate;
        this.configMap = configMap;
    }

    protected String getPath(String funcName) {
        return configMap.getFilePath() + File.separator + getFileName(funcName);
    }

    private String getFileName(String fileName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String nowStr = (LocalDateTime.now()).format(formatter);
        return String.format("%s.csv.%s", fileName, nowStr);
    }

    @Transactional(readOnly = true)
    public <P, R> DBResponse<R> dbGet(DBConfig config, P params, Class<?> resultClass) {
        DBResponse<R> result = new DBResponse<>(DBResponse.DB_CONN_TYPE_DB);
        List<SqlParameter> inParameters = AppUtils.ob2SqlInParams(params, true, true);

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dbTemplate)
                .withFunctionName(config.getFuncName()).withoutProcedureColumnMetaDataAccess()
                .declareParameters(inParameters.toArray(new SqlParameter[]{}))
                .returningResultSet(C_CURSOR, new TMSBeanPropertyRowMapper<>(resultClass));

        SqlParameterSource in = AppUtils.ob2SqlSource(params, true);

        Map<String, Object> map = simpleJdbcCall.execute(in);
        result.setErrorMsg((String) map.get(OUT_FAIL_MESSAGE));
        result.setErrorCode((Integer) map.get(OUT_STATUS));
        result.setRowCount((Integer) map.get(OUT_COUNT_ROW));
        @SuppressWarnings("unchecked")
        R cursor = (R) map.get(C_CURSOR);
        result.setResult(cursor);

        return result;
    }

    public <T> DBResponse<String> insOrUpdFile(DBConfig config, T params) {
        try (Writer out = new FileWriter(getPath(config.getFuncName()), true)) {
            CSVEntryConverter<T> converter = AppUtils.ob2Str(params);
            CSVWriter<T> csvWriter = new CSVWriterBuilder<T>(out)
                    .strategy(CSVStrategy.UK_DEFAULT).entryConverter(converter).build();
            csvWriter.write(params);
            csvWriter.flush();
            return new DBResponse<>(DBResponse.DB_CONN_TYPE_FILE);
        } catch (IOException e) {
            return new DBResponse<>(DBResponse.DB_CONN_TYPE_FILE, 1, e.getMessage());
        }
    }

    public <T> DBResponse<String> insOrUpdDb(DBConfig config, T params) {
        List<SqlParameter> inParameters = AppUtils.ob2SqlInParams(params, false);
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dbTemplate).withFunctionName(config.getFuncName())
                .withoutProcedureColumnMetaDataAccess().declareParameters(inParameters.toArray(new SqlParameter[]{}))
                .declareParameters(new SqlOutParameter(OUT_STATUS, Types.INTEGER),
                        new SqlOutParameter(OUT_FAIL_MESSAGE, Types.VARCHAR));

        SqlParameterSource in = AppUtils.ob2SqlSource(params);
        Map<String, Object> map = simpleJdbcCall.execute(in);

        DBResponse<String> result = new DBResponse<>(DBResponse.DB_CONN_TYPE_DB);
        result.setErrorMsg((String) map.get(OUT_FAIL_MESSAGE));
        result.setErrorCode((Integer) map.get(OUT_STATUS));
        return result;
    }
}
