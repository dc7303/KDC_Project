package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    /**
     * ��� ���̵� üũ
     */
    @Override
    public boolean memberSelectById(String memberId) {
        
        boolean result = false;
        
        MemberDTO memberDTO = sqlSession.selectOne("memberMapper.selectByMemberId", memberId);
        
        //�����Ѵٸ� true , �������� �ʴٸ� false
        if(memberDTO != null) result = true;

        return result;
    }

    /**
     * ��� �г��� üũ
     */
    @Override
    public boolean memberSelectByNickName(String memberNickName) {
        
        boolean result = false;
        
      //�����Ѵٸ� true , �������� �ʴٸ� false
        MemberDTO memberDTO = sqlSession.selectOne("memberMapper.selectByMemberNickName", memberNickName);
        if(memberDTO != null) result = true;
        
        return result;
    }
    
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * ȸ������
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        
        return sqlSession.insert("memberMapper.insert", memberDTO);
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
