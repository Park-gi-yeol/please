package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MemberVO;

public class MemberView {
   Scanner sc = new Scanner(System.in);
   MemberVO mvo = new MemberVO(); // MemberVO 인스턴스를 필드로 선언하여 gamePoint()에서 활용

   // 게임 시작 화면
   public int showMenu1() {
	   System.out.println(
			    "===============================================\n" +
			    "  _   _                    _\n" +
			    "  | \\ | |                  | |\n" +
			    "  |  \\| | _   _  _ __ ___  | |__    ___  _ __\n" +
			    "  | . ` || | | || '_ ` _ \\ | '_ \\  / _ \\| '__|\n" +
			    "  | |\\  || |_| || | | | | || |_) ||  __/| |\n" +
			    "  \\_| \\_/ \\__,_||_| |_| |_||_.__/  \\___||_|\n" +
			    "\n" +
			    "  ______                    _             _  _\n" +
			    "  | ___ \\                  | |           | || |\n" +
			    "  | |_/ /  __ _  ___   ___ | |__    __ _ | || |\n" +
			    "  | ___ \\ / _` |/ __| / _ \\| '_ \\  / _` || || |\n" +
			    "  | |_/ /| (_| |\\__ \\|  __/| |_) || (_| || || |\n" +
			    "  \\____/  \\__,_||___/ \\___||_.__/  \\__,_||_||_|\n" +
			    "\n" +
			    "       _____\n" +
			    "      |  __ \\ \n" +
			    "      | |  \\/  __ _  _ __ ___    ___\n" +
			    "      | | __  / _` || '_ ` _ \\  / _ \\\n" +
			    "      | |_\\ \\| (_| || | | | | ||  __/\n" +
			    "       \\____/ \\__,_||_| |_| |_| \\___|\n" +
			    "===============================================\n"
			);

       System.out.println("══════════════════════");	
       System.out.println("    ⚾ 메인 메뉴 ⚾      ");
       System.out.println("══════════════════════");
       System.out.println("[1] 로그인         ");
       System.out.println("[2] 회원가입        ");
       System.out.println("[3] 게임 종료       ");
       System.out.println("══════════════════════");
       System.out.print("선택: ");
       int input1 = sc.nextInt(); //메뉴선택
       return input1;

    }

   // 로그인 후 화면
   public int showMenu2() {
      System.out.println("═════════════════════");
      System.out.println("     ⚾ 게임 메뉴 ⚾     ");
      System.out.println("═════════════════════");
      System.out.println("[1] 게임 시작       ");
      System.out.println("[2] 게임 설명       ");
      System.out.println("[3] 점수 조회       ");
      System.out.println("[4] 로그 아웃       ");      
      System.out.println("[5] 게임 종료       ");
      System.out.println("═════════════════════");
      System.out.print("선택: ");
      int input2 = sc.nextInt(); //게임메뉴 선택
      return input2;
   }

   // 로그인 정보 입력 메소드
   public MemberVO showLogin() {
      System.out.println("═════════════════════");
      System.out.println("     ⚾ 로그인 ⚾      ");
      System.out.println("═════════════════════");
      System.out.print("ID 입력 : ");
      String id = sc.next();
      System.out.print("PW 입력 : ");
      String pw = sc.next();
      System.out.println("══════════════════════");
      MemberVO mvo = new MemberVO();
      mvo.setId(id); // 입력값 mvo 저장
      mvo.setPw(pw); // 입력값 mvo 저장

      return mvo; // mvo 리턴
   }

   // 로그인 성공 여부를 출력하는 메소드 //
   public void statusLogin(String result) {
	      if ("NO_ID".equals(result)) {
	         System.out.println("⚠ 존재하지 않는 회원입니다. 회원가입 후 이용해주세요."); //아이디가 db에 없을시
	      } else if ("WRONG_PW".equals(result)) {
	         System.out.println("❌ 비밀번호가 올바르지 않습니다. 다시 입력해주세요."); // 아이디는 일치하지만 pw가 일치하지 않을시
	      } else if ("ERROR".equals(result)) {
	         System.out.println("⚠ 시스템 오류가 발생했습니다. 잠시 후 다시 시도해주세요."); // 기타오류일시 (ex-db연결 안돼있거나, 네트워크 접속 안돼있을시)
	      } else if (result != null) {
	         // 로그인 성공 (result == 회원 이름)
	         System.out.println("로그인 성공 !");
	         System.out.println(result + "님 환영합니다 !");
	      } else {
	         System.out.println("로그인 실패 (알 수 없는 이유).");
	      }
	   }

