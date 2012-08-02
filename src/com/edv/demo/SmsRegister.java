package com.edv.demo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//http://mobiforge.com/developing/story/sms-messaging-android
public class SmsRegister extends Activity {
	Button btnSendSMS;
    EditText txtPhoneNo;
    EditText txtMessage;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.sms_reg);        
    	 
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
 
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {                
                String phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage.getText().toString();                 
                if (phoneNo.length()>0 && message.length()>0)                
                    sendSMS(phoneNo, message);                
                else
                    Toast.makeText(getBaseContext(), 
                        "Please enter both phone number and message.", 
                        Toast.LENGTH_SHORT).show();
            }
        });        
    }
   //---sends an SMS message to another device---
     private void sendSMS(String phoneNumber, String message)
     {        
         String SENT = "SMS_SENT";
         String DELIVERED = "SMS_DELIVERED";
  
         PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
             new Intent(SENT), 0);
  
         PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
             new Intent(DELIVERED), 0);
  
         //---when the SMS has been sent---
         registerReceiver(new BroadcastReceiver(){
             @Override
             public void onReceive(Context arg0, Intent arg1) {
                 switch (getResultCode())
                 {
                     case Activity.RESULT_OK:
                         Toast.makeText(getBaseContext(), "SMS sent", 
                                 Toast.LENGTH_SHORT).show();
                         break;
                     case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                         Toast.makeText(getBaseContext(), "Generic failure", 
                                 Toast.LENGTH_SHORT).show();
                         break;
                     case SmsManager.RESULT_ERROR_NO_SERVICE:
                         Toast.makeText(getBaseContext(), "No service", 
                                 Toast.LENGTH_SHORT).show();
                         break;
                     case SmsManager.RESULT_ERROR_NULL_PDU:
                         Toast.makeText(getBaseContext(), "Null PDU", 
                                 Toast.LENGTH_SHORT).show();
                         break;
                     case SmsManager.RESULT_ERROR_RADIO_OFF:
                         Toast.makeText(getBaseContext(), "Radio off", 
                                 Toast.LENGTH_SHORT).show();
                         break;
                 }
             }
         }, new IntentFilter(SENT));
  
         //---when the SMS has been delivered---
         registerReceiver(new BroadcastReceiver(){
             @Override
             public void onReceive(Context arg0, Intent arg1) {
                 switch (getResultCode())
                 {
                     case Activity.RESULT_OK:
                         Toast.makeText(getBaseContext(), "SMS delivered", 
                                 Toast.LENGTH_SHORT).show();
                         break;
                     case Activity.RESULT_CANCELED:
                         Toast.makeText(getBaseContext(), "SMS not delivered", 
                                 Toast.LENGTH_SHORT).show();
                         break;                        
                 }
             }
         }, new IntentFilter(DELIVERED));        
  
         SmsManager sms = SmsManager.getDefault();
         sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);   
         
     
     }
    class SMSReciver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();

			Object messages[] = (Object[]) bundle.get("pdus");
			SmsMessage smsMessage[] = new SmsMessage[messages.length];
			for (int n = 0; n  < messages.length; n++) {
			smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
			}

			// show first message
			Toast toast = Toast.makeText(context,
			"Received SMS: " + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
			toast.show();
			String  returnstr = "Thx joining the Edvantage Mobile community. To see where you can save in your area click: http://www.link.com/m";
			if (smsMessage[0].getMessageBody().endsWith(returnstr))
		      {
		        // Stop it being passed to the main Messaging inbox
		        abortBroadcast();
		      }
			
			
			 //---get the SMS message passed in---    
	        SmsMessage[] msgs = null;
	        String str = "";            
	        if (bundle != null)
	        {
	            //---retrieve the SMS message received---
	            Object[] pdus = (Object[]) bundle.get("pdus");
	            msgs = new SmsMessage[pdus.length];            
	            for (int i=0; i<msgs.length; i++){
	                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
	                str += "SMS from " + msgs[i].getOriginatingAddress();                     
	                str += " :";
	                str += msgs[i].getMessageBody().toString();
	                str += "\n";        
	            }
	            //---display the new SMS message---
	            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	        }                
		}
    	
    }
}
