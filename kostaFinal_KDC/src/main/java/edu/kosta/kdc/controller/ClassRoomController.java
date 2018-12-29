package edu.kosta.kdc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

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
     * ���纰 Ŭ������ ��ü ����Ʈ
     * */
    @RequestMapping("/classList")
    public ModelAndView classList(String id) {
        
        List<ClassRoomInfoDTO> list = classRoomService.classList(id);
        
        //�ֿܼ��� Ȯ��
        for(ClassRoomInfoDTO dto:list) {
            System.out.println(dto.getClassRoomInfoName());
        }
        
        return new ModelAndView("classRoom/classList", "list", list);
        
    }
    
    
    /**
     * ���� - Ŭ���� �� ���� ������ �̵�
     * */
    @RequestMapping("/classRoomInsertForm")
    public void createClassRoomInfo() {}
    
    /**
     * ���� - Ŭ���� �� ���� + �� ä�ù� ���� ���� (�����̸� : Ŭ���� �ڵ�.txt)
     * */
    @RequestMapping("/insertClassRoom")
    public String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO,HttpSession session) throws Exception{

        //ä�ù� ������ ���� ��θ� �˾ƿ´�.
        String path = "C:\\Edu\\chatFile\\";
        
        //DB���� �����ϰ� ���� ���� �����ϹǷ�, �����ٷ��� �̿��ϱ� ���� ��θ� DTO�ȿ� chatFile�� set��Ų��.
        classRoomInfoDTO.setClassRoomInfoChatFile(path);
        
        String fileName = classRoomService.createClassRoom(classRoomInfoDTO);
        
        System.out.println("fileName : " + fileName);
        //ä�ù� ���� ����
        File file = new File(path, fileName);
        file.createNewFile();
        
        return "classRoom/classList";
    }
    
    /**
     * ���� - Ŭ���� �ڵ� �̸� �ߺ� üũ (ajax)
     * */
    @RequestMapping(value = "/codeCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String codeCheck(String classRoomCode) {
        
        return classRoomService.codeCheck(classRoomCode);
        
    }
    
    /**
     * ���� - ���� ���̵� üũ (ajax)
     * */
    @RequestMapping(value = "/teacherCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String teacherCheck(String teacherId) {
        
        return classRoomService.teacherCheck(teacherId);
        
    }
    
       
}
