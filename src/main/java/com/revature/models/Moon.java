package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Moon {
	
	private int id;
	private String name; 
	private int myPlanetId;

	// created a constructor to simply code in MoonDao
	public Moon(int id, String name, int myPlanetId) {
		this.id = id;
		this.name = name;
		this.myPlanetId = myPlanetId;
	}
}
