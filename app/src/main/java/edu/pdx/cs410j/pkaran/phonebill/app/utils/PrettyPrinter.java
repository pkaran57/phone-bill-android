package edu.pdx.cs410j.pkaran.phonebill.app.utils;

import edu.pdx.cs410j.pkaran.phonebill.app.db.phonecall.PhoneCall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrettyPrinter {

    public static final String FORMAT_STRING = "%-20s%-20s%-23s%-23s%-15s" + System.lineSeparator();

    public static String dump(List<PhoneCall> phoneCallsList) {
        StringBuffer stringBuffer = new StringBuffer();

        if (phoneCallsList.isEmpty()) {
            stringBuffer.append("No phone calls were found for the phone bill.");
        } else {
            stringBuffer.append("Following are the phone calls in the phone bill:" + System.lineSeparator() + System.lineSeparator());
            stringBuffer.append(getFormattedHeader());

            phoneCallsList = new ArrayList<>(phoneCallsList);
            Collections.sort(phoneCallsList);

            phoneCallsList.forEach(phoneCall ->
                    stringBuffer.append(String.format(FORMAT_STRING,
                            phoneCall.getCaller(), phoneCall.getCallee(), phoneCall.getStartTimeString(), phoneCall.getEndTimeString(), phoneCall.getDuration().getSeconds())));
        }

        return stringBuffer.toString();
    }

    private static String getFormattedHeader() {
        return String.format(FORMAT_STRING, "Caller", "Callee", "Start Time", "End Time", "Duration (in seconds)");
    }
}
