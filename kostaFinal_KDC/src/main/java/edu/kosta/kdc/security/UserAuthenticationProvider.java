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

import edu.kosta.kdc.dao.AuthDAO;
import edu.kosta.kdc.dao.UserInfoDAO;
import edu.kosta.kdc.dto.AuthorityDTO;
import edu.kosta.kdc.dto.MemberDTO;

@Service 
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserInfoDAO userDAO;
	
	@Autowired
	private AuthDAO authDAO;
	
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
		String userId = auth.getName();
		MemberDTO userDTO = null;
        try {
            userDTO = userDAO.findByUserId(userId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
        
		if(userDTO == null){
			throw new UsernameNotFoundException("������ ��ġ���� �ʽ��ϴ�.");//spring exception
		}
		
		//3.��й�ȣ ��
		String userPwd = (String)auth.getCredentials();
		
		if(!passwordEncoder.matches(userPwd, userDTO.getUserPwd())){
			throw new BadCredentialsException("������ ��ġ���� �ʽ��ϴ�.");
		}
		
        ////////////    ������� �Դٸ� ������ ������  ///////////////// 
        //4. id, password ��ΰ� ��ġ�ϸ� Authentication�� ���� ����.
        // ������� ������ ��ȸ : �ϳ��� ����ڴ� �������� ������ ����.
        List<AuthorityDTO> list = null;
        try {
            list = authDAO.findAuthByUserId(userId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if(list.isEmpty()){
            //�ƹ� ������ ���°��....
            throw new UsernameNotFoundException(userId + "�� �ƹ� ������ �����ϴ�.");
        }
        
        //db���� ������ �� ������ GrantedAuthority �� ��ȯ�ؾ���.
        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        
        for(AuthorityDTO authority : list){
            authList.add(new SimpleGrantedAuthority(authority.getAuthName()));
        }
        //UsernamePasswordAuthenticationToken(Object principal, Object credentials, authorities)
        //UsernamePasswordAuthenticationToken�� Authentication�� �ڽİ�ü
        //�����Ϸ�� ����� UsernamePasswordAuthenticationToken�� �����Ѵ�.
        return new UsernamePasswordAuthenticationToken(userDTO, null, authList);
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






