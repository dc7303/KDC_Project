package edu.kosta.kdc.model.dto;

public class CalendarDTO {
    
    private String classRoomCode; //Ŭ������ �ڵ�
    private String calendarTitle;   //����
    private String calendarStart;   //���۽ð�
    private String calendarEnd;     //�����½ð�
    
    public CalendarDTO(String classRoomCode, String calendarTitle, String calendarStart, String calendarEnd) {
        super();
        this.classRoomCode = classRoomCode;
        this.calendarTitle = calendarTitle;
        this.calendarStart = calendarStart;
        this.calendarEnd = calendarEnd;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public String getCalendarTitle() {
        return calendarTitle;
    }

    public void setCalendarTitle(String calendarTitle) {
        this.calendarTitle = calendarTitle;
    }

    public String getCalendarStart() {
        return calendarStart;
    }

    public void setCalendarStart(String calendarStart) {
        this.calendarStart = calendarStart;
    }

    public String getCalendarEnd() {
        return calendarEnd;
    }

    public void setCalendarEnd(String calendarEnd) {
        this.calendarEnd = calendarEnd;
    }
    
    
}
