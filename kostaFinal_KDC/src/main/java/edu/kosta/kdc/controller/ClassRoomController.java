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
     * 강사별 클래스룸 전체 리스트
     * */
    @RequestMapping("classList")
    public ModelAndView classList(String id) {
        
        List<ClassRoomInfoDTO> list = service.classList(id);
        
        //콘솔에서 확인
        for(ClassRoomInfoDTO dto:list) {
            System.out.println(dto.getClassRoomInfoName());
        }
        
        return new ModelAndView("classRoom/classList", "list", list);
        
    }
    
    /**
     * 클래스룸 생성
     * 입력받을 값 : code, 클래스이름, 시작일, 종료일
     * 넘겨받을 값 : 강사id
     * 채팅파일 이름은 임시로 클래스룸code.txt
     * */
    /*@RequestMapping("/classCreate")
    public String classCreate(ClassRoomInfoDTO dto) {
        dto.setClassRoomCode("B10B");
        dto.setClassRoomInfoTeacherId("heejung");
        dto.setClassRoomInfoStartDate("2018-12-10");
        dto.setClassRoomInfoEndDate("2019-05-15");
        dto.setClassRoomInfoName("190기 웹개발자과정");
        
        service.classCreate(dto);
        
        return "redirect:/classRoom/classList";
        
    }*/
    
}
