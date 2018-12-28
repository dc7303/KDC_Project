package edu.kosta.kdc.util;

import org.springframework.stereotype.Component;

import edu.kosta.kdc.model.dto.PageDTO;
import edu.kosta.kdc.util.interfaces.PageHandler;

@Component
public class PageHandlerImpl implements PageHandler {

    /**
     * 현재 페이지, 한페이지에 출력될 게시물 수, 한페이제 출력될 페이지 수, 총 레코드 수를 셋팅하여
     * 뷰에 뿌려질 페이징을 셋팅해주는 모듈
     * 
     * 
     * @return PageDTO
     */
    @Override
    public PageDTO pageInfoSet(int setPage, int setCountList, int setCountPage, int setTotalCount) {
        
        //현재 페이지
        //setPage가 0일 경우 디폴트 1
        int page = 1;
        if(setPage == 0) {
            page = setPage;
        }
        
        //한페이지에 출력될 게시물 수
        //5이하일 경우 디폴트 5로 설정
        int countList = 5;
        if(setCountList < 5) {
            countList = setCountList;
        }

        //한 화면에 출력될 페이지 수
        int countPage = 5;
        if(setCountPage < 5) {
            countPage = setCountPage;
        }
        
        //총 컬럼 수
        int totalCount = 0;
        if(setTotalCount > 0) {
            totalCount = setTotalCount;
        }
        
        int totalPage = totalCount / countList;     //전체 페이지
        
        boolean firstMove = false;      //첫 페이지로 이동하는 at 생성 여부
        boolean backPage = false;       //이전 페이지로
        boolean nextPage = false;       //다음 페이지로
        boolean lastMove = false;       //마지막 페이지로
        
        //컬럼이 255개일 경우 출력될 게시물 수 와 % 연산자 사용시 5개의 남는 컬럼이 발생함.
        //이를 해결하기위해 % 연산하여 0보다 클 경우 페이지 수를 +1 해준다.
        if(totalCount % countList > 0) {
            totalPage++;
        }
        
        //현재 페이지가 총 페이지보다 크다면 현재 페이지를 마지막 최종페이지 값으로.
        if(totalPage < page) {
            page = totalPage;
        }
        
        //페이지 시작 
        int startPage = ((page - 1) / 10) * 10 + 1;
        
        //마지막 페이지
        int endPage = startPage + countPage -1;
        
        //마지막 페이지가 totalPage보다 크면 안되기 때문에 end페이지가 클 경우 맞춘다.
        if(endPage > totalPage) {
            endPage = totalPage;
        }
        
        //startPage가 1이 아닐 경우 처음으로 이동하는 at을 추가할 수 있게 해준다.
        if(startPage > 1 || page > 1) {
            firstMove = true;
        }
        
        //페이지가 1이 아닌 경우 이전페이지로 이동해주는 at을 추가하게 해준다.
        if(page > 1) {
            backPage = true;
        }

        //<다음> 셋팅
        if(page < totalPage) {
            nextPage = true;
        }
        
        //마지막 페이지 셋팅
        if(endPage < totalPage) {
            lastMove = true;
        }
        
        
        return new PageDTO(page, countList, countPage, totalCount, totalPage, firstMove, backPage, nextPage, lastMove, startPage, endPage);
    }
}
