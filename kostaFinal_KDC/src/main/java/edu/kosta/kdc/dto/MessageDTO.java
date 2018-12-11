package edu.kosta.kdc.dto;

public class MessageDTO {
    
    private int messageNum;          //������ȣ
    private String senderId;         //������� ���̵�
    private String receiverId;       //�޴»�� ���̵�
    private String messageTitle;     //���� ����
    private String messageContents;  //���� ����
    private String messageDate;      //������
    private boolean messageIsRead;   //���� ����
    private boolean messageIsDelete; //���� ����
    
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
