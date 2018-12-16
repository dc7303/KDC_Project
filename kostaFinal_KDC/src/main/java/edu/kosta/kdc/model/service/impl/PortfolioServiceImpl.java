package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.kosta.kdc.model.dao.PortfolioDAO;
import edu.kosta.kdc.model.dao.PortfolioDetailDAO;
import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

@Service
public class PortfolioServiceImpl implements edu.kosta.kdc.model.service.PortfolioService {

    @Autowired
    private PortfolioDAO portfolioDAO;
    
    @Autowired
    private PortfolioDetailDAO portfolioDetailDAO;

    // 포트폴리오 삽입
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        return portfolioDAO.insertPortfolio(portfolioDTO);
    }

    // 포트폴리오 조회
    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    // 포트폴리오 수정
    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    // 포트폴리오 삭제
    @Override
    public int deletePortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return 0;
    }

    // ID에 해당하는 포트폴리오가 이미 존재하는지select
    @Override
    public boolean selectByMemberId(String memberId) {
        return portfolioDAO.selectByMemberId(memberId);
    }

    // 상세 삽입
    @Override
    public int insertDetail(PortfolioDetailDTO portfolioDetailDTO) {
        return portfolioDetailDAO.insertDetail(portfolioDetailDTO);
    }

    // 상세 조회(by memberid)
    @Override
    public List<PortfolioDetailDTO> selectDetailByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    // 상세 수정
    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    // 상세 삭제(by PortfolioDetail_pk)
    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        // TODO Auto-generated method stub
        return 0;
    }

}
