package com.tms.commons;

public interface DBFutureTask<T> extends FutureTask<T> {
    @Override
    default String type() {
        return "Database";
    }
}
