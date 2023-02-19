package com.jpa.example.domain.nice.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.jpa.example.domain.nice.dto.request.RequestNiceDTO;
import com.jpa.example.domain.nice.service.NiceService;
import com.jpa.example.global.core.CommonResponse;
import com.jpa.example.global.core.SuccessData;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/nices")
@RequiredArgsConstructor
public class NiceController {

  private final NiceService niceService;

  @PostMapping("/{boardId}")
  @ResponseStatus(CREATED)
  public CommonResponse<SuccessData> saveNice(@PathVariable final Long boardId,
                                              @RequestBody @Valid final RequestNiceDTO.SaveNice saveNice) {
    niceService.saveNice(boardId, saveNice.getEnabled());
    return CommonResponse.from(SuccessData.of());
  }


}
