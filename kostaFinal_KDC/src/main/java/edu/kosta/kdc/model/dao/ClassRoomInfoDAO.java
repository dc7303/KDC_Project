package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomInfoDAO {

    /**
     * ���纰 Ŭ������ ��ü ����Ʈ
     * */
    List<ClassRoomInfoDTO> classList(String id);
    
    /**
     * Ŭ������ ����
     * �Է¹��� �� : code, Ŭ�����̸�, ������, ������
     * �Ѱܹ��� �� : ����id
     * ä������ �̸��� �ӽ÷� Ŭ������code.txt
     * */
    void classCreate(ClassRoomInfoDTO dto);
    
}
