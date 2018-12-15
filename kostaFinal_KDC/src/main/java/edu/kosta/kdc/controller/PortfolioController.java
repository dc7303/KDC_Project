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
 * ��Ʈ������ CRUD 
 * ��Ʈ������ ������ 1��1�� ����
 * */

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {
    
    @Autowired
    private  PortfolioService service;
    
    //��Ʈ�������� �����ϱ����� �� -> myPage��ü
    @RequestMapping("/")
    public String myPage() {
        return "portfolio/myPageDummy";
    }
    
    //������ ������û�� ó���� controller
    @RequestMapping("/insertPortfolio")
    public String insertPortfolio(HttpSession session, PortfolioDTO portfolioDTO)throws KdcException {
        
        //��ǥ�̹����� ����� ��ġ
        String path = session.getServletContext().getRealPath("/resources/testimg/photos");
        //����ڰ� ÷���� ����
        MultipartFile file = portfolioDTO.getMainImageFile();
        //���ϸ��� DTO�� setter�� �̿��� ����
        portfolioDTO.setPortFolioMainImage(file.getOriginalFilename());
        try {
            file.transferTo(new File(path+"/"+portfolioDTO.getPortFolioMainImage()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(portfolioDTO);
        int result = service.insertPortfolio(portfolioDTO);
        if(result==0) throw new KdcException("��Ʈ������ ������ �����߽��ϴ�.");
       
        return "portfolio/myPageDummy";
    }
    
}
