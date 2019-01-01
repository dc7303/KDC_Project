package edu.kosta.kdc.model.dto;

public class ClassRoomDTO {
    
    private String memberId;             //유저 아이디. MemberDTO의 memberId를 FK하고 있다.
    private String classRoomCode;        //클래스 코드
    private boolean classRoomIsCurrent;  //현재 디폴트로 설정된 값 클래스룸 코드
    
    public ClassRoomDTO() {}

    public ClassRoomDTO(String memberId, String classRoomCode, boolean classRoomIsCurrent) {
        super();
        this.memberId = memberId;
        this.classRoomCode = classRoomCode;
        this.classRoomIsCurrent = classRoomIsCurrent;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public boolean isClassRoomIsCurrent() {
        return classRoomIsCurrent;
    }

    public void setClassRoomIsCurrent(boolean classRoomIsCurrent) {
        this.classRoomIsCurrent = classRoomIsCurrent;
    }



}
