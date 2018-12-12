package edu.kosta.kdc.model.dto;

public class MemberDTO {
    
    private String MemberId;          //����ID
    private String MemberPwd;         //����PWD
    private String MemberName;        //���� �̸�
    private String MemberNickName;    //�г���
    private String MemberBirth;       //����
    private String MemberPhone;       //�޴��� ��ȣ
    private String MemberEmail;       //���� �̸���
    private boolean MemberIsWithdrawal;  //Ż�𿩺�
    private String MemberDate;        //���� ������
    
    public MemberDTO() {}

    public MemberDTO(String memberId, String memberPwd, String memberName, String memberNickName, String memberBirth,
            String memberPhone, String memberEmail, boolean memberIsWithdrawal, String memberDate) {
        super();
        MemberId = memberId;
        MemberPwd = memberPwd;
        MemberName = memberName;
        MemberNickName = memberNickName;
        MemberBirth = memberBirth;
        MemberPhone = memberPhone;
        MemberEmail = memberEmail;
        MemberIsWithdrawal = memberIsWithdrawal;
        MemberDate = memberDate;
    }



    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getMemberPwd() {
        return MemberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        MemberPwd = memberPwd;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getMemberNickName() {
        return MemberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        MemberNickName = memberNickName;
    }

    public String getMemberBirth() {
        return MemberBirth;
    }

    public void setMemberBirth(String memberBirth) {
        MemberBirth = memberBirth;
    }

    public String getMemberPhone() {
        return MemberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        MemberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return MemberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        MemberEmail = memberEmail;
    }

    public boolean isMemberIsWithdrawal() {
        return MemberIsWithdrawal;
    }

    public void setMemberIsWithdrawal(boolean memberIsWithdrawal) {
        MemberIsWithdrawal = memberIsWithdrawal;
    }

    public String getMemberDate() {
        return MemberDate;
    }

    public void setMemberDate(String memberDate) {
        MemberDate = memberDate;
    }

    
}