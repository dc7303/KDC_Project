package edu.kosta.kdc.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PortfolioDTO {

    private String portFolioMemberId;           //포트폴리오 유저 아이디
    private String portFolioMainImage;          //포트폴리오 메인 이미지(file.originalName)
    private String portFolioMainTitle;          //포트폴리오 제목
    private Boolean portFolioVisibility;        //공개여부
    private Boolean portFolioIsDelete;          //삭제여부
    
    //폼입력 때문에 생성 기존 image필드 하나로 대체될 수 있을지 고민
    private MultipartFile MainImageFile;
    
    //포트폴리오 상세와 join한 DTO들
    private List<PortfolioDetailDTO> portFolioDetailList;
    
    //포트폴리오에 매핑된 유저
    private String portFolioMemberName;
    private String portFolioMemberNickName;
    private String portFolioMemberEmail;
    private String portFolioMemberPhoneNumber;
    
    public PortfolioDTO() {}

    public PortfolioDTO(String portFolioMemberId, String portFolioMainImage, String portFolioMainTitle,
            Boolean portFolioVisibility, Boolean portFolioIsDelete, MultipartFile mainImageFile,
            List<PortfolioDetailDTO> portFolioDetailList, String portFolioMemberName, String portFolioMemberNickName,
            String portFolioMemberEmail, String portFolioMemberPhoneNumber) {
        super();
        this.portFolioMemberId = portFolioMemberId;
        this.portFolioMainImage = portFolioMainImage;
        this.portFolioMainTitle = portFolioMainTitle;
        this.portFolioVisibility = portFolioVisibility;
        this.portFolioIsDelete = portFolioIsDelete;
        MainImageFile = mainImageFile;
        this.portFolioDetailList = portFolioDetailList;
        this.portFolioMemberName = portFolioMemberName;
        this.portFolioMemberNickName = portFolioMemberNickName;
        this.portFolioMemberEmail = portFolioMemberEmail;
        this.portFolioMemberPhoneNumber = portFolioMemberPhoneNumber;
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

    public Boolean getPortFolioVisibility() {
        return portFolioVisibility;
    }

    public void setPortFolioVisibility(Boolean portFolioVisibility) {
        this.portFolioVisibility = portFolioVisibility;
    }

    public Boolean getPortFolioIsDelete() {
        return portFolioIsDelete;
    }

    public void setPortFolioIsDelete(Boolean portFolioIsDelete) {
        this.portFolioIsDelete = portFolioIsDelete;
    }

    public MultipartFile getMainImageFile() {
        return MainImageFile;
    }

    public void setMainImageFile(MultipartFile mainImageFile) {
        MainImageFile = mainImageFile;
    }

    public List<PortfolioDetailDTO> getPortFolioDetailList() {
        return portFolioDetailList;
    }

    public void setPortFolioDetailList(List<PortfolioDetailDTO> portFolioDetailList) {
        this.portFolioDetailList = portFolioDetailList;
    }

    public String getPortFolioMemberName() {
        return portFolioMemberName;
    }

    public void setPortFolioMemberName(String portFolioMemberName) {
        this.portFolioMemberName = portFolioMemberName;
    }

    public String getPortFolioMemberNickName() {
        return portFolioMemberNickName;
    }

    public void setPortFolioMemberNickName(String portFolioMemberNickName) {
        this.portFolioMemberNickName = portFolioMemberNickName;
    }

    public String getPortFolioMemberEmail() {
        return portFolioMemberEmail;
    }

    public void setPortFolioMemberEmail(String portFolioMemberEmail) {
        this.portFolioMemberEmail = portFolioMemberEmail;
    }

    public String getPortFolioMemberPhoneNumber() {
        return portFolioMemberPhoneNumber;
    }

    public void setPortFolioMemberPhoneNumber(String portFolioMemberPhoneNumber) {
        this.portFolioMemberPhoneNumber = portFolioMemberPhoneNumber;
    }

    
    
}
