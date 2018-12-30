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
    List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange);

    /**
     * ��� ��ü �� ��������
     * 
     * @return
     */
    int memberTotalCount();

    /**
     * �ӽú�й�ȣ db�� update���ֱ�
     * */
    int updatePwdByEmail(String uuid, String email);

    /**
     * ��й�ȣ ã�⿡�� �̸��� �´��� Ȯ��
     * */
    boolean memberByEmailCheck(String emailCheck);

    /**
     * Ű���� �˻� ���� �� ���� ��������
     * @return
     */
    int memberSelectByKewordQuntity(String keyword, String word);

    /**
     * ��� Ű����� �˻� (����¡ ó��)
     * 
     * @param keyword
     * @param word
     * @param firstColumnRange
     * @param lastColumnRange
     * @return
     */
    List<MemberDTO> memberSelectByKeyword(String keyword, String word, int firstColumnRange, int lastColumnRange);
    
}
