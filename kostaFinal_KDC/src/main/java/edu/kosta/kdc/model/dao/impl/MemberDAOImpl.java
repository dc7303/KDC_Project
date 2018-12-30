package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.emailSend.EmailForm;
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
    
    

    /**
     * 임시비밀번호 db에 update해주기
     * */
    @Override
    public int updatePwdByEmail(String encodePwd, String email) {
        Map<String, String> map = new HashMap<>();
        map.put("memberPwd", encodePwd);
        map.put("memberEmail", email);
        
        return sqlSession.update("memberMapper.updatePwdByEmail",map);
    }

    /**
     * 멤버 이메일 체크
     */
    @Override
    public MemberDTO memberByEmailCheck(String emailCheck) {
        
        return sqlSession.selectOne("memberMapper.memberByEmailCheck", emailCheck);
    }
    
    /**
     * 멤버 키워드 검색 수량 가져오기
     */
    @Override
    public int memberSelectByKewordQuntity(String keyword, String word) {
        
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("word", word);

        return sqlSession.selectOne("memberMapper.memberSelectByKeywordQuantity", map);
    }

    /**
     * 멤버 키워드로 검색
     */
    @Override
    public List<MemberDTO> memberSelectByKeyword(String keyword, String word, int firstColumnRange,
            int lastColumnRange) {
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("keyword", keyword);
        map.put("word", word);
        map.put("firstColumn", firstColumnRange);
        map.put("lastColumn", lastColumnRange);
        
        return sqlSession.selectList("memberMapper.memberPagingSelectByKeyword", map);
    }


}
