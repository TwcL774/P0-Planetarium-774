package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Planet {
	
	private int id;
	private String name;
	private int ownerId;

	// created a constructor to simplify code in PlanetDao.java
	public Planet(int id, String name, int ownerId) {
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
	}
}
