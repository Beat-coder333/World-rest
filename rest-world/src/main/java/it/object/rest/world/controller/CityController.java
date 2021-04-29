package it.object.rest.world.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.object.rest.world.dao.ICityDao;
import it.object.rest.world.model.City;

@RestController
public class CityController {

	@Autowired
	private ICityDao daoCity;

	@GetMapping("city/name")
	public City getCityByName(@RequestParam("name") String name, HttpServletResponse response) {
		City city = daoCity.getCityByName(name);
		if (city == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return city;

	}

	@GetMapping("city/get-list-bycode")
	public List<City> citiesList(@RequestParam("CountryCode") String code, HttpServletResponse response) {
		List<City> cities = daoCity.getAllCitiesByCountryCode(code);
		if (code.length() > 3) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		} else {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		return cities;

	}

}
