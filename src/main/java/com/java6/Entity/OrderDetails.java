package com.java6.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor	
@Entity 
@Table(name = "Orderdetails")
public class OrderDetails  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	Double price;
	Integer quantity;
	String statusdeli;
	@ManyToOne
	@JoinColumn(name = "ProductId")
	Products products;
	@ManyToOne
	@JoinColumn(name = "OrderId")
	Orders orders;
}