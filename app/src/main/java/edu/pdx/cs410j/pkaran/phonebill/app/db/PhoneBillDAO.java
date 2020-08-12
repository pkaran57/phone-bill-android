package edu.pdx.cs410j.pkaran.phonebill.app.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneBillDAO {

    @Query("SELECT * FROM phonebill")
    List<PhoneBill> getAll();

    @Query("SELECT * FROM phonebill WHERE customer_name IN (:customerNames)")
    List<PhoneBill> loadAllByIds(String[] customerNames);

    @Query("SELECT * FROM phonebill WHERE customer_name = :customerName LIMIT 1")
    PhoneBill findByName(String customerName);

    @Insert
    void insertAll(PhoneBill... phoneBills);

    @Delete
    void delete(PhoneBill phoneBill);
}
