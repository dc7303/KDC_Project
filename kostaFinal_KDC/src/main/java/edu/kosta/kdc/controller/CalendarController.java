package edu.kosta.kdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * Ķ���� ���� ���
     * 
     * @param calendarDTO
     * @return
     */
    @RequestMapping(value = "/calendarInsert", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String calendarInsert(@RequestParam(value="title") String calendarTItle, 
            @RequestParam(value="start") String calendarStart, 
            @RequestParam(value="end") String calendarEnd) {
        
        //Ķ���� PK�� �������̱� ������ 0, ClassCode�� ���񽺿��� �ҷ���.
        calendarService.calendarInsert(new CalendarDTO(0, null, calendarTItle, calendarStart, calendarEnd));
        
        return "";
    }
    
    /**
     * ���� �巡�� �� ������� ������Ʈ ����
     * 
     * @return
     */
    @RequestMapping(value = "/calendarDropUpdate", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String calendarDropUpdate(@RequestParam(value="num") int calendarPk,
            @RequestParam(value="start") String calendarStart, 
            @RequestParam(value="end") String calendarEnd) {
        
        //classCode, title �� Null
        calendarService.calendarUpdate(new CalendarDTO(calendarPk, null, null, calendarStart, calendarEnd));
        
        return "";
    }
}
