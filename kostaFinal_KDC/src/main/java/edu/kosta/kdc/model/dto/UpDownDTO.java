package edu.kosta.kdc.model.dto;

public class UpDownDTO {

    private int updownPk;           //PK
    private int updownReplyBoardPk; //게시판 PK 참조
    private String updownMemberId;  //작성자 아이디
    private String isUp;           //업 or 다운
    
    
    public UpDownDTO() {}
    
    public UpDownDTO(int updownPk, int updownReplyBoardPk, String updownMemberId, String isUp) {
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

    public String getIsUp() {
        return isUp;
    }

    public void setIsUp(String isUp) {
        this.isUp = isUp;
    }

}
