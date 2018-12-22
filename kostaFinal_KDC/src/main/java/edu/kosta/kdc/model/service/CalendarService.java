package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.CalendarDTO;

public interface CalendarService {
    
    /**
     * ���� ��ȸ
     * 
     * @return
     */
    List<CalendarDTO> calendarSelectByClassCode();
    
    /**
     * ���� �߰�
     * 
     * @param calendarDTO
     * @return
     */
    int calendarInsert(CalendarDTO calendarDTO);
    
    /**
     * ���� ����
     * 
     * @param calendarDTO
     * @return
     */
    int calendarUpdate(CalendarDTO calendarDTO);
    
    /**
     * ���� ����
     * 
     * @param calendarDTO
     * @return
     */
    int calendarDelete(CalendarDTO calendarDTO);
}
