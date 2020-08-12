package edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;

@Dao
@TypeConverters({Converters.class})
public interface PhoneCallDAO {

    @Query("SELECT * FROM phonecall")
    List<PhoneCall> getAll();

    @Query("SELECT * FROM phonecall WHERE customer_name = :customerName")
    List<PhoneCall> findByCustomerName(String customerName);

    @Insert
    void insertAll(PhoneCall... phoneCalls);
}
