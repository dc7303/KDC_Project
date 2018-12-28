package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomService {

    /**
     * ���纰 Ŭ������ ��ü ����Ʈ
     * */
    List<ClassRoomInfoDTO> classList(String id);
    
    /**
     * ������ - Ŭ���� �� ����
     * 
     * @param classRoomInfoDTO
     * */
    String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO);

    /**
     * ������ - �ڵ� �ߺ� üũ(ajax)
     * 
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


}
