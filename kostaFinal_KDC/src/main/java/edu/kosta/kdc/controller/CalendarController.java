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
    
    /**
     * 캘린더 폼으로 이동
     * @return
     */
    @RequestMapping(value = "/calendarForm")
    public String calendarForm() {
        
        return "classRoom/calendarForm";
    }
    
    /**
     * 캘린더 조회하기
     * @return
     */
    @RequestMapping(value = "/calendarSelectByClassCode", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CalendarDTO> calendarSelect() {
        
        List<CalendarDTO> list = calendarService.calendarSelectByClassCode();
        
        return list;
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
     * 일정 drag & drop 으로 업데이트 및 Resize시 
     * 
     * @return
     */
    @RequestMapping(value = "/calendarUpdateDate", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String calendarUpdateDate(@RequestParam(value="num") int calendarPk, 
            @RequestParam(value="title") String calendarTitle,
            @RequestParam(value="start") String calendarStart, 
            @RequestParam(value="end") String calendarEnd) {
        
        //classCode, title 값 Null
        calendarService.calendarUpdateDate(new CalendarDTO(calendarPk, null, calendarTitle, calendarStart, calendarEnd));
        
        return "";
    }
    
    /**
     * 일정 삭제 프로세서
     * 
     * @return
     */
    @RequestMapping(value = "/calendarDelete", produces = "text/plain; cahrset=UTF-8")
    @ResponseBody
    public String calendarDelete(@RequestParam(value="num") int calendarPk) {
        
        calendarService.calendarDelete(calendarPk);
        
        return "";
    }

}
