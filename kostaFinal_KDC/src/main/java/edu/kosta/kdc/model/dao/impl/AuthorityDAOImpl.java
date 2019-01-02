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
     * ��� ���̵�� ���� ��ȸ
     */
    @Override
    public List<AuthorityDTO> authoritySelectByMemberId(String memberId) {
        
        return sqlSession.selectList("authMapper.selectByMemberId", memberId);
    }

    /**
     * ���� �߰�
     */
    @Override
    public int authorityInsert(AuthorityDTO authorityDTO) {
        
        return sqlSession.insert("authMapper.insert", authorityDTO);
    }

    /**
     * ������ ������ role_member��� role_student�� �ٲپ� �ش�.
     * */
    @Override
    public int authorityUpdate(String memberId) {
       
        return sqlSession.update("authMapper.authorityUpdate", memberId);
    }

}
