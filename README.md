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

# 📑 설명서
git-clone 을 통해 소스를 내려받으신 후 JDK 1.8 환경에서 <br/>
Application(embedded tomcat) run 해주시면 프로젝트가 실행됩니다. <br/><br/>
H2DB in-memory 환경으로 세팅하였고, 추후 서비스가 확장되고  <br/>
RDBMS환경으로 이동할 경우를 생각해 FLYWAY로 DB migration(MariaDB) 을 할 수 있게 했습니다.<br/><br/>
프로젝트 구동시 h2 DB는 JPA - Hibernate 를 참조하지않고 flyway를 참조하도록 설정했습니다. <br/><br/>
ApplicationListener를 상속받은 LoaderListener 파일들이 프로젝트 구동시 자동으로 DB에 값을 Insert 해줍니다. <br/><br/>
http header에 Authentication 값을 바탕으로 해당 유저 정보를 가져 올 수 있어야 하고, <br/>
해당코드가 불필요하게 비즈니스 로직에 섞이지 않도록 하기위해 <br/>
Spring Security에 authenticationProvider과 OncePerRequestFilter를 활용해 api요청시  <br/>
header에 Authentication 값을 가져와 TheadLocal기반의 SecurityContext에<br/>
값을 저장하고 필요한 경우 SecurityContextHolder를 통해 유저정보를 가져오는 식으로 작업을 하였습니다.<br/><br/>
현재 테스트 데이터는 유저 3개(공인중개사,임대인,임차인 각1명), 게시판 2개, 좋아요 2개 입니다. <br/>
### 엔티티 관계 다이어그램입니다 
![entity](https://user-images.githubusercontent.com/52563568/218697374-c1a8d054-9784-4506-9192-af23921777f6.png)  <br/>
JPA - Hibernate 를 사용하여 구현하였고, queryDsl 은 사용하지 않았습니다.  <br/>
사용하지 않은 이유는 start.spring.io 에서 지원해주지 않는다는 점 때문입니다.<br/><br/>
게시판 조회 시 발생하는 N+1문제는 EntityManager를 활용한 JPQL 쿼리로 작성하여 DTO 객체를 리턴받아 해결 하였습니다.<br/><br/>
accountType 의 경우는 Enum 클래스로 관리하였고 TypeName을 가져오기 위해 <br/>
DTO클래스에서 getValue 함수를 활용해 타입명을 가져왔습니다. <br/><br/>
apache.commons.lang3 의 addAll(객체 병합) 기능을 사용하기 위해 <br/>
gradle에 org.apache.commons:commons-lang3:3.12.0 를 따로 추가 하였습니다.<br/><br/>
TDD 기반으로 먼저 BoardControllerTest 클래스를 작성하였고, 테스트의 단위와 깊이는 BDD로 API호출에 대한 성공여부를 테스트 하였습니다. <br/>
