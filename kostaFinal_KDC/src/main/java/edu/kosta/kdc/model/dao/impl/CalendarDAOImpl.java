package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.CalendarDAO;
import edu.kosta.kdc.model.dto.CalendarDTO;

@Repository
public class CalendarDAOImpl implements CalendarDAO {

    @Override
    public List<CalendarDTO> calendarSelectAll() {
        // TODO Auto-generated method stub
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
