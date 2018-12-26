package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.exception.KdcException;
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
        if(list == null) {
            throw new KdcException("존재하지 않습니다.");
        }
        
        return list;
    }
    
    /**
     * 강사 - 클래스 룸 생성
     * */
    @Override
    public int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        int result = 0;

        result = classRoomInfoDAO.createClassRoom(classRoomInfoDTO);
        if(result == 0) {
            throw new KdcException("클래스룸 생성 실패");
        }
        
        return result;
        
    }

    /**
     * 강사 - 코드 중복 체크
     * */
    @Override
    public String codeCheck(String classRoomCode) {
        
        return classRoomInfoDAO.codeCheck(classRoomCode);
    }

    /**
     * 강사- 강사 아이디 체크
     * */
    @Override
    public String teacherCheck(String teacherId) {
        
        return classRoomInfoDAO.teacherCheck(teacherId);
    }


}
