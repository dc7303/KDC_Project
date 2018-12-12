package edu.kosta.kdc.model.dto;

public class ClassRoomDTO {
    
    private String MemberId;             //유저 아이디. MemberDTO의 memberId를 FK하고 있다.
    private String classRoomCode;        //클래스 코드
    
    public ClassRoomDTO() {}

    public ClassRoomDTO(String memberId, String classRoomCode) {
        super();
        MemberId = memberId;
        this.classRoomCode = classRoomCode;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }


    
}
