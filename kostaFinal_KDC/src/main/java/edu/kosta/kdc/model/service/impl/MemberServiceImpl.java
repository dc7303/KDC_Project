package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
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
    public MemberDTO memberSelectByMemberId(String memberId) {
        
        //������ ���� ��Ʈ�ѷ����� null�� ���ؼ� �޼��� ��� ����
        return memberDAO.memberSelectByMemberId(memberId);
        
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
            throw new KdcException("ȸ�� ���� �Է��� �߸��Ǿ����ϴ�.");
        }
        
        //�ڵ��Է��� ���� �� ����� ���Ե�
        if(authCode.equals("") || authCode == null) {
            authCode = "ROLE_MEMBER";
        }
        
        //���� Insert
        result = authorityDAO.authorityInsert(new AuthorityDTO(memberDTO.getMemberId(), authCode));
        if(result == 0) {
            throw new KdcException("���� �Է��� �߸��Ǿ����ϴ�.");
        }
        
        return result;
    }

    /**
     * ��� ���� ����
     */
    @Override
    public int updateByMemberInfo(MemberDTO memberDTO) {
        
        //���ڵ� �н����� ����
        String encodePwd = passwordEncoder.encode(memberDTO.getMemberPwd());
        memberDTO.setMemberPwd(encodePwd);
        
        int result = 0;
        
        result = memberDAO.updateByMemberInfo(memberDTO);
        if(result == 0) throw new KdcException("���� �����Դϴ�.");
        
        return result;
    }

    /**
     * ȸ������ ����
     */
    @Override 
    public int updateByIsWithDrawal(String memberId) throws KdcException {

        int result = 0;
        
        result = memberDAO.updateByIsWithDrawal(memberId);
        if(result == 0) throw new KdcException("Ż�� �����Դϴ�.");
        
        return result;
    }    

    /**
     * ��� ȸ�� ���� ��������
     * */
    @Override
    public List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange) {

        List<MemberDTO> list = memberDAO.memberSelectAll(firstColumnRange, lastColumnRange);
        if(list == null) {
            throw new KdcException("ȸ���� �������� �ʽ��ϴ�.");
        }
        
        return list;

    }

    /**
     * ��� ��ü �� ��������
     * 
     * @return
     */
    @Override
    public int memberTotalCount() {
        
        int result = 0;
        
        result = memberDAO.memberTotalCount();
        if(result == 0) {
            throw new KdcException("ȸ���� �������� �ʽ��ϴ�.");
        }
        
        return result;
    }
    
    /**
     * �ӽú�й�ȣ db�� update���ֱ�
     * */
    @Override
    public int updatePwdByEmail(String uuid, String email) {
      //���ڵ� �н����� ����
        String encodePwd = passwordEncoder.encode(uuid);
        System.out.println("���ڵ��� Pwd : "+encodePwd);
        int result = memberDAO.updatePwdByEmail(encodePwd, email);
        System.out.println("��� : "+ result);
        return result;
    }

    /**
     * ��� �̸��� üũ
     */
    @Override
    public boolean memberByEmailCheck(String emailCheck) {
        boolean result = false;
        MemberDTO memberDTO = memberDAO.memberByEmailCheck(emailCheck);
        
        //�˻� ��� DTO ������ ��� True ��ȯ
        if(memberDTO != null) result = true;
        
        return result;
    }
    
    /**
     * ��� ������ ���� ��������
     */
    @Override
    public int memberSelectByKewordQuntity(String keyword, String word) {
        
        return memberDAO.memberSelectByKewordQuntity(keyword, word);
    }

    /**
     * ��� Ű���� �˻�
     */
    @Override
    public List<MemberDTO> memberSelectByKeyword(String keyword, String word, int firstColumnRange,
            int lastColumnRange) {

        List<MemberDTO> list = memberDAO.memberSelectByKeyword(keyword, word, firstColumnRange, lastColumnRange);
        if(list == null) {
            throw new KdcException("�������� �ʽ��ϴ�.");
        }
        
        return list;
    }
    
}
