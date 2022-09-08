package com.backend.backend.apps;

import com.backend.backend.userInformationEntity;

import java.util.Date;

public class calendar {
    private String userId;
    private String eventType;
    private String Subject;
    private int Id;
    private Date StartTime;
    private boolean IsAllDay;
    private Date EndTime;
    private boolean cancel;
    private String CategoryColor;
    private String Location;
    private String Description;
    private String RecurrenceRule;
    private String EndTimezone;
    private String StartTimezone;
    private int RecurrenceID;
    private String RecurrenceException;

    public calendar (){
        RecurrenceID = 0;
        RecurrenceException = null;
        cancel = false;
        Location = null;
        Description = null;
        RecurrenceRule = null;
        EndTimezone = null;
        StartTimezone = null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = Integer.toString(userId);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public boolean getAllDay() {
        return IsAllDay;
    }

    public void setAllDay(boolean allDay) {
        System.out.println(allDay);
        IsAllDay = allDay;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        if(!cancel.equals("undefined")){
            this.cancel = Boolean.parseBoolean(cancel);
        }

    }

    public String getCategoryColor() {
        setCategoryColor();
        return CategoryColor;
    }

    public void setCategoryColor() {
        CategoryColor = "#00bdae";
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        if(!location.equals("undefined")){
            this.Location = location;
        }
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        if(!description.equals("undefined")){
            this.Description = description;
        }
    }

    public String getRecurrenceRule() {
        return RecurrenceRule;
    }

    public void setRecurrenceRule(String recurrenceRule) {
        if(!recurrenceRule.equals("undefined")){
            this.RecurrenceRule = recurrenceRule;
        }
    }

    public String getEndTimezone() {
        return EndTimezone;
    }

    public void setEndTimezone(String endTimezone) {
        if(!endTimezone.equals("undefined")){
            this.EndTimezone = endTimezone;
        }
    }

    public String getStartTimezone() {
        return StartTimezone;
    }

    public void setStartTimezone(String startTimezone) {
        if(!startTimezone.equals("undefined")){
            StartTimezone = startTimezone;
        }

    }

    public int getRecurrenceID() {
        return RecurrenceID;
    }

    public void setRecurrenceID(String recurrenceID) {
        if(!recurrenceID.equals("undefined")){
            RecurrenceID = Integer.parseInt(recurrenceID);
        }
    }

    public String getRecurrenceException() {
        return RecurrenceException;
    }

    public void setRecurrenceException(String recurrenceException) {
        if(!recurrenceException.equals("undefined")){
            RecurrenceException = recurrenceException;
        }
    }
}
