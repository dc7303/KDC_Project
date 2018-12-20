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
 * ��Ʈ������ CRUD ��Ʈ������ ������ 1��1�� ����
 */

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService service;

    /**
     * ��Ʈ�������� �����ϱ����� ��
     * IF ��Ʈ�������� �����ϸ� ������ư ����
     *    ��Ʈ������ �� ����
     */
    @RequestMapping("/myPage")
    public String myPage(Model model) {
        String memberId = "DONGS";
        
        //�α��ε� ������� ��Ʈ������, �� ������ ��ȸ
        PortfolioDTO portfolioDTO = service.selectPortfolioByMemberId(memberId);
        model.addAttribute("portfolio", portfolioDTO);
        
        return "portfolio/myPageDummy";
    }

    /**
     * ������û�� ó���� controller
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
            // ��ǥ�̹����� ����� ��ġ
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // ����ڰ� ÷���� ����
            MultipartFile file = portfolioDetailDTO.getDeltailProjectImage();
            // ���ϸ��� DTO�� setter�� �̿��� ����
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
     * ��Ʈ������ ����
     * */
    @RequestMapping("/updatePortfolio")
    public String updatePortfolio(HttpSession session, PortfolioDTO portfolioDTO) {
        
        // �̹����� ������� ������� ���ϻ�������
        if (!portfolioDTO.getMainImageFile().isEmpty()) {
            // ��ǥ�̹����� ����� ��ġ
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // ����ڰ� ÷���� ����
            MultipartFile file = portfolioDTO.getMainImageFile();
            // ���ϸ��� DTO�� setter�� �̿��� ����
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
     * ��Ʈ������ �� ����(detail�Ѱ�)
     * */
    @RequestMapping("/selectDetail/{detailPk}")
    public String selectDetail(@PathVariable int detailPk,Model model) {
        
        PortfolioDetailDTO portfolioDetailDTO = service.selectDetailByPk(detailPk);
        model.addAttribute("detail", portfolioDetailDTO);
        
        return "portfolio/detailDetail";
        
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
            // ��ǥ�̹����� ����� ��ġ
            String path = session.getServletContext().getRealPath("/resources/testimg/photos");
            // ����ڰ� ÷���� ����
            MultipartFile file = portfolioDetailDTO.getDeltailProjectImage();
            // ���ϸ��� DTO�� setter�� �̿��� ����
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
     * ��Ʈ������ �� ����
     * */
    @RequestMapping("/deleteDetail/{detailPk}")
    public String deleteDetail(@PathVariable int detailPk) {
        service.deleteDetail(detailPk);
        return "redirect:/portfolio/myPage";
    }
    
    /**
     * ��� ��Ʈ������ ��ȸ(isDelte = true, isVisibility = true)
     * */
    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        List<PortfolioDTO> list = service.selectAll();
        model.addAttribute("portfolioList", list);
        return "portfolio/selectAll";
    }
    
    /**
     * �� ��ȸ(�� ������ port)
     * */
    @RequestMapping("/selectAllDetail/{memberId}")
    public String selectAllDetail(@PathVariable String memberId, Model model){
        PortfolioDTO portfolioDTO= service.selectAllDetail(memberId);
        model.addAttribute("portfolio", portfolioDTO);
        return "portfolio/selectAllDetail";
    }
    
}
