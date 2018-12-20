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
     * ������ - Ŭ���� �� ����
     * */
    @Override
    public int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        return classRoomInfoDAO.createClassRoom(classRoomInfoDTO);
        
    }

    /**
     * ������ - �ڵ� �ߺ� üũ
     * */
    @Override
    public String codeCheck(String classRoomCode) {
        
        return classRoomInfoDAO.codeCheck(classRoomCode);
    }

    /**
     * ������ - ���� ���̵� üũ
     * */
    @Override
    public String teacherCheck(String teacherId) {
        
        return classRoomInfoDAO.teacherCheck(teacherId);
    }

    /**
     * ������ - ǮĶ������ �� Ŭ���� ���� ��� ��������
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
