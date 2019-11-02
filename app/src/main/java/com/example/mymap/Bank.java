package com.example.mymap;

public class Bank {
    String bankId;
    String bankName;
    String bankAddress;
    String bankPhone;

    public Bank()
    {

    }

    public Bank(String bankId, String bankName, String bankAddress, String bankPhone) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.bankPhone = bankPhone;
    }

    public String getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public String getBankPhone() {
        return bankPhone;
    }
}
