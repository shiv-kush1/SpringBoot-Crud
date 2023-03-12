package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.exception.ResourceNotFoundException;
import com.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userrepository;
	
	@GetMapping("/all")
	public List<User> getAllUsers(){
		
		return this.userrepository.findAll();
		
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value="id")long userId) {
		
		return this.userrepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User not found with ID :"+userId));
	}
	
	@PostMapping()
public User createUser(@RequestBody User user) {
		
		return this.userrepository.save(user);
		
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable(value="id")long userId) {
		
		User existingUser=this.userrepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User not found with ID :"+userId));
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		return this.userrepository.save(existingUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
		User existingUser=this.userrepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User not found with ID :"+userId));
		this.userrepository.delete(existingUser);
		return ResponseEntity.ok().build();
	
	}
	
	
	
}
