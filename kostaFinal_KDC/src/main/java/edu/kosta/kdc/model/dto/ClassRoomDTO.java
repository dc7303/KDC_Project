package edu.kosta.kdc.model.dto;

public class ClassRoomDTO {
    
    private String memberId;             //���� ���̵�. MemberDTO�� memberId�� FK�ϰ� �ִ�.
    private String classRoomCode;        //Ŭ���� �ڵ�
    private boolean classRoomIsCurrent;  //���� ����Ʈ�� ������ �� Ŭ������ �ڵ�
    
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
