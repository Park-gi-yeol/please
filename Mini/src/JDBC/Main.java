package JDBC;

import controller.MemberController;
import controller.NumberBaseballGame;
import model.MemberDAO;
import view.MemberView;

public class Main {
    public static void main(String[] args) {
        MemberView view = new MemberView();                  // 뷰 생성
        NumberBaseballGame game = new NumberBaseballGame(view); // 뷰 주입된 게임 생성
        MemberDAO dao = new MemberDAO();                     // DAO 생성

        // 컨트롤러에도 같은 view, game, dao 전달
        new MemberController(view, dao, game).run();
    }
}