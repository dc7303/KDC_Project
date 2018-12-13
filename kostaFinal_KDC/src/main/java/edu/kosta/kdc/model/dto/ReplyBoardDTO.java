package edu.kosta.kdc.model.dto;

public class ReplyBoardDTO {

    private int replyBoardPk;                       //게시판 PK
    private String replyBoardClassification;        //게시판 속성(댓글, 좋아요 기능이 들어간 게시판)
    private int replyBoardIsReply;                  //게시판인지 댓글인지.
    private String replyBoardTitle;                 //게시판 제목
    private String replyBoardWriterId;              //작성자 아이디
    private String replyBoardDate;                  //작성일
    private String replyBoardContents;              //게시글 내용
    private int replyBoardViews;                    //조회수
    private String replyBoardMention;               //댓글 태그할 아이디
    private String replyBoardIsDelete;             //삭제여부
    private int likeNum;                           //좋아요수
    private int replyNum;                          //댓글수
    
    //1:1인경우
    private MemberDTO member;
    private UpDownDTO updown;
    
    
    public ReplyBoardDTO() {}
    
    public ReplyBoardDTO(int replyBoardPk, String replyBoardClassification, int replyBoardIsReply,
            String replyBoardTitle, String replyBoardWriterId, String replyBoardDate, String replyBoardContents,
            int replyBoardViews, String replyBoardMention, String replyBoardIsDelete, int likeNum, int replyNum,
            MemberDTO member, UpDownDTO updown) {
        super();
        this.replyBoardPk = replyBoardPk;
        this.replyBoardClassification = replyBoardClassification;
        this.replyBoardIsReply = replyBoardIsReply;
        this.replyBoardTitle = replyBoardTitle;
        this.replyBoardWriterId = replyBoardWriterId;
        this.replyBoardDate = replyBoardDate;
        this.replyBoardContents = replyBoardContents;
        this.replyBoardViews = replyBoardViews;
        this.replyBoardMention = replyBoardMention;
        this.replyBoardIsDelete = replyBoardIsDelete;
        this.likeNum = likeNum;
        this.replyNum = replyNum;
        this.member = member;
        this.updown = updown;
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
    
    public int getReplyBoardIsReply() {
        return replyBoardIsReply;
    }
    public void setReplyBoardIsReply(int replyBoardIsReply) {
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
    
    public void setReplyBoardIsDelete(String replyBoardIsDelete) {
        this.replyBoardIsDelete = replyBoardIsDelete;
    }
    public String getReplyBoardIsDelete() {
        return replyBoardIsDelete;
    }
    
    public int getLikeNum() {
        return likeNum;
    }
    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
    
    public int getReplyNum() {
        return replyNum;
    }
    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public MemberDTO getMember() {
        return member;
    }
    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public UpDownDTO getUpdown() {
        return updown;
    }
    public void setUpdown(UpDownDTO updown) {
        this.updown = updown;
    }
}
