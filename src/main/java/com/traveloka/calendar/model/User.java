package com.traveloka.calendar.model;

public abstract class User {
    private String name ;
    private int id ;
    public User(String name, int id){
        this.name = name ;
        this.id = id;
    }
    public abstract boolean hasPermissionTocreateMeetingRoom() ;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
