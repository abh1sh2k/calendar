package com.traveloka.calendar.controller;

import com.traveloka.calendar.model.Admin;
import com.traveloka.calendar.model.Employee;
import com.traveloka.calendar.service.CalendarService;
import com.traveloka.calendar.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class CalendarController {
    CalendarService service ;
    CalendarController(CalendarService service){
        this.service = service;
    }
    @RequestMapping("/addEmployee/{command}")
    boolean addEmployee(@PathVariable String command) {
        try {
            String inputs[] = command.split(" ");
            int id = Integer.parseInt(inputs[0]);
            boolean admin = Boolean.parseBoolean(inputs[1]);
            User user = null;
            if (admin == true) {
                user = new Admin(inputs[2], id);
            } else {
                user = new Employee(inputs[2], id);
            }
            return service.addUsers(user);
        }
        catch (Exception ex){
            throw ex;
        }
    }
    //similarily can add other apis here
}
