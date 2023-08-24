package com.tms.commons;

public interface FileFutureTask<T> extends FutureTask<T> {
    @Override
    default String type() {
        return "File";
    }
}
