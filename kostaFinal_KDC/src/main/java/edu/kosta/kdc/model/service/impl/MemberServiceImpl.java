package edu.kosta.kdc.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.AuthorityDAO;
import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.AuthorityDTO;
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
     * ��� ���̵� üũ
     */
    @Override
    public boolean memberSelectByMemberId(String memberId) {
        
        boolean result = false;
        MemberDTO memberDTO= memberDAO.memberSelectByMemberId(memberId);
        
        //�˻� ��� DTO ������ ��� True ��ȯ
        if(memberDTO != null) result = true;
        
        return result;
    }

    /**
     * ��� �г��� üũ
     */
    @Override
    public boolean memberSelectByMemberNickName(String memberNickName) {

        boolean result = false;
        MemberDTO memberDTO = memberDAO.memberSelectByMemberNickName(memberNickName);
        
        //�˻� ��� DTO ������ ��� True ��ȯ
        if(memberDTO != null) result = true;
        
        return result;
    }
    
    /**
     * ��� ȸ������
     */
    @Override
    @Transactional
    public int memberInsert(MemberDTO memberDTO, String authCode) {

        //password ���ڵ�
        String encodePwd = passwordEncoder.encode(memberDTO.getMemberPwd());
        //���ڵ��� password ����
        memberDTO.setMemberPwd(encodePwd);
        
        int result = 0;
        
        //ȸ�� ���� Insert
        result = memberDAO.memberInsert(memberDTO);
        if(result == 0) {
            throw new RuntimeException("ȸ�� ���� �Է��� �߸��Ǿ����ϴ�.");
        }
        
        //���� Insert
        result = authorityDAO.authorityInsert(new AuthorityDTO(memberDTO.getMemberId(), authCode));
        if(result == 0) {
            throw new RuntimeException("���� �Է��� �߸��Ǿ����ϴ�.");
        }
        
        return result;
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
