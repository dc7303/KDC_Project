package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomInfoDAO {

    /**
     * ������ - Ŭ���� �� ����
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
     * ������ - ǮĶ������ �� Ŭ���� ���� ��� ��������
     * 
     * @return List<ClassRoomInfoDTO>
     * */
    List<ClassRoomInfoDTO> getClassInfo();

}
