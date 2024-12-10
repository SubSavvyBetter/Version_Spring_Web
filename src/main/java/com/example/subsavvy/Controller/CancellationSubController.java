package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.CancellationSub;
import com.example.subsavvy.Service.CancellationSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cancellations")
public class CancellationSubController {

    @Autowired
    private CancellationSubService cancellationSubService;

    @PostMapping
    public CancellationSub createCancellation(@RequestBody CancellationSub cancellationSub) {
        return cancellationSubService.addCancellation(cancellationSub);
    }

    @GetMapping
    public List<CancellationSub> getAllCancellations() {
        return cancellationSubService.getAllCancellations();
    }

    @GetMapping("/user/{userId}")
    public List<CancellationSub> getCancellationsByUserId(@PathVariable UUID userId) {
        return cancellationSubService.getCancellationsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteCancellation(@PathVariable UUID id) {
        cancellationSubService.deleteCancellation(id);
    }
}
