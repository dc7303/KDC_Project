package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioService {

    // ��Ʈ������ ����
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // ��Ʈ������ ��ȸ
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // ��Ʈ������ ����
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // ��Ʈ������ ����
    int deletePortfolioByMemberId(String memberId);

    // ID�� �ش��ϴ� ��Ʈ�������� �̹� �����ϴ���select
    boolean selectByMemberId(String memberId);
    
    // �� ����
    int insertDetail(PortfolioDetailDTO portfolioDetailDTO);
    
    // �� ��ȸ(by memberid)
    List<PortfolioDetailDTO> selectDetailByMemberId(String memberId);
    
    // �� ����
    int updateDetail(PortfolioDetailDTO portfolioDetailDTO);
    
    // �� ����(by PortfolioDetail_pk)
    int deleteDetail(int PortfolioDetailPk);
}
