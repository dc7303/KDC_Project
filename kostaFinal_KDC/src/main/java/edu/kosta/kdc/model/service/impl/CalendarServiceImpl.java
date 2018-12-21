package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.CalendarDAO;
import edu.kosta.kdc.model.dto.CalendarDTO;
import edu.kosta.kdc.model.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {

    private CalendarDAO calendarDAO;
    
    /**
     * Ä¶¸°´õ Á¶È¸
     */
    @Override
    public List<CalendarDTO> calendarSelectAll() {
        
        return null;
    }
    
    @Override
    public int calendarInsert(CalendarDTO calendarDTO) {
        // TODO Auto-generated method stub
        return 0;
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
