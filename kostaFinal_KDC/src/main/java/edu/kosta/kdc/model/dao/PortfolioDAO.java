package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioDAO {
    // ����
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // ��ȸ(id�� �ش��ϴ� ��Ʈ������)
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // �Խõ� ��� ��Ʈ������ ��ȸ
    List<PortfolioDTO> selectAll();

    // ����
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // ����
    int deletePortfolioByMemberId(String memberId);

    // ��Ʈ������ ��ȸ(������)
    PortfolioDTO selectAllDetail(String memberId);

    // �������� �˻�
    List<PortfolioDTO> selectByKeyword(String keyfield, String keyword);
}
