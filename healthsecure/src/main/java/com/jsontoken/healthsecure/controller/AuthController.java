package com.jsontoken.healthsecure.controller;



import com.jsontoken.healthsecure.Response.AuthenticationResponse;
import com.jsontoken.healthsecure.request.AuthenticationRequest;
import com.jsontoken.healthsecure.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest request) {
        log.info("createToken(-)");
        // Authenticate the user
        userDetailsService.loadUserByUsername(request.getUsername());

        // Generate the token
        String jwtToken = jwtUtil.generateToken(request.getUsername());

        return new AuthenticationResponse(jwtToken);
    }
}