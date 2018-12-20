package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;
import edu.kosta.kdc.exception.KdcException;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    @Autowired
    private NoticeBoardDAO noticeBoardDAO;
    
    /**
     *  전체검색
     */
    @Override
    public List<NoticeBoardDTO> selectAll(NoticeBoardDTO noticeBoard, boolean state) {
     
        List<NoticeBoardDTO> list = noticeBoardDAO.selectAll(noticeBoard);
        if(list == null) {
            throw new KdcException("게시글이 존재하지않습니다.");
        }
        
        if(state) {
            int views = noticeBoardDAO.noticeViewsUpdate(noticeBoard.getNoticeBoardPk());
            if(views==0)throw new KdcException("조회수 증가 오류입니다.");
        }
        return noticeBoardDAO.selectAll(noticeBoard);
    }
 
    /**
     *  레코드 삽입
     */
    @Override
    public int insert(NoticeBoardDTO noticeBoard) {
        return noticeBoardDAO.insert(noticeBoard);
        
    }
    
    /**
     *  제목 선택해서 상세보기
     */
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPk, boolean state) {
        if(state){
            int views = noticeBoardDAO.noticeViewsUpdate(noticeBoardPk);//조회수증가
            if(views==0) throw new KdcException("조회수 증가 오류입니다.");
        }
       
        return noticeBoardDAO.selectByNoticeBoardTitle(noticeBoardPk);
    }
    
    /**
     * 수정하기
     */
    @Override
    public int update(NoticeBoardDTO noticeBoard) {
        return noticeBoardDAO.update(noticeBoard);
    }
    
    /**
     *  삭제하기
     */
    @Override
    public int delete(int noticeBoardPk) {
        return noticeBoardDAO.delete(noticeBoardPk);
    }

    /**
     *   검색 리스트
     */
    @Override
    public List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch) {
     
        return noticeBoardDAO.SelechSerch(department, noticeBoardSearch);
    }

 }
