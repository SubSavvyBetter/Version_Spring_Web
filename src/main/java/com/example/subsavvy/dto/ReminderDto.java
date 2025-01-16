package com.example.subsavvy.dto;

import com.example.subsavvy.Data.Subscription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ReminderDto {

    private LocalDate reminder_date;
    private String message;


}
