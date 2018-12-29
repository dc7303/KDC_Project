package edu.kosta.kdc.model.dto;

public class PageDTO {

    private int page;       //����������(VIEW���� �����´�.)
    private int countList;  //���������� ��µ� �Խù� ��
    private int countPage;  //�� ȭ�鿡 ��µ� ������ ��
    private int totalCount; //total �÷� �� (DB���� �����´�)
    private int totalPage;  //��ü ������
    
    private int firstColumnRange;    //�÷� ù��° ����
    private int lastColumnRange;     //�÷� ������ ����
    
    //����¡ ��ư ��� ����
    private boolean firstMove;  //ù �������� �̵� at
    private boolean backPage;   //���� �������� at
    private boolean nextPage;   //���� �������� at
    private boolean lastMove;   //������ ��������
    
    private int startPage;      //������ ����
    private int endPage;        //������ ������
    
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
