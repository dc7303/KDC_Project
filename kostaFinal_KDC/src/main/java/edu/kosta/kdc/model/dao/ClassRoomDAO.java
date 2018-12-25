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
    
    
}
