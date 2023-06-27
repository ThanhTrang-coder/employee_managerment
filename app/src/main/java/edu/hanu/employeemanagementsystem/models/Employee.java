package edu.hanu.employeemanagementsystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import edu.hanu.employeemanagementsystem.EmployeeListener;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmployeeTypeException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;

@Entity
public class Employee implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int employeeId = 0;
    public String fullName;
    public String birthDay;
    public String phone;
    public String email;
    public String employeeType;
    @Ignore
    public Certificate certificate;

    public Employee(String fullName, String birthDay, String phone, String email, String employeeType) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.employeeType = employeeType;
    }

    public Employee(String fullName, String birthDay, String phone, String email, String employeeType, Certificate certificate) {
        this(fullName, birthDay, phone, email, employeeType);
        this.certificate = certificate;
    }

    public Employee() {
    }


    protected Employee(Parcel in) {
        fullName = in.readString();
        birthDay = in.readString();
        phone = in.readString();
        email = in.readString();
        employeeType = in.readString();
        certificate = (Certificate) in.readParcelable(Certificate.class.getClassLoader());
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) throws InvalidFullNameException {
        if (fullName.isEmpty()) {
            throw new InvalidFullNameException("Full name is required.");
        } else {
            this.fullName = fullName;
        }
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) throws InvalidBirthdayException{
        String birthDayPattern = "^([0-2]\\d||3[0-1])/(0\\d||1[0-2])/(\\d\\d)?\\d\\d$";
        if(!birthDay.matches(birthDayPattern)){
            throw new InvalidBirthdayException("Birthday is invalid. Example: 12/12/1988");
        } else if(birthDay.isEmpty()) {
            throw new InvalidBirthdayException("Birthday is required");
        } else {
            this.birthDay = birthDay;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws InvalidPhoneException {
        String phonePattern = "[0-9]{10,16}";
        if(!phone.matches(phonePattern)) {
            throw new InvalidPhoneException("Phone number is invalid. Example: 0988676003");
        } else if(phone.isEmpty()) {
            throw new InvalidPhoneException("Phone is required");
        } else {
            this.phone = phone;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException{
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[ _A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[ A-Za-z0-9]+)*(\\.[ A-Za-z]{2,})$";
        if(!email.matches(emailPattern)) {
            throw new InvalidEmailException("Email is invalid. Example: thanhhh@gmail.com");
        } else if(email.isEmpty()) {
            throw new InvalidEmailException("Email is required");
        } else {
            this.email = email;
        }
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) throws InvalidEmployeeTypeException {
        if(employeeType.isEmpty()) {
            throw new InvalidEmployeeTypeException("EmployeeType is required.");
        } else {
            this.employeeType = employeeType;
        }
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullName='" + fullName + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", certificate=" + certificate +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(fullName);
        parcel.writeString(birthDay);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(employeeType);
        parcel.writeParcelable(certificate, flags);
    }
}
