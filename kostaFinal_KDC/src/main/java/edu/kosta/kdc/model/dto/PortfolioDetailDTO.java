package edu.kosta.kdc.model.dto;

public class PortfolioDetailDTO {

    private int portFolioDetailPk;              //��Ʈ������ �� PK
    private String portFolioDetailMemberId;     //��Ʈ������ �������̵� ����
    private String portfolioDetailProjectName;  //��Ʈ������ ������Ʈ �̸�
    private String portfolioDeltailProjectImage;//��Ʈ������ �̹���
    private String portfolioDetailDescription;  //��Ʈ������ ����
    private boolean portfolioDetailIsDelete;    //��������
    
    public PortfolioDetailDTO() {}
    
    public PortfolioDetailDTO(int portFolioDetailPk, String portFolioDetailMemberId, String portfolioDetailProjectName,
            String portfolioDeltailProjectImage, String portfolioDetailDescription, boolean portfolioDetailIsDelete) {
        super();
        this.portFolioDetailPk = portFolioDetailPk;
        this.portFolioDetailMemberId = portFolioDetailMemberId;
        this.portfolioDetailProjectName = portfolioDetailProjectName;
        this.portfolioDeltailProjectImage = portfolioDeltailProjectImage;
        this.portfolioDetailDescription = portfolioDetailDescription;
        this.portfolioDetailIsDelete = portfolioDetailIsDelete;
    }
    public int getPortFolioDetailPk() {
        return portFolioDetailPk;
    }
    public void setPortFolioDetailPk(int portFolioDetailPk) {
        this.portFolioDetailPk = portFolioDetailPk;
    }
    public String getPortFolioDetailMemberId() {
        return portFolioDetailMemberId;
    }
    public void setPortFolioDetailMemberId(String portFolioDetailMemberId) {
        this.portFolioDetailMemberId = portFolioDetailMemberId;
    }
    public String getPortfolioDetailProjectName() {
        return portfolioDetailProjectName;
    }
    public void setPortfolioDetailProjectName(String portfolioDetailProjectName) {
        this.portfolioDetailProjectName = portfolioDetailProjectName;
    }
    public String getPortfolioDeltailProjectImage() {
        return portfolioDeltailProjectImage;
    }
    public void setPortfolioDeltailProjectImage(String portfolioDeltailProjectImage) {
        this.portfolioDeltailProjectImage = portfolioDeltailProjectImage;
    }
    public String getPortfolioDetailDescription() {
        return portfolioDetailDescription;
    }
    public void setPortfolioDetailDescription(String portfolioDetailDescription) {
        this.portfolioDetailDescription = portfolioDetailDescription;
    }
    public boolean isPortfolioDetailIsDelete() {
        return portfolioDetailIsDelete;
    }
    public void setPortfolioDetailIsDelete(boolean portfolioDetailIsDelete) {
        this.portfolioDetailIsDelete = portfolioDetailIsDelete;
    }
    
    
    
}
