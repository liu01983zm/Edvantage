package com.edv.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener{
	EditText   searchInput;
	ImageButton searchBtn;
	Button btn_partners;
	Button btn_special;
	Button btn_news;
	Button btn_contact;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);
    	searchInput = (EditText) this.findViewById(R.id.search_input);
    	searchBtn = (ImageButton) this.findViewById(R.id.search_btn);
    	searchBtn.setOnClickListener(this);
    	
    	btn_partners  = (Button) this.findViewById(R.id.main_btn_partners);
    	btn_partners.setOnClickListener(this);
    	btn_special  = (Button) this.findViewById(R.id.main_btn_special);
    	btn_special.setOnClickListener(this);
    	btn_news  = (Button) this.findViewById(R.id.main_btn_news);
    	btn_news.setOnClickListener(this);
    	btn_contact  = (Button) this.findViewById(R.id.main_btn_contact);
    	btn_contact.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.search_btn:
			break;
		case R.id.main_btn_partners:
			intent.setClass(MainActivity.this, PartnersActivity.class);
			startActivity(intent);
			break;
		case R.id.main_btn_special:
			break;
		case R.id.main_btn_news:
			break;
		case R.id.main_btn_contact:
			break;
		}
		
	}
}
