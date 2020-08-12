package edu.pdx.cs410j.pkaran.phonebill.app.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PhoneBill {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "customer_name")
    private String customerName;

    public PhoneBill(String customerName) {
        this.customerName = customerName;
    }

    @NonNull
    public String getCustomerName() {
        return customerName;
    }
}