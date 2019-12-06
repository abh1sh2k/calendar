package com.traveloka.calendar.model;

import java.util.HashMap;
import java.util.Map;

public enum  Command {
    NotValid(-1), AddEmployee(0) , AddMeetingRoom(1) , ListMeetingRoom(2) , NumberOfREquiredRoom(3);
    private final int id;
    //private static Map<Integer , Command> commands = new HashMap<>();

    private Command(int id){
       this.id = id;
    }
    public static Command getCommand(int id){
        if(id==0)
            return AddEmployee;
        if(id==1)
            return AddMeetingRoom;
        if(id==2)
            return ListMeetingRoom;
        if(id==3)
            return NumberOfREquiredRoom;
        return NotValid;
    }
}
