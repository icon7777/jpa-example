package com.jpa.example.global.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.jpa.example.global.core.CommonResponse;
import com.jpa.example.global.core.ErrorData;
import com.jpa.example.global.enums.ResponseCode;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(BAD_REQUEST)
  public CommonResponse<ErrorData> handleNullPointerException() {
    return CommonResponse.of(ResponseCode.IN0002.getCode(),ResponseCode.IN0002.getValue(), ErrorData.of());
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(BAD_REQUEST)
  public CommonResponse<ErrorData> handleBadRequestException(final BadRequestException exception) {
    return CommonResponse.of(exception.getResponseCode().getCode(),exception.getLocalizedMessage(), ErrorData.of());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(BAD_REQUEST)
  public CommonResponse<ErrorData> handleHttpMessageNotReadableException() {
    return CommonResponse.of(ResponseCode.CI0002.getCode(), ResponseCode.CI0002.getValue(), ErrorData.of());
  }

  @ExceptionHandler(BindException.class)
  @ResponseStatus(BAD_REQUEST)
  public CommonResponse<ErrorData> handleBindException() {
    return CommonResponse.of(ResponseCode.CI0001.getCode(), ResponseCode.CI0001.getValue(), ErrorData.of());
  }

  @ExceptionHandler(ServletRequestBindingException.class)
  @ResponseStatus(BAD_REQUEST)
  public CommonResponse<ErrorData> handleServletRequestBindingException() {
    return CommonResponse.of(ResponseCode.CI0001.getCode(), ResponseCode.CI0001.getValue(), ErrorData.of());
  }

  @ExceptionHandler(TypeMismatchException.class)
  @ResponseStatus(BAD_REQUEST)
  public CommonResponse<ErrorData> handleTypeMismatchException() {
    return CommonResponse.of(ResponseCode.CI0001.getCode(), "TypeMismatchException", ErrorData.of());
  }

  @ExceptionHandler(IOException.class)
  @ResponseStatus(BAD_REQUEST)
  public CommonResponse<ErrorData> handleIOException() {
    return CommonResponse.of(ResponseCode.IN0001.getCode(), ResponseCode.IN0001.getValue(), ErrorData.of());
  }

  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(FORBIDDEN)
  public CommonResponse<ErrorData> handleForbiddenException(final ForbiddenException exception) {
    return CommonResponse.of(exception.getResponseCode().getCode(), exception.getMessage(), ErrorData.of());
  }

}