package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberDAO {

    /**
     * 멤버 아이디로 조회
     * 
     * @param memberId
     * @return
     */
    MemberDTO memberSelectByMemberId(String memberId);
    
    /**
     * 멤버 닉네임으로 조회
     * 
     * @param memberNickName
     * @return
     */
    MemberDTO memberSelectByMemberNickName(String memberNickName);
    
    /**
     * 회원가입 프로세서에서 memberDTO insert하는 메소드
     * 
     * @param memberDTO
     * @return
     */
    int memberInsert(MemberDTO memberDTO);
    
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
     * 관리자 페이지에서 페이지에 해당하는 멤버 가져오기
     * 
     * @param
     * @return
     * */
    List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange);

    /**
     * 임시비밀번호 db에 update해주기
     * */
    int updatePwdByEmail(String encodePwd, String email);

    /**
     * 비밀번호 찾기에서 이메일 맞는지 확인
     * */
    MemberDTO memberByEmailCheck(String emailCheck);


    /**
     * 멤버 전체 수 가져오기
     * 
     * @return
     */
    int memberTotalCount();
}
