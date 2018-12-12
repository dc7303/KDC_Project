package edu.kosta.kdc.model.dto;

/**
 * 권한 테이블 DTO
 * 회원가입 시 권한 테이블 생성.
 * 권한을 복수로 가지고 있을 시 회원가입 프로세서에서 유연하게 처리할 필요가 있음.
 * 
 * 
 * @author mark
 *
 */
public class AuthorityDTO {
    
    private String memberId;      //Authority_ID   MemberDTO의 memberId를 FK하고 있다.
    private String authName;      //권한 이름
    
    
    public AuthorityDTO() {}


    public AuthorityDTO(String memberId, String authName) {
        super();
        this.memberId = memberId;
        this.authName = authName;
    }


    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    public String getAuthName() {
        return authName;
    }


    public void setAuthName(String authName) {
        this.authName = authName;
    }
    

    
}
