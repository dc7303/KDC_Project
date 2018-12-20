package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomInfoDAO {

    /**
     * 관리자 - 클래스 룸 생성
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

    /**
     * 관리자 - 풀캘린더에 들어갈 클래스 일정 모두 가져오기
     * 
     * @return List<ClassRoomInfoDTO>
     * */
    List<ClassRoomInfoDTO> getClassInfo();

}
