package com.ltm.extracurricular.controller;

import com.ltm.extracurricular.config.jwt.AuthUserDetails;
import com.ltm.extracurricular.config.jwt.JwtTokenProvider;
import com.ltm.extracurricular.config.jwt.UserDetailsServiceImpl;
import com.ltm.extracurricular.payload.LoginRequest;
import com.ltm.extracurricular.payload.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    final JwtTokenProvider tokenProvider;
    final AuthenticationManager authenticationManager;
    final UserDetailsServiceImpl userDetailsService;

    AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        String error = null;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Lấy thông tin của user
            AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
            String role = userDetails.getUser().getRole().toString();

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = tokenProvider.generateToken(userDetails);
            return new LoginResponse(accessToken, "Bearer", role);
        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            log.warn(ex.getMessage());
            error = "Co loi xay ra!!!";
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            // unknow error
            error = "Co loi xay ra!!!";
        }
        return new LoginResponse(error);
    }
}