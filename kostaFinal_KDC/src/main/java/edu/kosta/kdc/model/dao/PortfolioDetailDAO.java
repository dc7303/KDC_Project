package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioDetailDAO {
    // �� ����
    int insertDetail(PortfolioDetailDTO portfolioDetailDTO);

    // �� ��ȸ(by memberid)
    List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId);

    // �� ����
    int updateDetail(PortfolioDetailDTO portfolioDetailDTO);

    // �� ����(by PortfolioDetail_pk)
    int deleteDetail(int PortfolioDetailPk);
    
    // �ؽ��±� ����
    int insertHashTag(String hashTagName);
    
    // �� ��ȸ(by pk)
    PortfolioDetailDTO selectDetailByPk(int detailPk);
}
