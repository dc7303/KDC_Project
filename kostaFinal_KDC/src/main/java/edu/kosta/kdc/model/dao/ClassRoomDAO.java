package edu.kosta.kdc.model.dao;

import edu.kosta.kdc.model.dto.ClassRoomDTO;

public interface ClassRoomDAO {
    
    /**
     * memberId�� ����Ʈ�� ��ϵ� Ŭ������ ��ȸ�ϱ�
     * 
     * @param memberId
     * @return
     */
    ClassRoomDTO currentClassSelectByMemberId(String memberId);
    
    /**
     * member�� Ŭ���� �ڵ� �Է��ϸ� DB�� �ش� ������ �ڵ带 �Է½����ִ� �޼ҵ�
     * */
    int insertMemberIntoClass(String memberClassCode);
    
}
