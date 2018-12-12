package edu.kosta.kdc.model.dto;

public class HashTagDTO {

    private int hashTagPk;                  //�ؽ��±� pk
    private int hashTagReplyBoardPk;        //�Խ��� pk�� ����
    private int hashTagPortfolioDetailPk;   //�ؽ��±� pk�� ����
    private String hashTagName;             //�ؽ��±� �̸�
    private boolean hashTagIsDelete;        //��������
    
    public HashTagDTO() {}
    
    public HashTagDTO(int hashTagPk, int hashTagReplyBoardPk, int hashTagPortfolioDetailPk, String hashTagName,
            boolean hashTagIsDelete) {
        super();
        this.hashTagPk = hashTagPk;
        this.hashTagReplyBoardPk = hashTagReplyBoardPk;
        this.hashTagPortfolioDetailPk = hashTagPortfolioDetailPk;
        this.hashTagName = hashTagName;
        this.hashTagIsDelete = hashTagIsDelete;
    }

    public int getHashTagPk() {
        return hashTagPk;
    }

    public void setHashTagPk(int hashTagPk) {
        this.hashTagPk = hashTagPk;
    }

    public int getHashTagReplyBoardPk() {
        return hashTagReplyBoardPk;
    }

    public void setHashTagReplyBoardPk(int hashTagReplyBoardPk) {
        this.hashTagReplyBoardPk = hashTagReplyBoardPk;
    }

    public int getHashTagPortfolioDetailPk() {
        return hashTagPortfolioDetailPk;
    }

    public void setHashTagPortfolioDetailPk(int hashTagPortfolioDetailPk) {
        this.hashTagPortfolioDetailPk = hashTagPortfolioDetailPk;
    }

    public String getHashTagName() {
        return hashTagName;
    }

    public void setHashTagName(String hashTagName) {
        this.hashTagName = hashTagName;
    }

    public boolean isHashTagIsDelete() {
        return hashTagIsDelete;
    }

    public void setHashTagIsDelete(boolean hashTagIsDelete) {
        this.hashTagIsDelete = hashTagIsDelete;
    }
    
    
    
    
}
