# ⚾ 숫자 야구 게임 (Number Baseball Game)

자바 콘솔 기반의 숫자 추리 게임  
**회원 가입 → 로그인 → 난이도 선택 → 게임 플레이 → 점수 저장 & 랭킹 조회**

---

## 📅 제작 기간 & 참여 인원
- 2025.08.22 ~ 2025.08.27  
- 팀 프로젝트 (4인)
| 역할   | 이름   | 담당 업무       |
|--------|--------|----------------|
| 팀장   | 박기열 | 설계 담당       |
| 팀원   | 이용일 | 프로젝트 매니저 (PM) |
| 팀원   | 김주성 | 문서 담당       |
| 팀원   | 유도경 | 오류 점검 담당   |

---

## 🛠 사용 기술
- **Back-end / Logic**
  - Java 11
  - JDBC (ORACLE DB 11g 연동
  - MVC 패턴 기반 설계

- **Database**
  - MySQL 8.0
  - SQL (DDL, DML)

- **Tool / Environment**
  - Eclipse
  - Git / GitHub

---

## 📂 프로젝트 구조 (MVC)

```plaintext
src
 ┣ controller
 ┃ ┣ NumberBaseballGame.java   // 게임 진행 로직
 ┃ ┗ MemberController.java     // 전체 흐름 제어
 ┣ model
 ┃ ┣ MemberDAO.java            // DB 연결 및 CRUD
 ┃ ┗ MemberVO.java             // 회원 데이터 객체
 ┣ view
 ┃ ┗ MemberView.java           // 화면 출력 및 입력 처리
 ┗ Main.java                   // 실행 진입점
```

</br>



</br>

## 3. ERD 설계
![ERD](https://blogger.googleusercontent.com/img/a/AVvXsEi7V1oDAnFlLCN4UQFdyZp2wr0e9W5EUfHBL1GwDCupc_tGxHUib8FRiIwdgRWeoaTWpZLIrDAuGTXp5PNaE8rps5vkHbcnQUzBYELUXr5d3jUTls6On12sB19S0_4Jgpj53PTZYd7G8aXI2HtD5WGdy_FkyK11_cXnp-r-gs3Nyj7KjhlfQObC5O6bUujt))

## 4. 핵심 기능
이 서비스의 핵심 기능은 컨텐츠 등록 기능입니다.  
사용자는 단지 컨텐츠의 카테고리를 선택하고, URL만 입력하면 끝입니다.  
이 단순한 기능의 흐름을 보면, 서비스가 어떻게 동작하는지 알 수 있습니다.  

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

# 게임 프로그램 흐름

이 프로그램은 **회원 관리** 및 **숫자 야구 게임** 기능을 포함하는 Java 기반의 응용 프로그램입니다.

---

## 4. 핵심 기능

### 4.1 전체 흐름

<details>
  <summary>전체 흐름</summary>
  - 프로그램은 사용자의 요청에 따라 로그인, 메뉴 선택, 게임 실행 등의 흐름으로 진행됩니다.
  - 핵심 기능은 회원 관리와 숫자 야구 게임이며, 각 요청에 따라 컨트롤러가 처리하고 서비스가 로직을 실행하며, 결과를 화면에 표시합니다.
</details>

### 4.2 사용자 요청

<details>
  <summary>사용자 요청</summary>
  - **로그인 요청**:
    - 사용자가 로그인 정보를 입력하고, 서버로 요청을 보냅니다.
    - `MemberController`에서 로그인 요청을 처리합니다.

  - **게임 시작 요청**:
    - 사용자가 게임을 시작하려면, 메뉴에서 게임을 선택합니다.
    - `NumberBaseballGame` 클래스에서 게임 설정 및 실행이 처리됩니다.
</details>

### 4.3 Controller

<details>
  <summary>Controller</summary>
  - **요청 처리**:
    - `MemberController`는 사용자의 요청을 받고, 이를 처리하기 위해 `MemberDAO` 또는 `NumberBaseballGame`으로 요청을 위임합니다.
  
  - **결과 응답**:
    - `MemberController`는 처리된 결과를 화면에 응답으로 반환합니다.
    - 예: 로그인 성공/실패 메시지, 게임 시작 및 결과 정보.
</details>

### 4.4 Service

<details>
  <summary>Service</summary>
  - **회원 로그인 처리**:
    - 사용자가 입력한 로그인 정보가 정확한지 `MemberDAO`에서 데이터베이스를 통해 확인합니다.
    - 로그인 성공 시, 사용자 정보를 화면에 표시합니다.
  
  - **게임 설정**:
    - 사용자가 선택한 게임 난이도에 맞게 `NumberBaseballGame`에서 게임을 설정합니다.
    - 게임의 규칙을 설정하고, 사용자가 추측한 숫자를 정답과 비교하여 결과를 처리합니다.
</details>

### 4.5 Repository

<details>
  <summary>Repository</summary>
  - **데이터 저장**:
    - `MemberDAO`는 사용자가 입력한 정보 및 게임 결과를 데이터베이스에 저장합니다.
    - 저장된 데이터는 `MemberController`를 통해 화면에 표시됩니다.
</details>

---

## Ex) 세부 흐름 예시

### 5.1 로그인 흐름
1. 사용자가 로그인 정보(`id`, `password`)를 입력합니다.
2. `MemberController`에서 로그인 요청을 받습니다.
3. `MemberDAO`가 DB에서 로그인 정보가 맞는지 확인합니다.
4. 로그인 성공/실패에 대한 결과를 화면에 응답합니다.

### 5.2 게임 흐름
1. 사용자가 게임의 난이도를 선택합니다.
2. `NumberBaseballGame`에서 선택된 난이도에 맞는 게임을 설정합니다.
3. 사용자가 숫자를 추측하여 게임을 진행합니다.
4. `NumberBaseballGame`에서 정답을 비교하고, 결과를 화면에 표시합니다.

<details>
  
</br>

## 5. 핵심 트러블 슈팅
### 5.1. 컨텐츠 필터와 페이징 처리 문제
- 저는 이 서비스가 페이스북이나 인스타그램 처럼 가볍게, 자주 사용되길 바라는 마음으로 개발했습니다.  
때문에 페이징 처리도 무한 스크롤을 적용했습니다.

- 하지만 [무한스크롤, 페이징 혹은 “더보기” 버튼? 어떤 걸 써야할까](https://cyberx.tistory.com/82) 라는 글을 읽고 무한 스크롤의 단점들을 알게 되었고,  
다양한 기준(카테고리, 사용자, 등록일, 인기도)의 게시물 필터 기능을 넣어서 이를 보완하고자 했습니다.

- 그런데 게시물이 필터링 된 상태에서 무한 스크롤이 동작하면,  
필터링 된 게시물들만 DB에 요청해야 하기 때문에 아래의 **기존 코드** 처럼 각 필터별로 다른 Query를 날려야 했습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 Top10 (기준: 댓글 수 + 좋아요 수)
 * @return 인기순 상위 10개 게시물
 */
public Page<PostResponseDto> listTopTen() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "rankPoint", "likeCnt");
    return postRepository.findAll(pageRequest).map(PostResponseDto::new);
}

/**
 * 게시물 필터 (Tag Name)
 * @param tagName 게시물 박스에서 클릭한 태그 이름
 * @param pageable 페이징 처리를 위한 객체
 * @return 해당 태그가 포함된 게시물 목록
 */
public Page<PostResponseDto> listFilteredByTagName(String tagName, Pageable pageable) {

    return postRepository.findAllByTagName(tagName, pageable).map(PostResponseDto::new);
}

// ... 게시물 필터 (Member) 생략 

/**
 * 게시물 필터 (Date)
 * @param createdDate 게시물 박스에서 클릭한 날짜
 * @return 해당 날짜에 등록된 게시물 목록
 */
public List<PostResponseDto> listFilteredByDate(String createdDate) {

    // 등록일 00시부터 24시까지
    LocalDateTime start = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MIN);
    LocalDateTime end = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MAX);

    return postRepository
                    .findAllByCreatedAtBetween(start, end)
                    .stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
    }
