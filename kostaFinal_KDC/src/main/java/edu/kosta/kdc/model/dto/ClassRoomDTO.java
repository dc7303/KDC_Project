package edu.kosta.kdc.model.dto;

public class ClassRoomDTO {
    
    private String memberId;             //���� ���̵�. MemberDTO�� memberId�� FK�ϰ� �ִ�.
    private String classRoomCode;        //Ŭ���� �ڵ�
    private boolean classRoomIsCurrent;           //���� ����Ʈ�� ������ �� Ŭ������ �ڵ�
    
    public ClassRoomDTO() {}



    public ClassRoomDTO(String memberId, String classRoomCode, boolean classRoomIsCurrent) {
        super();
        memberId = memberId;
        this.classRoomCode = classRoomCode;
        this.classRoomIsCurrent = classRoomIsCurrent;
    }

    public String getmemberId() {
        return memberId;
    }

    public void setmemberId(String memberId) {
        memberId = memberId;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public boolean classRoomIsCurrent() {
        return classRoomIsCurrent;
    }

    public void setCurrent(boolean classRoomIsCurrent) {
        this.classRoomIsCurrent = classRoomIsCurrent;
    }
    
}
