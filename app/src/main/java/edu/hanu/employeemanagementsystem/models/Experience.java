package edu.hanu.employeemanagementsystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Experience extends Employee implements Parcelable{
    @PrimaryKey
    private int experienceId;
    private int employeeId;
    private String expInYear;
    private String proSkill;
    private Certificate certificate;

    public Experience() {
    }

    public Experience(String name, String birthDay, String phone, String email, String employeeType, String expInYear, String proSkill) {
        super(name, birthDay, phone, email, employeeType);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public Experience(String name, String birthDay, String phone, String email, String employeeType) {
        super(name, birthDay, phone, email, employeeType);
    }

    public Experience(String expInYear, String proSkill, Certificate certificate) {
        this.expInYear = expInYear;
        this.proSkill = proSkill;
        this.certificate = certificate;
    }

    protected Experience(Parcel in) {
        fullName = in.readString();
        birthDay = in.readString();
        phone = in.readString();
        email = in.readString();
        employeeType = in.readString();
        expInYear = in.readString();
        proSkill = in.readString();
        certificate = (Certificate) in.readParcelable(Certificate.class.getClassLoader());
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(String expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "fullName=" + fullName +
                "birthDay=" + birthDay +
                "certificateExp=" + certificate +
                ", expInYear='" + expInYear + '\'' +
                ", proSkill='" + proSkill + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(birthDay);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(employeeType);
        parcel.writeString(expInYear);
        parcel.writeString(proSkill);
        parcel.writeParcelable(certificate, i);
    }
}
