package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.AuthorityDAO;
import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;
    
    @Autowired
    private AuthorityDAO authorityDAO;//권한 설정
    
    @Autowired
    private PasswordEncoder passwordEncoder;//Insert, update, delete시 패스워드 인코딩 

    /**
     * 관리자 - 강사 회원가입
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        
        return memberDAO.memberInsert(memberDTO);
        
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
     * 회원정보 삭제
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

        return memberDAO.selectAll();

    }
    
    /**
     * 관리자 페이지에서 아이디 검색 시 해당 유저 가져오는 메소드
     * */
    @Override
    public List<MemberDTO> selectByUserId(String userId) {
        
        return memberDAO.selectByUserId(userId);
    
    }

    /**
     * 관리자 페이지에서 삭제 버튼 클릭 시 유저 삭제
     * */
    @Override
    public int deleteMemberByUserId(String userId) {

        return memberDAO.deleteByUserId(userId);
        
    }
    
    
    
}
