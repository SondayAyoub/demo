package com.HFTest.service.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HFTest.dao.RoleRepository;
import com.HFTest.dao.UserRepository;
import com.HFTest.entities.Role;
import com.HFTest.entities.User;
import com.HFTest.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User u = null;
		try {
			u = getUser(email);
			System.out.println(u.getPassword());
		} catch (UsernameNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(u == null) {
			System.out.println("No such user!!");
			throw new UsernameNotFoundException("No such user "+ email);
		}
		else if(u.getRoles().isEmpty()) {
			System.out.println("User no authorities!!");
			throw new UsernameNotFoundException("User "+ email + " has no authorities!");
		}
		
		System.out.println("user jebru");
		
		u.setLastAccessDate(Calendar.getInstance().getTime());
		
		try {
			updateUser(u.getId(), u);
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public void addUser(User user) {
		Set<Role> user_roles = new HashSet<Role>();
		Role r_u = roleRepository.findByName("user");
		user_roles.add(r_u);
		user.setRoles(user_roles);
		userRepository.save(user);
		
	}

	@Override
	public User getUserbyId(String userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User getUser(String email) {
		User u = userRepository.findByEmail(email);
		System.out.println(u);
		System.out.println("user men getuser");
		return userRepository.findByEmail(email);
	}

	@Override
	public void updateUser(String id, User user) {
		user.setId(id);
		Set<Role> user_roles = new HashSet<Role>();
		Role r_u = roleRepository.findByName("user");
		user_roles.add(r_u);
		user.setRoles(user_roles);
		userRepository.save(user);
		
	}

	@Override
	public boolean deleteUser(String userId) {
		User u = getUserbyId(userId);
		try {
			if (u != null) {
				userRepository.delete(u);
				return true;
			}else
				return false; 
		} catch (Exception e) {
			System.out.println("User does not exist!");
			return false;
		}	
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean assignRoleToUser(Role role, User user) {
		Role assign_role = roleRepository.findByName(role.getName());
		
		if(assign_role == null) {
			return false;
		}
		
		User assign_user = userRepository.findByEmail(user.getEmail());
		
		if(assign_user == null) {
			return false;
		}
		
		assign_user.getRoles().add(assign_role);
		userRepository.save(assign_user);
		return true;
	}
	
	
	
	
//	@Override
//	public void save(User user) {
//		user.setPassword(user.getPassword());
//		user.setRoles(new HashSet<Role>(roleRepository.findAll()));
//		userRepository.save(user);
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		return userRepository.findByEmail(email);
//	}

}
