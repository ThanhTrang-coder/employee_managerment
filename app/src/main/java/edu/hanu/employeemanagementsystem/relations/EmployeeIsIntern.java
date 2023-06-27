package edu.hanu.employeemanagementsystem.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Intern;

public class EmployeeIsIntern {
    @Embedded
    public Employee employee;
    @Relation(
            parentColumn = "employeeId",
            entityColumn = "employeeId"
    )
    public Intern intern;
}
