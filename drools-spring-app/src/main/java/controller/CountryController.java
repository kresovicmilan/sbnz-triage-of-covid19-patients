package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import model.Country;

@RestController
@RequestMapping(value = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {
	
	@RequestMapping(value = "/addCountry", method = RequestMethod.POST)
	public ResponseEntity<?> addCountry(Country myCountry)  {
		
		return null;
	}

}
