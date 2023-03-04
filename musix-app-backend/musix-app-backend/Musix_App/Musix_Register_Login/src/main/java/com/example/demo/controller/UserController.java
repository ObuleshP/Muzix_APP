package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;

import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.exception.*;
import com.example.demo.model.*;

import com.example.demo.service.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class UserController {
    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/register")
    @CrossOrigin(origins="*")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        ResponseEntity<?> response = null;

        try {
            userService.registerUser(user);
            response = new
                    ResponseEntity<>(HttpStatus.OK);//c
        } catch (UserExistsException e) {
            response = new
                    ResponseEntity<>(HttpStatus.CONFLICT);//c
            e.printStackTrace();
        } catch (Exception e) {
            response = new
                    ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//c
            e.printStackTrace();
        }
        return (ResponseEntity<Object>) response;
    }

    @PostMapping("/login")
    @CrossOrigin(origins="*")
    public ResponseEntity<Object> login(@RequestBody User user)
            throws UserNotFoundException {

        User validUser = userService.login(user.getEmail(),
                user.getPassword());

        if (validUser == null) {
            throw new UserNotFoundException();
        }

        // Build the Json Web Token
        String token =
                Jwts.builder().
                setId(validUser.getEmail()).
                setSubject(validUser.getPassword())
                .setIssuedAt(new Date()).
                signWith(SignatureAlgorithm.HS256,
                        "usersecretkey").
                compact();

        // Add it to a Map and send the map in response body
        Map<String, String> map1 = new
                HashMap<>();//c
        map1.put("token", token);
        map1.put("message", "User Successfully logged in");

        return new ResponseEntity<>(
                        map1, HttpStatus.OK);//c
    }
    
    @CrossOrigin(origins="*")
    @GetMapping("/getuserdetails/{userId}")
    public ResponseEntity<User> getUserdetail(@PathVariable("userId") String userId) {
		Optional<User> peruser = userService.getregistrationbyemail(userId);//c
		if(peruser.isPresent()) {//c
			User user = peruser.get();//c
			return new ResponseEntity<>(user,HttpStatus.OK);//c
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);//c
	}
    
    @PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		System.out.print("inside updateUser method");
		User u = UserService.updateUser(user);
		System.out.print("user has been updated");
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
    
}