package com.francis.earthhub.security;

import com.francis.earthhub.exception.UserNameNotFoundException;
import com.francis.earthhub.security.dto.AuthRequestDTO;
import com.francis.earthhub.security.dto.AuthResponseDTO;
import com.francis.earthhub.user.AppUser;
import com.francis.earthhub.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public AuthResponseDTO login (@Valid @RequestBody AuthRequestDTO request) {
        AppUser user = userRepository.findByEmail(request.email()).orElseThrow(()-> new UserNameNotFoundException("User not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(),request.password()));
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken);
    }


}
