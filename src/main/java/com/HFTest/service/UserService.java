package com.HFTest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.HFTest.entities.Role;
import com.HFTest.entities.User;

public interface UserService extends UserDetailsService {

	public void addUser(User user);
	public User getUserbyId(String userId);
	public User getUser(String email);
	public void updateUser(String Id, User user);
	public boolean deleteUser(String userId);
	public List<User> getUsers();
	public boolean assignRoleToUser(Role role, User user);
	
}
