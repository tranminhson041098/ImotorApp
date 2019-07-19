package com.linearlayout.imotorapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResult {

@SerializedName("customerName")
@Expose
public String customerName;
@SerializedName("taxCode")
@Expose
public String taxCode;
@SerializedName("address")
@Expose
public String address;
@SerializedName("phone")
@Expose
public String phone;
@SerializedName("listBike")
@Expose
public List<String> listBike = null;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getListBike() {
        return listBike;
    }

    public void setListBike(List<String> listBike) {
        this.listBike = listBike;
    }
}

