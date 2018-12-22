package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.CalendarDAO;
import edu.kosta.kdc.model.dao.ClassRoomDAO;
import edu.kosta.kdc.model.dto.CalendarDTO;
import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarDAO calendarDAO;
    
    @Autowired
    private ClassRoomDAO classRoomDAO;

    /**
     * Ķ���� ��ȸ
     */
    @Override
    public List<CalendarDTO> calendarSelectByClassCode() {

        // �ݺ� ���������� ��� ���� Ŭ������ �ڵ�
        String classRoomCode = null;

        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   
        // �α��� �ȵȻ����� anonymousUser�� �ƴ� ��� ���� ����
        if (!contextHolder.toString().equals("anonymousUser")) {
            MemberDTO memberDTO = (MemberDTO) contextHolder;

            // ���� ����Ʈ�� ������ Ŭ�����ڵ� ��������
            ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
            if (classRoomDTO == null) {
                throw new KdcException("����Ʈ�� ������ Ŭ�������� �������� �ʽ��ϴ�. �������������� �������ּ���.");
            }

            classRoomCode = classRoomDTO.getClassRoomCode();
        } else {
            throw new KdcException("Kosta ������ �Ǵ� ���縸 ���ٰ����մϴ�.");
        }
        
        List<CalendarDTO> list = calendarDAO.calendarSelectByClassCode(classRoomCode);
        if(list == null) {
            throw new KdcException("������ �������� �ʽ��ϴ�.");
        }

        return list;
    }

    
    /**
     * Ķ���� �����߰�
     */
    @Override
    public int calendarInsert(CalendarDTO calendarDTO) {
        
        String classRoomCode = null;

        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // �α��� �ȵȻ����� anonymousUser�� �ƴ� ��� ���� ����
        if (!contextHolder.toString().equals("anonymousUser")) {
            MemberDTO memberDTO = (MemberDTO) contextHolder;

            // ���� ����Ʈ�� ������ Ŭ�����ڵ� ��������
            ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
            if (classRoomDTO == null) {
                throw new KdcException("����Ʈ�� ������ Ŭ�������� �������� �ʽ��ϴ�. �������������� �������ּ���.");
            }

            classRoomCode = classRoomDTO.getClassRoomCode();
        } else {
            throw new KdcException("Kosta ������ �Ǵ� ���縸 ���ٰ����մϴ�.");
        }
        
        //Ŭ���� �ڵ� ����
        calendarDTO.setClassRoomCode(classRoomCode);
        
        int result = 0;
        
        result = calendarDAO.calendarInsert(calendarDTO);
        if(result == 0) {
            throw new KdcException("������ ��� �����Դϴ�.");
        }
        
        return result;
    }

    
    @Override
    public int calendarUpdate(CalendarDTO calendarDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int calendarDelete(CalendarDTO calendarDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

}
