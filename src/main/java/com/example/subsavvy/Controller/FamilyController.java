package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Family;
import com.example.subsavvy.Data.User;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.FamilyService;
import com.example.subsavvy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Ajouter une famille
    @PostMapping("/add")
    public ResponseEntity<Object> addFamily(
            @RequestParam String name,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);
        String userId = jwtTokenProvider.getUserIdFromToken(token);

        return ResponseEntity.ok(familyService.addFamily(new Family(name, UUID.fromString(userId))));
    }

    // Ajouter un membre à une famille
    @PostMapping("/addMember")
    public ResponseEntity<Object> addMember(
            @RequestParam String mail,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);
        String userId = jwtTokenProvider.getUserIdFromToken(token);

        Family family = familyService.getFamilyById(UUID.fromString(userId));
        User user = userService.getUserByMail(mail);

        if (user == null) {
            // Inscription à faire si l'utilisateur n'existe pas
            return ResponseEntity.status(500).body("User not found. Please register first.");
        }

        family.addMember(user);
        return ResponseEntity.ok("Member added successfully.");
    }

    // Obtenir toutes les familles
    @GetMapping
    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    // Obtenir une famille par ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getFamilyById(
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);
        String userId = jwtTokenProvider.getUserIdFromToken(token);

        return ResponseEntity.ok(familyService.getFamilyById(UUID.fromString(userId)));
    }

    // Supprimer une famille par ID
    @DeleteMapping("/{id}")
    public void deleteFamily(@PathVariable UUID id) {
        familyService.deleteFamily(id);
    }
}
