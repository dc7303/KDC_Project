package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.AdminDAO;
@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * �湮�� �� �����ϴ� �޼ҵ�
     * */
    @Override
    public int userCountIntoDB(int todayUserCount) {
        
        return sqlSession.insert("adminMapper.insertTodayUserCount", todayUserCount);
    }

}
