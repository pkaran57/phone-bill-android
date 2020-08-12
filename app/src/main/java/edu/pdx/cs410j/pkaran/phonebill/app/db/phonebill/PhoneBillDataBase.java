package edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PhoneBill.class}, version = 1)
public abstract class PhoneBillDataBase extends RoomDatabase {
    public abstract PhoneBillDAO phoneBillDAO();

    private static PhoneBillDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PhoneBillDataBase getPhoneBillDataBase(Context context) {
        if(INSTANCE == null) {
            synchronized (PhoneBillDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneBillDataBase.class, "phone_bill_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}