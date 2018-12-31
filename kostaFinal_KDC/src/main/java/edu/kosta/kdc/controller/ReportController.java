package edu.kosta.kdc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    
    /**
     * 신고 자세히보기
     * 
     * @param reportPk
     * @return
     */
    @RequestMapping(value = "/reportRead", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ReportDTO reportRead(int reportPk) {
        
        ReportDTO reportDTO = reportService.selectByReportPk(reportPk);
        
        return reportDTO;
    }
}
