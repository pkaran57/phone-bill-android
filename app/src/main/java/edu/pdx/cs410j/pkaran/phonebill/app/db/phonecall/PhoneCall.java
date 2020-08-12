package edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
public class PhoneCall implements Comparable {

    //example:  01/02/2020 9:16 pm
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("M/d/yyyy h:m a");
    private static final DateFormat DATE_FORMAT = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

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

    /**
     * Check if the phone number is valid
     * @param phoneNumber phone number to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && Pattern.matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d", phoneNumber);
    }

    /**
     * Check if the timestamp is valid
     * @param timestamp timestamp to check
     * @return true if valid, false otherwise
     */
    public static boolean isTimeStampValid(String timestamp) {
        return parseTimeStamp(timestamp) != null;
    }

    /**
     * Parse timestamp into Date
     * @param timestamp timestamp to parse
     * @return parsed Date, if error encountered during parsing, null is returned
     */
    public static Date parseTimeStamp(String timestamp) {
        if (timestamp == null) {
            return null;
        }

        try {
            return SIMPLE_DATE_FORMAT.parse(timestamp);
        } catch (ParseException dateTimeParseException) {
            return null;
        }
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

    public String getStartTimeString() {
        return DATE_FORMAT.format(startTime);
    }

    public String getEndTimeString() {
        return DATE_FORMAT.format(endTime);
    }

    public Duration getDuration() {
        return Duration.between(startTime.toInstant(), endTime.toInstant());
    }

    @Override
    public int compareTo(Object object) {
        return Comparator
                .comparing(PhoneCall::getStartTime)
                .thenComparing(PhoneCall::getCaller)
                .compare(this, (PhoneCall) object);
    }

    @Override
    public String toString() {
        return String.format("Caller: %s\nCallee: %s\nStart time: %s\nEnd time: %s\nDuration (in sec.): %d",
                caller, callee, getStartTimeString(), getEndTimeString(), getDuration().getSeconds());
    }
}
