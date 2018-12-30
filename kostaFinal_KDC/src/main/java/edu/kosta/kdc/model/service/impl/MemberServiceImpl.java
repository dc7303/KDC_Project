package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.AuthorityDAO;
import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.AuthorityDTO;
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
     * 멤버 아이디 체크
     */
    @Override
    public MemberDTO memberSelectByMemberId(String memberId) {
        
        //재사용을 위해 컨트롤러에서 null값 비교해서 메세지 출력 구현
        return memberDAO.memberSelectByMemberId(memberId);
        
    }

    /**
     * 멤버 닉네임 체크
     */
    @Override
    public boolean memberSelectByMemberNickName(String memberNickName) {

        boolean result = false;
        MemberDTO memberDTO = memberDAO.memberSelectByMemberNickName(memberNickName);
        
        //검색 결과 DTO 존재할 경우 True 반환
        if(memberDTO != null) result = true;
        
        return result;
    }
    
    /**
     * 멤버 회원가입
     */
    @Override
    @Transactional
    public int memberInsert(MemberDTO memberDTO, String authCode) {

        //password 인코딩
        String encodePwd = passwordEncoder.encode(memberDTO.getMemberPwd());
        //인코딩된 password 셋팅
        memberDTO.setMemberPwd(encodePwd);
        
        int result = 0;
        
        //회원 정보 Insert
        result = memberDAO.memberInsert(memberDTO);
        if(result == 0) {
            throw new KdcException("회원 정보 입력이 잘못되었습니다.");
        }
        
        //코드입력이 없을 시 멤버로 가입됨
        if(authCode.equals("") || authCode == null) {
            authCode = "ROLE_MEMBER";
        }
        
        //권한 Insert
        result = authorityDAO.authorityInsert(new AuthorityDTO(memberDTO.getMemberId(), authCode));
        if(result == 0) {
            throw new KdcException("권한 입력이 잘못되었습니다.");
        }
        
        return result;
    }

    /**
     * 멤버 정보 수정
     */
    @Override
    public int updateByMemberInfo(MemberDTO memberDTO) {
        
        //인코딩 패스워드 셋팅
        String encodePwd = passwordEncoder.encode(memberDTO.getMemberPwd());
        memberDTO.setMemberPwd(encodePwd);
        
        int result = 0;
        
        result = memberDAO.updateByMemberInfo(memberDTO);
        if(result == 0) throw new KdcException("수정 실패입니다.");
        
        return result;
    }

    /**
     * 회원정보 삭제
     */
    @Override 
    public int updateByIsWithDrawal(String memberId) throws KdcException {

        int result = 0;
        
        result = memberDAO.updateByIsWithDrawal(memberId);
        if(result == 0) throw new KdcException("탈퇴 실패입니다.");
        
        return result;
    }    

    /**
     * 모든 회원 정보 가져오기
     * */
    @Override
    public List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange) {

        List<MemberDTO> list = memberDAO.memberSelectAll(firstColumnRange, lastColumnRange);
        if(list == null) {
            throw new KdcException("회원이 존재하지 않습니다.");
        }
        
        return list;

    }

    /**
     * 멤버 전체 수 가져오기
     * 
     * @return
     */
    @Override
    public int memberTotalCount() {
        
        int result = 0;
        
        result = memberDAO.memberTotalCount();
        if(result == 0) {
            throw new KdcException("회원이 존재하지 않습니다.");
        }
        
        return result;
    }
    
    /**
     * 임시비밀번호 db에 update해주기
     * */
    @Override
    public int updatePwdByEmail(String uuid, String email) {
      //인코딩 패스워드 셋팅
        String encodePwd = passwordEncoder.encode(uuid);
        System.out.println("인코딩된 Pwd : "+encodePwd);
        int result = memberDAO.updatePwdByEmail(encodePwd, email);
        System.out.println("결과 : "+ result);
        return result;
    }

    /**
     * 멤버 이메일 체크
     */
    @Override
    public boolean memberByEmailCheck(String emailCheck) {
        boolean result = false;
        MemberDTO memberDTO = memberDAO.memberByEmailCheck(emailCheck);
        
        //검색 결과 DTO 존재할 경우 True 반환
        if(memberDTO != null) result = true;
        
        return result;
    }
    
    /**
     * 멤버 범위내 수량 가져오기
     */
    @Override
    public int memberSelectByKewordQuntity(String keyword, String word) {
        
        return memberDAO.memberSelectByKewordQuntity(keyword, word);
    }

    /**
     * 멤버 키워드 검색
     */
    @Override
    public List<MemberDTO> memberSelectByKeyword(String keyword, String word, int firstColumnRange,
            int lastColumnRange) {

        List<MemberDTO> list = memberDAO.memberSelectByKeyword(keyword, word, firstColumnRange, lastColumnRange);
        if(list == null) {
            throw new KdcException("존재하지 않습니다.");
        }
        
        return list;
    }
    
}
