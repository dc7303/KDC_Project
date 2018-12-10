package edu.kosta.kdc.dao;

import java.sql.SQLException;

import edu.kosta.kdc.dto.UserInfoDTO;

public interface UserInfoDAO {

    public int insert(UserInfoDTO userDTO) throws SQLException;
    
    public UserInfoDTO findByUserId(String userId) throws SQLException;
}