   // 회원가입 정보 입력 메소드 
   public MemberVO showJoin() {
      System.out.println("══════════════════════");
      System.out.println("    ⚾ 회원 가입 ⚾      ");
      System.out.println("══════════════════════");
      System.out.print("ID 입력 : ");
      String id = sc.next();
      System.out.print("PW 입력 : ");
      String pw = sc.next();
      System.out.print("이름 입력 : ");
      String name = sc.next();
      System.out.println("══════════════════════");
      MemberVO mvo = new MemberVO();
      mvo.setId(id);	// 회원가입할 id 세팅
      mvo.setPw(pw);	// 회원가입할 pw 세팅	
      mvo.setName(name);	// 회원가입할 id의 이름 세팅
      return mvo;	// 위의 정보들을 mvo에 반환 
   }

   // 회원가입 성공/실패를 출력하는 메소드
   public void statusJoin(int row) {
      System.out.println("══════════════════════");
      if (row > 0) { //row = 영향을 받은 행이 있는지 없는지 즉 회원가입을 하면 db에 sql구문에 영향을 받은 행이 하나 더 생겨서 회원가입성공 출력 실패시 영향을 받은 행이없으므로 출력x
         System.out.println("     ✅ 회원가입 성공         ");
      } else {
         System.out.println("     ❌ 회원가입 실패         ");
      }
      System.out.println("══════════════════════");
   }

   // 난이도 선택 출력 메소드
   public int levelMenu() {
      System.out.println("╔═══════════════════╗");
      System.out.println("    ⚾ 난이도 선택 ⚾  ");
      System.out.println("╠═══════════════════╣");
      System.out.println("  [1] Easy 🟢       ");
      System.out.println("  [2] Normal 🟡     ");
      System.out.println("  [3] Hard 🔴       ");
      System.out.println("╚═══════════════════╝");
      System.out.print("선택: ");
      int level = sc.nextInt(); //위의 세개중 하나를 입력받아 level에 저장 할 수 있도록  

      return level;
   }

   // 사용자가 정답을 입력하는 출력
   public String getUseranswer() {
      System.out.print("⚾ 정답을 입력하세요 : ");
      String useranswer = sc.next(); //유저 정답 입력
      return useranswer;
   }

   // 사용자가 잘못 입력했을 경우 출력 ex - 입력해야할 숫자는 3자리인데 4자리를 입력했을경우
   public void userWrong() {
      System.out.println("╔═══════════════════════╗");
      System.out.println("  ❌ 자릿수가 맞지 않습니다. ");
      System.out.println("  다시 입력하세요.           ");
      System.out.println("╚═══════════════════════╝");
   }

   // 정답을 맞췄을 시 게임 출력
   public void success(String num, int count) {
      System.out.println("════════════════════════════════");
      System.out.println(" 🎉 " + num + " 정답입니다 !!     ");
      System.out.println(" 총 " + count + "회 시도했습니다 !  ");
      System.out.println("════════════════════════════════");
   }

   // 실패 했을 시 게임 출력
   public void fail(int length, int[] answer) {
      System.out.println("╔═══════════════════╗");
      System.out.println("  😭 오답입니다.");
      System.out.print("  정답은 ");
      for (int i = 0; i < length; i++) {
         System.out.print(answer[i]);
      }
      System.out.println(" 입니다. ");
      System.out.println("╚═══════════════════╝");
   }

   // 난이도별 점수 계산 및 출력
   public int gamePoint(int count, int level, String result) {
       int point = 0;

       if (level == 1) { // Easy 총 10번 기회 3회안에 정답시 30 6회안에 정답시 20 10회에 정답시 10
           if (count <= 3) point = 30;   
           else if (count <= 6) point = 20;
           else if (count <= 10) point = 10;
           else point = 0;
       } else if (level == 2) { // Normal 총 20번 기회 6회안에 정답시 60 13회 안에 정답시 50 20회에 정답시 40
           if (count <= 6) point = 60;
           else if (count <= 13) point = 50;
           else if (count <= 20) point = 40;
           else point = 0;
       } else if (level == 3) { // Hard 총 30번 기회 10회안에 정답시 100 20회 안에 정답시 80 30회에 정답시 70
           if (count <= 10) point = 100;
           else if (count <= 20) point = 80;
           else if (count <= 30) point = 70;
           else point = 0;
       }

       // MemberVO에 결과 세팅 (필드 mvo 사용)
       mvo.setPoint(point);
       mvo.setCount(count);
       mvo.setResult(result);

       System.out.println("╔═══════════════════╗");
       System.out.println(" 💰 " + point + "점 획득하였습니다!   ");
       System.out.println("╚═══════════════════╝");
       return point;
   }

   // 결과 출력 메소드
   public void printResult(int s, int b, int count) {
      System.out.println("╔═══════════════════╗");
      System.out.println("  시도 횟수: " + count + "       ");
      System.out.println("  스트라이크: " + s + "  볼: " + b + "   ");
      System.out.println("╚═══════════════════╝");
   }

   // 정답 생성 알림 메소드
   public void gameStart() {
      System.out.println("╔═════════════════════╗");
      System.out.println("  ⚾ 정답이 생성되었습니다!  ");
      System.out.println("╚═════════════════════╝");
   }


