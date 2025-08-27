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
	int level; //난이도
	int length; //난이도별 맞춰야될 숫자 개수 
	int[] answer; //컴퓨터가 생성한 정답
	int[] useranswer; //유저가 입력한 정답을 정수형 배열로 전환 
	String num;	// 유저가 입력한 정답 
	int count = 0; // 유저가 정답을 맞추는데 시도한 횟수
	int maxAttempts = 0; // 난이도별 시도할 수 있는 횟수
	int s = 0;	//s 개수
	int b = 0; //b개수
	String result = null; // 성공과 실패
	MemberView view = new MemberView();
	MemberVO mvo = new MemberVO();
	MemberDAO dao = new MemberDAO();

	public NumberBaseballGame(MemberView view) {
		this.view = view;
	}

	// 컴퓨터가 정답 생성하는 메소드
	public void createAnswer() {
		//
		while (true) {
			//난이도 선택 1번 이지 2번 노말 3번 하드
			level = view.levelMenu();
			if (level == 1) {
				maxAttempts = 10;
				length = 3;
				break;
			} else if (level == 2) {
				maxAttempts = 20;
				length = 4;
				break;
			} else if (level == 3) {
				maxAttempts = 30;
				length = 5;
				break;
			} else {
				//난이도 선택창에 없는 번호를 선택시 에러가 뜨게 끔 
				view.showError();
			}
		}
		//입력받은 난이도로 정답을 생성
		answer = new int[length];
		for (int i = 0; i < length; i++) {
			answer[i] = rd.nextInt(10);
			for (int j = 0; j < i; j++) {
				if (answer[i] == answer[j]) {
					//배열간 인덱스 요소를 비교해 중복이 있을시 반복문을 탈출하고 해당인덱스에 요소를 다시 뽑게 
					i--;
					break;
				}
			}
		}
		view.gameStart(); // 정답 생성 완료시 출력
	}

	// 유저가 정답 입력하는 메소드
	public void userAnswer() {
		useranswer = new int[length];
		while (true) {
			num = view.getUseranswer(); // 유저가 정답을 문자열로 입력 
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
				//유저가 입력한 정답을 정수형 배열에 담는 과정 
				//num.charAt(i) - '0' 은 num에있는 문자열에서 i번째 문자를꺼내는 과정 그 후 0의 아스키코드 =48 만큼 빼 정수로 나오게끔 
				useranswer[i] = num.charAt(i) - '0';  //유저가 입력한 정답을 정수형 배열에 담는 과정 num.charAt(i) - '0'
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
					//먼저 컴퓨터의 정답과 유저의 정답에 일치하는 숫자가 있는지 판단
					if (answer[i] == useranswer[j]) { 
						//일치하는 숫자가 있다면 자릿수가 일치하는지 확인 인덱스번호가 같다면 자릿수가 일치 
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
				//s의 개수가 정답의 크기와 일치하다면 정답 
				view.success(num, count);
				int point = view.gamePoint(count, level, result);
				view.endGame();
				// 결과 담기
				mvo.setCount(count);
				mvo.setResult("성공");
				mvo.setPoint(point);
				dao.saveResult(mvo);
				count = 0;
				break;

			}
			if (count == maxAttempts) {
				//시도 횟수가 난이도별 총 시도횟수보다 넘어 섰을 경우 실패 
				view.fail(length, answer);
				int point = view.gamePoint(count, level, result);
				view.endGame();
				// 결과담기
				mvo.setCount(count);
				mvo.setResult("실패");
				mvo.setPoint(point);
				dao.saveResult(mvo);
				count = 0;
				break;
			}
			view.printResult(s, b, count); // 아직 안 끝났을 때 결과 출력
		}
	}

}
