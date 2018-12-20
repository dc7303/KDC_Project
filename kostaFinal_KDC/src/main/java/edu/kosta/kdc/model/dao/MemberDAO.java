package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberDAO {

    /**
     * 회원가입 프로세서에서 memberDTO insert하는 메소드
     * 
     * @param memberDTO
     * @return
     */
    int memberInsert(MemberDTO memberDTO);
    
    /**
     * 멤버 아이디로 정보 조회
     * 
     * @param memberId
     * @return
     */
    MemberDTO memberSelectByMemberId(String memberId);
    
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

    /**
     * 관리자 페이지에서 유저 조회 시 모든 유저 가져오는 메소드
     * 
     * @param
     * @return
     * */
    List<MemberDTO> selectAll();

    /**
     * 관리자 페이지에서 아이디 검색 시 해당 유저 가져오는 메소드
     * 
     * @param
     * @return
     * */
    List<MemberDTO> selectByUserId(String userId);

    /**
     * 관리자 페이지에서 삭제 버튼 클릭 시 유저 삭제
     * 
     * @param String UserId
     * @return
     * */
    int deleteByUserId(String userId);
}
