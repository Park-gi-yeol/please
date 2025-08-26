package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.MemberDAO;
import model.MemberVO;
import view.MemberView;

public class MemberController {
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();

	private MemberView view;
	private MemberDAO dao;
	private NumberBaseballGame nbg;
	private MemberVO mvo;

	public MemberController(MemberView view, MemberDAO dao, NumberBaseballGame nbg) {
		this.view = view;
		this.dao = dao;
		this.nbg = nbg;
	}

	public void playGame(MemberVO mvo) {
		nbg.createAnswer();
		nbg.compareAnswer(mvo);
	}

	public void run() {

		outer: while (true) {
			int input1 = view.showMenu1();
			if (input1 == 1) {
				MemberVO mvo = view.showLogin();
				String result = dao.login(mvo);
				view.statusLogin(result); // <-- 여기서 모든 경우 처리
				if (!"NO_ID".equals(result) && !"WRONG_PW".equals(result) && !"ERROR".equals(result)
						&& result != null) {// 로그인 성공했을 때만 다음 메뉴 이동
					mvo.setName(result);
					while (true) {
						int nextMenu = view.showMenu2();
						if (nextMenu == 1) {
							playGame(mvo);
							continue;
						} else if (nextMenu == 2) {
							view.showRule();
						} else if (nextMenu == 3) {
							ArrayList<MemberVO> list = dao.showStats(); // DAO에서 GAME→RESULT 업데이트 + 통계 조회
							view.showStats(list); // 화면에 통계 출력
						} else if (nextMenu == 4) {
							// 로그아웃 로그아웃시 로그인 화면으로 이동
							view.showLogout();
							continue outer;
						} else if (nextMenu == 5) {
							view.endGame();
							break;
						} else {
							view.showError();
						}
					}
				} else {
					
				}
			} else if (input1 == 2) {
				int row = dao.Join(view.showJoin());
				view.statusJoin(row);
			} else if (input1 == 3) {
				view.endGame();
			} else {
				view.showError();
			}
		}
	}
}
