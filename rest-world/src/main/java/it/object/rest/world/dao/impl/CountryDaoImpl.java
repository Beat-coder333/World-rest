package it.object.rest.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.object.rest.world.dao.ICountryDao;
import it.object.rest.world.model.Country;

@Component
public class CountryDaoImpl implements ICountryDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<String> getAllContinent() {
		List<String> conts = new ArrayList<String>();

		try {
			Connection conn = dataSource.getConnection();
			String sql = "SELECT DISTINCT Continent FROM country";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String cont = rs.getString("Continent");
				conts.add(cont);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conts;
	}

	@Override
	public List<Country> getCountryByNationOrContinent(String nationName, String continent) {
		List<Country> countries = new ArrayList<Country>();

		try {
			Connection conn = dataSource.getConnection();
			String sql = "SELECT * FROM country WHERE (''= ? OR Name=?) AND ( Continent= ? OR ''=? )";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nationName);
			stmt.setString(2, nationName);
			stmt.setString(3, continent);
			stmt.setString(4, continent);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				countries.add(getFromResultSet(rs));

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return countries;
	}

	@Override
	public List<Country> getCountryByContinent(String continent) {
		List<Country> countries = new ArrayList<Country>();
		try {
			Connection conn = dataSource.getConnection();
			String sql = "SELECT * FROM world.country WHERE Continent = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, continent);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				countries.add(getFromResultSet(rs));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countries;
	}

	private Country getFromResultSet(ResultSet rs) throws SQLException {
		Country count = new Country();
		count.setCode(rs.getString("Code"));
		count.setName(rs.getString("Name"));
		count.setContinent(rs.getString("Continent"));
		count.setSurfaceArea(rs.getFloat("SurfaceArea"));
		count.setPopulation(rs.getInt("Population"));
		count.setRegion(rs.getString("Region"));

		return count;

	}

}
