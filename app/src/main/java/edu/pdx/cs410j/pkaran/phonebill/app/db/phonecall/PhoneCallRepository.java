package edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall;

import android.app.Application;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PhoneCallRepository {

    private final PhoneCallDAO phoneCallDAO;

    public PhoneCallRepository(Application application) {
        PhoneCallDataBase db = PhoneCallDataBase.getPhoneCallDataBase(application);
        phoneCallDAO = db.phoneCallDAO();
    }

    public void addPhoneCall(PhoneCall phoneCall) throws ExecutionException, InterruptedException {
        Future<?> future = PhoneCallDataBase.databaseWriteExecutor.submit(() -> phoneCallDAO.insertAll(phoneCall));
        future.get();
    }
}