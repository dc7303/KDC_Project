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
    List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange);

    /**
     * 멤버 전체 수 가져오기
     * 
     * @return
     */
    int memberTotalCount();

    /**
     * 임시비밀번호 db에 update해주기
     * */
    int updatePwdByEmail(String uuid, String email);

    /**
     * 비밀번호 찾기에서 이메일 맞는지 확인
     * */
    boolean memberByEmailCheck(String emailCheck);

    /**
     * 키워드 검색 범위 내 수량 가져오기
     * @return
     */
    int memberSelectByKewordQuntity(String keyword, String word);

    /**
     * 멤버 키워드로 검색 (페이징 처리)
     * 
     * @param keyword
     * @param word
     * @param firstColumnRange
     * @param lastColumnRange
     * @return
     */
    List<MemberDTO> memberSelectByKeyword(String keyword, String word, int firstColumnRange, int lastColumnRange);
    
}
