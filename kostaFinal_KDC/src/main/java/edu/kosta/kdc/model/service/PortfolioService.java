package edu.kosta.kdc.model.service;

import edu.kosta.kdc.model.dto.PortfolioDTO;

public interface PortfolioService {

    // 삽입
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // 조회
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // 수정
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // 삭제
    int deletePortfolioByMemberId(String memberId);

    // ID에 해당하는 포트폴리오가 이미 존재하는지select
    boolean selectByMemberId(String memberId);
}