   // 게임 종료 메소드
   public void endGame() {
	      {
	         System.out.println("====================================================\n" + "\n"
	               + "    _____                                                    \n"
	               + "   |  __ \\                                                   \n"
	               + "   | |  \\/  __ __  _ __  ___    ___    \n" + "   | | __  / _` | | |_ ` _  \\  / _ \\  \n"
	               + "   | |_\\ \\| (_| | | | | | | ||  __/      \n" + "   \\____/ \\__,__| |_| |_| |_| \\___|    \n"

	               + "           \n" + "     ___   __   __  ___  _ __ __\n" + "    / _ \\  \\ \\ / / / / _ \\| '__|\n"
	               + "   | (_) |  \\ V / | __/|   |   \n" + "    \\___/    \\_/   \\___| |_|   \n"
	               + "                         \n" + "====================================================\n"
	               + "                      ");

	      }
	   }

   // 게임 설명 메소드
   public void showRule() {

       System.out.println("+============================================================================+");
       System.out.println("║                          ⚾⚾⚾ 숫자 야구 게임 설명 ⚾⚾⚾                        ║");
       System.out.println("+============================================================================+");
       System.out.println();
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("|  1. 게임 규칙                                                                 |");
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("   - 컴퓨터가 임의로 만든 숫자로 구성된 정답을 맞추는 게임입니다.");
       System.out.println("   - 플레이어는 숫자를 입력하여 정답을 추리합니다.");
       System.out.println("   - 같은 숫자가 같은 위치에 있으면 '스트라이크' ⚾,");
       System.out.println("   - 같은 숫자가 다른 위치에 있으면 '볼' 🟡로 판정됩니다.");
       System.out.println("   - 모든 숫자의 위치를 맞추면 게임에서 승리합니다! (정답 자리수 = 전부 스트라이크)");
       System.out.println();
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("|  2. 입력 규칙                                                                 |");
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("   - 정답은 0~9까지의 숫자로 구성됩니다.");
       System.out.println("   - 같은 숫자는 중복되지 않습니다.");
       System.out.println("   - 플레이어는 난이도에 맞게 3자리/4자리/5자리 숫자를 정확히 입력해야 합니다.");
       System.out.println();
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("|  3. 난이도별 규칙                                                              |");
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("   - Easy (쉬움) 🟢: 정답 3자리, 최대 10번 도전 가능");
       System.out.println("   - Normal (보통) 🟡: 정답 4자리, 최대 20번 도전 가능");
       System.out.println("   - Hard (어려움) 🔴: 정답 5자리, 최대 30번 도전 가능");
       System.out.println();
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("|  4. 보상 점수                                                                 |");
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("   - Easy: 3회 이내 30점, 6회 이내 20점, 10회 이내 10점");
       System.out.println("   - Normal: 6회 이내 60점, 14회 이내 50점, 20회 이내 40점");
       System.out.println("   - Hard: 10회 이내 100점, 20회 이내 80점, 30회 이내 70점");
       System.out.println();
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("|  5. 게임 종료 조건                                                             |");
       System.out.println("+----------------------------------------------------------------------------+");
       System.out.println("   - 정답을 맞추거나 도전 횟수를 모두 소진하면 게임이 종료됩니다.");
       System.out.println("   - 실패 시 컴퓨터가 만든 정답이 공개됩니다.");
       System.out.println("+============================================================================+");
   }

   // 회원 정보 조회 메소드
   public void showStats(ArrayList<MemberVO> list) {
	      System.out.println("╔══════════════════════╗");
	      System.out.println("    ⚾ 회원 통계 조회 ⚾   ");
	      System.out.println("╚══════════════════════╝");
	      System.out.println("회원ID\t   | 총점\t     | 평균시도\t  | 승률(%)");
	          for (MemberVO mvo : list) {
	        	  System.out.printf("%-10s | %-7d | %-10.2f | %-7.2f%%%n",
	                      mvo.getId(),
	                      mvo.getTotalScore(),
	                      mvo.getAvgTry(),
	                      mvo.getWinRate());
	          }
	      }

   // 잘못 입력 시 출력
   public void showError() {
      System.out.println("╔═══════════════════╗");
      System.out.println("  ⚠️ 잘못 입력하였습니다. ");
      System.out.println("╚═══════════════════╝");
   }
   // 로그아웃 성공 메소드
   public void showLogout() {
	   System.out.println("====================================================");   
       System.out.println("        | |    ___   __ _    ___  _   _| |_ ");
       System.out.println("        | |   / _ \\ / _` |  / _ \\| | | | __| ");
       System.out.println("        | |__| (_) | (_| | | (_) | |_| | |_  ");
       System.out.println("        |_____|\\___/ \\__,|  \\___/ \\__,_|\\__| ");
       System.out.println("                     |___/                   ");
       System.out.println("====================================================");
   }
   

}
