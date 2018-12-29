package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MemberDTO;

public interface MemberDAO {

    /**
     * ��� ���̵�� ��ȸ
     * 
     * @param memberId
     * @return
     */
    MemberDTO memberSelectByMemberId(String memberId);
    
    /**
     * ��� �г������� ��ȸ
     * 
     * @param memberNickName
     * @return
     */
    MemberDTO memberSelectByMemberNickName(String memberNickName);
    
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
    int updateByMemberInfo(MemberDTO memberDTO);
    
    /**
     * ��� ȸ�� Ż��.
     * 
     * @param memberDTO
     * @return
     */

    int updateByIsWithDrawal(String memberId);
    
    /**
     * ������ ���������� �������� �ش��ϴ� ��� ��������
     * 
     * @param
     * @return
     * */
    List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange);

    /**
     * �ӽú�й�ȣ db�� update���ֱ�
     * */
    int updatePwdByEmail(String encodePwd, String email);

    /**
     * ��й�ȣ ã�⿡�� �̸��� �´��� Ȯ��
     * */
    MemberDTO memberByEmailCheck(String emailCheck);


    /**
     * ��� ��ü �� ��������
     * 
     * @return
     */
    int memberTotalCount();
}
