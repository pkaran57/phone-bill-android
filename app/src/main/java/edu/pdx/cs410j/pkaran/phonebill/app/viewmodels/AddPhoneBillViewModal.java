package edu.pdx.cs410j.pkaran.phonebill.app.viewmodels;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import edu.pdx.cs410j.pkaran.phonebill.app.db.PhoneBillRepository;

import java.util.concurrent.ExecutionException;

public class AddPhoneBillViewModal extends ViewModel {

    private PhoneBillRepository phoneBillRepository;

    public void instantiateRepo(Application application) {
        phoneBillRepository = new PhoneBillRepository(application);
    }

    public void addPhoneBill(String customerName) throws ExecutionException, InterruptedException {
        phoneBillRepository.addPhoneBill(customerName);
    }
}
