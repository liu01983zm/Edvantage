package com.edv.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SecTitleView extends LinearLayout implements OnClickListener{
    MenuDataItem  data;
	public SecTitleView(Context context, MenuDataItem  data) {
		super(context);
		this.data = data;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}

class MenuDataItem{
	int type = 0;
}