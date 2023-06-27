package edu.hanu.employeemanagementsystem.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.hanu.employeemanagementsystem.models.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDb extends RoomDatabase {
    private static final String DATABASE_NAME = "employee.db";
    private static EmployeeDb instance;

    public static synchronized EmployeeDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EmployeeDb.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract EmployeeDao getEmployeeDao();
}
