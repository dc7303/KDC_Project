package edu.kosta.kdc.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.kosta.kdc.dto.AuthorityDTO;

@Repository
public interface AuthDAO {
    
    List<AuthorityDTO> findAuthByUserId(String userId) throws SQLException;
    
    int insertAuth(AuthorityDTO authDTO);
}
