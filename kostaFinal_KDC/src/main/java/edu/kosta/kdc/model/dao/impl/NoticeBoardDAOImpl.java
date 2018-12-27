package edu.kosta.kdc.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;

@Repository
public class NoticeBoardDAOImpl implements NoticeBoardDAO {
    
    @Autowired
    private SqlSession session;
    
    /**
     *  전체 검색
     */
    @Override
    public List<NoticeBoardDTO> selectAll(String classification, String classRoomCode) {
        
        Map<String, String> map = new HashMap<>();      //파라미터 담을 Map
        
        map.put("classification", classification);
        //반별게시판으로 접근했다면
        if(classRoomCode != null) {
           map.put("classRoomCode", classRoomCode);
        }
        
        return session.selectList("noticeBoardMapper.noticeBoardSelect", map);
    }

    /**
     * 레코드 삽입
     */
    @Override
    public int noticeInsert(NoticeBoardDTO noticeBoard) {
        
        return session.insert("noticeBoardMapper.noticeBoardInsert", noticeBoard);
    }
    
    /**
     *  제목 선택해서 상세보기
     */
    @Override
    public NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPk)  {
        
      return session.selectOne("noticeBoardMapper.SelectNoticeBoardPK",noticeBoardPk);
       
    }

    /**
     * 수정하기
     */
    @Override
    public int update(NoticeBoardDTO noticeBoard) {
        
        return session.update("noticeBoardMapper.noticeBoardUpdate", noticeBoard);
    }

    /**
     * 삭제하기
     */
    @Override
    public int delete(int noticeBoardPk) {
        
        return session.delete("noticeBoardMapper.noticeBoardDelete",noticeBoardPk );
        
    }

    /**
     * 조회수 증가
     */
    @Override
    public int noticeViewsUpdate(int noticeBoardPk) {
        
        return session.update("noticeBoardMapper.noticeBoardViewsUpdate" ,noticeBoardPk);
     
       
    }

    @Override
    public List<NoticeBoardDTO> SelechSerch(String department, String noticeBoardSearch) {
        
        Map<String, Object> map = new HashMap<>();
        map.put("department", department);
        map.put("noticeBoardSearch", noticeBoardSearch);
        
        return session.selectList("noticeBoardMapper.SearchTitleAndContents",map);
    }
    /**
     * classification 기준으로 컬럼 수량 가져오기.
     *
     * @param classification
     * @return
     */
    @Override
    public int boardQuantityByClassification(String classification) {
        
        return session.selectOne("noticeBoardMapperboardQuantityByClassification", classification);
    }
 }
