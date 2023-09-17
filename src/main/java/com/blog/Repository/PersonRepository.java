package com.blog.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blog.entity.Person;

@Repository
public class PersonRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	public List<Person> getallPerson() {
		List<Person> list = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbl_57", "root", "root");
			statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from person");
			while (resultset.next()) {
				Person p = new Person(resultset.getString("name"), resultset.getInt("id"));
				list.add(p);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		return list;

	}

	public Person addPerson(Person person) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbl_57", "root", "root");
			statement = connection.createStatement();
			boolean result = statement.execute("insert into person(id,name) VALUES ('" +person.getId() +"' , '" +person.getName() +"')");
			logger.info("the result of query is {}",result);
		}catch (SQLException e) {
			throw new RuntimeException();
		}  finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		return person;
	}

}
