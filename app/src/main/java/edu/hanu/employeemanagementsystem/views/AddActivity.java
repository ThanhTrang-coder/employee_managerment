package edu.hanu.employeemanagementsystem.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.db.EmployeeDb;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;
import edu.hanu.employeemanagementsystem.models.Employee;

public class AddActivity extends AppCompatActivity{
    private String name, birthDay, phone, email;
    private EditText edtName, edtBirthDay, edtPhone, edtEmail;
    private Button btnAdd, btnCancel;

    private Employee employee = new Employee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        btnCancel.setOnClickListener(view -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnAdd.setOnClickListener(view -> {
            btnAddClick();
        });
    }

    private void initView() {
        edtName = findViewById(R.id.edtName);
        edtBirthDay = findViewById(R.id.edtBirthDay);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);

        //button
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void btnAddClick() {
        name = edtName.getText().toString();
        birthDay = edtBirthDay.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();

        if(checkEmployeeInfo()) {
            Employee employee = new Employee(name, birthDay, phone, email);
            EmployeeDb.getInstance(this).getEmployeeDao().insertEmployee(employee);
            Toast.makeText(this, "Add user successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    private boolean checkEmployeeInfo() {
        try {
            employee.setFullName(name);
            employee.setBirthDay(birthDay);
            employee.setPhone(phone);
            employee.setEmail(email);
            return true;
        } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException | InvalidEmailException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
