package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.CalendarDTO;

public interface CalendarDAO {

    /**
     * 일정 조회
     * 
     * @return
     */
    List<CalendarDTO> calendarSelectByClassCode(String classRoomCode);
    
    /**
     * 일정 추가
     * 
     * @param calendarDTO
     * @return
     */
    int calendarInsert(CalendarDTO calendarDTO);
    
    /**
     * 일정 수정
     * 
     * @param calendarDTO
     * @return
     */
    int calendarUpdateDate(CalendarDTO calendarDTO);
    
    /**
     * 일정 삭제
     * 
     * @param calendarDTO
     * @return
     */
    int calendarDelete(int calendarPk);
}
