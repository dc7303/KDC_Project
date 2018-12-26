package edu.kosta.kdc.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PortfolioDetailDTO {

    private int portFolioDetailPk; // 포트폴리오 상세 PK
    private String portFolioDetailMemberId; // 포트폴리오 유저아이디 참조
    private String portfolioDetailProjectName; // 포트폴리오 프로젝트 이름
    private String portfolioDeltailProjectImage;// 포트폴리오 이미지
    private String portfolioDetailDescription; // 포트폴리오 설명
    private String portfolioDetailDate; //추가시킴 날짜순 정렬의 필요
    private boolean portfolioDetailIsDelete; // 삭제여부

    // 폼입력 때문에 생성 기존 image필드 하나로 대체될 수 있을지 고민
    private MultipartFile DeltailProjectImage;
    
    // 포트폴리오 상세에 포함된 해쉬태그
    private List<HashTagDTO> portfolioDetailHashTagList;

    public PortfolioDetailDTO() {
    }

    public PortfolioDetailDTO(int portFolioDetailPk, String portFolioDetailMemberId, String portfolioDetailProjectName,
            String portfolioDeltailProjectImage, String portfolioDetailDescription, String portfolioDetailDate,
            boolean portfolioDetailIsDelete, MultipartFile deltailProjectImage,
            List<HashTagDTO> portfolioDetailHashTagList) {
        super();
        this.portFolioDetailPk = portFolioDetailPk;
        this.portFolioDetailMemberId = portFolioDetailMemberId;
        this.portfolioDetailProjectName = portfolioDetailProjectName;
        this.portfolioDeltailProjectImage = portfolioDeltailProjectImage;
        this.portfolioDetailDescription = portfolioDetailDescription;
        this.portfolioDetailDate = portfolioDetailDate;
        this.portfolioDetailIsDelete = portfolioDetailIsDelete;
        DeltailProjectImage = deltailProjectImage;
        this.portfolioDetailHashTagList = portfolioDetailHashTagList;
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

    public String getPortfolioDetailDate() {
        return portfolioDetailDate;
    }

    public void setPortfolioDetailDate(String portfolioDetailDate) {
        this.portfolioDetailDate = portfolioDetailDate;
    }

    public boolean isPortfolioDetailIsDelete() {
        return portfolioDetailIsDelete;
    }

    public void setPortfolioDetailIsDelete(boolean portfolioDetailIsDelete) {
        this.portfolioDetailIsDelete = portfolioDetailIsDelete;
    }

    public MultipartFile getDeltailProjectImage() {
        return DeltailProjectImage;
    }

    public void setDeltailProjectImage(MultipartFile deltailProjectImage) {
        DeltailProjectImage = deltailProjectImage;
    }

    public List<HashTagDTO> getPortfolioDetailHashTagList() {
        return portfolioDetailHashTagList;
    }

    public void setPortfolioDetailHashTagList(List<HashTagDTO> portfolioDetailHashTagList) {
        this.portfolioDetailHashTagList = portfolioDetailHashTagList;
    }

    
   
    

}
