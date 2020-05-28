package sbnz.integracija.example.controller;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Patient;
import sbnz.integracija.example.model.AppUser;
import sbnz.integracija.example.repository.CountryRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.MyService;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MyService myService;
		
	// LOGIN POST
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(@RequestBody AppUser myUser) {
    	
    	try {
    		AppUser u = this.userRepository.findByUsername(myUser.getUsername()).orElseThrow(Exception::new);
    		if (u.getPassword().equals(myUser.getPassword())) {
            	return new ResponseEntity<>(u, HttpStatus.OK);
    		}
    	}
    	catch(Exception e) {
        	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    	}

    	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    }
    
    // REGISTER POST
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> userRegister(@RequestBody AppUser myUser) {
    	
    	try {
    		AppUser u = this.userRepository.findByUsername(myUser.getUsername()).orElseThrow(Exception::new);
    	}
    	catch(Exception e) {
            this.userRepository.save(myUser);
        	return new ResponseEntity<>("REGISTER SUCCESS", HttpStatus.OK);
    	}
    	return new ResponseEntity<>("LOGIN FAILED", HttpStatus.BAD_REQUEST);
    }
    
    // GET CURRING MEASURES POST
    @RequestMapping(value = "/getCurringMeasures/{cname}", method = RequestMethod.POST)
    public ResponseEntity<?> getCurringMeasures(@PathVariable String cname, Patient p) {
    	
    	p = this.myService.getCuringMeassures(p, cname);
    	
    	if (p!=null) {
    		return new ResponseEntity<>("CURRING MEASURES SUCCESSFULLY FOUND", HttpStatus.OK);
    	}
    	
		return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);

    }
    

}
