package com.traveloka.calendar.model;

public class Admin extends User {
    public Admin(String name , int id) {
        super(name,id);
    }

    @Override
    public boolean hasPermissionTocreateMeetingRoom() {
        return true;
    }
}
