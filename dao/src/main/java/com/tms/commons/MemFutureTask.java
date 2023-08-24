package com.tms.commons;

public interface MemFutureTask<T> extends FutureTask<T> {
    @Override
    default String type() {
        return "Memory";
    }
}
