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
    public List<NoticeBoardDTO> selectAll(NoticeBoardDTO noticeBoard) {
        
        List<NoticeBoardDTO> list = session.selectList("noticeBoardMapper.noticeBoardSelect");
        return list;
    }

    /**
     * 레코드 삽입
     */
    @Override
    public int insert(NoticeBoardDTO noticeBoard) {
        
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
    public void update(NoticeBoardDTO noticeBoard) {
        
        System.out.println(noticeBoard);
        session.update("noticeBoardMapper.noticeBoardUpdate", noticeBoard);
        
    }

    /**
     * 삭제하기
     */
    @Override
    public void delete(int noticeBoardPk) {
        session.delete("noticeBoardMapper.noticeBoardDelete",noticeBoardPk );
        
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
        List<NoticeBoardDTO> list = session.selectList("noticeBoardMapper.SearchTitleAndContents",map);
        return list;
    }
 }


    

    
    


   


 



 



    

   


  

 
    

