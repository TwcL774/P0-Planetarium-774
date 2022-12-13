package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Moon> moon = new ArrayList<>();
			String sql = "select * from moons";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				moon.add(new Moon(rs.getInt("id"),
										rs.getString("name"),
										rs.getInt("myplanetid")));
			}

			return moon;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Moon getMoonByName(String username, String moonName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon getMoonById(String username, int moonId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon createMoon(String username, Moon m) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteMoonById(int moonId) {
		// TODO Auto-generated method stub
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		// TODO Auto-generated method stub
		return null;
	}
}
