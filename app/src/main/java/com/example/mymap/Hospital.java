package com.example.mymap;

public class Hospital {
    String hospitalId;
    String hospitalName;
    String hospitalAddress;
    String hospitalPhone;

    public Hospital()
    {

    }

    public Hospital(String hospitalId, String hospitalName, String hospitalAddress, String hospitalPhone) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalPhone = hospitalPhone;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }
}
