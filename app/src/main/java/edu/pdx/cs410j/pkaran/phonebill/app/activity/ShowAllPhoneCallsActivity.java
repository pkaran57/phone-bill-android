package edu.pdx.cs410j.pkaran.phonebill.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.pdx.cs410j.pkaran.phonebill.R;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.viewmodels.ShowAllPhoneCallsViewModal;

import java.util.List;

import static edu.pdx.cs410j.pkaran.phonebill.app.utils.DialogueUtils.showErrorDialogue;

public class ShowAllPhoneCallsActivity extends AppCompatActivity {

    private ShowAllPhoneCallsViewModal viewModal;

    public synchronized ShowAllPhoneCallsViewModal getShowAllPhoneCallsViewModal() {
        if (viewModal == null) {
            ViewModelProvider provider = new ViewModelProvider(this);
            viewModal = provider.get(ShowAllPhoneCallsViewModal.class);
            viewModal.instantiateRepo(this.getApplication());
        }
        return viewModal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_phone_calls);

        viewModal = getShowAllPhoneCallsViewModal();

        Button searchPhoneBillButton = findViewById(R.id.search_phone_bill_button);
        EditText customerNameEditText = findViewById(R.id.customer_name_input_2);
        ListView phoneCallsListVew = findViewById(R.id.phone_calls_list);

        searchPhoneBillButton.setOnClickListener(view -> {
            String customerName = customerNameEditText.getText().toString();

            if(customerName.isEmpty()) {
                showErrorDialogue(this, "Customer name cannot be empty");
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
                        items = phoneCallsForCustomer.stream().map(PhoneCall::toString).toArray(String[]::new);
                    }
                }

                ArrayAdapter adapter = new ArrayAdapter<>(this,
                        R.layout.phone_calls_listview,
                        items);

                phoneCallsListVew.setAdapter(adapter);
            } catch (Exception e) {
                showErrorDialogue(this, e.getMessage());
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