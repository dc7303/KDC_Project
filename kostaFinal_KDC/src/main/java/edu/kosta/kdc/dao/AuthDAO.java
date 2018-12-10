package edu.kosta.kdc.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.kosta.kdc.dto.AuthDTO;

@Repository
public interface AuthDAO {
    
    List<AuthDTO> findAuthByUserId(String userId) throws SQLException;
    
    int insertAuth(AuthDTO authDTO);
}
