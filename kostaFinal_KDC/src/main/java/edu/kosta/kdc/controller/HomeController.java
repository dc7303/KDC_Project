package edu.kosta.kdc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(HttpSession session) {
        
        String userId = "aa";
        
        session.setAttribute("userId", userId);
        
        return "test";
    }
    
}
