package edu.eci.arep.databasearep.Entities;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import edu.eci.arep.databasearep.IDataRepository;


@Component
public class StudentService implements IDataServices{
	
	@Autowired
	private IDataRepository dataRepository;
	
	@Override
	public List<Student> findAll() throws SQLException {
		return dataRepository.findAll();
    }
    @Override
	public void insertStudent(Student s) throws SQLException {
		dataRepository.insertStudent(s);
	}
}