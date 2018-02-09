package com.HFTest.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HFTest.dao.ShopsRepository;
import com.HFTest.dao.UserRepository;
import com.HFTest.entities.Shops;
import com.HFTest.entities.User;
import com.HFTest.service.ShopsService;

@Service
@Transactional
public class ShopsServiceImpl implements ShopsService {

	@Autowired
	private ShopsRepository shopsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void addShop(Shops shop) {
		shopsRepository.save(shop);
	}

	@Override
	public Shops getShopsbyId(String shopId) {
		return shopsRepository.findById(shopId);
	}

	@Override
	public List<Shops> getShop(String name) {
		return shopsRepository.findByName(name);
	}

	@Override
	public void updateShops(String id, Shops shop) {
		shop.setId(id);
		shopsRepository.save(shop);
	}

	@Override
	public boolean deleteShops(String shopIs) {
		Shops s = getShopsbyId(shopIs);
		try {
			if (s != null) {
				shopsRepository.delete(s);
				return true;
			}else
				return false; 
		} catch (Exception e) {
			System.out.println("User does not exist!");
			return false;
		}	
	}

	@Override
	public List<Shops> getShops() {
		return shopsRepository.findAll();
	}

	@Override
	public Set<Shops> getShopsByUser(User user) {
		return userRepository.findByEmail(user.getEmail()).getFavShops();
	}

	@Override
	public boolean assignShopToUser(Shops shop, User user) {
		Shops assign_shop = shopsRepository.findById(shop.getId());
		if(assign_shop == null) {
			return false;
		}
		
		User assign_user = userRepository.findByEmail(user.getEmail());
		
		if(assign_user == null) {
			return false;
		}
		
		assign_user.getFavShops().add(assign_shop);
		userRepository.save(assign_user);
		return true;
	}

}
