package edu.kosta.kdc.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class NoticeBoardDTO {



    private int noticeBoardPk;                  //�Խ��� PK
    private String noticeBoardClassification;   //�Խ��� �Ӽ�(��������, ä��Խ���, �ݺ���������)
    private String noticeBoardClassRoomCode;    //Ŭ������ �ڵ�
    private String noticeBoardTitle;            //�Խ��� ����
    private String noticeBoardWriterId;         //�ۼ���ID
    private String noticeBoardDate;             //�ۼ���
    private String noticeBoardContents;         //����
    private int noticeBoardViews;               //��ȸ��
    private String noticeBoardAttachment;       //÷������
    private boolean noticeBoardIsDelete;        //���� ����
    private String authName;                    //�Խñ۾� ����� ����

    private MemberDTO member;
   
    private MultipartFile file;
    
    public NoticeBoardDTO() {}

    public NoticeBoardDTO(int noticeBoardPk, String noticeBoardClassification, String noticeBoardClassRoomCode,
            String noticeBoardTitle, String noticeBoardWriterId, String noticeBoardDate, String noticeBoardContents,
            int noticeBoardViews, String noticeBoardAttachment, boolean noticeBoardIsDelete, MultipartFile file, String authName, MemberDTO member) {
        super();
        this.noticeBoardPk = noticeBoardPk;
        this.noticeBoardClassification = noticeBoardClassification;
        this.noticeBoardClassRoomCode = noticeBoardClassRoomCode;
        this.noticeBoardTitle = noticeBoardTitle;
        this.noticeBoardWriterId = noticeBoardWriterId;
        this.noticeBoardDate = noticeBoardDate;
        this.noticeBoardContents = noticeBoardContents;
        this.noticeBoardViews = noticeBoardViews;
        this.noticeBoardAttachment = noticeBoardAttachment;
        this.noticeBoardIsDelete = noticeBoardIsDelete;
        this.file = file;
        this.authName = authName;
        this.member = member;
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

    public String getNoticeBoardClassRoomCode() {
        return noticeBoardClassRoomCode;
    }

    public void setNoticeBoardClassRoomCode(String noticeBoardClassRoomCode) {
        this.noticeBoardClassRoomCode = noticeBoardClassRoomCode;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }
    
}
