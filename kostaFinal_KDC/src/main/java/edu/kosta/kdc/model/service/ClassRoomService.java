package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomDTO;
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
     * 관리자 - 강사 아이디 체크(ajax)
     * 
     * @param String
     * @return String
     * */
    String teacherCheck(String teacherId);

    /**
     * 마이페이지 - member가 클래스 코드 입력하면 DB에 해당 유저와 코드를 입력시켜주는 메소드
     * */
    String insertMyClassRoom(ClassRoomDTO classRoomDTO);

    /**
     * myClassRoom 페이지에서 radio 버튼 선택 했을 때 해당 코드로 CurrentClass = True 로 바꾸는 메소드 
     * */
    int defaultClassSet(ClassRoomDTO classRoomDTO);

    /**
     * classRoomIsCurrent = 'TRUE' 인 클래스룸DTO 가져오는 메소드
     * */
    List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId);

}
