package com.example.demo.service;


import java.util.Optional;




import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
public class UserService {
	
	@Autowired
    private static UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public static User getByEmail(String email) {
		List<User> listUser = (List<User>) userRepo.findAll();
		for (User user : listUser) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
    }
    public static User updateUser(User updatedData) {
    	User user = getByEmail(updatedData.getEmail());
		if (user != null) {
			
			user.setPassword(updatedData.getPassword());
			user.setName(updatedData.getName());
			user.setImage(updatedData.getImage());
			userRepo.save(user);
			return user;
		}
		return null;
    }

    public void registerUser(User user) throws UserExistsException {
        Optional<User> optUser = userRepo.findById(user.getEmail());
        if (optUser.isPresent()) {
            throw new UserExistsException();
        }
        String hashpw =
                BCrypt.hashpw(user.getPassword(),
                        BCrypt.gensalt());
        System.out.println(hashpw);
        user.setPassword(hashpw);
        
        userRepo.save(user);
        
    }

    public User login(String email, String password) {
        Optional<User> userSearch =
                userRepo.findById(email);
        User user = null;
        if(userSearch.isPresent()) {
            user = userSearch.get();
            boolean matched = BCrypt.checkpw(password, user.getPassword());
            if(!matched) {
                user = null;
            }
        }
        return user;
    }
    

    public Optional<User> getregistrationbyemail(String email) {
        Optional<User> userSearch =
                userRepo.findById(email);
        User user = null;
        if(userSearch.isPresent()) {
             
            
            return userSearch;
        }
        return Optional.ofNullable(user);
    }
    
    

}