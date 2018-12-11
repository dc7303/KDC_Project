package edu.kosta.kdc.dto;

public class MessageDTO {
    
    private int messageNum;          //쪽지번호
    private String senderId;         //보낸사람 아이디
    private String receiverId;       //받는사람 아이디
    private String messageTitle;     //쪽지 제목
    private String messageContents;  //쪽지 내용
    private String messageDate;      //전송일
    private boolean messageIsRead;   //읽음 여부
    private boolean messageIsDelete; //삭제 여부
    
    public MessageDTO() {}
    
    public MessageDTO(int messageNum, String senderId, String receiverId, String messageTitle, String messageContents,
            String messageDate, boolean messageIsRead, boolean messageIsDelete) {
        super();
        this.messageNum = messageNum;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageTitle = messageTitle;
        this.messageContents = messageContents;
        this.messageDate = messageDate;
        this.messageIsRead = messageIsRead;
        this.messageIsDelete = messageIsDelete;
    }
    public int getMessageNum() {
        return messageNum;
    }
    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    public String getMessageTitle() {
        return messageTitle;
    }
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }
    public String getMessageContents() {
        return messageContents;
    }
    public void setMessageContents(String messageContents) {
        this.messageContents = messageContents;
    }
    public String getMessageDate() {
        return messageDate;
    }
    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }
    public boolean isMessageIsRead() {
        return messageIsRead;
    }
    public void setMessageIsRead(boolean messageIsRead) {
        this.messageIsRead = messageIsRead;
    }
    public boolean isMessageIsDelete() {
        return messageIsDelete;
    }
    public void setMessageIsDelete(boolean messageIsDelete) {
        this.messageIsDelete = messageIsDelete;
    }
    
    
}
