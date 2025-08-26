package model;

public class MemberVO {
   protected String id;
   protected String pw;
   protected String name;
   protected int point;
   protected int count;
   protected String result;
    private int totalScore;      // 총점
       private double avgTry;       // 평균 시도
       private double winRate;      // 승률(%)

       // ✅ 기본 생성자
       public MemberVO() {}

       

       public int getTotalScore() {
           return totalScore;
       }
       public void setTotalScore(int totalScore) {
           this.totalScore = totalScore;
       }

       public double getAvgTry() {
           return avgTry;
       }
       public void setAvgTry(double avgTry) {
           this.avgTry = avgTry;
       }

       public double getWinRate() {
           return winRate;
       }
       public void setWinRate(double winRate) {
           this.winRate = winRate;
       }
   
   public int getPoint() {
      return point;
   }
   public void setPoint(int point) {
      this.point = point;
   }
   public int getCount() {
      return count;
   }
   public void setCount(int count) {
      this.count = count;
   }
   public String getResult() {
      return result;
   }
   public void setResult(String result) {
      this.result = result;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
 
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   
}
