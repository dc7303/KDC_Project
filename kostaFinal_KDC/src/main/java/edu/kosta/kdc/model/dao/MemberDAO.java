package edu.kosta.kdc.model.dao;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberDAO {

    /**
     * ȸ������ ���μ������� memberDTO insert�ϴ� �޼ҵ�
     * 
     * @param memberDTO
     * @return
     */
    int memberInsert(MemberDTO memberDTO);
    
    /**
     * ��� ���̵�� ���� ��ȸ
     * 
     * @param memberId
     * @return
     */
    MemberDTO memberSelectByMemberId(String memberId);
    
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