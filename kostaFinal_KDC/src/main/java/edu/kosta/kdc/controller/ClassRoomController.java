package edu.kosta.kdc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.ClassRoomService;

@Controller
@RequestMapping("/classRoom")
public class ClassRoomController {
    
    @Autowired
    private ClassRoomService classRoomService;
    
    /**
     * ���������� - ����Ŭ������ ������ �̵�
     * */
    @RequestMapping("/myClassRoom")
    public ModelAndView myClassRoom(HttpServletRequest request) {
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //���� ������ ��ü Ŭ������ ����Ʈ ��������
        List<ClassRoomInfoDTO> myClassRoomList = classRoomService.classList(member.getMemberId());
        
        //�� �� �� ¥���� �޼ҵ�
        if(!myClassRoomList.isEmpty()) {
            for(ClassRoomInfoDTO dto:myClassRoomList) {
                dto.setClassRoomInfoStartDate(dto.getClassRoomInfoStartDate().substring(0, 10));
                dto.setClassRoomInfoEndDate(dto.getClassRoomInfoEndDate().substring(0, 10));
            }
        }
        
        
        //classRoomIsCurrent = 'TRUE' �� Ŭ������DTO ��������
        List<ClassRoomInfoDTO> classRoomIsCurrentList = classRoomService.selectCurrentClassRoom(member.getMemberId());
        
        //�� �� �� ¥���� �޼ҵ�
        if(!classRoomIsCurrentList.isEmpty()) {
            for(ClassRoomInfoDTO dto:classRoomIsCurrentList) {
                dto.setClassRoomInfoStartDate(dto.getClassRoomInfoStartDate().substring(0, 10));
                dto.setClassRoomInfoEndDate(dto.getClassRoomInfoEndDate().substring(0, 10));
            }
        }
        
        request.setAttribute("classRoomIsCurrentList", classRoomIsCurrentList);
        
        return new ModelAndView("classRoom/myClassRoom", "myClassRoomList", myClassRoomList);
        
    }
    
    /**
     * ���������� - ���� Ŭ���� �ڵ� insert
     * */
    @RequestMapping(value = "/insertMyClassRoom", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String insertMyClassRoom(String myClassRoomCode) {
        
        //�α��� �Ǿ��ִ� ���̵� �������� ����
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //�Ҹ� �� �־��ֱ� ���ؼ� ���� ����
        boolean result = false;
        
        //Ŭ������ dto ����
        ClassRoomDTO classRoomDTO = new ClassRoomDTO(member.getMemberId(), myClassRoomCode, result);

        String message = classRoomService.insertMyClassRoom(classRoomDTO);
        return message;
    }
    
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
     * myClassRoom ���������� radio ��ư ���� ���� �� �ش� �ڵ�� CurrentClass = True �� �ٲٴ� �޼ҵ�
     * */
    @RequestMapping(value = "/defaultClassSet", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String defaultClassSet(String classRoomCode) {
        
        //�α��� �Ǿ��ִ� ���̵� �������� ����
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //�Ҹ� �� �־��ֱ� ���ؼ� ���� ����
        boolean result = true;
        
        //Ŭ������ dto ����
        ClassRoomDTO classRoomDTO = new ClassRoomDTO(member.getMemberId(), classRoomCode, result);
        
        int re = classRoomService.defaultClassSet(classRoomDTO);
        
        return "����";
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
        
        String fileName = classRoomService.createClassRoom(classRoomInfoDTO).substring(16);
        
        System.out.println("fileName : " + fileName);
        //ä�ù� ���� ����
        File file = new File(path, fileName);
        file.createNewFile();
        
        return "index";
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
