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
     * 사용자가 화면에서 로그인을 진행하면 아래의 메소드가 실행된다.
     * 매개변수 : 인증시 필요한 정보 - Authentication(사용자가 입력한 ID,PASSWORD가 저장되어 있다)
     * 리턴값 : 인증된 정보를 가진 Authentication
     * 
     * 결론 : 인자값으로 전달된 Authentication객체를 받아 인증처리를 한 뒤 인증한 정보를 
     *        Authentication에 담아서 리턴한다.
     */
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
	    
	    //1. 파라미터로 전달받은 Authentication 객체의 인증처리가 지원되지 않으면 null이 리턴
		if(!supports(auth.getClass())){
			return null;
		}
		
		//2. 인증됬다면, 인수로 받는 user정보를 가지고 디비에 존재하는지 체크(id check)
		String memberId = auth.getName();
		MemberDTO memberDTO = null;
        
		memberDTO = memberDAO.memberSelectByMemberId(memberId);
		
        
		if(memberDTO == null){
			throw new UsernameNotFoundException("정보가 일치하지 않습니다.");//spring exception
		}
		
		//isMemberIsWithdrawal이 TURE라면 탈퇴한 회원임으로 실패.
		if(memberDTO.isMemberIsWithdrawal()) {
		    throw new UsernameNotFoundException("정보가 일치하지 않습니다.");
		}
		
		//3.비밀번호 비교
		String memberPwd = (String)auth.getCredentials();
		
		if(!passwordEncoder.matches(memberPwd, memberDTO.getMemberPwd())){
			throw new BadCredentialsException("정보가 일치하지 않습니다.");
		}


        //4. id, password 모두가 일치하면 Authentication를 만들어서 리턴.
        // 사용자의 권한을 조회 : 하나의 사용자는 여러개의 권한을 가짐.
        List<AuthorityDTO> list = null;
        list = authorityDAO.authoritySelectByMemberId(memberId);

        if(list.isEmpty()){
            //아무 권한이 없는경우....
            throw new UsernameNotFoundException(memberId + "는 아무 권한이 없습니다.");
        }
        
        //db에서 가지고 온 권한을 GrantedAuthority 로 변환해야함.
        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        
        for(AuthorityDTO authority : list){
            authList.add(new SimpleGrantedAuthority(authority.getAuthName()));
        }
        //UsernamePasswordAuthenticationToken(Object principal, Object credentials, authorities)
        //UsernamePasswordAuthenticationToken는 Authentication의 자식객체
        //인증완료된 결과로 UsernamePasswordAuthenticationToken를 리턴한다.
        return new UsernamePasswordAuthenticationToken(memberDTO, null, authList);
    }

    /**
     * 해당 타입의 Authentication객체를 이용해서 인증 처리를
     * 할수 있는지 여부를 리턴해주는 메소드
     * */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}






