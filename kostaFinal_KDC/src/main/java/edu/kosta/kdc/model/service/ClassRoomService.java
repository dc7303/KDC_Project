package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomService {

    /**
     * ������ - Ŭ���� �� ����
     * 
     * @param classRoomInfoDTO
     * */
    int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO);

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

    /**
     * ������ - ǮĶ������ �� Ŭ���� ���� ��� ��������
     * 
     * @return List<ClassRoomInfoDTO>
     * */
    List<ClassRoomInfoDTO> getClassInfo();

}
