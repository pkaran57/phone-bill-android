package edu.pdx.cs410j.pkaran.phonebill.app.viewmodels;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill.PhoneBill;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonebill.PhoneBillRepository;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCallRepository;

import java.util.concurrent.ExecutionException;

public class AddPhoneCallViewModal extends ViewModel {

    private PhoneCallRepository phoneCallRepository;
    private PhoneBillRepository phoneBillRepository;

    public void instantiateRepo(Application application) {
        phoneCallRepository = new PhoneCallRepository(application);
        phoneBillRepository = new PhoneBillRepository(application);
    }

    public boolean addPhoneCall(PhoneCall phoneCall) throws ExecutionException, InterruptedException {
        boolean newPhoneBillCreated = false;

        PhoneBill phoneBill = phoneBillRepository.findPhoneBill(phoneCall.getCustomerName());

        if(phoneBill == null) {
            phoneBillRepository.addPhoneBill(phoneCall.getCustomerName());
            newPhoneBillCreated = true;
        }

        phoneCallRepository.addPhoneCall(phoneCall);
        return newPhoneBillCreated;
    }
}
