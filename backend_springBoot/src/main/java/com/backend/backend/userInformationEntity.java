package com.backend.backend;

import javax.persistence.*;

@Entity(name = "users")
@Table(name = "users")
public class userInformationEntity {

    public userInformationEntity(){}

    public userInformationEntity(String password, String Name, String Surname, String email,
            String phoneNum, String LASnumber) {
        this.password = password;
        this.name = Name;
        this.surname = Surname;
        this.email = email;
        this.contact = phoneNum;
        this.img = "C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\"
                + LASnumber + "\\img\\";
        this.status = "Online";
        this.tasks = "Tasks:";
        this.meetings = "Meet:";
        this.notes = "Notes:";
        this.fileStorageDir = "C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\"
                + LASnumber + "\\excel\\";
        this.tickets = "TODO:PROG:DONE:";
        this.chats = "Chats:";
        this.notifications = "Notifications:";

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginName")
    private int loginName;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "contact")
    private String contact;
    @Column(name = "img")
    private String img;
    @Column(name = "status")
    private String status;
    @Column(name = "tasks")
    private String tasks;
    @Column(name = "meetings")
    private String meetings;
    @Column(name = "notes")
    private String notes;
    @Column(name = "fileStorageDir")
    private String fileStorageDir;
    @Column(name = "tickets")
    private String tickets;
    @Column(name = "chats")
    private String chats;
    @Column(name = "notifications")
    private String notifications;

    public int getLoginName() {
        return loginName;
    }

    public void setLoginName(int loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getMeetings() {
        return meetings;
    }

    public void setMeetings(String meetings) {
        this.meetings = meetings;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFileStorageDir() {
        return fileStorageDir;
    }

    public void setFileStorageDir(String fileStorageDir) {
        this.fileStorageDir = fileStorageDir;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getChats() {
        return chats;
    }

    public void setChats(String chats) {
        this.chats = chats;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }
}
