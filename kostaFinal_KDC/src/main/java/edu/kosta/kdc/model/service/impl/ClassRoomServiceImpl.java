package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
