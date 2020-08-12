package edu.pdx.cs410j.pkaran.phonebill.app.viewmodels;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCallRepository;

import java.util.concurrent.ExecutionException;

public class AddPhoneCallViewModal extends ViewModel {

    private PhoneCallRepository phoneCallRepository;

    public void instantiateRepo(Application application) {
        phoneCallRepository = new PhoneCallRepository(application);
    }

    public void addPhoneCall(PhoneCall phoneCall) throws ExecutionException, InterruptedException {
        phoneCallRepository.addPhoneCall(phoneCall);
    }
}
