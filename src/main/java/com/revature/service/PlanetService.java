package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

/**
 * 
 */
public class PlanetService {

	private PlanetDao dao;

	public PlanetService() {
		this.dao = new PlanetDao();
	}

	/**
	 * Interacts with the dao that retrieves all Planet objects
	 * 
	 * @return a List with Planet objects.
	 */
	public List<Planet> getAllPlanets() {
		return this.dao.getAllPlanets();
	}

	/**
	 * Interacts with the dao that retrieves Planet object by name.
	 * 
	 * @param owner      from sessionAttribute username
	 * @param planetName from ctx.pathParam("name") GET:/planet/{name}
	 * @return Planet object
	 */
	public Planet getPlanetByName(String owner, String planetName) {
		return this.dao.getPlanetByName(owner, planetName);
	}

	/**
	 * Interacts with the dao that retrieves Planet object by id.
	 * 
	 * @param username from sessionAttribute username
	 * @param planetId from ctx.pathParam("id") GET:/planet/id/{id}
	 * @return Planet object
	 */
	public Planet getPlanetById(String username, int planetId) {
		return this.dao.getPlanetById(username, planetId);
	}

	/**
	 * Interacts with the dao that adds Planet object.
	 * 
	 * @param username from sessionAttribute username
	 * @param p        from ctx.boy POST:/api/planet
	 * @return Planet object
	 */
	public Planet createPlanet(String username, Planet p) {
		return this.dao.createPlanet(username, p);
	}

	/**
	 * Interacts with the dao that deletes Planet object.
	 * 
	 * @param planetId from ctx.body DELETE:/api/planet/{id}
	 */
	public int deletePlanetById(int planetId) {
		return this.dao.deletePlanetById(planetId);
	}
}
