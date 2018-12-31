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
     * ������ - Ŭ���� �ڵ� �ߺ� üũ(ajax)
     * @param String
     * @return String
     * */
    String codeCheck(String classRoomCode);

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


}
