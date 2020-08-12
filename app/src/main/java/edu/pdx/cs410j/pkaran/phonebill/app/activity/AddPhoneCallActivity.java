package edu.pdx.cs410j.pkaran.phonebill.app.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.pdx.cs410j.pkaran.phonebill.R;
import edu.pdx.cs410j.pkaran.phonebill.app.viewmodels.AddPhoneCallViewModal;

public class AddPhoneCallActivity extends AppCompatActivity {

    private AddPhoneCallViewModal viewModal;

    public synchronized AddPhoneCallViewModal getAddCallBillViewModal() {
        if (viewModal == null) {
            ViewModelProvider provider = new ViewModelProvider(this);
            viewModal = provider.get(AddPhoneCallViewModal.class);
            viewModal.instantiateRepo(this.getApplication());
        }
        return viewModal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_call);

        viewModal = getAddCallBillViewModal();

        Button createPhoneCallButton = findViewById(R.id.add_phone_call_to_phone_bill_button);

        EditText customer = findViewById(R.id.phone_bill_customer_name_edit_text);
        EditText caller = findViewById(R.id.caller_edit_text);
        EditText callee = findViewById(R.id.callee_edit_text);
        EditText start = findViewById(R.id.start_edit_text);
        EditText end = findViewById(R.id.end_edit_text);

        //viewModal.addPhoneCall();
    }
}