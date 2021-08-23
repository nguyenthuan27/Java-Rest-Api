package com.java6.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.java6.Entity.ItemCart;


@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	Map<Integer , ItemCart> maps= new HashMap<>();
	@Override
	public void add(ItemCart item) {
		ItemCart cartItem = maps.get(item.getProId());
		if (cartItem == null) {
			maps.put(item.getProId(), item);
		}else {
			cartItem.setQty(cartItem.getQty() + 1);
		}
	}
	@Override
	public void remove(int id) {
		maps.remove(id);
	}
	@Override
	public ItemCart update(int proId,int qty) {
		ItemCart cartItem = maps.get(proId);
		cartItem.setQty(qty);
		return cartItem;
	}
	
	@Override
	public void clear() {
		maps.clear();
	}
	
	@Override
	public Collection<ItemCart> getAllItems(){
		return maps.values();
	}
	
	@Override
	public int getCount() {
		return maps.values().size();
	}
	
	@Override
	public double getAmount() {
		return maps.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
	}
}
