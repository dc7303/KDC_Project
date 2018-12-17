package edu.kosta.kdc.model.service;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberService {
    
    /**
     * 멤버 아이디 체크
     * 
     * @param memberId
     * @return
     */
    boolean memberSelectByMemberId(String memberId);
    
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
    int memberUpdate(MemberDTO memberDTO);
    
    /**
     * 멤버 회원 탈퇴.
     * 
     * @param memberDTO
     * @return
     */
    int memberDelete(MemberDTO memberDTO);
    
}
