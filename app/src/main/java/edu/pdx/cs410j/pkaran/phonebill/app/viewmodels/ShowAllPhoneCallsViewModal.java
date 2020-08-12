package edu.pdx.cs410j.pkaran.phonebill.app.viewmodels;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCallRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShowAllPhoneCallsViewModal extends ViewModel {

    private PhoneCallRepository phoneCallRepository;

    public void instantiateRepo(Application application) {
        phoneCallRepository = new PhoneCallRepository(application);
    }

    public List<PhoneCall> getPhoneCallsForCustomer(String customerName) throws ExecutionException, InterruptedException {
        return phoneCallRepository.getPhoneCalls(customerName);
    }
}
