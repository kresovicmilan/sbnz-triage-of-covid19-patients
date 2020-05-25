package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import repository.MyRepository;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	MyRepository myRepository;
	
	// LOGIN POST
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(User myUser) {
    	
    	User u = null;
    	u = this.myRepository.findByUsername(myUser.getUsername());
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
    	u = myRepository.findByUsername(myUser.getUsername());
    	if (!u.equals(null)) {
        	return new ResponseEntity<>("REGISTER FAILED", HttpStatus.BAD_REQUEST);
    	}
    	
        this.myRepository.save(myUser);
    	return new ResponseEntity<>("REGISTER SUCCESS", HttpStatus.OK);
    }
    
    // GET CURRING MEASURES POST
}
