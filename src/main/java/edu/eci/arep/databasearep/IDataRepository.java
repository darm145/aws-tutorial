package edu.eci.arep.databasearep;

import java.sql.SQLException;
import java.util.*;

import edu.eci.arep.databasearep.Entities.Student;


public interface IDataRepository {
    public List<Student> findAll() throws SQLException ;  
    public void insertStudent(Student s) throws SQLException;
}