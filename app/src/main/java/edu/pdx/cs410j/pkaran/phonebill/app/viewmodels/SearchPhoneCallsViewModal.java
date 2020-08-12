package edu.pdx.cs410j.pkaran.phonebill.app.viewmodels;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill.PhoneBill;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill.PhoneBillRepository;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCallRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchPhoneCallsViewModal extends ViewModel {

    private PhoneCallRepository phoneCallRepository;
    private PhoneBillRepository phoneBillRepository;

    public void instantiateRepo(Application application) {
        phoneCallRepository = new PhoneCallRepository(application);
        phoneBillRepository = new PhoneBillRepository(application);
    }

    public List<PhoneCall> getPhoneCallsForCustomer(String customerName) throws ExecutionException, InterruptedException {
        return phoneCallRepository.getPhoneCalls(customerName);
    }

    public boolean phoneBillExist(String customerName) throws ExecutionException, InterruptedException {
        PhoneBill phoneBill = phoneBillRepository.findPhoneBill(customerName);
        return phoneBill != null;
    }
}
