package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ClassRoomDAO;
import edu.kosta.kdc.model.dao.ClassRoomInfoDAO;
import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.service.ClassRoomService;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomInfoDAO classRoomInfoDAO;
    
    @Autowired
    private ClassRoomDAO classRoomDAO;
    
    @Override
    public List<ClassRoomInfoDTO> classList(String id) {
        
        List<ClassRoomInfoDTO> list = classRoomInfoDAO.classList(id);
        if(list == null) {
            throw new KdcException("존재하지 않습니다.");
        }
        
        return list;
    }
    
    /**
     * 강사 - 클래스 룸 생성
     * */
    @Override
    @Transactional
    public String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        int result = 0;

        result = classRoomInfoDAO.createClassRoom(classRoomInfoDTO);
        if(result == 0) {
            throw new KdcException("클래스룸 생성 실패");
        }

        //클래스룸 생성이 성공했을 때, 채팅 파일 생성을 위한 파일명(경로+파일명 으로 되어있음.) 을 가져와야 한다.
        return classRoomInfoDAO.selectChatFileName(classRoomInfoDTO);
        
    }

    /**
     * 강사- 강사 아이디 체크
     * */
    @Override
    public String teacherCheck(String teacherId) {
        
        return classRoomInfoDAO.teacherCheck(teacherId);
    }

    /**
     * 마이페이지 - member가 클래스 코드 입력하면 DB에 해당 유저와 코드를 입력시켜주는 메소드
     * */
    @Override
    public String insertMyClassRoom(ClassRoomDTO classRoomDTO) {
        
        int result = 0;
        
        //클래스룸 코드가 등록되어 있는지 체크
        result = classRoomInfoDAO.codeCheck(classRoomDTO.getClassRoomCode());
        if(result == 0) {
            return "존재하지 않는 클래스코드 입니다.";
        }
        
        //이미 아이디에 등록된 코드인지 검사해주는 메소드
        result = classRoomDAO.selectMyClassRoomCodeByClassRoomDTO(classRoomDTO);
        if(result == 0) {
            return "이미 등록된 클래스코드 입니다.";
        }
        
        //클래스 코드와 아이디를 매핑해서 DB에 저장함. 
        result = classRoomDAO.insertMyClassRoom(classRoomDTO);
        if(result == 0) {
            throw new KdcException("클래스 코드 등록 실패");
        }
        
        //마이페이지에서 클래스 코드가 DB에 등록 되었음.
        return "등록되었습니다.";
    }

    
    /**
     *  myClassRoom 페이지에서 radio 버튼 선택 했을 때 해당 코드로 CurrentClass = True 로 바꾸는 메소드
     * */
    @Override
    @Transactional
    public int defaultClassSet(ClassRoomDTO classRoomDTO) {
        
        int result = 0;
        
        //업데이트로 defaultClass를 바꾸기 전에 다른 Class의 CurrentClass=False로 바꾸어야 한다.
        result = classRoomDAO.updateOtherCurrentClass(classRoomDTO);
        if(result == 0) {
            throw new KdcException("마이페이지 기본 클래스 변경 실패");
        }
        
        result = classRoomDAO.defaultClassSet(classRoomDTO);
        
        if(result == 0) {
            throw new KdcException("기본 클래스 등록 실패");
        }
        
        return result;
        
    }

    /**
     * classRoomIsCurrent = 'TRUE' 인 클래스룸DTO 가져오기
     * */
    @Override
    public List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId) {
        
        return classRoomDAO.selectCurrentClassRoom(memberId);
    }

}
