package edu.hanu.employeemanagementsystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import edu.hanu.employeemanagementsystem.models.Certificate;

@Entity
public class Fresher extends Employee implements Parcelable{
    @PrimaryKey
    private int fresherId;
    private int employeeId;
    private String graduationDate;
    private int graduationRank;
    private String graduationEdu;
    private Certificate certificate;

    public Fresher() {

    }

    public Fresher(String fullName, String birthDay, String phone, String email, String employeeType, String graduationDate, int graduationRank, String graduationEdu) {
        super(fullName, birthDay, phone, email, employeeType);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.graduationEdu = graduationEdu;
    }

    public Fresher(String name, String birthDay, String phone, String email, String employeeType) {
        super(name, birthDay, phone, email, employeeType);
    }

    protected Fresher(Parcel in) {
        fullName = in.readString();
        birthDay = in.readString();
        phone = in.readString();
        email = in.readString();
        employeeType = in.readString();
        graduationDate = in.readString();
        graduationRank = in.readInt();
        graduationEdu = in.readString();
        certificate = (Certificate) in.readParcelable(Certificate.class.getClassLoader());
    }

    public static final Creator<Fresher> CREATOR = new Creator<Fresher>() {
        @Override
        public Fresher createFromParcel(Parcel in) {
            return new Fresher(in);
        }

        @Override
        public Fresher[] newArray(int size) {
            return new Fresher[size];
        }
    };

    public int getFresherId() {
        return fresherId;
    }

    public void setFresherId(int fresherId) {
        this.fresherId = fresherId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getGraduationEdu() {
        return graduationEdu;
    }

    public void setGraduationEdu(String graduationEdu) {
        this.graduationEdu = graduationEdu;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public int getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(int graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return graduationEdu;
    }

    public void setEducation(String graduationEdu) {
        this.graduationEdu = graduationEdu;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Fresher{" +
                "certificateFresher=" + certificate +
                ", graduationDate='" + graduationDate + '\'' +
                ", graduationRank=" + graduationRank +
                ", education='" + graduationEdu + '\'' +
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
        parcel.writeString(graduationDate);
        parcel.writeInt(graduationRank);
        parcel.writeString(graduationEdu);
        parcel.writeParcelable(certificate, i);
    }
}
