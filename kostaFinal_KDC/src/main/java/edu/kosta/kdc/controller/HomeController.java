package edu.kosta.kdc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        
        return "index";
    }
    
}
