package edu.kosta.kdc.model.dto;

public class VisitDTO {
    
    private int visitPk;        //visit 테이블 pk
    private String visitDate;   //날짜
    private int visitNum;       //방문자 수
    
    public VisitDTO() {}

    public VisitDTO(int visitPk, String visitDate, int visitNum) {
        super();
        this.visitPk = visitPk;
        this.visitDate = visitDate;
        this.visitNum = visitNum;
    }
    
    public int getVisitPk() {
        return visitPk;
    }

    public void setVisitPk(int visitPk) {
        this.visitPk = visitPk;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }
    
}
