package edu.kosta.kdc.model.dto;

public class UpDownDTO {

    private int updownPk;           //PK
    private int updownReplyBoardPk; //�Խ��� PK ����
    private String updownMemberId;  //�ۼ��� ���̵�
    private String isUp;           //�� or �ٿ�
    
    
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
