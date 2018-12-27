package edu.kosta.kdc.model.service;


import java.util.List;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberService {
    
    /**
     * 멤버 아이디 체크
     * 
     * @param memberId
     * @return
     */
    MemberDTO memberSelectByMemberId(String memberId);
    
    /**
     * 멤버 닉네임 체크
     * 
     * @param memberNickName
     * @return
     */
    boolean memberSelectByMemberNickName(String memberNickName);

    /**
     * 회원가입 프로세서에서 memberDTO insert하는 메소드
     * 
     * @param memberDTO
     * @return
     */
    int memberInsert(MemberDTO memberDTO, String authCode);
    
    /**
     * 멤버 정보 수정.
     * service에서 password encoding 후 memberDTO 가져온다.
     * 
     * @param memberDTO
     * @return
     */
    int updateByMemberInfo(MemberDTO memberDTO);
    
    /**
     * 멤버 회원 탈퇴.
     * 
     * @param memberDTO
     * @return
     */

    int updateByIsWithDrawal(String memberId);
    
    /**
     * 관리자 페이지에서 유저 조회 시 모든 유저 가져오는 메소드
     * 
     * @param
     * @return
     * */
    List<MemberDTO> memberSelectAll();

    /**
     * 이메일 보내기
     * */
    int pwdSearchEmailSend(String email);

  
}
