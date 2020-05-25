package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Country;
import model.Patient;
import model.User;
import repository.CountryRepository;
import repository.UserRepository;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	// LOGIN POST
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(User myUser) {
    	
    	User u = null;
    	u = this.userRepository.findByUsername(myUser.getUsername());
    	if (u.equals(null)) {
        	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    	}
    	if(u.getPassword().equals(myUser.getPassword())) {
        	return new ResponseEntity<>("LOGIN SUCCESS", HttpStatus.OK);
    	}
    	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    }
    
    // REGISTER POST
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> userRegister(User myUser) {
    	
    	User u = null;
    	u = userRepository.findByUsername(myUser.getUsername());
    	if (!u.equals(null)) {
        	return new ResponseEntity<>("REGISTER FAILED", HttpStatus.BAD_REQUEST);
    	}
    	
        this.userRepository.save(myUser);
    	return new ResponseEntity<>("REGISTER SUCCESS", HttpStatus.OK);
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
