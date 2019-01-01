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
     * 마이페이지 - 나의클래스룸 페이지 이동
     * */
    @RequestMapping("/myClassRoom")
    public ModelAndView myClassRoom(HttpServletRequest request) {
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //내가 수강한 전체 클래스룸 리스트 가져오기
        List<ClassRoomInfoDTO> myClassRoomList = classRoomService.classList(member.getMemberId());
        
        //시 분 초 짜르는 메소드
        if(!myClassRoomList.isEmpty()) {
            for(ClassRoomInfoDTO dto:myClassRoomList) {
                dto.setClassRoomInfoStartDate(dto.getClassRoomInfoStartDate().substring(0, 10));
                dto.setClassRoomInfoEndDate(dto.getClassRoomInfoEndDate().substring(0, 10));
            }
        }
        
        
        //classRoomIsCurrent = 'TRUE' 인 클래스룸DTO 가져오기
        List<ClassRoomInfoDTO> classRoomIsCurrentList = classRoomService.selectCurrentClassRoom(member.getMemberId());
        
        //시 분 초 짜르는 메소드
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
     * 마이페이지 - 나의 클래스 코드 insert
     * */
    @RequestMapping(value = "/insertMyClassRoom", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String insertMyClassRoom(String myClassRoomCode) {
        
        //로그인 되어있는 아이디 가져오는 변수
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //불린 값 넣어주기 위해서 만든 변수
        boolean result = false;
        
        //클래스룸 dto 만듦
        ClassRoomDTO classRoomDTO = new ClassRoomDTO(member.getMemberId(), myClassRoomCode, result);

        String message = classRoomService.insertMyClassRoom(classRoomDTO);
        return message;
    }
    
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
     * myClassRoom 페이지에서 radio 버튼 선택 했을 때 해당 코드로 CurrentClass = True 로 바꾸는 메소드
     * */
    @RequestMapping(value = "/defaultClassSet", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String defaultClassSet(String classRoomCode) {
        
        //로그인 되어있는 아이디 가져오는 변수
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //불린 값 넣어주기 위해서 만든 변수
        boolean result = true;
        
        //클래스룸 dto 만듦
        ClassRoomDTO classRoomDTO = new ClassRoomDTO(member.getMemberId(), classRoomCode, result);
        
        int re = classRoomService.defaultClassSet(classRoomDTO);
        
        return "성공";
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
    public String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO,HttpSession session) throws Exception{

        //채팅방 파일을 만들 경로를 알아온다.
        String path = "C:\\Edu\\chatFile\\";
        
        //DB에서 랜덤하게 파일 명을 생성하므로, 스케줄러를 이용하기 위해 경로를 DTO안에 chatFile에 set시킨다.
        classRoomInfoDTO.setClassRoomInfoChatFile(path);
        
        String fileName = classRoomService.createClassRoom(classRoomInfoDTO).substring(16);
        
        System.out.println("fileName : " + fileName);
        //채팅방 파일 생성
        File file = new File(path, fileName);
        file.createNewFile();
        
        return "index";
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
