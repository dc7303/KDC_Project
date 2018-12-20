package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberService {

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
    
    /**
     * ������ ���������� ���� ��ȸ �� ��� ���� �������� �޼ҵ�
     * 
     * @param
     * @return
     * */
    List<MemberDTO> selectAll();

    /**
     * ������ ���������� ���̵� �˻� �� �ش� ���� �������� �޼ҵ�
     * 
     * @param String UserId
     * @return
     * */
    List<MemberDTO> selectByUserId(String userId);

    /**
     * ������ ���������� ���� ��ư Ŭ�� �� ���� ����
     * 
     * @param String UserId
     * @return
     * */
    int deleteMemberByUserId(String userId);
}
