package edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill;

import android.app.Application;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PhoneBillRepository {

    private PhoneBillDAO phoneBillDAO;

    public PhoneBillRepository(Application application) {
        PhoneBillDataBase db = PhoneBillDataBase.getPhoneBillDataBase(application);
        phoneBillDAO = db.phoneBillDAO();
    }

    public void addPhoneBill(String customerName) throws ExecutionException, InterruptedException {
        Future<?> future = PhoneBillDataBase.databaseWriteExecutor.submit(() -> {
            PhoneBill phoneBill = new PhoneBill(customerName);
            phoneBillDAO.insertAll(phoneBill);
        });

        future.get();
    }
}