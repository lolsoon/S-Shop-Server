package com.vti.configuration.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    private final String exceptionPath = "http://localhost:8080/api/v1/exception/%d";

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, "Lỗi server", LocaleContextHolder.getLocale());
    }

    // Default exception
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception) {
        String message = getMessage("Exception.message");
        String detailMessage = exception.getLocalizedMessage();
        int code = 1;
        String moreInformation = String.format(exceptionPath, code);

        ErrorResponse response = new ErrorResponse(message, detailMessage, null, code, moreInformation);
        log.error(detailMessage, exception);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Not found url handler
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = getMessage("NoHandlerFoundException.message") + exception.getHttpMethod() + " " + exception.getRequestURL();
        String detailMessage = exception.getLocalizedMessage();
        int code = 2;
        String moreInformation = String.format(exceptionPath, code);

        ErrorResponse response = new ErrorResponse(message, detailMessage, null, code, moreInformation);
        log.error(detailMessage, exception);

        return new ResponseEntity<>(response, status);
    }

    // Not support HTTP Method
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = getMessageFromHttpRequestMethodNotSupportedException(exception);
        String detailMessage = exception.getLocalizedMessage();
        int code = 3;
        String moreInformation = String.format(exceptionPath, code);

        ErrorResponse response = new ErrorResponse(message, detailMessage, null, code, moreInformation);
        return new ResponseEntity<>(response, status);
    }

    private String getMessageFromHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        StringBuilder message = new StringBuilder(exception.getMethod())
                .append(" ")
                .append(getMessage("HttpRequestMethodNotSupportedException.message"));
        for (HttpMethod method : exception.getSupportedHttpMethods()) {
            message.append(method).append(" ");
        }
        return message.substring(0, message.length() - 1);
    }

    // Not support media type
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = getMessageFromHttpMediaTypeNotSupportedException(exception);
        String detailMessage = exception.getLocalizedMessage();
        int code = 4;
        String moreInformation = String.format(exceptionPath, code);

        ErrorResponse response = new ErrorResponse(message, detailMessage, null, code, moreInformation);
        return new ResponseEntity<>(response, status);
    }

    private String getMessageFromHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        StringBuilder message = new StringBuilder(exception.getContentType() + " ")
                .append(getMessage("HttpMediaTypeNotSupportedException.message"));
        for (MediaType method : exception.getSupportedMediaTypes()) {
            message.append(method).append(", ");
        }
        return message.substring(0, message.length() - 2);
    }

    // MissingServletRequestPartException: This exception is thrown when the part of a multipart request not found
    // MissingServletRequestParameterException: This exception is thrown when request missing parameter:
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = exception.getParameterName()
                + " " + getMessage("MissingServletRequestParameterException.message");
        String detailMessage = exception.getLocalizedMessage();
        int code = 7;
        String moreInformation = String.format(exceptionPath, code);

        ErrorResponse response = new ErrorResponse(message, detailMessage, null, code, moreInformation);
        return new ResponseEntity<>(response, status);
    }

    // TypeMismatchException: This exception is thrown when try to set bean property with wrong type.
    // MethodArgumentTypeMismatchException: This exception is thrown when method argument is not the expected type:
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        String message = exception.getName()
                + " " + getMessage("MethodArgumentTypeMismatchException.message")
                + exception.getRequiredType().getName();
        String detailMessage = exception.getLocalizedMessage();
        int code = 8;
        String moreInformation = String.format(exceptionPath, code);

        ErrorResponse response = new ErrorResponse(message, detailMessage, null, code, moreInformation);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // BindException: This exception is thrown when fatal binding errors occur.
    // MethodArgumentNotValidException: This exception is thrown when argument
    // annotated with @Valid failed validation:
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = getMessage("MethodArgumentNotValidException.message");
        String detailMessage = exception.getLocalizedMessage();
        int code = 5;
        String moreInformation = String.format(exceptionPath, code);

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        ErrorResponse response = new ErrorResponse(message, detailMessage, errors, code, moreInformation);
        return new ResponseEntity<>(response, status);
    }

    // bean validation error
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        String message = getMessage("ConstraintViolationException.message");
        String detailMessage = exception.getLocalizedMessage();
        int code = 6;
        String moreInformation = String.format(exceptionPath, code);

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        ErrorResponse response = new ErrorResponse(message, detailMessage, errors, code, moreInformation);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
