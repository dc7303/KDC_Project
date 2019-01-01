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
            throw new KdcException("�������� �ʽ��ϴ�.");
        }
        
        return list;
    }
    
    /**
     * ���� - Ŭ���� �� ����
     * */
    @Override
    @Transactional
    public String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        int result = 0;

        result = classRoomInfoDAO.createClassRoom(classRoomInfoDTO);
        if(result == 0) {
            throw new KdcException("Ŭ������ ���� ����");
        }

        //Ŭ������ ������ �������� ��, ä�� ���� ������ ���� ���ϸ�(���+���ϸ� ���� �Ǿ�����.) �� �����;� �Ѵ�.
        return classRoomInfoDAO.selectChatFileName(classRoomInfoDTO);
        
    }

    /**
     * ����- ���� ���̵� üũ
     * */
    @Override
    public String teacherCheck(String teacherId) {
        
        return classRoomInfoDAO.teacherCheck(teacherId);
    }

    /**
     * ���������� - member�� Ŭ���� �ڵ� �Է��ϸ� DB�� �ش� ������ �ڵ带 �Է½����ִ� �޼ҵ�
     * */
    @Override
    public String insertMyClassRoom(ClassRoomDTO classRoomDTO) {
        
        int result = 0;
        
        //Ŭ������ �ڵ尡 ��ϵǾ� �ִ��� üũ
        result = classRoomInfoDAO.codeCheck(classRoomDTO.getClassRoomCode());
        if(result == 0) {
            return "�������� �ʴ� Ŭ�����ڵ� �Դϴ�.";
        }
        
        //�̹� ���̵� ��ϵ� �ڵ����� �˻����ִ� �޼ҵ�
        result = classRoomDAO.selectMyClassRoomCodeByClassRoomDTO(classRoomDTO);
        if(result == 0) {
            return "�̹� ��ϵ� Ŭ�����ڵ� �Դϴ�.";
        }
        
        //Ŭ���� �ڵ�� ���̵� �����ؼ� DB�� ������. 
        result = classRoomDAO.insertMyClassRoom(classRoomDTO);
        if(result == 0) {
            throw new KdcException("Ŭ���� �ڵ� ��� ����");
        }
        
        //�������������� Ŭ���� �ڵ尡 DB�� ��� �Ǿ���.
        return "��ϵǾ����ϴ�.";
    }

    
    /**
     *  myClassRoom ���������� radio ��ư ���� ���� �� �ش� �ڵ�� CurrentClass = True �� �ٲٴ� �޼ҵ�
     * */
    @Override
    @Transactional
    public int defaultClassSet(ClassRoomDTO classRoomDTO) {
        
        int result = 0;
        
        //������Ʈ�� defaultClass�� �ٲٱ� ���� �ٸ� Class�� CurrentClass=False�� �ٲپ�� �Ѵ�.
        result = classRoomDAO.updateOtherCurrentClass(classRoomDTO);
        if(result == 0) {
            throw new KdcException("���������� �⺻ Ŭ���� ���� ����");
        }
        
        result = classRoomDAO.defaultClassSet(classRoomDTO);
        
        if(result == 0) {
            throw new KdcException("�⺻ Ŭ���� ��� ����");
        }
        
        return result;
        
    }

    /**
     * classRoomIsCurrent = 'TRUE' �� Ŭ������DTO ��������
     * */
    @Override
    public List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId) {
        
        return classRoomDAO.selectCurrentClassRoom(memberId);
    }

}
