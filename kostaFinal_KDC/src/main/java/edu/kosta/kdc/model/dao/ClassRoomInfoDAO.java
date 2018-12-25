package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomInfoDAO {

    /**
     * 강사별 클래스룸 전체 리스트
     * */
    List<ClassRoomInfoDTO> classList(String id);
    
    
     /* 관리자 - 클래스 룸 생성
     * 
     * */
    int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO);

    /**
     * 관리자 - 클래스 코드 중복 체크(ajax)
     * @param String
     * @return String
     * */
    String codeCheck(String classRoomCode);

    /**
     * 관리자 - 강사 아이디 체크(ajax)
     * 
     * @param String
     * @return String
     * */
    String teacherCheck(String teacherId);


}
