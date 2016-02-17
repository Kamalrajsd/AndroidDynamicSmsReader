package com.example.kamalrajsd.tracksensetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    private final String LOG_TAG = "SMSBroadcastReceiver";
    public SMSBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(LOG_TAG, "onReceive - begins");
        if(Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction()))
        {
            for(SmsMessage objSmsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent))
            {
                Log.i(LOG_TAG, "reading SMS");
                String messageBody = objSmsMessage.getMessageBody();
                String from = objSmsMessage.getDisplayOriginatingAddress();
                Toast.makeText(context, messageBody, Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "read SMS " + messageBody);
            }
        }



        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
