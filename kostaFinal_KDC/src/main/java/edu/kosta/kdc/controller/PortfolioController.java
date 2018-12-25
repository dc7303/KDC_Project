package edu.kosta.kdc.controller;

import java.io.File;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.MemberDTO;
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
     * 마이페이지 포트폴리오
     */
    @RequestMapping("/myPage")
    public String myPage(Model model) {
        /*
         * 시큐리티에서 로그인된 
         * 회원정보를 받아옴
         * */
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //로그인된 사용자의 포트폴리오, 상세 정보를 조회
        PortfolioDTO portfolioDTO = service.selectPortfolioByMemberId(member.getMemberId());
        model.addAttribute("portfolio", portfolioDTO);
        
        return "portfolio/myPage";
    }

    /**
     * 포트폴리오 생성
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
            portfolioDTO.setPortFolioMainImage(saveImage(path, file));
        }
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        portfolioDTO.setPortFolioMemberId(member.getMemberId());
        
        int result = service.insertPortfolio(portfolioDTO);

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
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            MultipartFile file = portfolioDetailDTO.getDeltailProjectImage();
            portfolioDetailDTO.setPortfolioDeltailProjectImage(saveImage(path, file));
        }
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        portfolioDetailDTO.setPortFolioDetailMemberId(member.getMemberId());
        
        int result = service.insertDetail(portfolioDetailDTO,hashTagName);
        
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * 포트폴리오 수정
     * */
    @RequestMapping("/updatePortfolio")
    public String updatePortfolio(HttpSession session, PortfolioDTO portfolioDTO) {
        
        // 이미지를 등록하지 않을경우 파일생성안함
        if (!portfolioDTO.getMainImageFile().isEmpty()) {
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            MultipartFile file = portfolioDTO.getMainImageFile();
            portfolioDTO.setPortFolioMainImage(saveImage(path, file));
        }
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        portfolioDTO.setPortFolioMemberId(member.getMemberId());
        
        int result = service.updatePortfolio(portfolioDTO);
        
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * 포트폴리오 상세 보기(detail한개)
     * */
    @RequestMapping("/selectDetail/{detailPk}")
    public String selectDetail(@PathVariable int detailPk,Model model) {
        
        PortfolioDetailDTO portfolioDetailDTO = service.selectDetailByPk(detailPk);
        model.addAttribute("detail", portfolioDetailDTO);
        
        return "portfolio/myPageDetail";
        
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
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            MultipartFile file = portfolioDetailDTO.getDeltailProjectImage();
            portfolioDetailDTO.setPortfolioDeltailProjectImage(saveImage(path, file));
            
        }
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        portfolioDetailDTO.setPortFolioDetailMemberId(member.getMemberId());
        
        int result = service.updateDetail(portfolioDetailDTO, hashTagName);
        
        return "redirect:selectDetail/"+detailPk;
    }
    
    /**
     * 포트폴리오 상세 삭제
     * */
    @RequestMapping("/deleteDetail/{detailPk}")
    public String deleteDetail(@PathVariable int detailPk) {
        int result = service.deleteDetail(detailPk);
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * 모든 포트폴리오 조회
     * */
    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        List<PortfolioDTO> list = service.selectAll();
        model.addAttribute("portfolioList", list);
        return "portfolio/selectAll";
    }
    
    /**
     * 상세 조회(한 유저의 포트폴리오)
     * */
    @RequestMapping("/selectAllDetail/{memberId}")
    public String selectAllDetail(@PathVariable String memberId, Model model){
        PortfolioDTO portfolioDTO= service.selectAllDetail(memberId);
        model.addAttribute("portfolio", portfolioDTO);
        return "portfolio/selectAllDetail";
    }
    
    /**
     * 분류별 키워드 검색 
     * */
    @RequestMapping("/portfolioListSearch")
    public String selectByKeyword(Model model, String keyfield, String keyword) {
        List<PortfolioDTO> list= service.selectByKeyword(keyfield,keyword);
        
        model.addAttribute("portfolioList", list);
        return "portfolio/selectAll";
    }
    

    
    
    /**
     * 이미지 저장 메소드
     * */
    private String saveImage(String path, MultipartFile file) {
        String saveFilename=file.getOriginalFilename();
        
        /*
         * 길이가 50바이트 이상일 경우
         * 파일명을 뒤에서부터 50자(확장자 들어가야하므로)
         * */
        if(saveFilename.getBytes().length > 50) {
            saveFilename = saveFilename.substring(saveFilename.length()-49, saveFilename.length());
        }
        
        try {
            file.transferTo(new File(path + "/" + saveFilename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveFilename;
    }
    
}
