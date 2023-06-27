package edu.hanu.employeemanagementsystem.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Experience;
import edu.hanu.employeemanagementsystem.models.Fresher;
import edu.hanu.employeemanagementsystem.models.Intern;

@Dao
public interface EmployeeDao {
    @Insert
    void insertEmployee(Employee employee);

    @Query("SELECT * FROM employee")
    List<Employee> getListEmployee();
}
