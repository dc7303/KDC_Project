package edu.kosta.kdc.dto;

public class PortfolioDTO {

    private String portFolioMemberId;           //��Ʈ������ ���� ���̵�
    private String portFolioMainImage;          //��Ʈ������ ���� �̹���
    private String portFolioMainTitle;          //��Ʈ������ ����
    private boolean portFolioVisibility;        //��������
    private boolean portFolioIsDelete;          //��������
    
    public PortfolioDTO() {}
    
    public PortfolioDTO(String portFolioMemberId, String portFolioMainImage, String portFolioMainTitle,
            boolean portFolioVisibility, boolean portFolioIsDelete) {
        super();
        this.portFolioMemberId = portFolioMemberId;
        this.portFolioMainImage = portFolioMainImage;
        this.portFolioMainTitle = portFolioMainTitle;
        this.portFolioVisibility = portFolioVisibility;
        this.portFolioIsDelete = portFolioIsDelete;
    }
    public String getPortFolioMemberId() {
        return portFolioMemberId;
    }
    public void setPortFolioMemberId(String portFolioMemberId) {
        this.portFolioMemberId = portFolioMemberId;
    }
    public String getPortFolioMainImage() {
        return portFolioMainImage;
    }
    public void setPortFolioMainImage(String portFolioMainImage) {
        this.portFolioMainImage = portFolioMainImage;
    }
    public String getPortFolioMainTitle() {
        return portFolioMainTitle;
    }
    public void setPortFolioMainTitle(String portFolioMainTitle) {
        this.portFolioMainTitle = portFolioMainTitle;
    }
    public boolean isPortFolioVisibility() {
        return portFolioVisibility;
    }
    public void setPortFolioVisibility(boolean portFolioVisibility) {
        this.portFolioVisibility = portFolioVisibility;
    }
    public boolean isPortFolioIsDelete() {
        return portFolioIsDelete;
    }
    public void setPortFolioIsDelete(boolean portFolioIsDelete) {
        this.portFolioIsDelete = portFolioIsDelete;
    }
    
    
    
}
