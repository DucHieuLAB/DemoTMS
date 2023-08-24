package com.tms.api.exception;

import com.tms.api.commons.ApiValidatorError;
import com.tms.api.commons.ApiSubError;
import com.tms.api.commons.TMSResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class TMSExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("Handle method argument not valid");

        List<ApiSubError> details = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            Object rejectValue = ((FieldError) error).getRejectedValue();
            String message = error.getDefaultMessage();

            details.add(new ApiValidatorError(fieldName, rejectValue, message));
        });
        return new ResponseEntity<>(TMSResponse.buildResponse(ErrorMessages.INVALID_VALUE, details), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("Handle http message not readable");
        logger.error(ex.getMessage(), ex);
        TMSInvalidInputException exception = (TMSInvalidInputException) ex.getCause().getCause();
        return new ResponseEntity<>(TMSResponse.buildResponse(exception.getErrorMessage(), exception.getApiSubErrors()), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("Handle no handler found exception");
        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(TMSResponse.buildResponse(ErrorMessages.NOT_FOUND), HttpStatus.OK);
    }

    @ExceptionHandler(TMSException.class)
    protected ResponseEntity<Object> handleTMSException(TMSException ex) {
        logger.info("Handle TMS exception");
        logger.error(ex.getErrorMessage(), ex);
        return buildResponseEntity(ex);
    }

    @ExceptionHandler(TMSDbException.class)
    protected ResponseEntity<Object> handleTMSDbException(TMSDbException ex) {
        logger.info("Handle TMS database exception");
        logger.error(ex.getErrorMessage(), ex);
        return buildResponseEntity(ex);
    }

    @ExceptionHandler(TMSEntityNotFoundException.class)
    protected ResponseEntity<Object> handleTMSEntityNotFound(TMSEntityNotFoundException ex) {
        logger.info("Handle TMS entity not found exception");
        logger.error(ex.getErrorMessage(), ex);
        return new ResponseEntity<>(TMSResponse.buildResponse(ex.getErrorMessage(), Collections.singletonList(ex.getApiMessageError())), HttpStatus.OK);
    }

    @ExceptionHandler(TMSInvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInputRequest(TMSInvalidInputException ex) {
        logger.info("Handle TMS invalid input exception");
        logger.error(ex.getErrorMessage(), ex);
        return new ResponseEntity<>(TMSResponse.buildResponse(ex.getErrorMessage(), ex.getApiSubErrors()), HttpStatus.OK);
    }

    private ResponseEntity<Object> buildResponseEntity(TMSException ex) {
        return new ResponseEntity<>(TMSResponse.buildApplicationException(ex), HttpStatus.OK);
    }
}
