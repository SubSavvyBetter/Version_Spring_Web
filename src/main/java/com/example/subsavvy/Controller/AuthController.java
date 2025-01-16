package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.User;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userService.findByUsername(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenProvider.generateToken(String.valueOf(user.getId()));
            return ResponseEntity.ok(new AuthResponse("Bearer " + token));
        }
        throw new BadCredentialsException("Invalid username or password");
    }

}

@Setter
@Getter
class AuthRequest {
    private String username;
    private String password;

}

@Getter
@Setter
@AllArgsConstructor
class AuthResponse {
    private String token;
}

