package com.jpa.example.global.exception;

import com.jpa.example.global.enums.ResponseCode;
import com.jpa.example.global.util.MsgSourceUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ForbiddenException extends RuntimeException {

  private ResponseCode responseCode;

  private ForbiddenException(final ResponseCode responseCode) {
    super(responseCode.getValue());
    this.responseCode = responseCode;
  }

  private ForbiddenException(final ResponseCode responseCode,
                             final String message) {
    super(message);
    this.responseCode = responseCode;
  }

  public static ForbiddenException from(final ResponseCode responseCode) {
    return new ForbiddenException(responseCode);
  }

  public static ForbiddenException of(final ResponseCode responseCode,
                                      final String code) {
    return new ForbiddenException(responseCode, MsgSourceUtil.getMsg(code));
  }

  public static ForbiddenException of(final ResponseCode responseCode,
                                      final String code,
                                      final Object object) {
    return new ForbiddenException(responseCode, MsgSourceUtil.getMsg(code, object));
  }

  public static ForbiddenException of(final ResponseCode responseCode,
                                      final String message,
                                      final Object... objects) {
    return new ForbiddenException(responseCode, MsgSourceUtil.getMsg(message, objects));
  }

}
