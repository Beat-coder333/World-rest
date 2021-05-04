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

import it.object.rest.world.dao.ICityDao;
import it.object.rest.world.model.City;

@Component
public class CityDaoImpl implements ICityDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public City getCityByName(String cityName) {
		City city = null;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM city WHERE name=?");
			stmt.setString(1, cityName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				city = this.getFromResultSet(rs);

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	@Override
	public List<City> getAllCitiesByCountryCode(String code) {
		List<City> cities = new ArrayList<City>();

		try {
			Connection conn = dataSource.getConnection();
			String sql = "SELECT * from city c where CountryCode =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				cities.add(getFromResultSet(rs));

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cities;
	}

	private City getFromResultSet(ResultSet rs) throws SQLException {

		City c = new City();
		c.setId(rs.getInt("ID"));
		c.setCountryCode(rs.getString("CountryCode"));
		c.setName(rs.getString("Name"));
		c.setPopulation(rs.getInt("Population"));
		c.setDistrict(rs.getString("District"));

		return c;

	}

}
