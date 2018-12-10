package edu.kosta.kdc.dto;

public class AuthDTO {
    
    private String userId;
    private String authName;
    
    
    public AuthDTO() {}
    
    public AuthDTO(String userId, String authName) {
        super();
        this.userId = userId;
        this.authName = authName;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAuthName() {
        return authName;
    }
    public void setAuthName(String authName) {
        this.authName = authName;
    }
    
    
}
