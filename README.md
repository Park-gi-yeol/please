# ⚾ 숫자 야구 게임 (Number Baseball Game)

자바 콘솔 기반의 숫자 추리 게임  
**회원 가입 → 로그인 → 난이도 선택 → 게임 플레이 → 점수 저장 & 랭킹 조회**

---

## 📅 제작 기간 & 참여 인원
- **2025.08.22 ~ 2025.08.27**  
- **팀 프로젝트 (4인)**

| 역할   | 이름   | 담당 업무          |
|--------|--------|-------------------|
| 팀장   | 박기열 | 설계 담당          |
| 팀원   | 이용일 | 프로젝트 매니저 (PM) |
| 팀원   | 김주성 | 문서 담당          |
| 팀원   | 유도경 | 오류 점검 담당     |

---

## 🛠 사용 기술
- **Back‑end / Logic**: Java 11, JDBC (Oracle 11g), MVC 패턴  
- **Database**: MySQL 8.0, SQL (DDL/DML)  
- **Tool / Environment**: Eclipse, Git / GitHub  

---

## 📂 프로젝트 구조 (MVC)
```plaintext
src
 ┣ controller
 ┃ ┣ NumberBaseballGame.java   // 게임 진행 로직
 ┃ ┗ MemberController.java     // 전체 흐름 담당
 ┣ model
 ┃ ┣ MemberDAO.java            // DB 연결 및 CRUD
 ┃ ┗ MemberVO.java             // 회원 데이터 객체
 ┣ view
 ┃ ┗ MemberView.java           // UI 입력/출력 처리
 ┗ Main.java                   // 실행 진입점
```

---

