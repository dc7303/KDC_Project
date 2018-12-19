package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomService {

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
