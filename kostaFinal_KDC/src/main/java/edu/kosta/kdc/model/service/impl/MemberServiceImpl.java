package edu.kosta.kdc.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.AuthorityDAO;
import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;
    
    @Autowired
    private AuthorityDAO authorityDAO;//���� ����
    
    @Autowired
    private PasswordEncoder passwordEncoder;//Insert, update, delete�� �н����� ���ڵ� 

    /**
     * ��� ȸ������
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * ��� ���� ����
     */
    @Override
    public int memberUpdate(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * ȸ������ ����
     */
    @Override 
    public int memberDelete(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
    
}
