package edu.hanu.employeemanagementsystem.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.db.EmployeeDb;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;
import edu.hanu.employeemanagementsystem.models.Employee;

public class UpdateActivity extends AppCompatActivity {
    private String name, birthDay, phone, email;
    private EditText edtName, edtBirthDay, edtPhone, edtEmail;
    Button btnUpdate, btnCancel;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();

        employee = (Employee) getIntent().getExtras().get("updateEmployee");
        setGeneralInformation(employee);

        btnCancel.setOnClickListener(view -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnUpdate.setOnClickListener(view -> {
            btnUpdateClick();
        });
    }

    private void btnUpdateClick() {
        name = edtName.getText().toString();
        birthDay = edtBirthDay.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();

        try {
            employee.setFullName(name);
            employee.setBirthDay(birthDay);
            employee.setPhone(phone);
            employee.setEmail(email);
        } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException |
                 InvalidEmailException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        EmployeeDb.getInstance(this).getEmployeeDao().updateEmployee(employee);
        Toast.makeText(this, "Update employee successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void initView() {
        edtName = findViewById(R.id.edtName);
        edtBirthDay = findViewById(R.id.edtBirthDay);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);

        //button
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void setGeneralInformation(Employee employee) {
        edtName.setText(employee.getFullName());
        edtBirthDay.setText(employee.getBirthDay());
        edtPhone.setText(employee.getPhone());
        edtEmail.setText(employee.getEmail());
    }
}