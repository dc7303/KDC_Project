package edu.kosta.kdc.model.dto;

public class CalendarDTO {
    
    private String classRoomCode; //클래스룸 코드
    private String calendarTitle;   //제목
    private String calendarStart;   //시작시간
    private String calendarEnd;     //끝나는시간
    
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
