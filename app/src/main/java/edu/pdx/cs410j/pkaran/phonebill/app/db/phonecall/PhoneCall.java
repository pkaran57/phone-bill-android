package edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall;

import androidx.annotation.NonNull;
import androidx.room.*;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill.PhoneBill;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = PhoneBill.class,
        parentColumns = "customer_name",
        childColumns = "customer_name"))
public class PhoneCall {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "customer_name")
    private String customerName;

    @NonNull
    @ColumnInfo(name = "caller")
    private String caller;

    @NonNull
    @ColumnInfo(name = "callee")
    private String callee;

    @NonNull
    @ColumnInfo(name = "start_time")
    @TypeConverters({Converters.class})
    private Date startTime;

    @NonNull
    @ColumnInfo(name = "end_time")
    @TypeConverters({Converters.class})
    private Date endTime;

    public PhoneCall(@NonNull String customerName, @NonNull String caller, @NonNull String callee, @NonNull Date startTime, @NonNull Date endTime) {
        this.customerName = customerName;
        this.caller = caller;
        this.callee = callee;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCaller() {
        return caller;
    }

    public String getCallee() {
        return callee;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
