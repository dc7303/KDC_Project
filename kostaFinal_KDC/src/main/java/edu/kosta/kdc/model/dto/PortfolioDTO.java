package edu.kosta.kdc.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PortfolioDTO {

    private String portFolioMemberId;           //��Ʈ������ ���� ���̵�
    private String portFolioMainImage;          //��Ʈ������ ���� �̹���(file.originalName)
    private String portFolioMainTitle;          //��Ʈ������ ����
    private Boolean portFolioVisibility;        //��������
    private Boolean portFolioIsDelete;          //��������
    
    //���Է� ������ ���� ���� image�ʵ� �ϳ��� ��ü�� �� ������ ���
    private MultipartFile MainImageFile;
    
    //��Ʈ������ �󼼿� join�� DTO��
    private List<PortfolioDetailDTO> portFolioDetailList;
    
    
    public PortfolioDTO() {}

    public PortfolioDTO(String portFolioMemberId, String portFolioMainImage, String portFolioMainTitle,
            Boolean portFolioVisibility, Boolean portFolioIsDelete, MultipartFile mainImageFile,
            List<PortfolioDetailDTO> portFolioDetailList) {
        super();
        this.portFolioMemberId = portFolioMemberId;
        this.portFolioMainImage = portFolioMainImage;
        this.portFolioMainTitle = portFolioMainTitle;
        this.portFolioVisibility = portFolioVisibility;
        this.portFolioIsDelete = portFolioIsDelete;
        MainImageFile = mainImageFile;
        this.portFolioDetailList = portFolioDetailList;
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

    public List<PortfolioDetailDTO> getportFolioDetailList() {
        return portFolioDetailList;
    }

    public void setportFolioDetailList(List<PortfolioDetailDTO> portFolioDetailList) {
        this.portFolioDetailList = portFolioDetailList;
    }

    @Override
    public String toString() {
        return "PortfolioDTO [portFolioMemberId=" + portFolioMemberId + ", portFolioMainImage=" + portFolioMainImage
                + ", portFolioMainTitle=" + portFolioMainTitle + ", portFolioVisibility=" + portFolioVisibility
                + ", portFolioIsDelete=" + portFolioIsDelete + ", MainImageFile=" + MainImageFile
                +" ]";
    }

    
    

    
        
    
}
