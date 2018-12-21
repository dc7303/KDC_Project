package edu.kosta.kdc.model.dao.impl;

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
     *  ��ü �˻�
     */
    @Override
    public List<NoticeBoardDTO> selectAll(NoticeBoardDTO noticeBoard) {
        
        List<NoticeBoardDTO> list = session.selectList("noticeBoardMapper.noticeBoardSelect");
        return list;
    }

    /**
     * ���ڵ� ����
     */
    @Override
    public int insert(NoticeBoardDTO noticeBoard) {
        
        return session.insert("noticeBoardMapper.noticeBoardInsert", noticeBoard);
    }
    
    /**
     *  ���� �����ؼ� �󼼺���
     */
    @Override
    public NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPk)  {
        
      return session.selectOne("noticeBoardMapper.SelectNoticeBoardPK",noticeBoardPk);
       
    }

    /**
     * �����ϱ�
     */
    @Override
    public int update(NoticeBoardDTO noticeBoard) {
        
        return session.update("noticeBoardMapper.noticeBoardUpdate", noticeBoard);
    }

    /**
     * �����ϱ�
     */
    @Override
    public int delete(int noticeBoardPk) {
        
        return session.delete("noticeBoardMapper.noticeBoardDelete",noticeBoardPk );
        
    }

    /**
     * ��ȸ�� ����
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
 }
