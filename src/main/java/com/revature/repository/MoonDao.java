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
    
    public List<Moon> getAllMoons() throws SQLException {
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
		}
	}

	public Moon getMoonByName(String username, String moonName) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, moonName);

			ResultSet rs = ps.executeQuery();

			rs.next();
			return new Moon(rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("myplanetid"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public Moon getMoonById(String username, int moonId) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, moonId);

			ResultSet rs = ps.executeQuery();

			rs.next();
			return new Moon(rs.getInt("id"),
								rs.getString("name"),
								rs.getInt("myplanetid"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public Moon createMoon(String username, Moon m) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "insert into moons values (default, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, m.getName());
			ps.setInt(2, m.getMyPlanetId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();

			rs.next();

			return new Moon(rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("myplanetid"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new Moon();
        }
	}

	public void deleteMoonById(int moonId) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, moonId);
			int rowsAffected = ps.executeUpdate();			
			System.out.println("Affected Rows: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) throws SQLException {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Moon> moon = new ArrayList<>();
			String sql = "select * from moons where myplanetid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, planetId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				moon.add(new Moon(rs.getInt("id"),
									rs.getString("name"),
									rs.getInt("myplanetid")));
			}

			return moon;
		}
	}

    // to test method implementations
	// public static void main(String[] args) throws SQLException {
	// 	MoonDao mDao = new MoonDao();
	// 	System.out.println(mDao.createMoon("test", new Moon(0, "Moon 1", 1)) + " Moon 1 is added to the DB");
	// 	System.out.println(mDao.createMoon("test", new Moon(0, "Moon 2", 2)) + " Moon 2 is added to the DB");
	// 	System.out.println(mDao.createMoon("test", new Moon(0, "Moon 3", 1)) + " Moon 3 is added to the DB");
	// 	System.out.println(mDao.getMoonByName("test", "Moon 1") + " Moon: Moon 1 is retrieved by name");
	// 	System.out.println(mDao.getMoonById("test", 2) + " Moon: Moon 2 is retrieved by id");
	// 	System.out.println(mDao.getAllMoons() + " All moons were retrieved");
	// 	System.out.println(mDao.getMoonsFromPlanet(1) + " All moons from Planet 1 retrieved");
	// 	mDao.deleteMoonById(3);
	// }
}
