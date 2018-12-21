package edu.kosta.kdc.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class NoticeBoardDTO {



    private int noticeBoardPk;                  //게시판 PK
    private String noticeBoardClassification;   //게시판 속성(공지사항, 채용게시판, 반별공지사항)
    private String noticeBoardTitle;            //게시판 제목
    private String noticeBoardWriterId;         //작성자ID
    private String noticeBoardDate;             //작성일
    private String noticeBoardContents;         //내용
    private int noticeBoardViews;               //조회수
    private String noticeBoardAttachment;       //첨부파일
    private boolean noticeBoardIsDelete;        //삭제 여부
   
    private MultipartFile file;
    
    public NoticeBoardDTO() {}
    



    public NoticeBoardDTO(int noticeBoardPk, String noticeBoardClassification, String noticeBoardTitle,
            String noticeBoardWriterId, String noticeBoardDate, String noticeBoardContents, int noticeBoardViews,
            String noticeBoardAttachment, boolean noticeBoardIsDelete, MultipartFile file) {
        super();
        this.noticeBoardPk = noticeBoardPk;
        this.noticeBoardClassification = noticeBoardClassification;
        this.noticeBoardTitle = noticeBoardTitle;
        this.noticeBoardWriterId = noticeBoardWriterId;
        this.noticeBoardDate = noticeBoardDate;
        this.noticeBoardContents = noticeBoardContents;
        this.noticeBoardViews = noticeBoardViews;
        this.noticeBoardAttachment = noticeBoardAttachment;
        this.noticeBoardIsDelete = noticeBoardIsDelete;
        this.file = file;
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

    public boolean getNoticeBoardIsDelete() {
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


 
    
    
}
