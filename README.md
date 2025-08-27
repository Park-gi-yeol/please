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
![](https://blogger.googleusercontent.com/img/a/AVvXsEi7V1oDAnFlLCN4UQFdyZp2wr0e9W5EUfHBL1GwDCupc_tGxHUib8FRiIwdgRWeoaTWpZLIrDAuGTXp5PNaE8rps5vkHbcnQUzBYELUXr5d3jUTls6On12sB19S0_4Jgpj53PTZYd7G8aXI2HtD5WGdy_FkyK11_cXnp-r-gs3Nyj7KjhlfQObC5O6bUujt)




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

### 5.2. Git-hub 연결
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

### 5.3. DB연결 문제2
## ❓분석 단계

| **클래스 명** | MemberDAO |
| --- | --- |
| **이슈 사항** | `MERGE INTO RESULT` 실행 시 쿼리 오류 및 데이터 미반영 |
| **원인** | `MERGE` 구문 자체는 정상 작동하지만, `g.MEMBER_ID`가 null인 경우 삽입 또는 갱신 안됨 |

## ⁉해결 단계

- **시도1. merge 쿼리점검**

    → 구문상 문제 없음

- **시도2. result 문제확인**

    → `RESULT` 테이블 컬럼 확인

## ❗해결 완료

- GAME 테이블에서 null 값 제거 또는 기본값 처리

```sql
-- GAME 테이블에서 null 값 제거 또는 기본값 처리
UPDATE GAME SET COUNT_TRY = 0 WHERE COUNT_TRY IS NULL;
MERGE 쿼리 내부에 WHERE MEMBER_ID IS NOT NULL 조건 추가

sql
코드 복사
-- MERGE 쿼리 내부에 WHERE MEMBER_ID IS NOT NULL 조건 추가

SELECT MEMBER_ID, SUM(SCORE) AS TOTAL_SCORE, ...
FROM GAME
WHERE MEMBER_ID IS NOT NULL
GROUP BY MEMBER_ID
Java 코드에서 rs.next() 결과가 없을 경우 메시지 출력 추가
    
</br>

## 6. 회고 / 느낀점
>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/
