package edu.kosta.kdc.model.service;


import java.util.List;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberService {
    
    /**
     * ��� ���̵� üũ
     * 
     * @param memberId
     * @return
     */
    MemberDTO memberSelectByMemberId(String memberId);
    
    /**
     * ��� �г��� üũ
     * 
     * @param memberNickName
     * @return
     */
    boolean memberSelectByMemberNickName(String memberNickName);

    /**
     * ȸ������ ���μ������� memberDTO insert�ϴ� �޼ҵ�
     * 
     * @param memberDTO
     * @return
     */
    int memberInsert(MemberDTO memberDTO, String authCode);
    
    /**
     * ��� ���� ����.
     * service���� password encoding �� memberDTO �����´�.
     * 
     * @param memberDTO
     * @return
     */
    int updateByMemberInfo(MemberDTO memberDTO);
    
    /**
     * ��� ȸ�� Ż��.
     * 
     * @param memberDTO
     * @return
     */

    int updateByIsWithDrawal(String memberId);
    
    /**
     * ������ ���������� ���� ��ȸ �� ��� ���� �������� �޼ҵ�
     * 
     * @param
     * @return
     * */
    List<MemberDTO> memberSelectAll();

    /**
     * �̸��� ������
     * */
    int pwdSearchEmailSend(String email);

  
}
