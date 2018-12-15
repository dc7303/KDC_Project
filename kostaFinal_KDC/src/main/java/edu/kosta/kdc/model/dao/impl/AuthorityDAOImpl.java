package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.AuthorityDAO;
import edu.kosta.kdc.model.dto.AuthorityDTO;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 멤버 아이디로 권한 조회
     */
    @Override
    public List<AuthorityDTO> authoritySelectByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 권한 추가
     */
    @Override
    public int authorityInsert(AuthorityDTO authorityDTO) {
        
        return sqlSession.insert("authMapper.insert", authorityDTO);
    }

}
