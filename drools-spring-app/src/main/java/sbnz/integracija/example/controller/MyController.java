package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Patient;
import sbnz.integracija.example.model.AppUser;
import sbnz.integracija.example.repository.CountryRepository;
import sbnz.integracija.example.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	// LOGIN POST
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(AppUser myUser) {
    	
    	try {
    		AppUser u = this.userRepository.findByUsername(myUser.getUsername()).orElseThrow(Exception::new);
    		if (u.getPassword().equals(myUser.getPassword())) {
            	return new ResponseEntity<>("LOGIN SUCCESS", HttpStatus.OK);
    		}
    	}
    	catch(Exception e) {
        	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    	}

    	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    }
    
    // REGISTER POST
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> userRegister(AppUser myUser) {
    	
    	try {
    		AppUser u = this.userRepository.findByUsername(myUser.getUsername()).orElseThrow(Exception::new);
    	}
    	catch(Exception e) {
            this.userRepository.save(myUser);
        	return new ResponseEntity<>("REGISTER SUCCESS", HttpStatus.OK);
    	}
    	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    }
    
    
    // COUNTRY POST
    @RequestMapping(value = "/addCountry", method = RequestMethod.POST)
    public ResponseEntity<?> addCountry(Country myCountry) {
    	
    	Country c = null;
    	c = this.countryRepository.findByCountryName(myCountry.getCountryName());
    	if (!c.equals(null)) {
        	return new ResponseEntity<>("ADDING COUNTRY FAILED", HttpStatus.BAD_REQUEST);
    	}
    	
    	this.countryRepository.save(c);
    	return new ResponseEntity<>("ADDING COUNTRY SUCCESS", HttpStatus.OK);
    }
    
    // GET CURRING MEASURES POST
    @RequestMapping(value = "/getCurringMeasures", method = RequestMethod.POST)
    public ResponseEntity<?> getCurringMeasures(Patient p) {
    	
    	return new ResponseEntity<>("CURRING MEASURES SUCCESSFULLY FOUND", HttpStatus.OK);
    }
}
