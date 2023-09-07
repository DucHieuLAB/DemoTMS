package com.tms.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
public class DBResponse<T> {

    public static final String DB_TIME_OUT_N = "N";
    public static final String DB_TIME_OUT_Y = "Y";
    public static final String DB_TIME_OUT_SRC_CONF = "CONF";
    public static final String DB_TIME_OUT_SRC_JDBC = "JDBC";
    public static final String DB_CONN_TYPE_DB = "DB";
    public static final String DB_CONN_TYPE_FILE = "FILE";
    public static final String DB_CONN_TYPE_MEMORY = "MEMORY";

    public static final String ACTION_QUERY = "QUERY";
    public static final String ACTION_INSERT = "INSERT";

    private String action;
    private String sessionId;
    @Setter
    private String errorMsg;
    @Setter
    private int errorCode;
    @Setter
    private int rowCount;
    @Setter
    private T result;

    private String dbTimeout = DB_TIME_OUT_N;
    private String dbTimeoutSrc;
    private String dbConnType;

    public DBResponse() {
    }

    public DBResponse(String conn) {
        this.dbConnType = conn;
    }

    public DBResponse(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DBResponse(String dbConnType, int errorCode, String errorMsg) {
        this.dbConnType = dbConnType;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DBResponse<T> dbTimeout() {
        this.dbTimeout = DB_TIME_OUT_Y;
        return this;
    }

    public DBResponse<T> dbNotTimeout() {
        this.dbTimeout = DB_TIME_OUT_N;
        return this;
    }

    public DBResponse<T> dbTimeoutConf() {
        this.dbTimeoutSrc = DB_TIME_OUT_SRC_CONF;
        return this;
    }

    public DBResponse<T> dbTimeoutJdbc() {
        this.dbTimeoutSrc = DB_TIME_OUT_SRC_JDBC;
        return this;
    }

    public DBResponse<T> dbConDB() {
        this.dbConnType = DB_CONN_TYPE_DB;
        return this;
    }

    public DBResponse<T> dbConFile() {
        this.dbConnType = DB_CONN_TYPE_FILE;
        return this;
    }

    public DBResponse<T> dbConMEMORY() {
        this.dbConnType = DB_CONN_TYPE_MEMORY;
        return this;
    }

    public static <T> DBResponse<T> qConfigError(String sessionId, String msg) {
        DBResponse<T> response = new DBResponse<>();
        response.action = ACTION_QUERY;
        response.errorCode = 2;
        response.errorMsg = msg;
        response.sessionId = sessionId;
        return response;
    }

    public static <T> DBResponse<T> iConfigError(String sessionId, String msg) {
        DBResponse<T> response = new DBResponse<>();
        response.action = ACTION_INSERT;
        response.errorCode = 2;
        response.errorMsg = msg;
        response.sessionId = sessionId;
        return response;
    }

    @Override
    public String toString() {
        StringBuilder bf = new StringBuilder()
                .append(sessionId)
                .append(":")
                .append(action);
        if (errorCode == 0) {
            bf.append(": Successful");
        } else {
            bf.append(errorMsg);
        }
        return bf.toString();
    }


}
