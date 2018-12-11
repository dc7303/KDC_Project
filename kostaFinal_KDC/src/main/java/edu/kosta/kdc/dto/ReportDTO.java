package edu.kosta.kdc.dto;

public class ReportDTO {

    private int reportPk;               //�Ű� ��ȣPK
    private int reportReplyBoardPk;     //�Խ��� PK ����
    private String reportReporterId;    //�Ű��� ���̵�
    private String reportDate;          //�Ű���
    private String reportPurpose;       //�Ű� ����
    private boolean reportIsDelete;     //��������
    
    public ReportDTO() {}
    
    public ReportDTO(int reportPk, int reportReplyBoardPk, String reportReporterId, String reportDate,
            String reportPurpose, boolean reportIsDelete) {
        super();
        this.reportPk = reportPk;
        this.reportReplyBoardPk = reportReplyBoardPk;
        this.reportReporterId = reportReporterId;
        this.reportDate = reportDate;
        this.reportPurpose = reportPurpose;
        this.reportIsDelete = reportIsDelete;
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
    
    
}
