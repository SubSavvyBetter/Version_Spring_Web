package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.User;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        User user = userService.findByUsername(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return jwtTokenProvider.generateToken(String.valueOf(user.getId()));
        }
        throw new RuntimeException("Invalid username or password");
    }
}

@Setter
@Getter
class AuthRequest {
    private String username;
    private String password;

    // Getters and setters
}
