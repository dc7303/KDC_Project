package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.CalendarDTO;

public interface CalendarService {
    
    /**
     * 일정 조회
     * 
     * @return
     */
    List<CalendarDTO> calendarSelectByClassCode();
    
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
    int calendarUpdate(CalendarDTO calendarDTO);
    
    /**
     * 일정 삭제
     * 
     * @param calendarDTO
     * @return
     */
    int calendarDelete(CalendarDTO calendarDTO);
}
