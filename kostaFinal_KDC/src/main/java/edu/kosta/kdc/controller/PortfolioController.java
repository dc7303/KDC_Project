package edu.kosta.kdc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * ��Ʈ�������� �����ϱ����� �� -> myPage��ü
     */
    @RequestMapping("/")
    public String myPage() {
        return "portfolio/myPageDummy";
    }

    /**
     * ������û�� ó���� controller
     */
    @RequestMapping("/insertPortfolio")
    public String insertPortfolio(HttpSession session, PortfolioDTO portfolioDTO) throws KdcException {
        // �ش� ���̵� ���� ��Ʈ�������� �����ϴ��� �ߺ��˻�
        // (SQLException���� ó�����ֹǷ� ���ʿ� �� �� ����)
        if (service.selectByMemberId(portfolioDTO.getPortFolioMemberId())) {
            // �̹� ��Ʈ�������� �����ϴ°��
            throw new KdcException("��Ʈ������ ������ �����߽��ϴ�.");
        }

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

        int result = service.insertPortfolio(portfolioDTO);

        if (result == 0)
            throw new KdcException("��Ʈ������ ������ �����߽��ϴ�.");

        return "portfolio/myPageDummy";
    }

    /**
     * ��Ʈ������ �� ��
     */
    @RequestMapping("/detailForm")
    public void detailForm() {
    }

    /**
     * ��Ʈ������ �� ����
     */
    @RequestMapping("/insertDetail")
    public String insertDetail(HttpSession session, PortfolioDetailDTO portfolioDetailDTO) {

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

        int result = service.insertDetail(portfolioDetailDTO);
        if (result == 0)
            throw new KdcException("��Ʈ������ �� ������ �����߽��ϴ�.");
        return "portfolio/myPageDummy";
    }

}
