package edu.pdx.cs410j.pkaran.phonebill.activity;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.pdx.cs410j.pkaran.phonebill.R;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_PHONE_BILL_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addPhoneBillButton = findViewById(R.id.add_phone_bill_button);
        Button addPhoneCallButton = findViewById(R.id.add_phone_call_button);

        addPhoneBillButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddPhoneBillActivity.class);
            startActivityForResult(intent, ADD_PHONE_BILL_CODE);
        });
    }
}