package com.HFTest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HFTest.entities.User;
import com.HFTest.service.UserService;

@RestController
@CrossOrigin("*")
public class UserRestService {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public User sign(@RequestBody User user) {
		User u;
		System.out.println(user.getEmail());
		u = (User) userService.loadUserByUsername(user.getEmail());
		System.out.println(u);
		String p = user.getPassword();
		
		if (p.equals(u.getPassword())) {
			return u;
		}
		else
			return null;
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public void register(@RequestBody User user) {
		userService.addUser(user);
	}

	@RequestMapping(value="/user", method=RequestMethod.DELETE)
	public boolean deleteUser(String idUser) {
		
		return userService.deleteUser(idUser);
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public void updateUser(@PathVariable String id, @RequestBody User user) {
	    userService.updateUser(id, user);
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable("id")String userId) {
		return userService.getUserbyId(userId);
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getUsers();
	}

}
