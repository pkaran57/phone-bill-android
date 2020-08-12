package edu.pdx.cs410j.pkaran.phonebill.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.pdx.cs410j.pkaran.phonebill.R;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.viewmodels.AddPhoneCallViewModal;

import java.util.Date;

import static edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall.*;
import static edu.pdx.cs410j.pkaran.phonebill.app.utils.ToastUtil.showErrorToast;

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

        EditText customerEditText = findViewById(R.id.phone_bill_customer_name_edit_text);
        EditText callerEditText = findViewById(R.id.caller_edit_text);
        EditText calleeEditText = findViewById(R.id.callee_edit_text);
        EditText startEditText = findViewById(R.id.start_edit_text);
        EditText endEditText = findViewById(R.id.end_edit_text);

        createPhoneCallButton.setOnClickListener(view -> {
            String customer = customerEditText.getText().toString();
            String caller = callerEditText.getText().toString();
            String callee = calleeEditText.getText().toString();
            String startString = startEditText.getText().toString();
            String endString = endEditText.getText().toString();

            if(customer.isEmpty()) {
                showErrorToast(this, "Customer Name cannot be empty");
                return;
            }

            if (!isValidPhoneNumber(caller)) {
                showErrorToast(this, String.format("Caller phone number is invalid. Phone numbers should have the form nnn-nnn-nnnn where n is a number 0-9 but got %s", caller));
                return;
            }

            if (!isValidPhoneNumber(callee)) {
                showErrorToast(this, String.format("Callee phone number is invalid. Phone numbers should have the form nnn-nnn-nnnn where n is a number 0-9 but got %s", caller));
                return;
            }

            Date startDate;
            if (isTimeStampValid(startString)) {
                startDate = parseTimeStamp(startString);
            } else {
                showErrorToast(this, String.format("Start time is invalid. It should be in the following format: 'mm/dd/yyyy hh:mm am/pm' but got %s", startString));
                return;
            }

            Date endDate;
            if (isTimeStampValid(endString)) {
                endDate = parseTimeStamp(endString);
            } else {
                showErrorToast(this, String.format("End time is invalid. It should be in the following format: 'mm/dd/yyyy hh:mm am/pm' but got %s", endString));
                return;
            }

            PhoneCall phoneCall = new PhoneCall(customer, caller, callee, startDate, endDate);

            try {
                boolean newPhoneBillCreated = viewModal.addPhoneCall(phoneCall);
                String message = newPhoneBillCreated ? "SUCCESS: Created a new phone bill for customer and added phone call to it." :
                        "SUCCESS: Added phone call to the existing phone bill for customer.";

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                showErrorToast(this, e.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.read_me) {
            Intent intent = new Intent(this, ReadMeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}