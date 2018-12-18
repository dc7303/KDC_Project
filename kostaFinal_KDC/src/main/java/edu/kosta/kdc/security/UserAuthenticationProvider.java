package edu.kosta.kdc.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.AuthorityDAO;
import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.AuthorityDTO;
import edu.kosta.kdc.model.dto.MemberDTO;

@Service 
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    /*
     * ����ڰ� ȭ�鿡�� �α����� �����ϸ� �Ʒ��� �޼ҵ尡 ����ȴ�.
     * �Ű����� : ������ �ʿ��� ���� - Authentication(����ڰ� �Է��� ID,PASSWORD�� ����Ǿ� �ִ�)
     * ���ϰ� : ������ ������ ���� Authentication
     * 
     * ��� : ���ڰ����� ���޵� Authentication��ü�� �޾� ����ó���� �� �� ������ ������ 
     *        Authentication�� ��Ƽ� �����Ѵ�.
     */
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
	    
	    //1. �Ķ���ͷ� ���޹��� Authentication ��ü�� ����ó���� �������� ������ null�� ����
		if(!supports(auth.getClass())){
			return null;
		}
		
		//2. ������ٸ�, �μ��� �޴� user������ ������ ��� �����ϴ��� üũ(id check)
		String memberId = auth.getName();
		MemberDTO memberDTO = null;
        
		memberDTO = memberDAO.memberSelectByMemberId(memberId);
		
        
		if(memberDTO == null){
			throw new UsernameNotFoundException("������ ��ġ���� �ʽ��ϴ�.");//spring exception
		}
		
		//isMemberIsWithdrawal�� TURE��� Ż���� ȸ�������� ����.
		if(memberDTO.isMemberIsWithdrawal()) {
		    throw new UsernameNotFoundException("������ ��ġ���� �ʽ��ϴ�.");
		}
		
		//3.��й�ȣ ��
		String memberPwd = (String)auth.getCredentials();
		
		if(!passwordEncoder.matches(memberPwd, memberDTO.getMemberPwd())){
			throw new BadCredentialsException("������ ��ġ���� �ʽ��ϴ�.");
		}


        //4. id, password ��ΰ� ��ġ�ϸ� Authentication�� ���� ����.
        // ������� ������ ��ȸ : �ϳ��� ����ڴ� �������� ������ ����.
        List<AuthorityDTO> list = null;
        list = authorityDAO.authoritySelectByMemberId(memberId);

        if(list.isEmpty()){
            //�ƹ� ������ ���°��....
            throw new UsernameNotFoundException(memberId + "�� �ƹ� ������ �����ϴ�.");
        }
        
        //db���� ������ �� ������ GrantedAuthority �� ��ȯ�ؾ���.
        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        
        for(AuthorityDTO authority : list){
            authList.add(new SimpleGrantedAuthority(authority.getAuthName()));
        }
        //UsernamePasswordAuthenticationToken(Object principal, Object credentials, authorities)
        //UsernamePasswordAuthenticationToken�� Authentication�� �ڽİ�ü
        //�����Ϸ�� ����� UsernamePasswordAuthenticationToken�� �����Ѵ�.
        return new UsernamePasswordAuthenticationToken(memberDTO, null, authList);
    }

    /**
     * �ش� Ÿ���� Authentication��ü�� �̿��ؼ� ���� ó����
     * �Ҽ� �ִ��� ���θ� �������ִ� �޼ҵ�
     * */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}






