package edu.hanu.employeemanagementsystem.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Experience;

public class EmployeeIsExperience {
    @Embedded public Employee employee;
    @Relation(
            parentColumn = "employeeType",
            entityColumn = "employeeType"
    )
    public Experience experience;
}
