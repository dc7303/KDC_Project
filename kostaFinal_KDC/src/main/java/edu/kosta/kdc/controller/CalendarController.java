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
     * Ķ���� ������ �̵�
     * @return
     */
    @RequestMapping(value = "/calendarForm")
    public String calendarForm() {
        
        return "classRoom/calendarForm";
    }
    
    /**
     * Ķ���� ��ȸ�ϱ�
     * @return
     */
    @RequestMapping(value = "/calendarSelectByClassCode", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CalendarDTO> calendarSelect() {
        
        List<CalendarDTO> list = calendarService.calendarSelectByClassCode();
        
        return list;
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
     * ���� drag & drop ���� ������Ʈ �� Resize�� 
     * 
     * @return
     */
    @RequestMapping(value = "/calendarUpdateDate", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String calendarUpdateDate(@RequestParam(value="num") int calendarPk, 
            @RequestParam(value="title") String calendarTitle,
            @RequestParam(value="start") String calendarStart, 
            @RequestParam(value="end") String calendarEnd) {
        
        //classCode, title �� Null
        calendarService.calendarUpdateDate(new CalendarDTO(calendarPk, null, calendarTitle, calendarStart, calendarEnd));
        
        return "";
    }
    
    /**
     * ���� ���� ���μ���
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
