package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * ������ - ���� ȸ������
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        
        return memberDAO.memberInsert(memberDTO);
        
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
    
    /**
     * ��� ȸ�� ���� ��������
     * */
    @Override
    public List<MemberDTO> selectAll() {

        return memberDAO.selectAll();

    }
    
    /**
     * ������ ���������� ���̵� �˻� �� �ش� ���� �������� �޼ҵ�
     * */
    @Override
    public List<MemberDTO> selectByUserId(String userId) {
        
        return memberDAO.selectByUserId(userId);
    
    }

    /**
     * ������ ���������� ���� ��ư Ŭ�� �� ���� ����
     * */
    @Override
    public int deleteMemberByUserId(String userId) {

        return memberDAO.deleteByUserId(userId);
        
    }
    
    
    
}
