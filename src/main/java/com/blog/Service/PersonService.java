package com.blog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repository.PersonRepository;
import com.blog.entity.Person;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public List<Person>getPersonData(){
		return personRepository.getallPerson();
	}
	
	public Person addPerson(Person person) {
		return personRepository.addPerson(person);
	}

}
