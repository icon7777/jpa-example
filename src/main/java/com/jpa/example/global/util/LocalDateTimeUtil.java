package com.jpa.example.global.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeUtil {


  public static LocalDateTime getLocalDateTime() {
    return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
  }

}
