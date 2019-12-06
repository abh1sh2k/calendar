package com.traveloka.calendar.model;

import java.util.*;

public class MeetingRoom {
    private int id ;
    private Date start ;
    private Date end ;
    private int admin ;
    private Set<Integer> users;
    //currenly allowing meeting room with list of users , can decide on design
//    MeetingRoom(int id ,int admin , Date start , Date end){
//        this.admin = admin;
//        this.start = start ;
//        this.end = end ;
//        this.users = new HashSet<>();
//        users.add(admin);
//    }
    public MeetingRoom(int id , int admin , Date start , Date end , Set<Integer> users){
        this.id = id;
        this.admin = admin;
        this.start = start ;
        this.end = end ;
        this.users  = users;
    }

    public int getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public int getAdmin() {
        return admin;
    }

    public Set<Integer> getUsers() {
        return users;
    }
}
