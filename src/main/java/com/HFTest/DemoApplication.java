package com.HFTest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.HFTest.dao.PermissionRepository;
import com.HFTest.dao.RoleRepository;
import com.HFTest.dao.UserRepository;
import com.HFTest.entities.Permission;
import com.HFTest.entities.Role;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//	@Autowired
//	private ShopsRepository shopsRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
//	private double[] coor = {6.55, 33};
//	private double[] coor1 = {1.66, 27};
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
//		shopsRepository.save(new Shops("name", "picture", "city", "email", new Location("Point", coor)));
//		shopsRepository.save(new Shops("name1", "picture11", "city111", "email@cc2.co", new Location("Point", coor1)));
		
//		shopsRepository.findAll().forEach(s->{
//			System.out.println("s.getName()");
//		});	
		
		Permission p = new Permission("Edit");
		
		Set<Permission> perms = new HashSet<Permission>();
		perms.add(p);
		
		Role r = new Role("editor");
		r.setPermissions(perms);
		
		
//		userRepository.save(new User("yush@yush.com", "password"));
//		permissionRepository.save(p);
//		roleRepository.save(r);
		
		
		userRepository.findAll().forEach(s->{
			System.out.println(s.getEmail());
		});	
		
		roleRepository.findAll().forEach(s->{
			System.out.println(s.toString());
		});
		
		permissionRepository.findAll().forEach(s->{
			System.out.println(s.toString());
		});
	}
}
