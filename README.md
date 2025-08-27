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

<details>
  <summary>세부 흐름 예시</summary>
## Ex) 세부 흐름 예시

### ex1. 로그인 흐름
1. 사용자가 로그인 정보(`id`, `password`)를 입력합니다.
2. `MemberController`에서 로그인 요청을 받습니다.
3. `MemberDAO`가 DB에서 로그인 정보가 맞는지 확인합니다.
4. 로그인 성공/실패에 대한 결과를 화면에 응답합니다.

### ex2. 게임 흐름
1. 사용자가 게임의 난이도를 선택합니다.
2. `NumberBaseballGame`에서 선택된 난이도에 맞는 게임을 설정합니다.
3. 사용자가 숫자를 추측하여 게임을 진행합니다.
4. `NumberBaseballGame`에서 정답을 비교하고, 결과를 화면에 표시합니다.

  - **게임 결과 처리**:
    - 게임 결과(정답 맞추기/틀리기)를 `NumberBaseballGame`에서 확인하고, 결과를 화면에 표시합니다.
    - 게임 종료 후, 다시 메인 메뉴나 게임 재시작을 선택할 수 있는 흐름으로 이어집니다.
</details>
</br>

## 5. 핵심 트러블 슈팅
### 5.1. Git-hub 연결
| **이슈 사항** | Git-hub의 연결 |
| --- | --- |

## ⁉ 해결 단계

- **시도 1. 기존 프로젝트에서 Push/Pull 시도**
    - 팀원 간 충돌 발생
    - 일부 팀원만 Pull 성공
    
- **시도 2. git-hub 프로젝트 생성부터 다시 시작**
    - 동일한 오류 반복 발생
    - Push는 성공했지만 Pull은 계속 실패
    
- **시도 3.** “**.metadata**”로 인한 충돌 오류 발견
    - `.gitignore` 파일 생성하여 `.metadata` 제외 시도
    - 여전히 충돌 문제 해결되지 않음

#### ❓ 분석 단계

| --- | --- |
| **원인** | **사전 상황**  
→ Java에서 Export한 프로젝트 파일을 팀원들과 공유함  
→ 공유된 파일 경로에 GitHub 저장소 경로를 그대로 설정함  

**문제 발생 원인**  
→ 공유된 경로에 이미 동일한 파일들이 존재  
→ GitHub에서 Pull 시, 로컬 경로에 중복 파일이 있어 병합 불가  
→ 이로 인해 Pull 작업이 실패하고 충돌 오류 발생 |

## ❗ 해결 완료

| **해결 방법** | **정상적인 GitHub 사용 방법 (최초 Push 팀원 제외)** |
| --- | --- |
| 동일한 파일명을 가진 **빈 폴더**에 GitHub 저장소 경로를 설정한 이후, 순서대로 `add → commit → pull 또는 push` 수행. 충돌 없이 GitHub 연동 및 협업 가능. |

**p.s**
- **`.gitignore` 파일 관련**: 시도 3에서 직접 생성하려 했던 `.gitignore` 파일은 실제로는 **Pull 과정에서 자동 생성됨**. 따라서 별도로 만들 필요 없음.
- **`.metadata` 충돌 문제**: `.gitignore`에 의해 자동으로 제외되므로 충돌 없이 GitHub 사용 가능.

### 5.1. Git-hub 연결
#### ❓ 분석 단계

| **클래스 명** | allLogic |
| --- | --- |
| **이슈 사항** | 단일 명령문을 따로 실행하는 도중 실행 후 **ORA-02291** 발생 |
| **원인** | **코드 목적** : 게임을 진행하면서 게임 기록을 DB 테이블에 저장  
→ M_ID의 값을 테이블에 넣으려 했지만, M_ID는 아직 회원 테이블에 존재하지 않음 |

## ⁉ 해결 단계

- **시도. 시도한 내용**
    
    : test목적의 클래스를 생성하여 기능확인을 하고자 했으나, **ORA-02291** 코드가 생성되어 일단 원본 클래스의 로그인 기능까지 가져와서 test 실행.

## ❗ 해결 완료

| **해결 방법** | member 정보가 먼저 생성되도록 수정하는 **로그인 기능을 추가**  
→ 일단 실행 후 해결된 이유 도출  
→ 로그인 시 사용자 정보 등록과 조회 후 M_ID를 확보  
→ 게임 중 데이터 삽입 시 외래 키 제약 조건 충족 |

**해결 전 코드**
<details>
  <summary>코드 보기</summary>
