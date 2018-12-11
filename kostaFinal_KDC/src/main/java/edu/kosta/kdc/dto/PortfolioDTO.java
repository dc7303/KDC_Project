package edu.kosta.kdc.dto;

public class PortfolioDTO {

    private String portFolioMemberId;           //포트폴리오 유저 아이디
    private String portFolioMainImage;          //포트폴리오 메인 이미지
    private String portFolioMainTitle;          //포트폴리오 제목
    private boolean portFolioVisibility;        //공개여부
    private boolean portFolioIsDelete;          //삭제여부
    
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
