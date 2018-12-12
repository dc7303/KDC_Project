package edu.kosta.kdc.model.dto;

public class PortfolioDetailDTO {

    private int portFolioDetailPk;              //포트폴리오 상세 PK
    private String portFolioDetailMemberId;     //포트폴리오 유저아이디 참조
    private String portfolioDetailProjectName;  //포트폴리오 프로젝트 이름
    private String portfolioDeltailProjectImage;//포트폴리오 이미지
    private String portfolioDetailDescription;  //포트폴리오 설명
    private boolean portfolioDetailIsDelete;    //삭제여부
    
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
