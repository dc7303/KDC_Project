package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.AuthorityDTO;

public interface AuthorityDAO {

    /**
     * ��� ���̵�� ���� ��ȸ
     * 
     * @param memberId
     * @return
     */
    List<AuthorityDTO> authoritySelectByMemberId(String memberId);
    
    /**
     * ��� ���� ����
     * 
     * @param authorityDTO
     * @return
     */
    int authorityInsert(AuthorityDTO authorityDTO);

    /**
     * ������ ������ role_member��� role_student�� �ٲپ� �ش�.
     * */
    int authorityUpdate(String memberId);
}
