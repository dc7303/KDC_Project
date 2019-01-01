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
        
        return sqlSession.selectList("authMapper.selectByMemberId", memberId);
    }

    /**
     * 권한 추가
     */
    @Override
    public int authorityInsert(AuthorityDTO authorityDTO) {
        
        return sqlSession.insert("authMapper.insert", authorityDTO);
    }

    /**
     * 가져온 권한이 role_member라면 role_student로 바꾸어 준다.
     * */
    @Override
    public int authorityUpdate(String memberId) {
       
        return sqlSession.update("authMapper.authorityUpdate", memberId);
    }

}
