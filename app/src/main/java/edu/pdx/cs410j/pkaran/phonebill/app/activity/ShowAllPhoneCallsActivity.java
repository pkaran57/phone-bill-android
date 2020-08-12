package edu.pdx.cs410j.pkaran.phonebill.app.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.pdx.cs410j.pkaran.phonebill.R;
import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;
import edu.pdx.cs410j.pkaran.phonebill.app.utils.PrettyPrinter;
import edu.pdx.cs410j.pkaran.phonebill.app.viewmodels.ShowAllPhoneCallsViewModal;

import java.util.List;

import static edu.pdx.cs410j.pkaran.phonebill.app.utils.ToastUtil.showErrorToast;

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
        TextView showAllPhoneCallsTextView = findViewById(R.id.show_all_phone_calls_text_view);

        showAllPhoneCallsTextView.setMovementMethod(new ScrollingMovementMethod());
        showAllPhoneCallsTextView.setHorizontallyScrolling(true);

        searchPhoneBillButton.setOnClickListener(view -> {
            String customerName = customerNameEditText.getText().toString();

            if(customerName.isEmpty()) {
                showErrorToast(this, "Customer Name cannot be empty");
                return;
            }

            List<PhoneCall> phoneCallsForCustomer;
            try {
                phoneCallsForCustomer = viewModal.getPhoneCallsForCustomer(customerName);
                String dump = PrettyPrinter.dump(phoneCallsForCustomer);
                showAllPhoneCallsTextView.setText(dump);
            } catch (Exception e) {
                showErrorToast(this, e.getMessage());
            }
        });
    }
}