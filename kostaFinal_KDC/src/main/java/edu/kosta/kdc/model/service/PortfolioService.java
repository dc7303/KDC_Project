package edu.kosta.kdc.model.service;

import edu.kosta.kdc.model.dto.PortfolioDTO;

public interface PortfolioService {

    // ����
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // ��ȸ
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // ����
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // ����
    int deletePortfolioByMemberId(String memberId);

    // ID�� �ش��ϴ� ��Ʈ�������� �̹� �����ϴ���select
    boolean selectByMemberId(String memberId);
}
