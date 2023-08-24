package com.tms.api.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tms.api.exception.ErrorMessage;
import com.tms.api.exception.TMSException;
import com.tms.api.helper.DateHelper;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
public class TMSResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateHelper.GLOBAL_DATE_TIME)
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int code;
    private String message;
    private List<ApiSubError> details;
    private T data;
    private int total;

    public static <T> TMSResponse<T> buildResponse(ErrorMessage errorMessage) {
        TMSResponse<T> response = new TMSResponse<>();
        response.code = errorMessage.getErrorCode();
        response.message = errorMessage.getErrorMessage();
        return response;
    }

    public static <T> TMSResponse<T> buildResponse(ErrorMessage errorMessage, List<ApiSubError> details) {
        TMSResponse<T> response = buildResponse(errorMessage);
        response.details = details;
        return response;
    }

    public static <T> TMSResponse<T> buildResponse(T data) {
        TMSResponse<T> response = new TMSResponse<>();
        response.data = data;
        if (data instanceof Collection) {
            response.total = ((Collection<T>) data).size();
        }
        response.code = 200;
        return response;
    }

    public static <T> TMSResponse<T> buildResponse(T data, Integer total) {
        TMSResponse<T> response = buildResponse(data);
        response.total = total;
        return response;
    }

    public static <T> TMSResponse<T> buildResponse(T data, ErrorMessage errorMessage) {
        TMSResponse<T> response = buildResponse(errorMessage);
        response.data = data;
        return response;
    }

    public static <T> TMSResponse<T> buildResponse(T data, Integer total, String message, Integer errCode) {
        TMSResponse<T> response = buildResponse(data, total);
        response.code = errCode;
        response.message = message;
        return response;
    }

    public static TMSResponse<String> buildApplicationException(TMSException exception) {
        return buildResponse(exception.getErrorMessage());
    }
}