package edu.kosta.kdc.model.dto;

public class CalendarDTO {
    
    private int calendarPk;         //캘린더 PK
    private String classRoomCode; //클래스룸 코드
    private String calendarTitle;   //제목
    private String calendarStart;   //시작시간
    private String calendarEnd;     //끝나는시간
    private String calendarColor;   //일정 컬러
    
    public CalendarDTO() {}
    

    public CalendarDTO(int calendarPk, String classRoomCode, String calendarTitle, String calendarStart,
            String calendarEnd, String calendarColor) {
        super();
        this.calendarPk = calendarPk;
        this.classRoomCode = classRoomCode;
        this.calendarTitle = calendarTitle;
        this.calendarStart = calendarStart;
        this.calendarEnd = calendarEnd;
        this.calendarColor = calendarColor;
    }


    public int getCalendarPk() {
        return calendarPk;
    }

    public void setCalendarPk(int calendarPk) {
        this.calendarPk = calendarPk;
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

    public String getCalendarColor() {
        return calendarColor;
    }

    public void setCalendarColor(String calendarColor) {
        this.calendarColor = calendarColor;
    }
    
    
    
}
