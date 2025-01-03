package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Family;
import com.example.subsavvy.Service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;


    @PostMapping
    public Family addFamily(@RequestBody Family family) {
        return familyService.addFamily(family);
    }

    @GetMapping
    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    @GetMapping("/{id}")
    public Optional<Family> getFamilyById(@PathVariable UUID id) {
        return familyService.getFamilyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFamily(@PathVariable UUID id) {
        familyService.deleteFamily(id);
    }
}
