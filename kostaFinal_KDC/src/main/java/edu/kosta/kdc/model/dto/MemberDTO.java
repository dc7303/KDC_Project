package edu.kosta.kdc.model.dto;

public class MemberDTO {
    
    private String memberId;          //유저ID
    private String memberPwd;         //유저PWD
    private String memberName;        //유저 이름
    private String memberNickName;    //닉네임
    private String memberBirth;       //생일
    private String memberPhone;       //휴대폰 번호
    private String memberEmail;       //유저 이메일
    private String memberIsWithdrawal;  //탈퇴여부
    private String memberDate;        //유저 가입일
    
    public MemberDTO() {}

    public MemberDTO(String memberId, String memberPwd, String memberName, String memberNickName, String memberBirth,
            String memberPhone, String memberEmail, String memberIsWithdrawal, String memberDate) {
        super();
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.memberBirth = memberBirth;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberIsWithdrawal = memberIsWithdrawal;
        this.memberDate = memberDate;
    }



    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberBirth() {
        return memberBirth;
    }

    public void setMemberBirth(String memberBirth) {
        this.memberBirth = memberBirth;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String isMemberIsWithdrawal() {
        return memberIsWithdrawal;
    }

    public void setMemberIsWithdrawal(String memberIsWithdrawal) {
        this.memberIsWithdrawal = memberIsWithdrawal;
    }

    public String getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }

    
}