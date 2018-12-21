package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ClassRoomDAO;
import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    @Autowired
    private NoticeBoardDAO noticeBoardDAO;
    
    @Autowired
    private ClassRoomDAO classRoomDAO;
    
    /**
     *  전체검색
     */
    @Override
    public List<NoticeBoardDTO> selectAll(String classification) {
        
        //반별 공지사항일 경우 사용될 클래스룸 코드
        String classRoomCode = null;
        
        //반별 공지사항일 경우 디폴트로 설정된 클래스룸을 가져온다.
        if(classification.equals("classNotice")) {
            MemberDTO memberDTO = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
            ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
            if(classRoomDTO == null) {
                throw new KdcException("디폴트로 설정된 클래스룸이 존재하지 않습니다. 마이페이지에서 설정해주세요.");
            }
            
            classRoomCode = classRoomDTO.getClassRoomCode();
        }
        
        List<NoticeBoardDTO> list = noticeBoardDAO.selectAll(classification, classRoomCode);
        if(list == null) {
            throw new KdcException("게시글이 존재하지않습니다.");
        }
        
        return list;
    }
 
    /**
     *  레코드 삽입
     */
    @Override
    public int insert(NoticeBoardDTO noticeBoard) {
        
        int result = 0;
        //게시글 Insert
        result = noticeBoardDAO.insert(noticeBoard);
        if(result == 0) {
            throw new KdcException("작성 실패입니다.");
        }
        
        return result;
        
    }
    
    /**
     *  제목 선택해서 상세보기
     */
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPk, boolean state) {
        
        //게시글 조회 
        NoticeBoardDTO noticeDTO = noticeBoardDAO.selectByNoticeBoardTitle(noticeBoardPk);
        if(noticeDTO == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        //조회수 증가
        if(state){
            int views = noticeBoardDAO.noticeViewsUpdate(noticeBoardPk);//조회수증가
            if(views == 0) throw new KdcException("조회수 증가 오류입니다.");
        }
       
        return noticeDTO;
    }
    
    /**
     * 수정하기
     */
    @Override
    public int update(NoticeBoardDTO noticeBoard) {
        
        int result = 0;
        //게시글 update
        result = noticeBoardDAO.update(noticeBoard);
        if(result == 0) {
            throw new KdcException("업데이트 실패입니다.");
        }
        
        return result;
    }
    
    /**
     *  삭제하기
     */
    @Override
    public int delete(int noticeBoardPk) {
        
        int result = 0;
        //게시글 delte
        result = noticeBoardDAO.delete(noticeBoardPk);
        if(result == 0) {
            throw new KdcException("삭제 실패입니다.");
        }
        
        return result;
    }

    /**
     *   검색 리스트
     */
    @Override
    public List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch) {
     
        //게시글 조회
        List<NoticeBoardDTO> list = noticeBoardDAO.SelechSerch(department, noticeBoardSearch);
        if(list == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        return list;
    }

 }
