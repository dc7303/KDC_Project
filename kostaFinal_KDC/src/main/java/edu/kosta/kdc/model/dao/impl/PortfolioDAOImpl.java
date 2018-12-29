package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

@Repository
public class PortfolioDAOImpl implements edu.kosta.kdc.model.dao.PortfolioDAO {

    @Autowired
    private SqlSession sqlSession;
    
    // ��Ʈ������ ����
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        int result = sqlSession.insert("portfolioMapper.insertPortfolio", portfolioDTO);   
        return result;
    }

    //��Ʈ������ ��ȸ
    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        return sqlSession.selectOne("portfolioMapper.selectPortfolioByMemberId", memberId);
    }

    // ��Ʈ������ ����
    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        return sqlSession.update("portfolioMapper.updatePortfolio", portfolioDTO);
    }

    // ����(�̱���)
    @Override
    public int deletePortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return 0;
    } 

    // �Խõ� ��� ��Ʈ������ ��ȸ
    @Override
    public List<PortfolioDTO> selectAll() {
        return sqlSession.selectList("portfolioMapper.selectAll");
    }

    // ��Ʈ������ ��ȸ(������)
    @Override
    public PortfolioDTO selectAllDetail(String memberId) {
        return sqlSession.selectOne("portfolioMapper.selectAllDetail", memberId);
    }

    //�з��� Ű���� �˻�
    @Override
    public List<PortfolioDTO> selectByKeyword(String keyfield, String keyword) {
        Map<String, String> map = new HashMap<>();
        map.put("keyfield", keyfield);
        map.put("keyword", keyword);
        return sqlSession.selectList("portfolioMapper.selectByKeyword", map);
    }

    

}
