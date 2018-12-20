package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.ClassRoomInfoDAO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.service.ClassRoomService;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomInfoDAO classRoomInfoDAO;
    
    @Override
    public List<ClassRoomInfoDTO> classList(String id) {
        
        List<ClassRoomInfoDTO> list = classRoomInfoDAO.classList(id);
        
        return list;
    }
    
    /**
     * 관리자 - 클래스 룸 생성
     * */
    @Override
    public int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        return classRoomInfoDAO.createClassRoom(classRoomInfoDTO);
        
    }

    /**
     * 관리자 - 코드 중복 체크
     * */
    @Override
    public String codeCheck(String classRoomCode) {
        
        return classRoomInfoDAO.codeCheck(classRoomCode);
    }

    /**
     * 관리자 - 강사 아이디 체크
     * */
    @Override
    public String teacherCheck(String teacherId) {
        
        return classRoomInfoDAO.teacherCheck(teacherId);
    }

    /**
     * 관리자 - 풀캘린더에 들어갈 클래스 일정 모두 가져오기
     * */
    @Override
    public List<ClassRoomInfoDTO> getClassInfo() {
        
        List<ClassRoomInfoDTO> list = classRoomInfoDAO.getClassInfo();
        
        for(ClassRoomInfoDTO dto : list) {
            
            dto.setClassRoomInfoStartDate(dto.getClassRoomInfoStartDate().substring(0,10));
            dto.setClassRoomInfoEndDate(dto.getClassRoomInfoEndDate().substring(0, 10));
        }
        
        return list;
    }

}
