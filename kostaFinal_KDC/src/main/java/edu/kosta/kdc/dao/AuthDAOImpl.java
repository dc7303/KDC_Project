package edu.kosta.kdc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.dto.AuthDTO;

@Repository
public class AuthDAOImpl implements AuthDAO {

    @Autowired
    private SqlSession session;
    
    @Override
    public List<AuthDTO> findAuthByUserId(String userId) throws SQLException {
        
        return session.selectList("authMapper.authSelect", userId);
    }
    
    @Override
    public int insertAuth(AuthDTO authDTO) {
        
        return session.insert("authMapper.authInsert", authDTO);
    }

}
