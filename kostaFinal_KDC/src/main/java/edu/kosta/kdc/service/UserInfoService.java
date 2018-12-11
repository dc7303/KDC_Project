package edu.kosta.kdc.service;

import java.sql.SQLException;

import edu.kosta.kdc.dto.MemberDTO;

public interface UserInfoService {

    public int insert(MemberDTO userDTO) throws SQLException;
}
