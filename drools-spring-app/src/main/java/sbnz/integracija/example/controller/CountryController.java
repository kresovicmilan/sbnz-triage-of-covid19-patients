package sbnz.integracija.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.dto.CountryDTO;
import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@RestController
@RequestMapping(value = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {
	
	@Autowired
	CountryRepository countryRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCountry(Country myCountry)  {
		try {
			Country country = this.countryRepository.findByCountryName(myCountry.getCountryName()).orElseThrow(Exception::new);
			return new ResponseEntity<>("Country has not been added - Failed", HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			this.countryRepository.save(myCountry);
			return new ResponseEntity<>("Country has been added - Success", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<?> modifyCountry(Country editedCountry)  {
		try {
			Country country = this.countryRepository.findByCountryName(editedCountry.getCountryName()).orElseThrow(Exception::new);
			country.setCountryName(editedCountry.getCountryName());
			country.setCovidPositive(editedCountry.isCovidPositive());
			country.setIdvIndex(editedCountry.getIdvIndex());
			this.countryRepository.save(country);
			return new ResponseEntity<>("Country has been changed - Success", HttpStatus.OK);	
		} catch(Exception e) {
			return new ResponseEntity<>("Country has not been changed - Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCountry()  {
		try {
			List<Country> listOfCountries = this.countryRepository.findAll();
			List<CountryDTO> listOfCountriesDTO = listOfCountries.stream().map(
                    s -> new CountryDTO(s)
            ).collect(Collectors.toList());
			return new ResponseEntity<>(listOfCountriesDTO, HttpStatus.OK);	
		} catch(Exception e) {
			return new ResponseEntity<>("List of countries cannot be retrieved - Failed", HttpStatus.BAD_REQUEST);
		}
	}
	

}
