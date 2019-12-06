package com.traveloka.calendar;

import com.traveloka.calendar.model.Admin;
import com.traveloka.calendar.model.MeetingRoom;
import com.traveloka.calendar.repository.InMemoryAbstractRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class TestMinimumNumberOfRoom {
    InMemoryAbstractRepository repository = new InMemoryAbstractRepository();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    void testMinimumRoom(){
        repository.addUsers(new Admin("admin" , 1));
        try {
            repository.addMeetingRoom(new MeetingRoom(1 , 1 ,format.parse("06/12/2019 10:00:00") ,
                    format.parse("06/12/2019 11:00:00"), new HashSet<>()));

            repository.addMeetingRoom(new MeetingRoom(2 , 1 ,format.parse("06/12/2019 10:45:00") ,
                    format.parse("06/12/2019 11:45:00"), new HashSet<>()));

            repository.addMeetingRoom(new MeetingRoom(3 , 1 ,format.parse("06/12/2019 11:30:00") ,
                    format.parse("06/12/2019 12:30:00"), new HashSet<>()));

            repository.addMeetingRoom(new MeetingRoom(4 , 1 ,format.parse("06/12/2019 12:15:00") ,
                    format.parse("06/12/2019 13:15:00"), new HashSet<>()));


            int numberOfRoom = repository.intMinimumRoomRequired();
            System.out.println(numberOfRoom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestMinimumNumberOfRoom room = new TestMinimumNumberOfRoom();
        room.testMinimumRoom();
    }
}
