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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "select * from planets inner join users on (planets.ownerid = users.id) where username = ? and planet.name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, owner);
			ps.setString(2, planetName);

			ResultSet rs = ps.executeQuery();

			// called for the 1st pass in the do-while loop
			rs.next();

			// do-while loop in case there's a duplicate Planet from the same owner
			// there's a chance where same owner create a duplicate planet
			do {
				return new Planet(rs.getInt("id"),
									rs.getString("name"),
									rs.getInt("ownerid"));
			} while(rs.next());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Planet getPlanetById(String username, int planetId) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "select * from planets inner join users on (planets.ownerid = users.id) where username = ? and planet.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setInt(2, planetId);

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
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.createConnection()) {
			UserDao uDao = new UserDao();
            
			if (uDao.getUserByUsername(username).getId() == p.getId()) {
				String sql = "insert into planets values (default, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, p.getName());
				ps.setInt(2, p.getOwnerId());
				ps.execute();
				
				ResultSet rs = ps.getGeneratedKeys();

				rs.next();

				return new Planet(rs.getInt("id"),
								rs.getString("name"),
								rs.getInt("onwerid"));  
			}

			throw new RuntimeException();
        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
	}

	public void deletePlanetById(int planetId) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "delete * from planets where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int rowsAffected = ps.executeUpdate();			
			System.out.println("Affected Rows: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
