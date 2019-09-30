package edu.eci.arep.databasearep;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arep.databasearep.Entities.Student;



@Component
public class DataRepositoryImpl implements IDataRepository {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Student> findAll() throws SQLException {
		String query = "SELECT * FROM students;";
		List<Student> auctions = new ArrayList<Student>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Student Student = new Student();
                Student.setId(rs.getString("id"));
                Student.setName(rs.getString("name"));						
				auctions.add(Student);
			}
			connection.close();
			return auctions;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	
	}

    @Override
    public void insertStudent(Student s) throws SQLException {
        String query = "insert Into students(id,name) values ('"+s.getId()+"','"+s.getName()+"');";
        Connection connection = null;
		try {
			connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
    }
}