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
 * 포트폴리오 CRUD 포트폴리오 유저와 1대1로 생성
 */

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService service;

    /**
     * 포트폴리오를 생성하기위한 폼 -> myPage대체
     */
    @RequestMapping("/")
    public String myPage() {
        return "portfolio/myPageDummy";
    }

    /**
     * 생성요청시 처리할 controller
     */
    @RequestMapping("/insertPortfolio")
    public String insertPortfolio(HttpSession session, PortfolioDTO portfolioDTO) throws KdcException {
        // 해당 아이디에 대한 포트폴리오가 존재하는지 중복검사
        // (SQLException에서 처리해주므로 불필요 할 수 있음)
        if (service.selectByMemberId(portfolioDTO.getPortFolioMemberId())) {
            // 이미 포트폴리오가 존재하는경우
            throw new KdcException("포트폴리오 생성을 실패했습니다.");
        }

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

        int result = service.insertPortfolio(portfolioDTO);

        if (result == 0)
            throw new KdcException("포트폴리오 생성을 실패했습니다.");

        return "portfolio/myPageDummy";
    }

    /**
     * 포트폴리오 상세 폼
     */
    @RequestMapping("/detailForm")
    public void detailForm() {
    }

    /**
     * 포트폴리오 상세 생성
     */
    @RequestMapping("/insertDetail")
    public String insertDetail(HttpSession session, PortfolioDetailDTO portfolioDetailDTO) {

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

        int result = service.insertDetail(portfolioDetailDTO);
        if (result == 0)
            throw new KdcException("포트폴리오 상세 생성을 실패했습니다.");
        return "portfolio/myPageDummy";
    }

}
