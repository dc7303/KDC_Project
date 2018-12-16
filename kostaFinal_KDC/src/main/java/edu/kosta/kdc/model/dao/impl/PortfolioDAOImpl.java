package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dto.PortfolioDTO;

@Repository
public class PortfolioDAOImpl implements edu.kosta.kdc.model.dao.PortfolioDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * ��Ʈ������ ����
     * */
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        int result = sqlSession.insert("portfolioMapper.insertPortfolio", portfolioDTO);   
        return result;
    }

    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deletePortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * ID�� �ش��ϴ� 
     * @return true = �̹�����, false = �������� 
     * */
    @Override
    public boolean selectByMemberId(String memberId) {
        // ���̵� �ش��ϴ� ��Ʈ�������� ������ true, ������ false�� result�� ���� 
        boolean result = 
                sqlSession.selectOne("portfolioMapper.selectByMemberId", memberId)!=null?true:false;
        return result;
    }

}
