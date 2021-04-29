package it.object.rest.world.dao;


import java.util.List;

import it.object.rest.world.model.City;



public interface ICityDao {

	public City getCityByName(String cityName);
	
	public List<City> getAllCitiesByCountryCode(String Code);
}
