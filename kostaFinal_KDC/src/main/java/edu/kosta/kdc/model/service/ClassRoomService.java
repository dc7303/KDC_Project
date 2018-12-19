package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomService {

    /**
     * 강사별 클래스룸 전체 리스트
     * */
    List<ClassRoomInfoDTO> classList(String id);
    
    /**
     * 클래스룸 생성
     * 입력받을 값 : code, 클래스이름, 시작일, 종료일
     * 넘겨받을 값 : 강사id
     * 채팅파일 이름은 임시로 클래스룸code.txt
     * */
    void classCreate(ClassRoomInfoDTO dto);

}
