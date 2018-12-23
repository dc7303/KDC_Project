package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.CalendarDAO;
import edu.kosta.kdc.model.dto.CalendarDTO;

@Repository
public class CalendarDAOImpl implements CalendarDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 클래스 코드로 일정 가져오기.
     */
    @Override
    public List<CalendarDTO> calendarSelectByClassCode(String classRoomCode) {

        return sqlSession.selectList("calendarMapper.selectByClassRoomCode", classRoomCode);
    
    }

    @Override
    public int calendarInsert(CalendarDTO calendarDTO) {
        
        return sqlSession.insert("calendarMapper.insert", calendarDTO);
    }

    @Override
    public int calendarUpdateDate(CalendarDTO calendarDTO) {
        
        return sqlSession.update("calendarMapper.updateDate", calendarDTO);
    }

    @Override
    public int calendarDelete(int calendarPk) {

        return sqlSession.delete("calendarMapper.deleteDate", calendarPk);
    }



}
