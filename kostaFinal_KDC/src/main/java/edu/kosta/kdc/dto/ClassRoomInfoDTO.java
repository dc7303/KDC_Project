package edu.kosta.kdc.dto;

public class ClassRoomInfoDTO {

    private String classRoomCode;               //클래스 코드
    private String classRoomInfoName;           //클래스 이름
    private String classRoomInfoStartDate;      //클래스 개강일
    private String classRoomInfoEndDate;        //클래스 종강일
    private String classRoomInfoTeacherId;      //클래스 강사 아이디
    private String classRoomInfoChatFile;       //채팅 파일 명
    private boolean classRommInfoIsDelete;      //삭제여부
    
    public ClassRoomInfoDTO() {}
    
    public ClassRoomInfoDTO(String classRoomCode, String classRoomInfoName, String classRoomInfoStartDate,
            String classRoomInfoEndDate, String classRoomInfoTeacherId, String classRoomInfoChatFile,
            boolean classRommInfoIsDelete) {
        super();
        this.classRoomCode = classRoomCode;
        this.classRoomInfoName = classRoomInfoName;
        this.classRoomInfoStartDate = classRoomInfoStartDate;
        this.classRoomInfoEndDate = classRoomInfoEndDate;
        this.classRoomInfoTeacherId = classRoomInfoTeacherId;
        this.classRoomInfoChatFile = classRoomInfoChatFile;
        this.classRommInfoIsDelete = classRommInfoIsDelete;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public String getClassRoomInfoName() {
        return classRoomInfoName;
    }

    public void setClassRoomInfoName(String classRoomInfoName) {
        this.classRoomInfoName = classRoomInfoName;
    }

    public String getClassRoomInfoStartDate() {
        return classRoomInfoStartDate;
    }

    public void setClassRoomInfoStartDate(String classRoomInfoStartDate) {
        this.classRoomInfoStartDate = classRoomInfoStartDate;
    }

    public String getClassRoomInfoEndDate() {
        return classRoomInfoEndDate;
    }

    public void setClassRoomInfoEndDate(String classRoomInfoEndDate) {
        this.classRoomInfoEndDate = classRoomInfoEndDate;
    }

    public String getClassRoomInfoTeacherId() {
        return classRoomInfoTeacherId;
    }

    public void setClassRoomInfoTeacherId(String classRoomInfoTeacherId) {
        this.classRoomInfoTeacherId = classRoomInfoTeacherId;
    }

    public String getClassRoomInfoChatFile() {
        return classRoomInfoChatFile;
    }

    public void setClassRoomInfoChatFile(String classRoomInfoChatFile) {
        this.classRoomInfoChatFile = classRoomInfoChatFile;
    }

    public boolean isClassRommInfoIsDelete() {
        return classRommInfoIsDelete;
    }

    public void setClassRommInfoIsDelete(boolean classRommInfoIsDelete) {
        this.classRommInfoIsDelete = classRommInfoIsDelete;
    }
    
    
    
}
