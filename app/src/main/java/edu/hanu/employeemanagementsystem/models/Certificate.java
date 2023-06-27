package edu.hanu.employeemanagementsystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Certificate implements Parcelable{
    @PrimaryKey
    private int certificatedID;
    private String certificateName;
    private String certificateRank;
    private String certificatedDate;

    public Certificate (String certificateName, String certificateRank, String certificatedDate) {
        this.certificateName = certificateName;
        this.certificateRank = certificateRank;
        this.certificatedDate = certificatedDate;
    }

    public Certificate (String certificateName) {
        this.certificateName = certificateName;
        this.certificateRank = getCertificateRank();
        this.certificatedDate = getCertificatedDate();
    }

    protected Certificate(Parcel in) {
        certificatedID = in.readInt();
        certificateName = in.readString();
        certificateRank = in.readString();
        certificatedDate = in.readString();
    }

    public static final Creator<Certificate> CREATOR = new Creator<Certificate>() {
        @Override
        public Certificate createFromParcel(Parcel in) {
            return new Certificate(in);
        }

        @Override
        public Certificate[] newArray(int size) {
            return new Certificate[size];
        }
    };

    public int getCertificatedID() {
        return certificatedID;
    }

    public void setCertificatedID(int certificatedID) {
        this.certificatedID = certificatedID;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateRank() {
        return certificateRank;
    }

    public void setCertificateRank(String certificateRank) {
        this.certificateRank = certificateRank;
    }

    public String getCertificatedDate() {
        return certificatedDate;
    }

    public void setCertificatedDate(String certificatedDate) {
        this.certificatedDate = certificatedDate;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificatedID=" + certificatedID +
                ", certificateName='" + certificateName + '\'' +
                ", certificateRank='" + certificateRank + '\'' +
                ", certificatedDate='" + certificatedDate + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(certificatedID);
        parcel.writeString(certificateName);
        parcel.writeString(certificateRank);
        parcel.writeString(certificatedDate);
    }
}
