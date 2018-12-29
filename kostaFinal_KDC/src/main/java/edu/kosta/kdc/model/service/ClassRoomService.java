package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomService {

    /**
     * 강사별 클래스룸 전체 리스트
     * */
    List<ClassRoomInfoDTO> classList(String id);
    
    /**
     * 관리자 - 클래스 룸 생성
     * 
     * @param classRoomInfoDTO
     * */
    String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO);

    /**
     * 관리자 - 코드 중복 체크(ajax)
     * 
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
