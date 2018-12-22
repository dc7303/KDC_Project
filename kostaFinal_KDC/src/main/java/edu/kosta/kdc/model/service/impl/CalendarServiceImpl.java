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
     * 캘린더 조회
     */
    @Override
    public List<CalendarDTO> calendarSelectByClassCode() {

        // 반별 공지사항일 경우 사용될 클래스룸 코드
        String classRoomCode = null;

        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   
        // 로그인 안된상태인 anonymousUser가 아닐 경우 로직 수행
        if (!contextHolder.toString().equals("anonymousUser")) {
            MemberDTO memberDTO = (MemberDTO) contextHolder;

            // 현재 디폴트로 설정된 클래스코드 가져오기
            ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
            if (classRoomDTO == null) {
                throw new KdcException("디폴트로 설정된 클래스룸이 존재하지 않습니다. 마이페이지에서 설정해주세요.");
            }

            classRoomCode = classRoomDTO.getClassRoomCode();
        } else {
            throw new KdcException("Kosta 수강생 또는 강사만 접근가능합니다.");
        }
        
        List<CalendarDTO> list = calendarDAO.calendarSelectByClassCode(classRoomCode);
        if(list == null) {
            throw new KdcException("일정이 존재하지 않습니다.");
        }

        return list;
    }

    
    /**
     * 캘린더 일정추가
     */
    @Override
    public int calendarInsert(CalendarDTO calendarDTO) {
        
        String classRoomCode = null;

        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 로그인 안된상태인 anonymousUser가 아닐 경우 로직 수행
        if (!contextHolder.toString().equals("anonymousUser")) {
            MemberDTO memberDTO = (MemberDTO) contextHolder;

            // 현재 디폴트로 설정된 클래스코드 가져오기
            ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
            if (classRoomDTO == null) {
                throw new KdcException("디폴트로 설정된 클래스룸이 존재하지 않습니다. 마이페이지에서 설정해주세요.");
            }

            classRoomCode = classRoomDTO.getClassRoomCode();
        } else {
            throw new KdcException("Kosta 수강생 또는 강사만 접근가능합니다.");
        }
        
        //클래스 코드 셋팅
        calendarDTO.setClassRoomCode(classRoomCode);
        
        int result = 0;
        
        result = calendarDAO.calendarInsert(calendarDTO);
        if(result == 0) {
            throw new KdcException("스케줄 등록 실패입니다.");
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
