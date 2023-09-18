package com.blog.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.DriverManager;
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
	
//	@Autowired
	private Connection connection;
	
	public PersonRepository(Connection connection) {
		this.connection=connection;
		createTablePerson1();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	public List<Person> getallPerson() {
		List<Person> list = new ArrayList<>();
//		Connection connection = null;
		Statement statement = null;
		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbl_57", "root", "root");
			statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from person");
			while (resultset.next()) {
				Person p = new Person(resultset.getString("name"), resultset.getInt("id"));
				list.add(p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;

	}

	public Person addPerson(Person person) {
//		Connection connection = null;
		Statement statement = null;
		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbl_57", "root", "root");
			statement = connection.createStatement();
			boolean result = statement.execute("insert into person(id,name) VALUES ('" +person.getId() +"' , '" +person.getName() +"')");
			logger.info("the result of query is {}",result);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}  finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException();
//			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return person;
	}
	
	public Person addPersonWithPreparedStatement(Person person) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("insert into person(id,name) VALUES (?,?)");
			ps.setInt(1,person.getId());
			ps.setString(2, person.getName());
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				ps.close();
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return person;
	}
	
	public void createTablePerson1() {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("create table if not exists person1(name varchar(30), id int)");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				statement.close();
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public int updatePerson(int id, String name) {
		Boolean autocommit = null;
		try {
			autocommit = connection.getAutoCommit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int result =0;
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement("update person set id =? where name =?");
			ps.setInt(1, id);
			ps.setString(2, name);
			result = ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
		return result;
	}
	

}
