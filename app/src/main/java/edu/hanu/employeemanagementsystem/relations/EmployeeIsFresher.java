package edu.hanu.employeemanagementsystem.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Fresher;

public class EmployeeIsFresher {
    @Embedded public Employee employee;
    @Relation(
            parentColumn = "employeeId",
            entityColumn = "employeeId"
    )
    public Fresher fresher;
}
