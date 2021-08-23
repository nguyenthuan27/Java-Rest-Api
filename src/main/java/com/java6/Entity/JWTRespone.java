package com.java6.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTRespone {
	private String jwtToken;
	 private boolean DIRE;
	 private boolean STAF;
}
