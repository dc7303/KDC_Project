package edu.kosta.kdc.dto;

public class AuthorityDTO {
    
    private String memberId;      //Authority_ID
    private String authName;    //권한 이름
    
    
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
