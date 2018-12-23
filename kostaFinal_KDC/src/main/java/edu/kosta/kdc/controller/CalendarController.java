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
     * 캘린더 일정 등록
     * 
     * @param calendarDTO
     * @return
     */
    @RequestMapping(value = "/calendarInsert", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String calendarInsert(@RequestParam(value="title") String calendarTItle, 
            @RequestParam(value="start") String calendarStart, 
            @RequestParam(value="end") String calendarEnd) {
        
        //캘린더 PK값 시퀀스이기 때문에 0, ClassCode는 서비스에서 불러옴.
        calendarService.calendarInsert(new CalendarDTO(0, null, calendarTItle, calendarStart, calendarEnd));
        
        return "";
    }
    
    /**
     * 일정 드래그 앤 드롭으로 업데이트 진행
     * 
     * @return
     */
    @RequestMapping(value = "/calendarDropUpdate", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String calendarDropUpdate(@RequestParam(value="num") int calendarPk,
            @RequestParam(value="start") String calendarStart, 
            @RequestParam(value="end") String calendarEnd) {
        
        //classCode, title 값 Null
        calendarService.calendarUpdate(new CalendarDTO(calendarPk, null, null, calendarStart, calendarEnd));
        
        return "";
    }
}
