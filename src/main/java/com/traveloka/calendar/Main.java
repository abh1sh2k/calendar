package com.traveloka.calendar;

import com.traveloka.calendar.model.*;
import com.traveloka.calendar.repository.AbstractRepository;
import com.traveloka.calendar.repository.InMemoryAbstractRepository;
import com.traveloka.calendar.service.CalendarService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    CalendarService service ;
     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Main( CalendarService service){
        this.service = service;
    }
    public static void main(String[] args) {
        AbstractRepository repository = new InMemoryAbstractRepository();
        CalendarService service = new CalendarService(repository);

        Scanner in = new Scanner(System.in);
        Main main = new Main(service);
        while (true){
            String inputs[] = in.nextLine().split(" ");
            try {
                main.process(inputs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void process(String [] inputs) throws Exception{
        try {
            if (inputs.length<1)
                throw new Exception("input size less than 1");
            int command = Integer.parseInt(inputs[0]);
            Command co = Command.getCommand(command);
            if(co == Command.NotValid)
                throw new Exception("not valid command");
            processCommand(co , Arrays.copyOfRange(inputs,1 , inputs.length));
        }catch (Exception ex){
            throw ex;
        }
    }
    void processCommand(Command command , String [] inputs) throws Exception{
        try {
            if (command == Command.AddEmployee) {
                int id = Integer.parseInt(inputs[0]);
                boolean admin = Boolean.parseBoolean(inputs[1]);
                User user = null;
                if(admin==true){
                    user = new Admin(inputs[2] , id);
                }
                else {
                    user = new Employee(inputs[2] , id);
                }
                boolean success = this.service.addUsers(user);
                if(success){
                    System.out.println("Successfully added user id " + id);
                }
                else{
                    System.out.println("Failed to add user id " + id);
                }
            }
            else if(command == Command.AddMeetingRoom){
                int meetingRoomid = Integer.parseInt(inputs[0]);
                int admin = Integer.parseInt(inputs[1]);
                Date startDate = dateFormat.parse(inputs[2]);
                Date endDate = dateFormat.parse(inputs[3]);
                Set<Integer> users = new HashSet<>();
                for(int i =4 ; i< inputs.length ;i++){
                    users.add(Integer.parseInt(inputs[i]));
                }
                MeetingRoom room = new MeetingRoom(meetingRoomid , admin, startDate , endDate, users);
                this.service.addMeetingRoom(room);
                boolean success = this.service.addMeetingRoom(room);
                if(success){
                    System.out.println("Successfully added meetingroom id " + meetingRoomid);
                }
                else{
                    System.out.println("Failed to add  meetingroom id  " + meetingRoomid);
                }
            }
            else if(command== Command.ListMeetingRoom){
                int userid = Integer.parseInt(inputs[0]);
                List<Integer> rooms = this.service.getMeetingRoom(userid);
                System.out.println("List of room for userid = "+userid+" are "+rooms);
            }
            else if(command== Command.NumberOfREquiredRoom){
                int totalRooms = this.service.intMinimumRoomRequired();
                System.out.println("Minimum number of room required " +totalRooms);
            }
        }catch (Exception ex){
            throw ex;
        }
    }
}
