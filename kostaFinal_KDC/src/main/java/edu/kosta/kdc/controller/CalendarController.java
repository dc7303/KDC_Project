package edu.kosta.kdc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.CalendarDTO;
import edu.kosta.kdc.model.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private CalendarService calendarService;
    
    @RequestMapping(value = "/calendarSelectAll")
    public ModelAndView calendarForm(String memberId) {
        
        List<CalendarDTO> list = calendarService.calendarSelectAll();
        
        return new ModelAndView("classRoom/calendarForm", "calendarList", list);
    }
    
    /**
     * 캘린더 일정 등록
     * 
     * @param calendarDTO
     * @return
     */
    @RequestMapping(value = "/calendarInsert", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String calendarInsert(CalendarDTO calendarDTO) {
        
        return "";
    }
}
