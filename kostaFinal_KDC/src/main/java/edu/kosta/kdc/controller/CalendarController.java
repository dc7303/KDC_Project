package edu.kosta.kdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.CalendarDTO;
import edu.kosta.kdc.model.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;
    
    @RequestMapping(value = "/calendarSelectByClassCode")
    public ModelAndView calendarForm() {
        
        List<CalendarDTO> list = calendarService.calendarSelectByClassCode();
        
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
