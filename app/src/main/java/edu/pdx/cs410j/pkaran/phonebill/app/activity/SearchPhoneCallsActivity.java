package edu.pdx.cs410j.pkaran.phonebill.app.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.pdx.cs410j.pkaran.phonebill.R;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.viewmodels.SearchPhoneCallsViewModal;

import java.util.Date;
import java.util.List;

import static edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall.*;
import static edu.pdx.cs410j.pkaran.phonebill.app.utils.ToastUtil.showErrorToast;

public class SearchPhoneCallsActivity extends AppCompatActivity {

    private SearchPhoneCallsViewModal viewModal;

    public synchronized SearchPhoneCallsViewModal getSearchPhoneCallsViewModal() {
        if (viewModal == null) {
            ViewModelProvider provider = new ViewModelProvider(this);
            viewModal = provider.get(SearchPhoneCallsViewModal.class);
            viewModal.instantiateRepo(this.getApplication());
        }
        return viewModal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_phone_calls);

        viewModal = getSearchPhoneCallsViewModal();

        Button searchPhoneCallsButton = findViewById(R.id.search_phone_calls_button_2);

        EditText customerEditText = findViewById(R.id.phone_bill_customer_name_edit_text2);
        EditText startEditText = findViewById(R.id.start_edit_text_2);
        EditText endEditText = findViewById(R.id.end_edit_text_2);
        ListView phoneCallsListVew = findViewById(R.id.search_phone_calls_result_list_view);

        searchPhoneCallsButton.setOnClickListener(view -> {
            String customerName = customerEditText.getText().toString();
            String startString = startEditText.getText().toString();
            String endString = endEditText.getText().toString();

            if(customerName.isEmpty()) {
                showErrorToast(this, "Customer name cannot be empty");
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

            String[] items;
            try {
                if(!viewModal.phoneBillExist(customerName)) {
                    items = new String[]{String.format("No Phone bill found for customer %s", customerName)};
                }
                else {
                    List<PhoneCall> phoneCallsForCustomer = viewModal.getPhoneCallsForCustomer(customerName);

                    if(phoneCallsForCustomer.isEmpty()) {
                        items = new String[]{String.format("No Phone calls found in phone bill for customer %s", customerName)};
                    } else {
                        List<PhoneCall> filteredPhoneCallsList = getPhoneCallsBetween(startDate, endDate, phoneCallsForCustomer);

                        if(filteredPhoneCallsList.isEmpty()) {
                            items = new String[]{String.format("No Phone calls found in between the start and end time for customer %s", customerName)};
                        } else {
                            items = filteredPhoneCallsList.stream().map(PhoneCall::toString).toArray(String[]::new);
                        }
                    }
                }

                ArrayAdapter adapter = new ArrayAdapter<>(this,
                        R.layout.phone_calls_listview,
                        items);

                phoneCallsListVew.setAdapter(adapter);
            } catch (Exception e) {
                showErrorToast(this, e.getMessage());
            }
        });
    }
}