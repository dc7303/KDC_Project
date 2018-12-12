package edu.kosta.kdc.model.dto;

public class NoticeBoardDTO {

    private int noticeBoardPk;                  //�Խ��� PK
    private String noticeBoardClassification;   //�Խ��� �Ӽ�(��������, ä��Խ���, �ݺ���������)
    private int noticeBoardNum;                 //�Խ��� ��ȣ
    private String noticeBoardTitle;            //�Խ��� ����
    private String noticeBoardWriterId;         //�ۼ���ID
    private String noticeBoardDate;             //�ۼ���
    private String noticeBoardContents;         //����
    private int noticeBoardViews;               //��ȸ��
    private String noticeBoardAttachment;       //÷������
    private boolean noticeBoardIsDelete;        //���� ����
    
    public NoticeBoardDTO() {}
    
    public NoticeBoardDTO(int noticeBoardPk, String noticeBoardClassification, int noticeBoardNum,
            String noticeBoardTitle, String noticeBoardWriterId, String noticeBoardDate, String noticeBoardContents,
            int noticeBoardViews, String noticeBoardAttachment, boolean noticeBoardIsDelete) {
        super();
        this.noticeBoardPk = noticeBoardPk;
        this.noticeBoardClassification = noticeBoardClassification;
        this.noticeBoardNum = noticeBoardNum;
        this.noticeBoardTitle = noticeBoardTitle;
        this.noticeBoardWriterId = noticeBoardWriterId;
        this.noticeBoardDate = noticeBoardDate;
        this.noticeBoardContents = noticeBoardContents;
        this.noticeBoardViews = noticeBoardViews;
        this.noticeBoardAttachment = noticeBoardAttachment;
        this.noticeBoardIsDelete = noticeBoardIsDelete;
    }

    public int getNoticeBoardPk() {
        return noticeBoardPk;
    }

    public void setNoticeBoardPk(int noticeBoardPk) {
        this.noticeBoardPk = noticeBoardPk;
    }

    public String getNoticeBoardClassification() {
        return noticeBoardClassification;
    }

    public void setNoticeBoardClassification(String noticeBoardClassification) {
        this.noticeBoardClassification = noticeBoardClassification;
    }

    public int getNoticeBoardNum() {
        return noticeBoardNum;
    }

    public void setNoticeBoardNum(int noticeBoardNum) {
        this.noticeBoardNum = noticeBoardNum;
    }

    public String getNoticeBoardTitle() {
        return noticeBoardTitle;
    }

    public void setNoticeBoardTitle(String noticeBoardTitle) {
        this.noticeBoardTitle = noticeBoardTitle;
    }

    public String getNoticeBoardWriterId() {
        return noticeBoardWriterId;
    }

    public void setNoticeBoardWriterId(String noticeBoardWriterId) {
        this.noticeBoardWriterId = noticeBoardWriterId;
    }

    public String getNoticeBoardDate() {
        return noticeBoardDate;
    }

    public void setNoticeBoardDate(String noticeBoardDate) {
        this.noticeBoardDate = noticeBoardDate;
    }

    public String getNoticeBoardContents() {
        return noticeBoardContents;
    }

    public void setNoticeBoardContents(String noticeBoardContents) {
        this.noticeBoardContents = noticeBoardContents;
    }

    public int getNoticeBoardViews() {
        return noticeBoardViews;
    }

    public void setNoticeBoardViews(int noticeBoardViews) {
        this.noticeBoardViews = noticeBoardViews;
    }

    public String getNoticeBoardAttachment() {
        return noticeBoardAttachment;
    }

    public void setNoticeBoardAttachment(String noticeBoardAttachment) {
        this.noticeBoardAttachment = noticeBoardAttachment;
    }

    public boolean isNoticeBoardIsDelete() {
        return noticeBoardIsDelete;
    }

    public void setNoticeBoardIsDelete(boolean noticeBoardIsDelete) {
        this.noticeBoardIsDelete = noticeBoardIsDelete;
    }
    
    
    
    
}
