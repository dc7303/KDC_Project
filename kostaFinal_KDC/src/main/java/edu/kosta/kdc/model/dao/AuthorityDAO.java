package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.AuthorityDTO;

public interface AuthorityDAO {

    /**
     * 멤버 아이디로 권한 조회
     * 
     * @param memberId
     * @return
     */
    List<AuthorityDTO> authoritySelectByMemberId(String memberId);
    
    /**
     * 멤버 권한 설정
     * 
     * @param authorityDTO
     * @return
     */
    int authorityInsert(AuthorityDTO authorityDTO);
}
