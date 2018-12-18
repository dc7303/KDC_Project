package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioService {

    // ��Ʈ������ ����
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // ��Ʈ������ ��ȸ(�󼼹�����)
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // ��Ʈ������ ����
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // ��Ʈ������ ����
    int deletePortfolioByMemberId(String memberId);

    // �� ����
    int insertDetail(PortfolioDetailDTO portfolioDetailDTO, String hashTagName);

    // �� ��ȸ(by memberid)
    List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId);

    // �� ����
    int updateDetail(PortfolioDetailDTO portfolioDetailDTO);

    // �� ����(by PortfolioDetail_pk)
    int deleteDetail(int PortfolioDetailPk);

    // �� ��ȸ(by pk)
    PortfolioDetailDTO selectDetailByPk(int detailPk);
}
