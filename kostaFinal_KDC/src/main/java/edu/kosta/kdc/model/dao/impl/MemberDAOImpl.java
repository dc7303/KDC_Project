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
    public MemberDTO memberSelectByMemberId(String memberId) {

        return sqlSession.selectOne("memberMapper.selectByMemberId", memberId);
    }

    /**
     * ��� �г��� üũ
     */
    @Override
    public boolean memberSelectByMemberNickName(String memberNickName) {
        
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
        
        int result = sqlSession.insert("memberMapper.insert", memberDTO);
        System.out.println(result);
        
        return result;
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