try {
    String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
    String user = "CGI_25IS_GA_P1_4";
    String password = "smhrd4";

    conn = DriverManager.getConnection(url, user, password);

    // MEMBER 테이블에서 아이디 확인
    String sql = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
    psmt = conn.prepareStatement(sql);
    psmt.setString(1, memberId);
    rs = psmt.executeQuery();

    if (rs.next()) {
        // 이미 회원 존재 -> 비밀번호 확인
        String dbPw = rs.getString("MEMBER_PW");
        if (dbPw.equals(memberPw)) {
            success = true;
            System.out.println("로그인 성공!");
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    } else {
        // 회원이 없으면 자동 등록
        rs.close();
        psmt.close();

        String insertSql = "INSERT INTO MEMBER(MEMBER_ID, MEMBER_PW, NAME) VALUES (?, ?, ?)";
        psmt = conn.prepareStatement(insertSql);
        psmt.setString(1, memberId);
        psmt.setString(2, memberPw);
        psmt.setString(3, "자동등록");
        int row = psmt.executeUpdate();
        if (row > 0) {
            success = true;
            System.out.println("회원 등록 완료, 로그인 성공!");
        }
    }

} catch (Exception e) {
    e.printStackTrace();
} finally {
    try {
        if (rs != null) rs.close();
        if (psmt != null) psmt.close();
        if (conn != null) conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

return success;
</details>
</br>

<details>
  <summary>코드 보기</summary>
  
  ```java
  package allLogic;

  import java.sql.*;
  import java.util.ArrayList;
  import java.util.Random;
  import java.util.Scanner;

  public class startGame2 {

      System.out.println("===== 게임 시작 =====");
      System.out.println("난이도를 선택하세요. [1] EASY [2] NORMAL [3] HARD");
      int level = sc.nextInt();

      int count = 0;
      int maxAttempts = 0;

      if (level == 1) {
          count = 3;
          maxAttempts = 10;
      } else if (level == 2) {
          count = 4;
          maxAttempts = 20;
      } else if (level == 3) {
          count = 5;
          maxAttempts = 30;
      }

      playGame(count, level, maxAttempts, memberId);
  }

 // 로그인 또는 회원 등록 -> 추가한 부분
  private static boolean loginOrRegister(String memberId, String memberPw) {
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      boolean success = false;

      try {
          String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
          String user = "CGI_25IS_GA_P1_4";
          String password = "smhrd4";

          conn = DriverManager.getConnection(url, user, password);

          // MEMBER 테이블에서 아이디 확인
          String sql = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
          psmt = conn.prepareStatement(sql);
          psmt.setString(1, memberId);
          rs = psmt.executeQuery();

          if (rs.next()) {
              // 이미 회원 존재 -> 비밀번호 확인
              String dbPw = rs.getString("MEMBER_PW");
              if (dbPw.equals(memberPw)) {
                  success = true;
                  System.out.println("로그인 성공!");
              } else {
                  System.out.println("비밀번호가 일치하지 않습니다.");
              }
          } else {
              // 회원이 없으면 자동 등록
              rs.close();
              psmt.close();

              String insertSql = "INSERT INTO MEMBER(MEMBER_ID, MEMBER_PW, NAME) VALUES (?, ?, ?)";
              psmt = conn.prepareStatement(insertSql);
              psmt.setString(1, memberId);
              psmt.setString(2, memberPw);
              psmt.setString(3, "자동등록");
              int row = psmt.executeUpdate();
              if (row > 0) {
                  success = true;
                  System.out.println("회원 등록 완료, 로그인 성공!");
              }
          }

      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              if (rs != null) rs.close();
              if (psmt != null) psmt.close();
              if (conn != null) conn.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      return success;
  }

  public static void playGame(int count, int level, int maxAttempts, String memberId) {
      Scanner sc = new Scanner(System.in);
      Random rd = new Random();

      ArrayList<Integer> number = new ArrayList<>();
      while (number.size() < count) {
          int num = rd.nextInt(10);
          if (!number.contains(num)) {
              number.add(num);
          }
      }

      System.out.println("게임 시작 " + count + "자리, 최대 시도 가능 횟수 : " + maxAttempts);
      int attempts = 0;
      boolean correct = false;

      while (!correct && attempts < maxAttempts) {
          System.out.println("숫자 입력 : ");
          String input = sc.next();

          int strike = 0;
          int ball = 0;

          for (int i = 0; i < count; i++) {
              int userNum = Character.getNumericValue(input.charAt(i));
              if (number.get(i) == userNum)
                  strike++;
              else if (number.contains(userNum))

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
