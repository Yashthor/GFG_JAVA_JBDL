package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Service.PersonService;
import com.blog.entity.Person;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping("/getPerson")
	public ResponseEntity<List<Person>> getPersonData(){
		List<Person>list = personService.getPersonData();
		ResponseEntity<List<Person>> responseEntity = new ResponseEntity<>(list,HttpStatus.OK);
		return responseEntity;		
	}
	
	@PostMapping("/addPerson")
	public ResponseEntity<Person> addPerson(@RequestBody Person person){
		if(person.getId()==null || person.getName()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Person p =personService.addPerson(person);
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}

}
