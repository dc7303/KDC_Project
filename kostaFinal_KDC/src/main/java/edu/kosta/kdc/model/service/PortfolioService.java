package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioService {

    // 마이페이지 포트폴리오 삽입
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // 마이페이지 포트폴리오 조회(상세미포함)
    PortfolioDTO selectPortfolioByMemberId(String memberId);
    

    // 마이페이지 포트폴리오 수정
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // 마이페이지 포트폴리오 삭제
    int deletePortfolioByMemberId(String memberId);

    // 마이페이지 상세 삽입
    int insertDetail(PortfolioDetailDTO portfolioDetailDTO, String hashTagName);

    // 마이페이지 상세 조회(by memberid)
    List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId);

    // 마이페이지 상세 수정
    int updateDetail(PortfolioDetailDTO portfolioDetailDTO, String hashTagName);

    // 마이페이지 상세 삭제(by PortfolioDetail_pk)
    int deleteDetail(int PortfolioDetailPk);

    // 마이페이지 상세 조회(by pk)
    PortfolioDetailDTO selectDetailByPk(int detailPk);
    
    // 포트폴리오 조회(상세포함)
    PortfolioDTO selectAllDetail(String memberId);
    
    // 게시된 모든 포트폴리오 조회
    List<PortfolioDTO> selectAll();
<<<<<<< HEAD
=======
    
    // 조건으로 검색
    List<PortfolioDTO> selectByKeyword(String keyfield, String keyword);
    
    
>>>>>>> MergBranch
}
