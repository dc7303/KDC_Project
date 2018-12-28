package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public MemberDTO memberSelectByMemberNickName(String memberNickName) {
        
        return sqlSession.selectOne("memberMapper.selectByMemberNickName", memberNickName);
    }
    
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 회원가입
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        
        return sqlSession.insert("memberMapper.insert", memberDTO);
    }

    /**
     * 멤버 정보 수정
     */
    @Override
    public int updateByMemberInfo(MemberDTO memberDTO) {
        
        return sqlSession.update("memberMapper.updateByMemberInfo", memberDTO);
    }

    /**
     * 회원탈퇴
     */
    @Override
    public int updateByIsWithDrawal(String memberId) {
        
        return sqlSession.update("memberMapper.updateByIsWithDrawal", memberId);
    }
    
    /**
     * 모든 회원 정보 가져오기 ( 페이징 처리 )
     * */
    @Override
    public List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange) {
        
        Map<String, Integer> map = new HashMap<>();
        
        map.put("firstColumn", firstColumnRange);
        map.put("lastColumn", lastColumnRange);
        
        return sqlSession.selectList("memberMapper.memberPagingSelect", map);
        
    }

    /**
     * 멤버 전체 수 가져오기
     */
    @Override
    public int memberTotalCount() {
        
        return sqlSession.selectOne("memberMapper.memberTotalCount");
    }
    
    

}
