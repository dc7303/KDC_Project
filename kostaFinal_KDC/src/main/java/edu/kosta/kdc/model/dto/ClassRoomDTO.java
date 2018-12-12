package edu.kosta.kdc.model.dto;

public class ClassRoomDTO {
    
    private String MemberId;             //���� ���̵�. MemberDTO�� memberId�� FK�ϰ� �ִ�.
    private String classRoomCode;        //Ŭ���� �ڵ�
    
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
