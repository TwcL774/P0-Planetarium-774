package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moon {
	
	private int id;
	private String name; 
	private int myPlanetId;
}
