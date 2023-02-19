# jpa-example
지원자 : 김정호 <br/>
기한 : 2023.02.10 ~ 20.2023.02.14 <br/>

# 📚 목차
* [사용 기술](#-사용-기술)
* [구현 기능](#-구현-기능)
* [API 명세서](#-API-명세서)
* [설명서](#-설명서)

# 🕹 사용 기술
### 📌 Backend
|기술|버전|
|----|----|
|Java|1.8|
|Spring Boot|2.7.8|
|Spring Security|2.7.8|
|Spring Data Jpa|2.7.8|
|Spring Restdocs Mockmvc|2.7.8|
|asciidoctor|3.3.2|
|flyway|8.5.13|
|H2DB|2.1.214|

# 🎢 구현 기능
* 게시판 기능
  * 모든 게시글 조회 (GET /api/v1/boards)
  * 게시글 검색 (GET /api/v1/boards/{boardId})
  * 게시글 작성 [회원] (POST /api/v1/boards)
  * 게시글 수정 [게시글 작성자] (PUT /api/v1/boards/{boardId})
  * 게시글 삭제 [게시글 작성자] (DELETE /api/v1/boards/{boardId})
  * 게시글 좋아요 [회원] (POST /api/v1/nices/{boardId})

# 🤙🏻 API 명세서
HTTP 메서드를 통해 행위를 명시할 수 있도록 RESTful 방식으로 설계했습니다. <br/><br/>

[게시판](http://1.234.189.11/docs/jari/board-guide.html)<br/>
[좋아요](http://1.234.189.11/docs/jari/nice-guide.html)<br/>
