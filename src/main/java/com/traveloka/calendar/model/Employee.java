package com.traveloka.calendar.model;

public class Employee extends User{
    public Employee( String name, int id) {
        super(name,id);
    }

    @Override
    public boolean hasPermissionTocreateMeetingRoom() {
        return false;
    }
}
