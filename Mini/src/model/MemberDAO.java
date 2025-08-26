package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	private static PreparedStatement psmt = null;
	private static Connection conn = null;
	private static ResultSet rs = null;

	// 드라이버로딩
	private static void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
			String user = "CGI_25IS_GA_P1_4";
			String password = "smhrd4";
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 자원반납
	private static void getClose() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입 메소드
	public int Join(MemberVO mvo) {
		int row = 0;
		try {
			getConn();
			String sql = "INSERT INTO MEMBER (MEMBER_ID,MEMBER_PW,NAME) VALUES(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mvo.getId());
			psmt.setString(2, mvo.getPw());
			psmt.setString(3, mvo.getName());
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}

	// 로그인 메소드
	public String login(MemberVO mvo) {
	       try {
	           getConn();  // DB 연결

	           // 아이디 존재 여부 먼저 확인
	           String sql = "SELECT MEMBER_PW, NAME FROM MEMBER WHERE MEMBER_ID = ?";
	           psmt = conn.prepareStatement(sql);
	           psmt.setString(1, mvo.getId());
	           rs = psmt.executeQuery();

	           if (!rs.next()) {
	               return "NO_ID";   // 아이디 없음
	           } else {
	               String dbPw = rs.getString("MEMBER_PW");
	               String name = rs.getString("NAME");

	               if (dbPw.equals(mvo.getPw())) {
	                   return name;  // 로그인 성공 (회원 이름 반환)
	               } else {
	                   return "WRONG_PW"; // 비밀번호 틀림
	               }
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           getClose();
	       }
	       return "ERROR"; // 기타 오류
	   }

	// 점수 기록 메소드
	public ArrayList<MemberVO> showStats() {
	       ArrayList<MemberVO> list = new ArrayList<>();
	       try {
	           getConn();

	           // 1. GAME → RESULT 통계 갱신
	           String mergeSql = "MERGE INTO RESULT r " +
	                             "USING ( " +
	                             "  SELECT MEMBER_ID, SUM(SCORE) AS TOTAL_SCORE, " +
	                             "         ROUND(AVG(COUNT_TRY),2) AS AVG_TRY, " +
	                             "         ROUND(SUM(CASE WHEN RESULT='성공' THEN 1 ELSE 0 END)/COUNT(*)*100,2) AS WIN_RATE " +
	                             "  FROM GAME " +
	                             "  GROUP BY MEMBER_ID " +
	                             ") g " +
	                             "ON (r.MEMBER_ID = g.MEMBER_ID) " +
	                             "WHEN MATCHED THEN " +
	                             "  UPDATE SET r.TOTAL_SCORE = g.TOTAL_SCORE, r.AVG_TRY = g.AVG_TRY, r.WIN_RATE = g.WIN_RATE " +
	                             "WHEN NOT MATCHED THEN " +
	                             "  INSERT (MEMBER_ID, TOTAL_SCORE, AVG_TRY, WIN_RATE) " +
	                             "  VALUES (g.MEMBER_ID, g.TOTAL_SCORE, g.AVG_TRY, g.WIN_RATE)";
	           
	           psmt = conn.prepareStatement(mergeSql);
	           psmt.executeUpdate();
	           psmt.close();

	           // 2. RESULT 테이블에서 통계 조회
	           String selectSql = "SELECT MEMBER_ID, TOTAL_SCORE, AVG_TRY, WIN_RATE FROM RESULT ORDER BY TOTAL_SCORE DESC";
	           psmt = conn.prepareStatement(selectSql);
	           rs = psmt.executeQuery();

	           while(rs.next()) {
	               MemberVO mvo = new MemberVO();
	               mvo.setId(rs.getString("MEMBER_ID"));
	               mvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
	               mvo.setAvgTry(rs.getDouble("AVG_TRY"));
	               mvo.setWinRate(rs.getDouble("WIN_RATE"));
	               list.add(mvo);
	           }

	       } catch(SQLException e) {
	           e.printStackTrace();
	       } finally {
	           getClose();
	       }

	       return list;
	   }


	// 회원조회
	public ArrayList<MemberVO> showFind() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();

		try {
			getConn();

			String sql = "SELECT MEMBER_ID, NAME FROM MEMBER";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				MemberVO mvo = new MemberVO();
//				String id = rs.getString("MEMBER_ID");
//				String name = rs.getString("NAME");


				list.add(mvo);

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getClose();
		}
		return list;

	}

	// 점수 저장
	public void saveResult(MemberVO mvo) {
		try {
			getConn();
			String sql = "INSERT INTO GAME (RESULT_ID, MEMBER_ID, RESULT, SCORE, COUNT_TRY) "
					+ "VALUES (LPAD(GAME_SEQ.NEXTVAL, 4, '0'), ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, mvo.getId());
			psmt.setString(2, mvo.getResult());
			psmt.setInt(3, mvo.getPoint());
			psmt.setInt(4, mvo.getCount());

			int row = psmt.executeUpdate();
			if (row > 0) {
				System.out.println("게임 결과가 DB에 저장되었습니다.");
			} else {
				System.out.println("게임 결과 저장 실패.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
	}
}