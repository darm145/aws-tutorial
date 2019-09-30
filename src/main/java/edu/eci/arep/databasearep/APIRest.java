package edu.eci.arep.databasearep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arep.databasearep.Entities.Student;

@RestController
public class APIRest {

	@Autowired
	IDataRepository studentServices;
	
	@RequestMapping(value="/Students",method = RequestMethod.GET)
	public ResponseEntity<?> listAllUsers(){
	    try {
	        return new ResponseEntity<>(studentServices.findAll(),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
	    }
	}
	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody Student student) {
		try {
			studentServices.insertStudent(student);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

	}
}