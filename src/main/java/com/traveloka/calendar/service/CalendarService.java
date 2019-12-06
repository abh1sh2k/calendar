package com.traveloka.calendar.service;

import com.traveloka.calendar.model.MeetingRoom;
import com.traveloka.calendar.model.User;
import com.traveloka.calendar.repository.AbstractRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalendarService {
    AbstractRepository repository ;
    public CalendarService(AbstractRepository repository){
        this.repository = repository;
    }

    public boolean addMeetingRoom(MeetingRoom room){
        return repository.addMeetingRoom(room);
    }

    public boolean addUsers(User user){
        return repository.addUsers(user);
    }

    public List<Integer> getMeetingRoom(int userid){
        return repository.getMeetingRoom(userid);
    }

    public int intMinimumRoomRequired(){
        return repository.intMinimumRoomRequired();
    }
}
