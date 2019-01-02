package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomInfoDAO {

    /**
     * ���纰 Ŭ������ ��ü ����Ʈ
     * */
    List<ClassRoomInfoDTO> classList(String id);
    
    
     /* ������ - Ŭ���� �� ����
     * 
     * */
    int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO);

    /**
     * ������ - ���� ���̵� üũ(ajax)
     * 
     * @param String
     * @return String
     * */
    String teacherCheck(String teacherId);


    /**
     * Ŭ������ ������ �������� �� ä�� ���� ������ ���� ���ϸ��� �������� �޼ҵ�
     * @param classRoomInfoDTO 
     * */
    String selectChatFileName(ClassRoomInfoDTO classRoomInfoDTO);

    /**
     * Ŭ������ �ڵ尡 �ִ��� üũ�ϴ� �޼ҵ�
     * */
    int codeCheck(String classRoomCode);


    /**
     * ���� �ֱٿ� ������ Ŭ���� �ڵ� �������� �޼ҵ�
     * */
    String selectClassCode(String memberId);


}
