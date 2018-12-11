package edu.kosta.kdc.dto;

public class UpDownDTO {

    private int updownPk;           //PK
    private int updownReplyBoardPk; //게시판 PK 참조
    private String updownMemberId;  //작성자 아이디
    private boolean isUp;           //업 or 다운
    
    
    public UpDownDTO() {}
    
    public UpDownDTO(int updownPk, int updownReplyBoardPk, String updownMemberId, boolean isUp) {
        super();
        this.updownPk = updownPk;
        this.updownReplyBoardPk = updownReplyBoardPk;
        this.updownMemberId = updownMemberId;
        this.isUp = isUp;
    }

    public int getUpdownPk() {
        return updownPk;
    }

    public void setUpdownPk(int updownPk) {
        this.updownPk = updownPk;
    }

    public int getUpdownReplyBoardPk() {
        return updownReplyBoardPk;
    }

    public void setUpdownReplyBoardPk(int updownReplyBoardPk) {
        this.updownReplyBoardPk = updownReplyBoardPk;
    }

    public String getUpdownMemberId() {
        return updownMemberId;
    }

    public void setUpdownMemberId(String updownMemberId) {
        this.updownMemberId = updownMemberId;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean isUp) {
        this.isUp = isUp;
    }
    
    
    
}
