package com.jpa.example.domain.nice.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RequestNiceDTO {

  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor
  @Getter
  public static class SaveNice {

    @NotNull
    private Boolean enabled;

  }

}
