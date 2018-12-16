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

    // ��Ʈ������ ����
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        return portfolioDAO.insertPortfolio(portfolioDTO);
    }

    // ��Ʈ������ ��ȸ
    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    // ��Ʈ������ ����
    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    // ��Ʈ������ ����
    @Override
    public int deletePortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return 0;
    }

    // ID�� �ش��ϴ� ��Ʈ�������� �̹� �����ϴ���select
    @Override
    public boolean selectByMemberId(String memberId) {
        return portfolioDAO.selectByMemberId(memberId);
    }

    // �� ����
    @Override
    public int insertDetail(PortfolioDetailDTO portfolioDetailDTO) {
        return portfolioDetailDAO.insertDetail(portfolioDetailDTO);
    }

    // �� ��ȸ(by memberid)
    @Override
    public List<PortfolioDetailDTO> selectDetailByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    // �� ����
    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    // �� ����(by PortfolioDetail_pk)
    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        // TODO Auto-generated method stub
        return 0;
    }

}
