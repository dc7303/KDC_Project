package edu.kosta.kdc.dto;

public class ReplyBoardDTO {

    private int replyBoardPk;                       //�Խ��� PK
    private String replyBoardClassification;        //�Խ��� �Ӽ�(���, ���ƿ� ����� �� �Խ���)
    private int replyBoardNum;                      //�Խ��� ��ȣ
    private boolean replyBoardIsReply;              //�Խ������� �������.
    private String replyBoardTitle;                 //�Խ��� ����
    private String replyBoardWriterId;              //�ۼ��� ���̵�
    private String replyBoardDate;                  //�ۼ���
    private String replyBoardContents;              //�Խñ� ����
    private int replyBoardViews;                    //��ȸ��
    private String replyBoardMention;               //��� �±��� ���̵�
    private boolean replyBoardIsDelete;             //��������
    
    
    public ReplyBoardDTO() {}
    
    public ReplyBoardDTO(int replyBoardPk, String replyBoardClassification, int replyBoardNum,
            boolean replyBoardIsReply, String replyBoardTitle, String replyBoardWriterId, String replyBoardDate,
            String replyBoardContents, int replyBoardViews, String replyBoardMention, boolean replyBoardIsDelete) {
        super();
        this.replyBoardPk = replyBoardPk;
        this.replyBoardClassification = replyBoardClassification;
        this.replyBoardNum = replyBoardNum;
        this.replyBoardIsReply = replyBoardIsReply;
        this.replyBoardTitle = replyBoardTitle;
        this.replyBoardWriterId = replyBoardWriterId;
        this.replyBoardDate = replyBoardDate;
        this.replyBoardContents = replyBoardContents;
        this.replyBoardViews = replyBoardViews;
        this.replyBoardMention = replyBoardMention;
        this.replyBoardIsDelete = replyBoardIsDelete;
    }
    public int getReplyBoardPk() {
        return replyBoardPk;
    }
    public void setReplyBoardPk(int replyBoardPk) {
        this.replyBoardPk = replyBoardPk;
    }
    public String getReplyBoardClassification() {
        return replyBoardClassification;
    }
    public void setReplyBoardClassification(String replyBoardClassification) {
        this.replyBoardClassification = replyBoardClassification;
    }
    public int getReplyBoardNum() {
        return replyBoardNum;
    }
    public void setReplyBoardNum(int replyBoardNum) {
        this.replyBoardNum = replyBoardNum;
    }
    public boolean isReplyBoardIsReply() {
        return replyBoardIsReply;
    }
    public void setReplyBoardIsReply(boolean replyBoardIsReply) {
        this.replyBoardIsReply = replyBoardIsReply;
    }
    public String getReplyBoardTitle() {
        return replyBoardTitle;
    }
    public void setReplyBoardTitle(String replyBoardTitle) {
        this.replyBoardTitle = replyBoardTitle;
    }
    public String getReplyBoardWriterId() {
        return replyBoardWriterId;
    }
    public void setReplyBoardWriterId(String replyBoardWriterId) {
        this.replyBoardWriterId = replyBoardWriterId;
    }
    public String getReplyBoardDate() {
        return replyBoardDate;
    }
    public void setReplyBoardDate(String replyBoardDate) {
        this.replyBoardDate = replyBoardDate;
    }
    public String getReplyBoardContents() {
        return replyBoardContents;
    }
    public void setReplyBoardContents(String replyBoardContents) {
        this.replyBoardContents = replyBoardContents;
    }
    public int getReplyBoardViews() {
        return replyBoardViews;
    }
    public void setReplyBoardViews(int replyBoardViews) {
        this.replyBoardViews = replyBoardViews;
    }
    public String getReplyBoardMention() {
        return replyBoardMention;
    }
    public void setReplyBoardMention(String replyBoardMention) {
        this.replyBoardMention = replyBoardMention;
    }
    public boolean isReplyBoardIsDelete() {
        return replyBoardIsDelete;
    }
    public void setReplyBoardIsDelete(boolean replyBoardIsDelete) {
        this.replyBoardIsDelete = replyBoardIsDelete;
    }
    
    
    
}
