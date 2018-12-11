package edu.kosta.kdc.dto;

public class UpDownDTO {

    private int updownPk;           //PK
    private int updownReplyBoardPk; //�Խ��� PK ����
    private String updownMemberId;  //�ۼ��� ���̵�
    private boolean isUp;           //�� or �ٿ�
    
    
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
