package edu.kosta.kdc.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import edu.kosta.kdc.model.dto.PageDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;
import edu.kosta.kdc.util.interfaces.PageHandler;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    @Autowired
    private NoticeBoardDAO noticeBoardDAO;
    
    @Autowired
    private ClassRoomDAO classRoomDAO;
    
    @Autowired
    private PageHandler pageHandler;
    
    /**
     *  전체검색
     */
    @Override
    public Map<String, Object> selectAll(String classification, int pageNum) {
        
        //반별 공지사항일 경우 사용될 클래스룸 코드
        String classRoomCode = null;
        
        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //반별 공지사항일 경우 디폴트로 설정된 클래스룸을 가져온다.
        if(classification != null && classification.equals("classNotice")) {
            
            //로그인 안된상태인 anonymousUser가 아닐 경우 로직 수행
            if(!contextHolder.toString().equals("anonymousUser")) {
                MemberDTO memberDTO = (MemberDTO)contextHolder;
                
                //현재 디폴트로 설정된 클래스코드 가져오기
                ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
                if(classRoomDTO == null) {
                    throw new KdcException("디폴트로 설정된 클래스룸이 존재하지 않습니다. 마이페이지에서 설정해주세요.");
                }
                
                classRoomCode = classRoomDTO.getClassRoomCode();
            }else {
                throw new KdcException("Kosta 수강생 또는 강사만 접근가능합니다.");
            }
        }
        
        int totalCount = noticeBoardDAO.boardQuantityByClassification(classification);
        PageDTO pageDTO = pageHandler.pageInfoSet(pageNum, 10, 10, totalCount);
        
        int firstColumnPage = pageDTO.getFirstColumnRange();
        int lastColumnPage = pageDTO.getLastColumnRange();
        
        Map<String, Object> map = new HashMap<>();
        
        if(classRoomCode != null) {
            map.put("classRoomCode", classRoomCode);
        }
        
        map.put("classification", classification);
        map.put("firstColumnPage", firstColumnPage);
        map.put("lastColumnPage", lastColumnPage);
        
        List<NoticeBoardDTO> list = noticeBoardDAO.selectAllForPaging(map);
        
        if(list == null) {
            throw new KdcException("게시글이 존재하지않습니다.");
        }
        
        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("noticeList", list);
        resultMap.put("pageDTO", pageDTO);
        
        return resultMap;
    }
 
    /**
     *  레코드 삽입
     */
    @Override
    public int noticeInsert(NoticeBoardDTO noticeBoard, String classification) {
        
        //반별 공지사항일 경우 사용될 클래스룸 코드
        String classRoomCode = null;
        
        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        
        //반별 공지사항일 경우 디폴트로 설정된 클래스룸을 가져온다.
        if(classification != null && classification.equals("classNotice")) {
            
            //로그인 안된상태인 anonymousUser가 아닐 경우 로직 수행
            if(!contextHolder.toString().equals("anonymousUser")) {
                MemberDTO memberDTO = (MemberDTO)contextHolder;
                
                //현재 디폴트로 설정된 클래스코드 가져오기
                ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
                if(classRoomDTO == null) {
                    throw new KdcException("디폴트로 설정된 클래스룸이 존재하지 않습니다. 마이페이지에서 설정해주세요.");
                }
                
                noticeBoard.setNoticeBoardWriterId(memberDTO.getMemberId());
                classRoomCode = classRoomDTO.getClassRoomCode();
            }else {
                throw new KdcException("Kosta 수강생 또는 강사만 접근가능합니다.");
            }
        }
        
        //DTO 셋팅
        noticeBoard.setNoticeBoardClassification(classification);
        noticeBoard.setNoticeBoardClassRoomCode(classRoomCode);
        System.out.println(classRoomCode);
        
        int result = 0;
        //게시글 Insert
        result = noticeBoardDAO.noticeInsert(noticeBoard);
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
    public List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch, String classification) {
     
        //게시글 조회
        List<NoticeBoardDTO> list = noticeBoardDAO.SelechSerch(department, noticeBoardSearch, classification);
        if(list == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        return list;
    }
    
    /**
     * 메인 페이지에 띄울 공지사항 글 5개 가져오는 메소드
     * 
     * @param
     * @return List<NoticeBoardDTO>
     * */
    @Override
    public List<NoticeBoardDTO> selectFive() {
        
        return noticeBoardDAO.selectFive();
    }

    /**
     * 페이징처리 조회
     */
    @Override
    public int selectNoticePagingCount(String department, String noticeBoardSearch, String classification) {

        return noticeBoardDAO.selectNoticePagingCount(department, noticeBoardSearch, classification);
    }

    /**
     * 페이징 처리시 조회할 컬럼 counter
     */
    @Override
    public Map<String, Object> selectNoticePaging(String department, String noticeBoardSearch, String classification, int pageNum) {
        
        //게시글 count
        int totalCount = noticeBoardDAO.selectNoticePagingCount(department, noticeBoardSearch, classification);
        PageDTO pageDTO = pageHandler.pageInfoSet(pageNum, 10, 10, totalCount);
        
        int firstColumnPage = pageDTO.getFirstColumnRange();
        int lastColumnPage = pageDTO.getLastColumnRange();
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("classification", classification);
        map.put("firstColumnPage", firstColumnPage);
        map.put("lastColumnPage", lastColumnPage);
        map.put("department", department);
        map.put("noticeBoardSearch", noticeBoardSearch);
        
        List<NoticeBoardDTO> list = noticeBoardDAO.selectNoticePaging(map);
        if(list == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("noticeList", list);
        resultMap.put("pageDTO", pageDTO);
        
        
        return resultMap;
    }
    
 }
