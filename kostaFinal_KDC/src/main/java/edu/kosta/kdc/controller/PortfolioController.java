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
 * ��Ʈ������ CRUD ��Ʈ������ ������ 1��1�� ����
 */
@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService service;

    /**
     * ���������� ��Ʈ������
     */
    @RequestMapping("/myPage")
    public String myPage(Model model) {
        /*
         * ��ť��Ƽ���� �α��ε� 
         * ȸ�������� �޾ƿ�
         * */
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //�α��ε� ������� ��Ʈ������, �� ������ ��ȸ
        PortfolioDTO portfolioDTO = service.selectPortfolioByMemberId(member.getMemberId());
        model.addAttribute("portfolio", portfolioDTO);
        
        return "portfolio/myPage";
    }

    /**
     * ��Ʈ������ ����
     */
    @RequestMapping("/insertPortfolio")
    public String insertPortfolio(HttpSession session, PortfolioDTO portfolioDTO) throws KdcException {
        // �̹����� ������� ������� ���ϻ�������
        if (!portfolioDTO.getMainImageFile().isEmpty()) {
            // ��ǥ�̹����� ����� ��ġ
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // ����ڰ� ÷���� ����
            MultipartFile file = portfolioDTO.getMainImageFile();
            // ���ϸ��� DTO�� setter�� �̿��� ����
            portfolioDTO.setPortFolioMainImage(saveImage(path, file));
        }
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        portfolioDTO.setPortFolioMemberId(member.getMemberId());
        
        int result = service.insertPortfolio(portfolioDTO);

        return "redirect:/portfolio/myPage";
    }

    /**
     * ��Ʈ������ �� ���� ��
     */
    @RequestMapping("/detailForm")
    public void detailForm() {
    }

    /**
     * ��Ʈ������ �� ����
     */
    @RequestMapping("/insertDetail")
    public String insertDetail(HttpSession session, PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {

        // �̹����� ������� ������� ���ϻ�������
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
     * ��Ʈ������ ����
     * */
    @RequestMapping("/updatePortfolio")
    public String updatePortfolio(HttpSession session, PortfolioDTO portfolioDTO) {
        
        // �̹����� ������� ������� ���ϻ�������
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
     * ��Ʈ������ �� ����(detail�Ѱ�)
     * */
    @RequestMapping("/selectDetail/{detailPk}")
    public String selectDetail(@PathVariable int detailPk,Model model) {
        
        PortfolioDetailDTO portfolioDetailDTO = service.selectDetailByPk(detailPk);
        model.addAttribute("detail", portfolioDetailDTO);
        
        return "portfolio/myPageDetail";
        
    }
    
    /**
     * ��Ʈ������ �� ���� ��
     * */
    @RequestMapping("/updateDetailForm/{detailPk}")
    public String updateDerailForm(@PathVariable int detailPk, Model model) {
        
        PortfolioDetailDTO portfolioDetailDTO = service.selectDetailByPk(detailPk);
        model.addAttribute("detail", portfolioDetailDTO);
        
        return "portfolio/detailForm";
    }
    
    /**
     * ��Ʈ������ �� ����
     * */
    @RequestMapping("/updateDetail")
    public String updateDetail(HttpSession session, PortfolioDetailDTO portfolioDetailDTO, String hashTagName) {
        int detailPk = portfolioDetailDTO.getPortFolioDetailPk();
        // �̹����� ������� ������� ���ϻ�������
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
     * ��Ʈ������ �� ����
     * */
    @RequestMapping("/deleteDetail/{detailPk}")
    public String deleteDetail(@PathVariable int detailPk) {
        int result = service.deleteDetail(detailPk);
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * ��� ��Ʈ������ ��ȸ
     * */
    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        List<PortfolioDTO> list = service.selectAll();
        model.addAttribute("portfolioList", list);
        return "portfolio/selectAll";
    }
    
    /**
     * �� ��ȸ(�� ������ ��Ʈ������)
     * */
    @RequestMapping("/selectAllDetail/{memberId}")
    public String selectAllDetail(@PathVariable String memberId, Model model){
        PortfolioDTO portfolioDTO= service.selectAllDetail(memberId);
        model.addAttribute("portfolio", portfolioDTO);
        return "portfolio/selectAllDetail";
    }
    
    /**
     * �з��� Ű���� �˻� 
     * */
    @RequestMapping("/portfolioListSearch")
    public String selectByKeyword(Model model, String keyfield, String keyword) {
        List<PortfolioDTO> list= service.selectByKeyword(keyfield,keyword);
        
        model.addAttribute("portfolioList", list);
        return "portfolio/selectAll";
    }
    

    
    
    /**
     * �̹��� ���� �޼ҵ�
     * */
    private String saveImage(String path, MultipartFile file) {
        String saveFilename=file.getOriginalFilename();
        
        /*
         * ���̰� 50����Ʈ �̻��� ���
         * ���ϸ��� �ڿ������� 50��(Ȯ���� �����ϹǷ�)
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
