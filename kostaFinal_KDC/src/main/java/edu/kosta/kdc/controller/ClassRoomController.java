package edu.kosta.kdc.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.CalendarDTO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.service.ClassRoomService;

@Controller
@RequestMapping("/classRoom")
public class ClassRoomController {
    
    @Autowired
    private ClassRoomService classRoomService;
    
    /**
     * 강사별 클래스룸 전체 리스트
     * */
    @RequestMapping("/classList")
    public ModelAndView classList(String id) {
        
        List<ClassRoomInfoDTO> list = classRoomService.classList(id);
        
        //콘솔에서 확인
        for(ClassRoomInfoDTO dto:list) {
            System.out.println(dto.getClassRoomInfoName());
        }
        
        return new ModelAndView("classRoom/classList", "list", list);
        
    }
    
    
    /**
     * 강사 - 클래스 룸 생성 페이지 이동
     * */
    @RequestMapping("/classRoomInsertForm")
    public void createClassRoomInfo() {}
    
    /**
     * 강사 - 클래스 룸 생성 + 각 채팅방 파일 생성 (파일이름 : 클래스 코드.txt)
     * */
    @RequestMapping("/insertClassRoom")
    public String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) throws Exception{

        File file = new File("C:\\Edu\\final_workspace\\kostaFinal_KDC\\src\\main\\webapp\\resources\\chatFile", classRoomInfoDTO.getClassRoomInfoChatFile());
        file.createNewFile();
        
        classRoomService.createClassRoom(classRoomInfoDTO);
        
        return "classRoom/classList";
    }
    
    /**
     * 강사 - 클래스 코드 이름 중복 체크 (ajax)
     * */
    @RequestMapping(value = "/codeCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String codeCheck(String classRoomCode) {
        
        return classRoomService.codeCheck(classRoomCode);
        
    }
    
    /**
     * 강사 - 강사 아이디 체크 (ajax)
     * */
    @RequestMapping(value = "/teacherCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String teacherCheck(String teacherId) {
        
        return classRoomService.teacherCheck(teacherId);
        
    }
    
       
}
