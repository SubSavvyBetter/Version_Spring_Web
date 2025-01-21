package com.example.subsavvy.Service;

import com.example.subsavvy.Data.Family;
import com.example.subsavvy.Repository.FamilyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;


    public Family addFamily(Family family) {
        return familyRepository.save(family);
    }

    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }

    public Family getFamilyById(UUID id) {
        return familyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Family with ID " + id + " not found"));
    }


    public void deleteFamily(UUID id) {
        familyRepository.deleteById(id);
    }
}
