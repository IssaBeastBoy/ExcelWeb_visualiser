package com.backend.backend.apps;

public class tickets {
    private String userId;
    private String Id;
    private String Status;
    private String Priority;
    private String Estimate;
    private String Color;
    private String Summary;
    private String eventType;
    private String ticketLocation;

    public String getTicketLocation() {
        return ticketLocation;
    }

    public void setTicketLocation(String ticketLocation) {
        this.ticketLocation = ticketLocation;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getEstimate() {
        return Estimate;
    }

    public void setEstimate(String estimate) {
        Estimate = estimate;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }
}
