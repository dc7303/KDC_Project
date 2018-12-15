package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    /**
     * 멤버 아이디 체크
     */
    @Override
    public MemberDTO memberSelectByMemberId(String memberId) {

        return sqlSession.selectOne("memberMapper.selectByMemberId", memberId);
    }

    /**
     * 멤버 닉네임 체크
     */
    @Override
    public boolean memberSelectByMemberNickName(String memberNickName) {
        
        boolean result = false;
        
      //존재한다면 true , 존재하지 않다면 false
        MemberDTO memberDTO = sqlSession.selectOne("memberMapper.selectByMemberNickName", memberNickName);
        if(memberDTO != null) result = true;
        
        return result;
    }
    
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 회원가입
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        
        int result = sqlSession.insert("memberMapper.insert", memberDTO);
        System.out.println(result);
        
        return result;
    }


    /**
     * 멤버 정보 수정
     */
    @Override
    public int memberUpdate(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * 회원탈퇴
     */
    @Override
    public int memberDelete(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

}
