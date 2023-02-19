package com.jpa.example.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode implements EnumMapperType {

  AA0000("Success"),
  ZZ9999("Fail"),

  CI0001("Bad Parameter"),
  CI0002("Null Request"),

  UY0001("Cannot Find User"),
  UY0002("Cannot Find AccountId"),

  BY0001("Cannot Find Board"),
  BY0002("Cannot Match AccountId"),

  IN0001("Common IO Exception"),
  IN0002("NullPoint Exception");


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
