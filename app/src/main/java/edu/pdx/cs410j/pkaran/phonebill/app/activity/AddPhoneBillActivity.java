package edu.pdx.cs410j.pkaran.phonebill.app.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
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
import edu.pdx.cs410j.pkaran.phonebill.app.viewmodels.AddPhoneBillViewModal;

public class AddPhoneBillActivity extends AppCompatActivity {

    private AddPhoneBillViewModal viewModal;

    public synchronized AddPhoneBillViewModal getAddPhoneBillViewModal() {
        if (viewModal == null) {
            ViewModelProvider provider = new ViewModelProvider(this);
            viewModal = provider.get(AddPhoneBillViewModal.class);
            viewModal.instantiateRepo(this.getApplication());
        }
        return viewModal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_bill);

        viewModal = getAddPhoneBillViewModal();

        Button createPhoneBillButton = findViewById(R.id.create_phone_bill_button);
        EditText customerNameEditText = findViewById(R.id.customer_name_input);

        createPhoneBillButton.setOnClickListener(view -> {
            String customerName = customerNameEditText.getText().toString();

            if(customerName.isEmpty()) {
                Toast.makeText(this, "Please enter name of the customer", Toast.LENGTH_SHORT).show();
                return;
            }

            try{
                viewModal.addPhoneBill(customerName);
            } catch (Exception exception) {
                if(exception.getCause() != null && exception.getCause() instanceof SQLiteConstraintException) {
                    Toast.makeText(this, "Phone bill for this customer already exist", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    throw new RuntimeException(exception);
                }
            }

            Toast.makeText(this, String.format("Phone bill created for customer '%s'", customerName), Toast.LENGTH_SHORT).show();
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