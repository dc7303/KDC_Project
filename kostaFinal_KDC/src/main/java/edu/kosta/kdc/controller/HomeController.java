package edu.kosta.kdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.util.KdcException;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        
        return "index";
    }
    
}
