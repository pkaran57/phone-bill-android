package edu.pdx.cs410j.pkaran.phonebill.app.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void showErrorToast(Context context, String message) {
        Toast.makeText(context, "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}
