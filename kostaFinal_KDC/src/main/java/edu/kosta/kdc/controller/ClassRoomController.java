package edu.kosta.kdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.service.ClassRoomService;

@Controller
@RequestMapping("/classRoom")
public class ClassRoomController {
    
    @Autowired
    private ClassRoomService service;
    
    /**
     * ���纰 Ŭ������ ��ü ����Ʈ
     * */
    @RequestMapping("classList")
    public ModelAndView classList(String id) {
        
        List<ClassRoomInfoDTO> list = service.classList(id);
        
        //�ֿܼ��� Ȯ��
        for(ClassRoomInfoDTO dto:list) {
            System.out.println(dto.getClassRoomInfoName());
        }
        
        return new ModelAndView("classRoom/classList", "list", list);
        
    }
    
    /**
     * Ŭ������ ����
     * �Է¹��� �� : code, Ŭ�����̸�, ������, ������
     * �Ѱܹ��� �� : ����id
     * ä������ �̸��� �ӽ÷� Ŭ������code.txt
     * */
    /*@RequestMapping("/classCreate")
    public String classCreate(ClassRoomInfoDTO dto) {
        dto.setClassRoomCode("B10B");
        dto.setClassRoomInfoTeacherId("heejung");
        dto.setClassRoomInfoStartDate("2018-12-10");
        dto.setClassRoomInfoEndDate("2019-05-15");
        dto.setClassRoomInfoName("190�� �������ڰ���");
        
        service.classCreate(dto);
        
        return "redirect:/classRoom/classList";
        
    }*/
    
}
