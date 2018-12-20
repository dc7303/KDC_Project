package edu.kosta.kdc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;
import edu.kosta.kdc.model.service.PortfolioService;

/**
 * 포트폴리오 CRUD 포트폴리오 유저와 1대1로 생성
 */

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService service;

    /**
     * 포트폴리오를 생성하기위한 폼
     * IF 포트폴리오가 존재하면 수정버튼 노출
     *    포트폴리오 상세 노출
     */
    @RequestMapping("/myPage")
    public String myPage(Model model) {
        String memberId = "DONGS";
        
        //로그인된 사용자의 포트폴리오, 상세 정보를 조회
        PortfolioDTO portfolioDTO = service.selectPortfolioByMemberId(memberId);
        model.addAttribute("portfolio", portfolioDTO);
        
        return "portfolio/myPageDummy";
    }

    /**
     * 생성요청시 처리할 controller
     */
    @RequestMapping("/insertPortfolio")
    public String insertPortfolio(HttpSession session, PortfolioDTO portfolioDTO) throws KdcException {
        // 이미지를 등록하지 않을경우 파일생성안함
        if (!portfolioDTO.getMainImageFile().isEmpty()) {
            // 대표이미지가 저장될 위치
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // 사용자가 첨부한 파일
            MultipartFile file = portfolioDTO.getMainImageFile();
            // 파일명을 DTO에 setter를 이용해 대입
            portfolioDTO.setPortFolioMainImage(file.getOriginalFilename());
            try {
                file.transferTo(new File(path + "/" + portfolioDTO.getPortFolioMainImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        service.insertPortfolio(portfolioDTO);

        return "redirect:/portfolio/myPage";
    }

    /**
     * 포트폴리오 상세 생성 폼
     */
    @RequestMapping("/detailForm")
    public void detailForm() {
    }

    /**
     * 포트폴리오 상세 생성
     */
    @RequestMapping("/insertDetail")
    public String insertDetail(HttpSession session, PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {

        // 이미지를 등록하지 않을경우 파일생성안함
        if (!portfolioDetailDTO.getDeltailProjectImage().isEmpty()) {
            // 대표이미지가 저장될 위치
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // 사용자가 첨부한 파일
            MultipartFile file = portfolioDetailDTO.getDeltailProjectImage();
            // 파일명을 DTO에 setter를 이용해 대입
            portfolioDetailDTO.setPortfolioDeltailProjectImage(file.getOriginalFilename());
            try {
                file.transferTo(new File(path + "/" + portfolioDetailDTO.getPortfolioDeltailProjectImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        service.insertDetail(portfolioDetailDTO,hashTagName);
        
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * 포트폴리오 수정
     * */
    @RequestMapping("/updatePortfolio")
    public String updatePortfolio(HttpSession session, PortfolioDTO portfolioDTO) {
        
        // 이미지를 등록하지 않을경우 파일생성안함
        if (!portfolioDTO.getMainImageFile().isEmpty()) {
            // 대표이미지가 저장될 위치
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // 사용자가 첨부한 파일
            MultipartFile file = portfolioDTO.getMainImageFile();
            // 파일명을 DTO에 setter를 이용해 대입
            portfolioDTO.setPortFolioMainImage(file.getOriginalFilename());
            try {
                file.transferTo(new File(path + "/" + portfolioDTO.getPortFolioMainImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        service.updatePortfolio(portfolioDTO);
        
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * 포트폴리오 상세 보기(detail한개)
     * */
    @RequestMapping("/selectDetail/{detailPk}")
    public String selectDetail(@PathVariable int detailPk,Model model) {
        
        PortfolioDetailDTO portfolioDetailDTO = service.selectDetailByPk(detailPk);
        model.addAttribute("detail", portfolioDetailDTO);
        
        return "portfolio/detailDetail";
        
    }
    
    /**
     * 포트폴리오 상세 수정 폼
     * */
    @RequestMapping("/updateDetailForm/{detailPk}")
    public String updateDerailForm(@PathVariable int detailPk, Model model) {
        
        PortfolioDetailDTO portfolioDetailDTO = service.selectDetailByPk(detailPk);
        model.addAttribute("detail", portfolioDetailDTO);
        
        return "portfolio/detailForm";
    }
    
    /**
     * 포트폴리오 상세 수정
     * */
    @RequestMapping("/updateDetail")
    public String updateDetail(HttpSession session, PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {
        int detailPk = portfolioDetailDTO.getPortFolioDetailPk();
        // 이미지를 등록하지 않을경우 파일생성안함
        if (!portfolioDetailDTO.getDeltailProjectImage().isEmpty()) {
            // 대표이미지가 저장될 위치
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // 사용자가 첨부한 파일
            MultipartFile file = portfolioDetailDTO.getDeltailProjectImage();
            // 파일명을 DTO에 setter를 이용해 대입
            portfolioDetailDTO.setPortfolioDeltailProjectImage(file.getOriginalFilename());
            try {
                file.transferTo(new File(path + "/" + portfolioDetailDTO.getPortfolioDeltailProjectImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.updateDetail(portfolioDetailDTO, hashTagName);
        return "redirect:selectDetail/"+detailPk;
    }
    
    /**
     * 포트폴리오 상세 삭제
     * */
    @RequestMapping("/deleteDetail/{detailPk}")
    public String deleteDetail(@PathVariable int detailPk) {
        service.deleteDetail(detailPk);
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * 모든 포트폴리오 조회(isDelte = true, isVisibility = true)
     * */
    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        List<PortfolioDTO> list = service.selectAll();
        model.addAttribute("portfolioList", list);
        return "portfolio/selectAll";
    }
    
    /**
     * 상세 조회(한 유저의 port)
     * */
    @RequestMapping("/selectAllDetail/{memberId}")
    public String selectAllDetail(@PathVariable String memberId, Model model){
        PortfolioDTO portfolioDTO= service.selectAllDetail(memberId);
        model.addAttribute("portfolio", portfolioDTO);
        return "portfolio/selectAllDetail";
    }
    
}
