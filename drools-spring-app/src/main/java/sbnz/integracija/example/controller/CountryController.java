package sbnz.integracija.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.dto.CountryDTO;
import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.repository.CountryRepository;
import sbnz.integracija.example.service.MyService;

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
	
	@Autowired
	MyService myService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCountry(@RequestBody Country myCountry)  {
		try {
			Country country = this.countryRepository.findByCountryName(myCountry.getCountryName()).orElseThrow(Exception::new);
			return new ResponseEntity<>("Country has not been added - Failed", HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			System.out.println(myCountry.toString());
			Country c = this.countryRepository.save(myService.getCountryDevelopmentLevel(myCountry));
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<?> modifyCountry(@RequestBody Country editedCountry)  {
		try {
			Country country = this.countryRepository.findById(editedCountry.getId()).orElseThrow(Exception::new);
			country.setCountryName(editedCountry.getCountryName());
			country.setCovidPositive(editedCountry.isCovidPositive());
			country.setIdvIndex(editedCountry.getIdvIndex());
			Country savedCountry = this.countryRepository.save(myService.getCountryDevelopmentLevel(country));
			return new ResponseEntity<>(savedCountry, HttpStatus.OK);	
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
