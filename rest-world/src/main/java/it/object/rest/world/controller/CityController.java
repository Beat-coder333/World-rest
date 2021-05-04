package it.object.rest.world.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.object.rest.world.dao.ICityDao;
import it.object.rest.world.model.City;

@RestController
public class CityController {

	@Autowired
	private ICityDao daoCity;

	@GetMapping("city/cityname")

	public City getCityByName(@RequestParam("cityname") String name, HttpServletResponse response) {
		City city = daoCity.getCityByName(name);
		if (city == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return city;

	}

	@GetMapping("city/country-code/{countryCode}")
	public List<City> citiesList(@PathVariable String countryCode, HttpServletResponse response) {
		List<City> cities = daoCity.getAllCitiesByCountryCode(countryCode);
		if (countryCode.length() > 3) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		} else {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		return cities;

	}

}
