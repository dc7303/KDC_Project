package edu.kosta.kdc.model.service;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberService {
    
    /**
     * ��� ���̵� üũ
     * 
     * @param memberId
     * @return
     */
    boolean memberSelectByMemberId(String memberId);
    
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
    int memberUpdate(MemberDTO memberDTO);
    
    /**
     * ��� ȸ�� Ż��.
     * 
     * @param memberDTO
     * @return
     */
    int memberDelete(MemberDTO memberDTO);
    
}
