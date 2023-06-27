package edu.hanu.employeemanagementsystem.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.employeemanagementsystem.EmployeeListener;
import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.adapters.EmployeeAdapter;
import edu.hanu.employeemanagementsystem.db.EmployeeDb;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;
import edu.hanu.employeemanagementsystem.models.Employee;

public class MainActivity extends AppCompatActivity implements EmployeeListener {
    private static int UPDATE_REQUEST_CODE = 6969;
    private static int ADD_REQUEST_CODE = 1010;
    private EmployeeAdapter adapter;
    SearchView searchView;
    private List<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycleView = findViewById(R.id.recycleView);
        searchView = findViewById(R.id.search);

        searchEmployee();

        adapter = new EmployeeAdapter(MainActivity.this, this);
        employees = new ArrayList<>();
        adapter.setData(employees);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, ADD_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Intent intent = new Intent(this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("updateEmployee", employee);
        intent.putExtras(bundle);
        startActivityForResult(intent, UPDATE_REQUEST_CODE);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete employee")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EmployeeDb.getInstance(getApplicationContext()).getEmployeeDao().deleteEmployee(employee);
                        Toast.makeText(MainActivity.this, "Delete employee successfully!", Toast.LENGTH_SHORT).show();
                        employees = EmployeeDb.getInstance(getApplicationContext()).getEmployeeDao().getListEmployee();
                        adapter.setData(employees);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
        else if(requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }

    private void loadData() {
        employees = EmployeeDb.getInstance(this).getEmployeeDao().getListEmployee();
        adapter.setData(employees);
    }

    private void searchEmployee() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}