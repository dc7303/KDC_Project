package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomDTO;
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
     * ������ - ���� ���̵� üũ(ajax)
     * 
     * @param String
     * @return String
     * */
    String teacherCheck(String teacherId);

    /**
     * ���������� - member�� Ŭ���� �ڵ� �Է��ϸ� DB�� �ش� ������ �ڵ带 �Է½����ִ� �޼ҵ�
     * */
    String insertMyClassRoom(ClassRoomDTO classRoomDTO);

    /**
     * myClassRoom ���������� radio ��ư ���� ���� �� �ش� �ڵ�� CurrentClass = True �� �ٲٴ� �޼ҵ� 
     * */
    int defaultClassSet(ClassRoomDTO classRoomDTO);

    /**
     * classRoomIsCurrent = 'TRUE' �� Ŭ������DTO �������� �޼ҵ�
     * */
    List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId);

}
