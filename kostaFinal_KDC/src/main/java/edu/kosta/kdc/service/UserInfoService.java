package edu.kosta.kdc.service;

import java.sql.SQLException;

import edu.kosta.kdc.dto.UserInfoDTO;

public interface UserInfoService {

    public int insert(UserInfoDTO userDTO) throws SQLException;
}
