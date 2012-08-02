package com.edv.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CMSShopping extends Activity {
   @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
   
   
   class  CMSRegister extends LinearLayout{
	public CMSRegister(Context context) {
		super(context);
		
	}
	   
   }
   class  CMSPay extends LinearLayout{
		public CMSPay(Context context) {
			super(context);
			
		}
		   
	   }
}
