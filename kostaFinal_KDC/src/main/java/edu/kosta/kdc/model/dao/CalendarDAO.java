package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.CalendarDTO;

public interface CalendarDAO {

    /**
     * ���� ��ȸ
     * 
     * @return
     */
    List<CalendarDTO> calendarSelectAll();
    
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