## 🧱 ERD 설계
![ERD 이미지](https://blogger.googleusercontent.com/img/a/AVvXsEi7V1oDAnFlLCN4UQFdyZp2wr0e9W5EUfHBL1GwDCupc_tGxHUib8FRiIwdgRWeoaTWpZLIrDAuGTXp5PNaE8rps5vkHbcnQUzBYELUXr5d3jUTls6On12sB19S0_4Jgpj53PTZYd7G8aXI2HtD5WGdy_FkyK11_cXnp-r-gs3Nyj7KjhlfQObC5O6bUujt)

---

## 🔧 핵심 기능

### ✅ 전체 흐름
<details>
  <summary>클릭하여 전체 흐름 보기</summary>

- 사용자 요청에 따라 로그인 → 메뉴 선택 → 게임 실행 흐름 진행
- 컨트롤러 → 서비스 → DB 저장 → 화면 반환 방식으로 전개

</details>

---

### 👤 사용자 요청
<details>
  <summary>클릭하여 사용자 요청 세부 보기</summary>

- **로그인 요청**: `MemberController`가 로그인 요청 처리
- **게임 시작 요청**: `NumberBaseballGame` 클래스에서 게임 설정 및 실행

</details>

---

### 🎮 Controller
<details>
  <summary>클릭하여 Controller 흐름 보기</summary>

- 요청을 `MemberDAO`, `NumberBaseballGame` 등으로 위임
- 처리 결과를 사용자에게 응답

</details>

---

### 🛠 Service
<details>
  <summary>클릭하여 Service 흐름 보기</summary>

- DB에서 로그인 정보 확인
- 게임 난이도 처리, 사용자 입력 추측 로직 실행

</details>

---

### 💾 Repository
<details>
  <summary>클릭하여 Repository 역할 보기</summary>

- 회원 정보 및 게임 결과 DB 저장
- 컨트롤러로 반환하여 화면 전송

</details>

---

### 📊 세부 흐름 예시
<details>
  <summary>클릭하여 세부 예시 보기</summary>

#### ex1. 로그인 흐름
1. 로그인 정보 입력
2. `MemberController` 로그인 요청
3. `MemberDAO` DB 조회
4. 로그인 결과 출력

#### ex2. 게임 흐름
1. 난이도 선택
2. 게임 설정 및 실행
3. 추측 입력 → 정답 비교
4. 결과 출력, 재시작/종료 옵션 표시

</details>

---

## 🚨 핵심 트러블 슈팅

<details>
<summary><strong>5.1 GitHub 연결 문제</strong></summary>

| **이슈 사항** | GitHub 연결 문제 및 충돌 발생 |
|---------------|------------------------------|

### ⁉ 해결 단계
- 기존 프로젝트에서 Push/Pull 시도 → 충돌 발생, 일부만 Pull 성공  
- 새 프로젝트 생성 후 Push/Pull 재시도 → 동일 오류 반복  
- `.metadata` 충돌 확인 및 `.gitignore` 생성 → 문제 지속

---

### ❓ 분석
- Java Export된 프로젝트가 팀원 간 공유됨  
- GitHub 저장소 경로가 공유된 경로에 설정됨  
- 해당 폴더에 동일 파일이 존재하여 Pull 시 병합 불가 → 오류 & 충돌

---

### ❗ 해결 완료

| **해결 방법** | 정상적인 GitHub 사용 방법 (최초 Push 팀원 외) |
|---------------|---------------------------------------------|
| **빈 폴더**에 GitHub 저장소 경로 설정 후 `add → commit → pull/push` 순서로 진행 → 충돌 없이 협업 성공 |

---

**p.s.**
- `.gitignore`는 Pull 과정에서 자동 생성됨  
- `.metadata`는 `.gitignore`로 제외되어 충돌 없음

</details>

---

<details>
<summary><strong>5.2 ORA-02291 제약 조건 오류</strong></summary>

| **클래스 명** | allLogic |
| --- | --- |
| **이슈 사항** | 단일 명령문 실행 중 **ORA-02291** 발생 |
| **원인** | 게임 중 기록 저장 시 참조하려는 `M_ID`가 회원 테이블에 존재하지 않음 |

---

### ⁉ 해결 단계
- 로그인 기능을 포함한 클래스로 테스트 재진행

---

### ❗ 해결 완료

| **해결 방법** | 게임 기록 저장 전, **로그인을 통해 사용자 정보 선등록**  
→ 로그인 시 `M_ID` 확보 → 외래키 제약 충족 후 기록 저장 가능 |

</details>

---

<details>
<summary><strong>5.3 MERGE INTO 오류 (데이터 미반영)</strong></summary>

| **클래스 명** | MemberDAO |
| --- | --- |
| **이슈 사항** | `MERGE INTO RESULT` 실행 시 쿼리 오류 및 데이터 미반영 |
| **원인** | `MERGE` 구문은 정상이나 `g.MEMBER_ID`가 null인 경우 갱신/삽입되지 않음 |

---

### ⁉ 해결 단계

- **시도1**: MERGE 구문 자체는 정상 작동 → 쿼리 점검  
- **시도2**: RESULT 테이블 구조 및 컬럼 제약 조건 확인  

---

### ❗ 해결 완료

- `GAME` 테이블의 null 값 처리 (기본값 설정)
- `MERGE` 시 `WHERE MEMBER_ID IS NOT NULL` 조건 추가
- `Java` 코드에서 `rs.next()` 없을 시 메시지 출력 추가

```sql
-- null 제거 처리
UPDATE GAME SET COUNT_TRY = 0 WHERE COUNT_TRY IS NULL;

-- MERGE 실행 시 조건 추가
MERGE INTO RESULT r
USING (
  SELECT MEMBER_ID, SUM(SCORE) AS TOTAL_SCORE
  FROM GAME
  WHERE MEMBER_ID IS NOT NULL
  GROUP BY MEMBER_ID
) g
ON (r.MEMBER_ID = g.MEMBER_ID)
WHEN MATCHED THEN
  UPDATE SET r.TOTAL_SCORE = g.TOTAL_SCORE
WHEN NOT MATCHED THEN
  INSERT (MEMBER_ID, TOTAL_SCORE) VALUES (g.MEMBER_ID, g.TOTAL_SCORE);
```

</details>
---

---

## 🪞 회고 / 느낀점
> 🔗 [프로젝트 회고 블로그 보기](https://zuminternet.github.io/ZUM-Pilot-integer/)

- 협업 중 Git 충돌, DB 오류, 외래키 제약 조건 등 다양한 문제에 부딪힘
- 실무에서 발생할 수 있는 트러블을 경험하며, 이를 문서화하고 해결해보는 좋은 기회였음
- Java 기반의 로직 구조와 MVC 패턴에 대한 감을 익힐 수 있었음
- 테스트 주도 개발과 예외 처리, 사용자 흐름 설계의 중요성도 체감함

