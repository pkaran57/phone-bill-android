package edu.pdx.cs410j.pkaran.phonebill.app.utils;

import android.app.AlertDialog;
import android.content.Context;

public class DialogueUtils {

    public static void showErrorDialogue(Context context, String message) {

        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        //Toast.makeText(context, "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}
