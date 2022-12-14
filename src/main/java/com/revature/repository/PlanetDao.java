package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
    public List<Planet> getAllPlanets() throws SQLException {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Planet> planet = new ArrayList<>();
			String sql = "select * from planets";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);

			while (rs.next()) {
				planet.add(new Planet(rs.getInt("id"),
										rs.getString("name"),
										rs.getInt("ownerid")));
			}

			return planet;
		}
	}

	public Planet getPlanetByName(String owner, String planetName) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "select * from planets where name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, planetName);

			ResultSet rs = ps.executeQuery();

			rs.next();
			return new Planet(rs.getInt("id"),
								rs.getString("name"),
								rs.getInt("ownerid"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Planet getPlanetById(String username, int planetId) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "select * from planets where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, planetId);

			ResultSet rs = ps.executeQuery();

			rs.next();
			return new Planet(rs.getInt("id"),
								rs.getString("name"),
								rs.getInt("ownerid"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Planet createPlanet(String username, Planet p) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "insert into planets values (default, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, p.getName());
			ps.setInt(2, p.getOwnerId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();

			rs.next();

			return new Planet(rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("ownerid"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
	}

	public void deletePlanetById(int planetId) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "delete from planets where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, planetId);
			int rowsAffected = ps.executeUpdate();			
			System.out.println("Affected Rows: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

    // to test method implementations
	// public static void main(String[] args) throws SQLException {
	// 	PlanetDao pDao = new PlanetDao();
	// 	System.out.println(pDao.createPlanet("test", new Planet(0, "Planet 1", 1)) + " Planet 1 is added to the DB");
	// 	System.out.println(pDao.createPlanet("test", new Planet(0, "Planet 2", 1)) + " Planet 2 is added to the DB");
	// 	System.out.println(pDao.createPlanet("test", new Planet(0, "Planet 2", 1)) + " Planet 2 is added to the DB");
	// 	System.out.println(pDao.createPlanet("test", new Planet(0, "Planet 3", 1)) + " Planet 3 is added to the DB");
	// 	System.out.println(pDao.getPlanetByName("test", "Planet 1") + " Planet: Planet 1 is retrieved by name");
	// 	System.out.println(pDao.getPlanetById("test", 2) + " Planet: Planet 2 is retrieved by id");
	// 	System.out.println(pDao.getAllPlanets() + " All planets were retrieved");
	// 	pDao.deletePlanetById(4);
	// }
}
