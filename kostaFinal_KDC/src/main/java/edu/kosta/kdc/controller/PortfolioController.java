package edu.kosta.kdc.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.service.PortfolioService;
import edu.kosta.kdc.util.KdcException;

/**
 * 포트폴리오 CRUD 
 * 포트폴리오 유저와 1대1로 생성
 * */

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {
    
    @Autowired
    private  PortfolioService service;
    
    //포트폴리오를 생성하기위한 폼 -> myPage대체
    @RequestMapping("/")
    public String myPage() {
        return "portfolio/myPageDummy";
    }
    
    //폼에서 생성요청시 처리할 controller
    @RequestMapping("/insertPortfolio")
    public String insertPortfolio(HttpSession session, PortfolioDTO portfolioDTO)throws KdcException {
        
        //대표이미지가 저장될 위치
        String path = session.getServletContext().getRealPath("/resources/testimg/photos");
        //사용자가 첨부한 파일
        MultipartFile file = portfolioDTO.getMainImageFile();
        //파일명을 DTO에 setter를 이용해 대입
        portfolioDTO.setPortFolioMainImage(file.getOriginalFilename());
        try {
            file.transferTo(new File(path+"/"+portfolioDTO.getPortFolioMainImage()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(portfolioDTO);
        int result = service.insertPortfolio(portfolioDTO);
        if(result==0) throw new KdcException("포트폴리오 생성을 실패했습니다.");
       
        return "portfolio/myPageDummy";
    }
    
}
