package controller;

import java.util.Random;
import java.util.Scanner;

import model.MemberDAO;
import model.MemberVO;
import view.MemberView;

public class NumberBaseballGame {
	// 접근제한자 설정했어야하는데 어떻게 건들어야할지 모르겠어서 이것도 쌤한테 물어봐야될듯
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);
	int level;
	int length;
	int[] answer;
	int[] useranswer;
	String num;
	int count = 0;
	int maxAttempts = 0;
	int s = 0;
	int b = 0;
	String result = null;
	MemberView view = new MemberView();
	MemberVO mvo = new MemberVO();
	MemberDAO dao = new MemberDAO();

	public NumberBaseballGame(MemberView view) {
		this.view = view;
	}

	// 뷰에서 받아온 레벨을 저장하고싶은데 이거 되는지 안되는지 모르겠음 일단 낼 가서 물어보기
//	public void levelSelect() {
//		level = view.levelMenu();
//	}

	// 컴퓨터가 정답 생성하는 메소드
	public void createAnswer() {
		//
		level = view.levelMenu();
		if (level == 1) {
			maxAttempts = 10;
			length = 3;
		} else if (level == 2) {
			maxAttempts = 20;
			length = 4;
		} else {
			maxAttempts = 30;
			length = 5;
		}
		answer = new int[length];
		for (int i = 0; i < length; i++) {
			answer[i] = rd.nextInt(10);
			for (int j = 0; j < i; j++) {
				if (answer[i] == answer[j]) {
					i--;
					break;
				}
			}
		}
		view.gameStart();
		//view.testView(length, answer);
	}

	// 유저가 정답 입력하는 메소드
	public void userAnswer() {
		useranswer = new int[length];
		while (true) {
			num = view.getUseranswer();
			// 길이가 일치한지검증하는 로직 //입력한문자열이 숫자가 맞는지 확인하는 로직
			if (num.length() != length || !num.matches("\\d{" + length + "}")) {
				// num.mateches == 메소드는 문자열이 정규식 패턴과 일치하는지 확인 반환값 True or False
				// \\d = 숫자 한 글자를 의미 : 정규식에서는 \d 가 숫자의미인데 자바 문자열에서는 \\d로 써야됨
				// {length} 반복 횟수를 의미 ex) length가 3이면 3자리 숫자
				// 즉 "\\d{" + length + "}" 는 길이가 length인 숫자 문자열만 허용한다는 뜻
				view.userWrong();
				continue; // 다시 입력 받기
			}
			for (int i = 0; i < length; i++) {
				useranswer[i] = num.charAt(i) - '0';
			}
			break;
		}
	}

	// 컴퓨터 정답과 유저의 정답을 비교하여 성공여부 판단
	public void compareAnswer(MemberVO mvo) {
		while (true) {
			userAnswer();
			s = 0; // 매번 새로 초기화
			b = 0;
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					if (answer[i] == useranswer[j]) {
						if (i == j) {
							s++; // 같은 자리 → 스트라이크
						} else {
							b++; // 다른 자리 → 볼
						}
					}
				}
			}

			count++; // 시도 횟수 증가
			
			if (s == length) {
				view.success(num, count);
				int point = view.gamePoint(count, level, result);
				view.endGame();
				// 결과 담기
			    mvo.setCount(count);
			    mvo.setResult("성공");
			    mvo.setPoint(point);
			    dao.saveResult(mvo);
				break;	
				
			}
			if (count > maxAttempts) {
				view.fail(length, answer);
				int point = view.gamePoint(count, level, result);
				view.endGame();
				//결과담기
				mvo.setCount(count);
			    mvo.setResult("실패");
			    mvo.setPoint(point);
			    dao.saveResult(mvo);
				break;
			} 
			view.printResult(s, b, count);	 // 아직 안 끝났을 때 결과 출력
		}
	}

}
