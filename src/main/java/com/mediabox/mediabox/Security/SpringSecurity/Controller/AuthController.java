package com.mediabox.mediabox.Security.SpringSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Repository.User.UserRepository;
import com.mediabox.mediabox.Request.Login.loginRequest;
import com.mediabox.mediabox.Resposes.AuthRespons;
import com.mediabox.mediabox.Security.SpringSecurity.Services.CustomerUserDetailsService;
import com.mediabox.mediabox.Security.config.JwtProvider;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("/Signup")
    public AuthRespons createUser(@RequestBody User user) throws Exception {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new Exception("User already Exist with this Email Id ");

        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BadCredentialsException("Password is required");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setGender(user.getGender());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveduser = userServices.RegisterUser(newUser);
        Authentication aauthentication = new UsernamePasswordAuthenticationToken(saveduser.getEmail(),
                saveduser.getPassword());
        String token = JwtProvider.genereateToken(aauthentication);
        AuthRespons response = new AuthRespons(token, "Register Sucessfully");
        return response;

    }

    @PostMapping("/Signin")
    public AuthRespons Singin(@RequestBody loginRequest loginRequest) {
        Authentication autnetication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.genereateToken(autnetication);
        return new AuthRespons(token, "Login Sucessfully");
    }

    private Authentication authenticate(String email, String password) {
        if (password == null || password.isBlank()) {
            throw new BadCredentialsException("Password is required");
        }
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Username and Password ");

        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

}
