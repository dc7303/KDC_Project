package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.PortfolioDAO;
import edu.kosta.kdc.model.dao.PortfolioDetailDAO;
import edu.kosta.kdc.model.dto.HashTagDTO;
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
        // ������ ��Ʈ�������� �̹� �����ϰų�
        // INSERT����� 0�� ��� ����� Exception �߻�
        if (portfolioDAO.selectPortfolioByMemberId(portfolioDTO.getPortFolioMemberId()) != null)
            throw new KdcException("��Ʈ������ ������ �����߽��ϴ�.");
        int result = portfolioDAO.insertPortfolio(portfolioDTO);
        if (result == 0)
            throw new KdcException("��Ʈ������ ������ �����߽��ϴ�.");

        return result;
    }

    // ��Ʈ������ ��ȸ
    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        return portfolioDAO.selectPortfolioByMemberId(memberId);
    }

    // ��Ʈ������ ����
    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        int result = portfolioDAO.updatePortfolio(portfolioDTO);
        if (result == 0)
            throw new KdcException("��Ʈ������ ������ �����߽��ϴ�.");
        return result;
    }

    // ��Ʈ������ ����(�̱���)
    @Override
    public int deletePortfolioByMemberId(String memberId) {
        return 0;
    }

    // �� ����(�ؽ��±�����)
    @Override
    @Transactional
    public int insertDetail(PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {
        int result = portfolioDetailDAO.insertDetail(portfolioDetailDTO);
        if (result == 0)
            throw new KdcException("��Ʈ������ �� ������ �����߽��ϴ�.");

        // �ؽ��±� insert
        if (hashTagName != null && (!hashTagName.equals(""))) {
            String[] hashTags = hashTagName.replaceAll(" ", "").split(",");
            for (String s : hashTags) {
                result = portfolioDetailDAO.insertHashTag(s);
                if (result == 0)
                    throw new KdcException("��Ʈ������ �� ������ �����߽��ϴ�.");
            }
        }

        return result;
    }

    // �� ��ȸ(by memberid)
    @Override
    public List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId) {
        return portfolioDetailDAO.selectDetailsByMemberId(memberId);
    }

    // �� ��ȸ(by pk)
    @Override
    public PortfolioDetailDTO selectDetailByPk(int detailPk) {
        PortfolioDetailDTO portfolioDetailDTO = portfolioDetailDAO.selectDetailByPk(detailPk);
        if (portfolioDetailDTO == null)
            throw new KdcException("���� ��Ʈ������ �Դϴ�.");

        return portfolioDetailDTO;
    }

    /*
     * �ؽ��±� ������ �Ұ��Ͽ� ����, ���� �� ������ ���� �� ����(�ؽ��±� ����->�ؽ��±� ����->�� ����)
     */
    @Transactional
    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {
        int result = 0;

        // �ؽ��±� ����
        result = portfolioDetailDAO.deleteHashTag(portfolioDetailDTO.getPortFolioDetailPk());

        // �ؽ��±� ����
        if (hashTagName != null && (!hashTagName.equals(""))) {
            String[] hashTags = hashTagName.replaceAll(" ", "").split(",");
            for (String s : hashTags) {
                result = portfolioDetailDAO.insertHashTag(s);
                if (result == 0)
                    throw new KdcException("������ �����߽��ϴ�.");
            }
        }
        result = portfolioDetailDAO.updateDetail(portfolioDetailDTO);
        if (result == 0)
            throw new KdcException("������ �����߽��ϴ�.");
        return result;
    }

    // �� ����(by PortfolioDetail_pk)
    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        int result = portfolioDetailDAO.deleteDetail(PortfolioDetailPk);
        if (result == 0)
            throw new KdcException("������ �����߽��ϴ�.");
        return result;
    }

    // �Խõ� ��� ��Ʈ������ ��ȸ
    @Override
    public List<PortfolioDTO> selectAll() {
        return portfolioDAO.selectAll();
    }

    // ��Ʈ������ ��ȸ(������)
    @Override
    public PortfolioDTO selectAllDetail(String memberId) {
        return portfolioDAO.selectAllDetail(memberId);
    }

    // �з��� Ű���� �˻�
    @Override
    public List<PortfolioDTO> selectByKeyword(String keyfield, String keyword) {
        return portfolioDAO.selectByKeyword(keyfield, keyword);
    }

}
