package com.tms.commons;

import java.util.function.Supplier;

public interface FutureTask<T> extends Supplier<T> {
    default String type() {
        return "";
    }
}
