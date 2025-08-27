package JDBC;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import controller.MemberController;
import controller.NumberBaseballGame;
import model.MemberDAO;
import view.MemberView;

public class Main {
    public static void main(String[] args) {
    	try {
            // 1) 윈도우면 코드페이지를 UTF-8(65001)로 전환
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "chcp 65001 > nul")
                    .inheritIO().start().waitFor();
            }
            // 2) 자바 표준 출력 스트림을 UTF-8로 래핑
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        } catch (Exception ignored) {}
    	
    	
    	
    	MemberView view = new MemberView();                  // 뷰 생성
        NumberBaseballGame game = new NumberBaseballGame(view); // 뷰 주입된 게임 생성
        MemberDAO dao = new MemberDAO();                     // DAO 생성

        // 컨트롤러에도 같은 view, game, dao 전달
        new MemberController(view, dao, game).run();
    }
}