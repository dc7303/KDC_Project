package edu.kosta.kdc.dto;

public class ReplyBoardDTO {

    private int replyBoardPk;                       //게시판 PK
    private String replyBoardClassification;        //게시판 속성(댓글, 좋아요 기능이 들어간 게시판)
    private int replyBoardNum;                      //게시판 번호
    private boolean replyBoardIsReply;              //게시판인지 댓글인지.
    private String replyBoardTitle;                 //게시판 제목
    private String replyBoardWriterId;              //작성자 아이디
    private String replyBoardDate;                  //작성일
    private String replyBoardContents;              //게시글 내용
    private int replyBoardViews;                    //조회수
    private String replyBoardMention;               //댓글 태그할 아이디
    private boolean replyBoardIsDelete;             //삭제여부
    
    
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
