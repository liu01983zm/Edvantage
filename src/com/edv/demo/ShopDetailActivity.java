package com.edv.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShopDetailActivity extends Activity {
	Button payBtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.shop_detail);
    	payBtn = (Button) this.findViewById(R.id.goods_pay);
    	payBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				  
			}
		});
    }
}
