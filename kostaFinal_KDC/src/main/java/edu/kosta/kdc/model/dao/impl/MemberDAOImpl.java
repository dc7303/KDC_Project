package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * ȸ������
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * ��� ���̵�� ��ȸ
     */
    @Override
    public MemberDTO memberSelectByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * ��� ���� ����
     */
    @Override
    public int memberUpdate(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * ȸ��Ż��
     */
    @Override
    public int memberDelete(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

}
