package edu.eci.arep.databasearep.Entities;

import java.sql.SQLException;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public interface IDataServices {

    public List<Student> findAll() throws SQLException ;  
    public void insertStudent(Student s) throws SQLException;
}