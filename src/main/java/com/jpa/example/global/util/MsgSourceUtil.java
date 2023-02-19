package com.jpa.example.global.util;

import com.jpa.example.global.provider.BeanProvider;
import com.jpa.example.global.provider.MessageSourceProvider;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MsgSourceUtil {

  private static MessageSource getDefaultMsgSource() {
    return BeanProvider.getBean(MessageSource.class);
  }

  public static String getMsg(final String code) {
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code), Locale.KOREA);
  }

  public static String getMsg(final String code,
                              final Object object) {
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, object), Locale.KOREA);
  }

  public static String getMsg(final String code,
                              final Object... objects) {
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, objects), Locale.KOREA);
  }


}
