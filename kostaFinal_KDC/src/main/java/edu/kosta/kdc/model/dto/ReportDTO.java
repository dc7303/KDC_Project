package edu.kosta.kdc.model.dto;

public class ReportDTO {

    private int reportPk;               //신고 번호PK
    private int reportReplyBoardPk;     //게시판 PK 참조
    private String reportReporterId;    //신고자 아이디
    private String reportDate;          //신고일
    private String reportPurpose;       //신고 내용
    private boolean reportIsDelete;     //삭제여부
    
    private int rnumOne;            //페이징 처리시 ROWNUM 잉여데이터
    private int rnumTwo;            //페이징 처리시 ROWNUM 잉여데이터
    
    
    //1:1인 경우
    private ReplyBoardDTO replyBoardDTO;
    
    public ReportDTO() {}
    
    public ReportDTO(int reportPk, int reportReplyBoardPk, String reportReporterId, String reportDate,
            String reportPurpose, boolean reportIsDelete, ReplyBoardDTO replyBoardDTO) {
        super();
        this.reportPk = reportPk;
        this.reportReplyBoardPk = reportReplyBoardPk;
        this.reportReporterId = reportReporterId;
        this.reportDate = reportDate;
        this.reportPurpose = reportPurpose;
        this.reportIsDelete = reportIsDelete;
        this.replyBoardDTO = replyBoardDTO;
    }
    
    
    public ReportDTO(int reportPk, int reportReplyBoardPk, String reportReporterId, String reportDate,
            String reportPurpose, boolean reportIsDelete, int rnumOne, int rnumTwo, ReplyBoardDTO replyBoardDTO) {
        super();
        this.reportPk = reportPk;
        this.reportReplyBoardPk = reportReplyBoardPk;
        this.reportReporterId = reportReporterId;
        this.reportDate = reportDate;
        this.reportPurpose = reportPurpose;
        this.reportIsDelete = reportIsDelete;
        this.rnumOne = rnumOne;
        this.rnumTwo = rnumTwo;
        this.replyBoardDTO = replyBoardDTO;
    }

    public int getReportPk() {
        return reportPk;
    }
    public void setReportPk(int reportPk) {
        this.reportPk = reportPk;
    }
    public int getReportReplyBoardPk() {
        return reportReplyBoardPk;
    }
    public void setReportReplyBoardPk(int reportReplyBoardPk) {
        this.reportReplyBoardPk = reportReplyBoardPk;
    }
    public String getReportReporterId() {
        return reportReporterId;
    }
    public void setReportReporterId(String reportReporterId) {
        this.reportReporterId = reportReporterId;
    }
    public String getReportDate() {
        return reportDate;
    }
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    public String getReportPurpose() {
        return reportPurpose;
    }
    public void setReportPurpose(String reportPurpose) {
        this.reportPurpose = reportPurpose;
    }
    public boolean isReportIsDelete() {
        return reportIsDelete;
    }
    public void setReportIsDelete(boolean reportIsDelete) {
        this.reportIsDelete = reportIsDelete;
    }

    public ReplyBoardDTO getReplyBoardDTO() {
        return replyBoardDTO;
    }

    public void setReplyBoardDTO(ReplyBoardDTO replyBoardDTO) {
        this.replyBoardDTO = replyBoardDTO;
    }

    public int getRnumOne() {
        return rnumOne;
    }

    public void setRnumOne(int rnumOne) {
        this.rnumOne = rnumOne;
    }

    public int getRnumTwo() {
        return rnumTwo;
    }

    public void setRnumTwo(int rnumTwo) {
        this.rnumTwo = rnumTwo;
    }
    
    
}
