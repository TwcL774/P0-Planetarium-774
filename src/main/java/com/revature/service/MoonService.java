package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;

/**
 * 
 */
public class MoonService {

	private MoonDao dao;

	public MoonService(){
		this.dao = new MoonDao();
	}

	/**
	 * Interacts with the dao that retrieve all Moon objects.
	 * @return a List with Moon object
	 */
	public List<Moon> getAllMoons() {
		try {
			return this.dao.getAllMoons();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Interacts with the dao that retrieves Moon object by name.
	 * @param username from sessionAttribute username
	 * @param moonName from ctx.pathParam("name") GET:/api/moon/{name}
	 * @return Moon object
	 */
	public Moon getMoonByName(String username, String moonName) {
		return this.dao.getMoonByName(username, moonName);
	}

	/**
	 * Interacts with the dao that retrieves Moon object by id.
	 * @param username from sessionAttribute username
	 * @param moonId from ctx.pathParam("id") GET:/api/moon/id/{id}
	 * @return Moon object
	 */
	public Moon getMoonById(String username, int moonId) {
		return this.dao.getMoonById(username, moonId);
	}

	/**
	 * Interacts with the dao that adds Moon object.
	 * @param username from sessionAttribute username
	 * @param m from ctx.body POST:/api/moon
	 * @return Moon object
	 */
	public Moon createMoon(String username, Moon m) {
		return this.dao.createMoon(username, m);
	}

	/**
	 * Interacts with the dao that deletes Moon object.
	 * @param moonId from ctx.body DELETE:/api/moon
	 */
	public void deleteMoonById(int moonId) {
		this.dao.deleteMoonById(moonId);
	}

	/**
	 * Interacts with the dao that retrieves all Moon objects associated to a Planet ID.
	 * @param planetId from ctx.pathParam("id") POST:/api/planet/{id}/moon
	 * @return
	 */
	public List<Moon> getMoonsFromPlanet(int planetId) {
		try {
			return this.dao.getMoonsFromPlanet(planetId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
