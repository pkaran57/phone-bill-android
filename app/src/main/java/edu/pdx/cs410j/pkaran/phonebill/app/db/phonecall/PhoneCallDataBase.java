package edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill.PhoneBill;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PhoneCall.class, PhoneBill.class}, version = 1)
public abstract class PhoneCallDataBase extends RoomDatabase {
    public abstract PhoneCallDAO phoneCallDAO();

    private static PhoneCallDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PhoneCallDataBase getPhoneCallDataBase(Context context) {
        if(INSTANCE == null) {
            synchronized (PhoneCallDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneCallDataBase.class, "phone_call_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}