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


    /**
     * 클래스룸 생성이 성공했을 때 채팅 파일 생성을 위한 파일명을 가져오는 메소드
     * @param classRoomInfoDTO 
     * */
    String selectChatFileName(ClassRoomInfoDTO classRoomInfoDTO);


}
