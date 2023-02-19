package com.jpa.example.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum accountType implements EnumMapperType {

  Realtor("공인중개사"),
  Lessor("임대인"),
  Lessee("임차인")
  ;

  private String value;

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getValue() {
    return value;
  }

}
