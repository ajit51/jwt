package com.jwtauthentication.controller;

import com.jwtauthentication.helper.JwtUtil;
import com.jwtauthentication.model.JwtRequest;
import com.jwtauthentication.model.JwtResponse;
import com.jwtauthentication.service.CustomUserDetailsServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServie customUserDetailsServie;

    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        System.out.println(jwtRequest);

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("UsernameNotFoundException");
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Crendential");
        }

        //fine area
        UserDetails userDetails = customUserDetailsServie.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        System.out.println("token: "+token);

        return ResponseEntity.ok(new JwtResponse(token));

    }
}
