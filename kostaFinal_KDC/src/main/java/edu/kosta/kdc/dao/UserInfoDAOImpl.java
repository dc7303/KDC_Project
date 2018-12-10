package edu.kosta.kdc.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.dto.UserInfoDTO;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

    @Autowired
    private SqlSession session;
    
    @Override
    public int insert(UserInfoDTO userDTO) throws SQLException {
        
        return session.insert("userMapper.userInsert", userDTO);
    }

    @Override
    public UserInfoDTO findByUserId(String userId) throws SQLException {
        
        return session.selectOne("userMapper.findByUserId", userId);
    }

}
