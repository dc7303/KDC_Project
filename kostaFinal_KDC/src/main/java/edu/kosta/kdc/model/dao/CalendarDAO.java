package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.CalendarDTO;

public interface CalendarDAO {

    /**
     * ���� ��ȸ
     * 
     * @return
     */
    List<CalendarDTO> calendarSelectByClassCode(String classRoomCode);
    
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
    int calendarUpdateDate(CalendarDTO calendarDTO);
    
    /**
     * ���� ����
     * 
     * @param calendarDTO
     * @return
     */
    int calendarDelete(int calendarPk);
}
