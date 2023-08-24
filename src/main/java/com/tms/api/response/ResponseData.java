package com.tms.api.response;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ResponseData<T> {
    private String messenger;
    private T data;

    public ResponseData(String messenger) {
        this.messenger = messenger;
    }

    public ResponseData(String messenger, T data) {
        this.messenger = messenger;
        this.data = data;
    }
}
