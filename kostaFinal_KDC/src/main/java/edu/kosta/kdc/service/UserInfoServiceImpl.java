package edu.kosta.kdc.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.dao.AuthDAO;
import edu.kosta.kdc.dao.UserInfoDAO;
import edu.kosta.kdc.dto.AuthorityDTO;
import edu.kosta.kdc.dto.MemberDTO;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDAO dao;
    
    @Autowired
    private AuthDAO authDAO;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @RequestMapping("/insert")
    public int insert(MemberDTO userDTO) throws SQLException {
        
        String encodePwd = null;
        
        encodePwd = passwordEncoder.encode(userDTO.getUserPwd());
        userDTO.setUserPwd(encodePwd);
        
        int result = 0;
        
        result = dao.insert(userDTO);
        if(result == 0) {
            throw new SQLException("가입실패");
        }
        
        result = authDAO.insertAuth(new AuthorityDTO(userDTO.getUserId(), userDTO.getAuth()));
        if(result == 0) {
            throw new SQLException("가입실패");
        }
        
        return result;
    }
    
    
    @RequestMapping("/authAdmin")
    public String authTest() {
        
        return "admin";
    }
    

}
