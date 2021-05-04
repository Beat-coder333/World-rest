package it.object.rest.world.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.object.rest.world.dao.ICountryDao;
import it.object.rest.world.model.Country;

@RestController
public class CountryController {

	@Autowired
	private ICountryDao daoCountry;

	@GetMapping("country/continents")
	public List<String> continents() {
		List<String> conts = daoCountry.getAllContinent();
		return conts;
	}

	@GetMapping("/country/search")
	public List<Country> searchedCountry(@RequestParam("countryname") String countryName,
			@RequestParam("continentname") String contName, HttpServletResponse response) {
		List<Country> countries = daoCountry.getCountryByNationOrContinent(countryName, contName);
		if (countryName.isEmpty() || countryName.isBlank() && contName.isEmpty() || contName.isBlank()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return countries;
	}

	@GetMapping("country/list_countries/{continent}")
	public List<Country> countriesList(@PathVariable String continent, HttpServletResponse response) {
		List<Country> countries = daoCountry.getCountryByContinent(continent);
		response.setStatus(HttpServletResponse.SC_OK);
		return countries;

	}

}