~~~

</div>
</details>

- 이 때 카테고리(tag)로 게시물을 필터링 하는 경우,  
각 게시물은 최대 3개까지의 카테고리(tag)를 가질 수 있어 해당 카테고리를 포함하는 모든 게시물을 질의해야 했기 때문에  
- 아래 **개선된 코드**와 같이 QueryDSL을 사용하여 다소 복잡한 Query를 작성하면서도 페이징 처리를 할 수 있었습니다.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 필터 (Tag Name)
 */
@Override
public Page<Post> findAllByTagName(String tagName, Pageable pageable) {

    QueryResults<Post> results = queryFactory
            .selectFrom(post)
            .innerJoin(postTag)
                .on(post.idx.eq(postTag.post.idx))
            .innerJoin(tag)
                .on(tag.idx.eq(postTag.tag.idx))
            .where(tag.name.eq(tagName))
            .orderBy(post.idx.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
            .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
}
~~~

</div>
</details>

</br>

## 6. 그 외 트러블 슈팅
<details>
<summary>npm run dev 실행 오류</summary>
<div markdown="1">

- Webpack-dev-server 버전을 3.0.0으로 다운그레이드로 해결
- `$ npm install —save-dev webpack-dev-server@3.0.0`

</div>
</details>

<details>
<summary>vue-devtools 크롬익스텐션 인식 오류 문제</summary>
<div markdown="1">
  
  - main.js 파일에 `Vue.config.devtools = true` 추가로 해결
  - [https://github.com/vuejs/vue-devtools/issues/190](https://github.com/vuejs/vue-devtools/issues/190)
  
</div>
</details>

<details>
<summary>ElementUI input 박스에서 `v-on:keyup.enter="메소드명"`이 정상 작동 안하는 문제</summary>
<div markdown="1">
  
  - `v-on:keyup.enter.native=""` 와 같이 .native 추가로 해결
  
</div>
</details>

<details>
<summary> Post 목록 출력시에 Member 객체 출력 에러 </summary>
<div markdown="1">
  
  - 에러 메세지(500에러)
    - No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.SerializationFeature.FAIL_ON_EMPTY_BEANS)
  - 해결
    - Post 엔티티에 @ManyToOne 연관관계 매핑을 LAZY 옵션에서 기본(EAGER)옵션으로 수정
  
</div>
</details>
    
<details>
<summary> 프로젝트를 git init으로 생성 후 발생하는 npm run dev/build 오류 문제 </summary>
<div markdown="1">
  
  ```jsx
    $ npm run dev
    npm ERR! path C:\Users\integer\IdeaProjects\pilot\package.json
    npm ERR! code ENOENT
    npm ERR! errno -4058
    npm ERR! syscall open
    npm ERR! enoent ENOENT: no such file or directory, open 'C:\Users\integer\IdeaProjects\pilot\package.json'
    npm ERR! enoent This is related to npm not being able to find a file.
    npm ERR! enoent

    npm ERR! A complete log of this run can be found in:
    npm ERR!     C:\Users\integer\AppData\Roaming\npm-cache\_logs\2019-02-25T01_23_19_131Z-debug.log
  ```
  
  - 단순히 npm run dev/build 명령을 입력한 경로가 문제였다.
   
</div>
</details>    

<details>
<summary> 태그 선택후 등록하기 누를 때 `object references an unsaved transient instance - save the transient instance before flushing` 오류</summary>
<div markdown="1">
  
  - Post 엔티티의 @ManyToMany에 영속성 전이(cascade=CascadeType.ALL) 추가
    - JPA에서 Entity를 저장할 때 연관된 모든 Entity는 영속상태여야 한다.
    - CascadeType.PERSIST 옵션으로 부모와 자식 Enitity를 한 번에 영속화할 수 있다.
    - 참고
        - [https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218](https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218)
   
</div>
</details>    

<details>
<summary> JSON: Infinite recursion (StackOverflowError)</summary>
<div markdown="1">
  
  - @JsonIgnoreProperties 사용으로 해결
    - 참고
        - [http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html](http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html)
        - [https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue](https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue)
        
</div>
</details>  
    
<details>
<summary> H2 접속문제</summary>
<div markdown="1">
  
  - H2의 JDBC URL이 jdbc:h2:~/test 으로 되어있으면 jdbc:h2:mem:testdb 으로 변경해서 접속해야 한다.
        
</div>
</details> 
    
<details>
<summary> 컨텐츠수정 모달창에서 태그 셀렉트박스 드랍다운이 뒤쪽에 보이는 문제</summary>
<div markdown="1">
  
   - ElementUI의 Global Config에 옵션 추가하면 해결
     - main.js 파일에 `Vue.us(ElementUI, { zIndex: 9999 });` 옵션 추가(9999 이하면 안됌)
   - 참고
     - [https://element.eleme.io/#/en-US/component/quickstart#global-config](https://element.eleme.io/#/en-US/component/quickstart#global-config)
        
</div>
</details> 

<details>
<summary> HTTP delete Request시 개발자도구의 XHR(XMLHttpRequest )에서 delete요청이 2번씩 찍히는 이유</summary>
<div markdown="1">
  
  - When you try to send a XMLHttpRequest to a different domain than the page is hosted, you are violating the same-origin policy. However, this situation became somewhat common, many technics are introduced. CORS is one of them.

        In short, server that you are sending the DELETE request allows cross domain requests. In the process, there should be a **preflight** call and that is the **HTTP OPTION** call.

        So, you are having two responses for the **OPTION** and **DELETE** call.

        see [MDN page for CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).

    - 출처 : [https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o](https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o)
        
</div>
</details> 

<details>
<summary> 이미지 파싱 시 og:image 경로가 달라서 제대로 파싱이 안되는 경우</summary>
<div markdown="1">
  
  - UserAgent 설정으로 해결
        - [https://www.javacodeexamples.com/jsoup-set-user-agent-example/760](https://www.javacodeexamples.com/jsoup-set-user-agent-example/760)
        - [http://www.useragentstring.com/](http://www.useragentstring.com/)
        
</div>
</details> 
    
<details>
<summary> 구글 로그인으로 로그인한 사용자의 정보를 가져오는 방법이 스프링 2.0대 버전에서 달라진 것</summary>
<div markdown="1">
  
  - 1.5대 버전에서는 Controller의 인자로 Principal을 넘기면 principal.getName(0에서 바로 꺼내서 쓸 수 있었는데, 2.0대 버전에서는 principal.getName()의 경우 principal 객체.toString()을 반환한다.
    - 1.5대 버전에서 principal을 사용하는 경우
    - 아래와 같이 사용했다면,

    ```jsx
    @RequestMapping("/sso/user")
    @SuppressWarnings("unchecked")
    public Map<String, String> user(Principal principal) {
        if (principal != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
            logger.info("details = " + details);  // id, email, name, link etc.
            Map<String, String> map = new LinkedHashMap<>();
            map.put("email", details.get("email"));
            return map;
        }
        return null;
    }
    ```

    - 2.0대 버전에서는
    - 아래와 같이 principal 객체의 내용을 꺼내 쓸 수 있다.

    ```jsx
    UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder
                            .getContext().getAuthentication();
            Map<String, Object> map = (Map<String, Object>) token.getPrincipal();

            String email = String.valueOf(map.get("email"));
            post.setMember(memberRepository.findByEmail(email));
    ```
        
</div>
</details> 
    
<details>
<summary> 랭킹 동점자 처리 문제</summary>
<div markdown="1">
  
  - PageRequest의 Sort부분에서 properties를 "rankPoint"를 주고 "likeCnt"를 줘서 댓글수보다 좋아요수가 우선순위 갖도록 설정.
  - 좋아요 수도 똑같다면..........
        
</div>
</details> 
    
</br>

## 6. 회고 / 느낀점
>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/
