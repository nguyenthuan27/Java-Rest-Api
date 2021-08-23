package com.java6.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class myproduct implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long countquantity;
	private String name;
	private Double priceproduct;
	private Date createDate;
	private String statusde;
}
