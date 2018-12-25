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

    // 포트폴리오 삽입
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        // 유저의 포트폴리오가 이미 존재하거나
        // INSERT결과가 0인 경우 사용자 Exception 발생
        if (portfolioDAO.selectPortfolioByMemberId(portfolioDTO.getPortFolioMemberId()) != null)
            throw new KdcException("포트폴리오 생성을 실패했습니다.");
        int result = portfolioDAO.insertPortfolio(portfolioDTO);
        if (result == 0)
            throw new KdcException("포트폴리오 생성을 실패했습니다.");

        return result;
    }

    // 포트폴리오 조회
    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        return portfolioDAO.selectPortfolioByMemberId(memberId);
    }

    // 포트폴리오 수정
    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        int result = portfolioDAO.updatePortfolio(portfolioDTO);
        if (result == 0)
            throw new KdcException("포트폴리오 생성을 실패했습니다.");
        return result;
    }

    // 포트폴리오 삭제(미구현)
    @Override
    public int deletePortfolioByMemberId(String memberId) {
        return 0;
    }

    // 상세 삽입(해쉬태그포함)
    @Override
    @Transactional
    public int insertDetail(PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {
        int result = portfolioDetailDAO.insertDetail(portfolioDetailDTO);
        if (result == 0)
            throw new KdcException("포트폴리오 상세 생성에 실패했습니다.");

        // 해쉬태그 insert
        if (hashTagName != null && (!hashTagName.equals(""))) {
            String[] hashTags = hashTagName.replaceAll(" ", "").split(",");
            for (String s : hashTags) {
                result = portfolioDetailDAO.insertHashTag(s);
                if (result == 0)
                    throw new KdcException("포트폴리오 상세 생성에 실패했습니다.");
            }
        }

        return result;
    }

    // 상세 조회(by memberid)
    @Override
    public List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId) {
        return portfolioDetailDAO.selectDetailsByMemberId(memberId);
    }

    // 상세 조회(by pk)
    @Override
    public PortfolioDetailDTO selectDetailByPk(int detailPk) {
        PortfolioDetailDTO portfolioDetailDTO = portfolioDetailDAO.selectDetailByPk(detailPk);
        if (portfolioDetailDTO == null)
            throw new KdcException("없는 포트폴리오 입니다.");

        return portfolioDetailDTO;
    }

    /*
     * 해쉬태그 수정이 불가하여 삭제, 삽입 후 상세정보 수정 상세 수정(해쉬태그 삭제->해쉬태그 삽입->상세 수정)
     */
    @Transactional
    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {
        int result = 0;

        // 해쉬태그 삭제
        result = portfolioDetailDAO.deleteHashTag(portfolioDetailDTO.getPortFolioDetailPk());

        // 해쉬태그 삽입
        if (hashTagName != null && (!hashTagName.equals(""))) {
            String[] hashTags = hashTagName.replaceAll(" ", "").split(",");
            for (String s : hashTags) {
                result = portfolioDetailDAO.insertHashTag(s);
                if (result == 0)
                    throw new KdcException("수정에 실패했습니다.");
            }
        }
        result = portfolioDetailDAO.updateDetail(portfolioDetailDTO);
        if (result == 0)
            throw new KdcException("수정에 실패했습니다.");
        return result;
    }

    // 상세 삭제(by PortfolioDetail_pk)
    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        int result = portfolioDetailDAO.deleteDetail(PortfolioDetailPk);
        if (result == 0)
            throw new KdcException("삭제에 실패했습니다.");
        return result;
    }

    // 게시된 모든 포트폴리오 조회
    @Override
    public List<PortfolioDTO> selectAll() {
        return portfolioDAO.selectAll();
    }

    // 포트폴리오 조회(상세포함)
    @Override
    public PortfolioDTO selectAllDetail(String memberId) {
        return portfolioDAO.selectAllDetail(memberId);
    }

    // 분류별 키워드 검색
    @Override
    public List<PortfolioDTO> selectByKeyword(String keyfield, String keyword) {
        return portfolioDAO.selectByKeyword(keyfield, keyword);
    }

}
