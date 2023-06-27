package edu.hanu.employeemanagementsystem.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.employeemanagementsystem.EmployeeListener;
import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.db.EmployeeDb;
import edu.hanu.employeemanagementsystem.models.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> implements Filterable {
    private Context context;
    private List<Employee> list;
    private List<Employee> listTemp;
    private EmployeeListener listener;

    public EmployeeAdapter(Context context, EmployeeListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<Employee> list) {
        this.list = list;
        this.listTemp = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee employee = list.get(position);
        holder.bindClick(employee);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()) {
                    list = listTemp;
                } else {
                    ArrayList<Employee> employees = new ArrayList<>();
                    for(Employee emp: listTemp) {
                        if(emp.getFullName().contains(strSearch)) {
                            employees.add(emp);
                        }
                    }
                    list = employees;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<Employee>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvBirthDay, tvPhone, tvEmail;
        private ImageButton btnEdit, btnDelete;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);

            initViewExperience();
        }

        private void initViewExperience() {
            tvName = itemView.findViewById(R.id.tvName);
            tvBirthDay = itemView.findViewById(R.id.tvBirthDay);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bindClick(Employee employee) {
            tvName.setText(employee.getFullName());
            tvBirthDay.setText("Birthday: " + employee.getBirthDay());
            tvPhone.setText("Phone: " + employee.getPhone());
            tvEmail.setText("Email: " + employee.getEmail());

            btnEdit.setOnClickListener(view -> {
                listener.updateEmployee(employee);
            });

            btnDelete.setOnClickListener(view -> {
                listener.deleteEmployee(employee);
            });
        }
    }
}
