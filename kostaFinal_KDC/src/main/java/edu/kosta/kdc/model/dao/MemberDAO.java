package edu.kosta.kdc.model.dao;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberDAO {

    /**
     * ��� ���̵�� ��ȸ
     * 
     * @param memberId
     * @return
     */
    boolean memberSelectById(String memberId);
    
    /**
     * ��� �г������� ��ȸ
     * 
     * @param memberNickName
     * @return
     */
    boolean memberSelectByNickName(String memberNickName);
    
    /**
     * ȸ������ ���μ������� memberDTO insert�ϴ� �޼ҵ�
     * 
     * @param memberDTO
     * @return
     */
    int memberInsert(MemberDTO memberDTO);
    
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
