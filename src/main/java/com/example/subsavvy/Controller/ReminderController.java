package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Reminder;
import com.example.subsavvy.Service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderService.addReminder(reminder);
    }

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @GetMapping("/user/{userId}")
    public List<Reminder> getRemindersByUserId(@PathVariable UUID userId) {
        return reminderService.getRemindersByUserId(userId);
    }

    @GetMapping("/subscription/{subscriptionId}")
    public List<Reminder> getRemindersBySubscriptionId(@PathVariable UUID subscriptionId) {
        return reminderService.getRemindersBySubscriptionId(subscriptionId);
    }

    @DeleteMapping("/{id}")
    public void deleteReminder(@PathVariable UUID id) {
        reminderService.deleteReminder(id);
    }
}
