package edu.kosta.kdc.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.dto.UserInfoDTO;
import edu.kosta.kdc.service.UserInfoService;

@Controller
public class UserInfoController {
    
    @Autowired
    private UserInfoService service;
    
    @RequestMapping("/insert")
    public String insert(UserInfoDTO userDTO) {
        
        try {
            service.insert(userDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "success";
    }
    
    @RequestMapping("/loginForm")
    public String loginForm() {
        
        return "loginForm";
    }
    
    @RequestMapping("/adminTest")
    public String adminTest() {
        
        return "admin";
    }
}
