package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomDAO {
    
    /**
     * memberId로 디폴트로 등록된 클래스룸 조회하기
     * 
     * @param memberId
     * @return
     */
    ClassRoomDTO currentClassSelectByMemberId(String memberId);
    
    /**
     * 마이페이지 - member가 클래스 코드 입력하면 DB에 해당 유저와 코드를 입력시켜주는 메소드
     * */
    int insertMyClassRoom(ClassRoomDTO classRoomDTO);

    /**
     * 마이페이지 - 이미 아이디에 등록된 코드인지 검사해주는 메소드
     * */
    int selectMyClassRoomCodeByClassRoomDTO(ClassRoomDTO classRoomDTO);

    /**
     *  myClassRoom 페이지에서 radio 버튼 선택 했을 때 해당 코드로 CurrentClass = True 로 바꾸는 메소드
     * */
    int defaultClassSet(ClassRoomDTO classRoomDTO);

    /**
     * classRoomIsCurrent = 'TRUE' 인 클래스룸DTO 가져오기
     * */
    List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId);

    /**
     * //업데이트로 defaultClass를 바꾸기 전에 다른 Class의 CurrentClass=False로 바꾸는 메소드
     * */
    int updateOtherCurrentClass(ClassRoomDTO classRoomDTO);
    
}
