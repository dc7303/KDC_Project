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
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * ���� �߰�
     */
    @Override
    public int authorityInsert(AuthorityDTO authorityDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

}