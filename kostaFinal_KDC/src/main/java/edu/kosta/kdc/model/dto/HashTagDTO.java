package edu.kosta.kdc.model.dto;

public class HashTagDTO {

    private int hashTagPk;                  //해쉬태그 pk
    private int hashTagReplyBoardPk;        //게시판 pk값 참조
    private int hashTagPortfolioDetailPk;   //해쉬태그 pk값 참조
    private String hashTagName;             //해쉬태그 이름
    private boolean hashTagIsDelete;        //삭제여부
    
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
