package edu.kosta.kdc.model.dto;

public class PageDTO {

    private int page;       //현재페이지(VIEW에서 가져온다.)
    private int countList;  //한페이지에 출력될 게시물 수
    private int countPage;  //한 화면에 출력될 페이지 수
    private int totalCount; //total 컬럼 수 (DB에서 가져온다)
    private int totalPage;  //전체 페이지
    
    private int firstColumnRange;    //컬럼 첫번째 범위
    private int lastColumnRange;     //컬럼 마지막 범위
    
    //페이징 버튼 출력 여부
    private boolean firstMove;  //첫 페이지로 이동 at
    private boolean backPage;   //이전 페이지로 at
    private boolean nextPage;   //다음 페이지로 at
    private boolean lastMove;   //마지막 페이지로
    
    private int startPage;      //페이지 시작
    private int endPage;        //페이지 마지막
    
    public PageDTO() {}
    
    public PageDTO(int page, int countList, int countPage, int totalCount, int totalPage, int firstColumnRange,
            int lastColumnRange, boolean firstMove, boolean backPage, boolean nextPage, boolean lastMove, int startPage,
            int endPage) {
        super();
        this.page = page;
        this.countList = countList;
        this.countPage = countPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.firstColumnRange = firstColumnRange;
        this.lastColumnRange = lastColumnRange;
        this.firstMove = firstMove;
        this.backPage = backPage;
        this.nextPage = nextPage;
        this.lastMove = lastMove;
        this.startPage = startPage;
        this.endPage = endPage;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getCountList() {
        return countList;
    }
    public void setCountList(int countList) {
        this.countList = countList;
    }
    public int getCountPage() {
        return countPage;
    }
    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public boolean isFirstMove() {
        return firstMove;
    }
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
    public boolean isBackPage() {
        return backPage;
    }
    public void setBackPage(boolean backPage) {
        this.backPage = backPage;
    }
    public boolean isNextPage() {
        return nextPage;
    }
    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }
    public boolean isLastMove() {
        return lastMove;
    }
    public void setLastMove(boolean lastMove) {
        this.lastMove = lastMove;
    }
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getFirstColumnRange() {
        return firstColumnRange;
    }

    public void setFirstColumnRange(int firstColumnRange) {
        this.firstColumnRange = firstColumnRange;
    }

    public int getLastColumnRange() {
        return lastColumnRange;
    }

    public void setLastColumnRange(int lastColumnRange) {
        this.lastColumnRange = lastColumnRange;
    }
    
    
    
}
