package edu.kosta.kdc.model.dao.impl;

import java.util.List;



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
     * 회원가입
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        
        return sqlSession.insert("memberMapper.teacherInsert", memberDTO);
    }

    /**
     * 멤버 아이디로 조회
     */
    @Override
    public MemberDTO memberSelectByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
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
    
    /**
     * 모든 회원 정보 가져오기
     * */
    @Override
    public List<MemberDTO> selectAll() {
        
        return sqlSession.selectList("memberMapper.selectAll");
        
    }

    /**
     * 관리자 페이지에서 아이디 검색 시 해당 유저 가져오는 메소드
     * */
    @Override
    public List<MemberDTO> selectByUserId(String userId) {
        
        return sqlSession.selectList("memberMapper.selectByMemberId", userId);
        
    }

    /**
     * 관리자 페이지에서 유저 삭제
     * */
    @Override
    public int deleteByUserId(String userId) {
       
        return sqlSession.update("memberMapper.deleteByUserId", userId);
        
    }

}
