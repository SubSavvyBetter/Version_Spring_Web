package com.example.subsavvy.Service;

import com.example.subsavvy.Data.Reminder;
import com.example.subsavvy.Repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    public Reminder addReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public List<Reminder> getRemindersByUserId(UUID userid) {
        return reminderRepository.findAllByUserid(userid);
    }

    public List<Reminder> getRemindersBySubscriptionId(UUID subscriptionId) {
        return reminderRepository.findAllBySubscriptionId(subscriptionId);
    }

    public void deleteReminder(UUID id) {
        reminderRepository.deleteById(id);
    }
}
