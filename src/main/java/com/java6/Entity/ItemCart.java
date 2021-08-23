package com.java6.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCart {
	Integer proId;
	String name;
	String image;
	double price;
	int qty = 1;
}
