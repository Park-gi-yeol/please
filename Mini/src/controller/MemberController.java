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
	
	//view, dao, nbg 필드 생성 
	private MemberView view; 
	private MemberDAO dao;
	private NumberBaseballGame nbg;
	private MemberVO mvo;
	
	//생성자
	public MemberController(MemberView view, MemberDAO dao, NumberBaseballGame nbg) {
		this.view = view;
		this.dao = dao;
		this.nbg = nbg;
	}
	
	//게임을 로직을 실행하는  메서드 
	public void playGame(MemberVO mvo) {
		nbg.createAnswer();
		nbg.compareAnswer(mvo);
	}

	public void run() {

		outer: while (true) { //outer 반복문에 이름을 붙였다생각하면 된다 
			int input1 = view.showMenu1();
			if (input1 == 1) {
				MemberVO mvo = view.showLogin();  //뷰에서 id, pw 입력받음 
				String result = dao.login(mvo);
				view.statusLogin(result); // <-- 여기서 모든 경우 처리
				if (!"NO_ID".equals(result) && !"WRONG_PW".equals(result) && !"ERROR".equals(result)
						&& result != null) {// 로그인 성공했을 때만 다음 메뉴 이동
					mvo.setName(result);
					while (true) {
						int nextMenu = view.showMenu2();
						if (nextMenu == 1) {
							playGame(mvo);
							continue; //continue를 붙여준 이유는 게임 종료시에 로그인 화면으로 넘어가는게 아닌 게임 시작 메뉴로 출력하기 위함 
						} else if (nextMenu == 2) {
							view.showRule();
						} else if (nextMenu == 3) {
							ArrayList<MemberVO> list = dao.showStats(); // DAO에서 GAME→RESULT 업데이트 + 통계 조회
							view.showStats(list); // 화면에 통계 출력
						} else if (nextMenu == 4) {
							// 로그아웃 로그아웃시 로그인 화면으로 이동
							view.showLogout();
							continue outer; //continue outer를 하면 outer라는 반복문으로 이동하는데 run()에서 outer는 첫번째 반복문
						} else if (nextMenu == 5) {
							view.endGame();
							break outer; //break outer;는 outer반복문을 끝낸다는건데 outer가 첫번쨰 반복문이므로 반복문 종료 즉 게임종료 
						} else {
							view.showError(); // 메뉴에 없는 번호를 입력했을시 
						}
					}
				} else {
					
				}
			} else if (input1 == 2) {
				int row = dao.Join(view.showJoin()); // 회원가입 
				view.statusJoin(row);
			} else if (input1 == 3) {
				view.endGame();
				break;
			} else {
				view.showError(); // 잘못 입력했을 시 
			}
		}
	}
}
