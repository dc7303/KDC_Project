package edu.kosta.kdc.dao;

import java.sql.SQLException;

import edu.kosta.kdc.dto.MemberDTO;

public interface UserInfoDAO {

    public int insert(MemberDTO userDTO) throws SQLException;
    
    public MemberDTO findByUserId(String userId) throws SQLException;
}
