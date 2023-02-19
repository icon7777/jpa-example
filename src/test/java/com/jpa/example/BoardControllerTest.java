package com.jpa.example;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jpa.example.util.ApiDocumentationUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureRestDocs
@SpringBootTest(properties = "spring.profiles.active=junit")
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class})
public class BoardControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  private HttpHeaders httpHeaders;

  @BeforeEach
  void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .apply(documentationConfiguration(restDocumentation))
        .build();

    this.httpHeaders = new HttpHeaders();
  }

  @DisplayName("게시판목록 조회")
  @Test
  void getBoards() throws Exception {
     mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/boards")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name()))

      .andExpect(status().isOk())
      .andDo(MockMvcRestDocumentation.document("/get-boards",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
         requestParameters(
            parameterWithName("page").description("Page 번호 - Default(0)").optional(),
            parameterWithName("size").description("Page 보여줄 갯수 - Default(10) Max(100)").optional()
        ),
        ApiDocumentationUtils.getResponseFieldsSnippetByFieldDescriptors(
            JsonFieldType.OBJECT,
            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("현재 Page 첫번째 페이지 여부"),
            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("현재 Page 마지막째 페이지 여부"),
            fieldWithPath("page").type(JsonFieldType.NUMBER).description("현재 Page 번호"),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("현재 Page 에서 보여줄 갯수"),
            fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 Data 갯수"),
            fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 Page 갯수"),
            fieldWithPath("content").type(JsonFieldType.ARRAY).description("Page 내용 (게시글 목록)"),
            fieldWithPath("content[0].boardId").type(JsonFieldType.NUMBER).description("게시글 ID"),
            fieldWithPath("content[0].boardTitle").type(JsonFieldType.STRING).description("게시글 제목"),
            fieldWithPath("content[0].createdDt").type(JsonFieldType.STRING).description("등록일시"),
            fieldWithPath("content[0].userNickname").type(JsonFieldType.STRING).description("작성자"),
            fieldWithPath("content[0].accountTypeName").type(JsonFieldType.STRING).description("작성자 타입명"),
            fieldWithPath("content[0].niceCount").type(JsonFieldType.NUMBER).description("좋아요수"),
            fieldWithPath("content[0].niceByMe").type(JsonFieldType.BOOLEAN).description("내 계정의 좋아요 여부")
        )
      )
    );
  }

  @DisplayName("게시글 조회")
  @Test
  void getBoardById() throws Exception {
    mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/boards/{boardId}","1")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name()))

      .andExpect(status().isOk())
      .andDo(document("/get-board",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        pathParameters(
          parameterWithName("boardId").description("게시판 ID")
        ),
        ApiDocumentationUtils.getResponseFieldsSnippetByFieldDescriptors(
          JsonFieldType.OBJECT,
          fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 ID"),
          fieldWithPath("boardTitle").type(JsonFieldType.STRING).description("게시글 제목"),
          fieldWithPath("boardContent").type(JsonFieldType.STRING).description("게시글 내용"),
          fieldWithPath("createdDt").type(JsonFieldType.STRING).description("작성일자"),
          fieldWithPath("userNickname").type(JsonFieldType.STRING).description("작성자"),
          fieldWithPath("accountTypeName").type(JsonFieldType.STRING).description("작성자 타입명"),
          fieldWithPath("niceCount").type(JsonFieldType.NUMBER).description("좋아요수"),
          fieldWithPath("niceByMe").type(JsonFieldType.BOOLEAN).description("내 계정의 좋아요 여부")
        )
      )
    );
  }

  @DisplayName("게시글 생성")
  @Test
  void CreateBoard() throws Exception {

    HashMap<String, String> createBoardForm = new HashMap<>();
    createBoardForm.put("boardTitle", "게시판 제목");
    createBoardForm.put("boardContent", "게시판 내용");

    this.httpHeaders.set("Authentication", "Realtor1");

    mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/boards")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name())
      .content(objectMapper.writeValueAsString(createBoardForm))
      .headers(this.httpHeaders))
      .andExpect(status().isCreated())

      .andDo(document("/post-boards",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        requestFields(
          fieldWithPath("boardTitle").type(JsonFieldType.STRING).description("게시판 제목"),
          fieldWithPath("boardContent").type(JsonFieldType.STRING).description("게시판 내용")
        ),
        ApiDocumentationUtils.getResponseFieldsSnippetByFieldDescriptors(JsonFieldType.NUMBER)
      )
    );
  }

  @DisplayName("게시글 수정")
  @Test
  void UpdateBoard() throws Exception {

    HashMap<String, String> createBoardForm = new HashMap<>();
    createBoardForm.put("boardTitle", "게시판 제목 수정");
    createBoardForm.put("boardContent", "게시판 내용 수정");

    this.httpHeaders.set("Authentication", "Realtor1");

    mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/boards/{boardId}","1")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name())
      .content(objectMapper.writeValueAsString(createBoardForm))
      .headers(this.httpHeaders))
      .andExpect(status().isCreated())

      .andDo(document("/put-boards",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        pathParameters(
            parameterWithName("boardId").description("게시판 ID")
        ),
        requestFields(
          fieldWithPath("boardTitle").type(JsonFieldType.STRING).description("게시판 제목"),
          fieldWithPath("boardContent").type(JsonFieldType.STRING).description("게시판 내용")
        ),
        ApiDocumentationUtils.getResponseFieldsSnippetByFieldDescriptors(JsonFieldType.NUMBER)
      )
    );
  }

  @DisplayName("게시글 삭제")
  @Test
  void DeleteBoard() throws Exception {

    this.httpHeaders.set("Authentication", "Realtor1");

    mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/boards/{boardId}","2")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name())
      .headers(this.httpHeaders))
      .andExpect(status().isCreated())

      .andDo(document("/delete-boards",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        pathParameters(
          parameterWithName("boardId").description("게시판 ID")
        ),
        ApiDocumentationUtils.getResponseFieldsSnippetByFieldDescriptors(JsonFieldType.NUMBER)
      )
    );
  }


}
