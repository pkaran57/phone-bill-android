package edu.pdx.cs410j.pkaran.phonebill.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import edu.pdx.cs410j.pkaran.phonebill.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addPhoneBillButton = findViewById(R.id.add_phone_bill_button);
        Button addPhoneCallButton = findViewById(R.id.add_phone_call_button);
        Button viewPhoneBillButton = findViewById(R.id.view_phone_bill_button);
        Button searchPhoneCallsButton = findViewById(R.id.search_phone_calls_button);

        addPhoneBillButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddPhoneBillActivity.class);
            startActivity(intent);
        });

        addPhoneCallButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddPhoneCallActivity.class);
            startActivity(intent);
        });

        viewPhoneBillButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ShowAllPhoneCallsActivity.class);
            startActivity(intent);
        });

        searchPhoneCallsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchPhoneCallsActivity.class);
            startActivity(intent);
        });
    }
}