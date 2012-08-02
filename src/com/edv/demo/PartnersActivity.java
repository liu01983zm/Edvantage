package com.edv.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class PartnersActivity extends Activity {
   ListView navList ;
   List<DetailItem> navs = new ArrayList<DetailItem>();
   ListView detailList;
   List<DetailItem> details = new ArrayList<DetailItem>();
   @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partners);
		navList = (ListView) this.findViewById(R.id.nav_list);
		detailList = (ListView) this.findViewById(R.id.detail_list);
		for(int i=0;i<6;i++){
			navs.add(new DetailItem());
			if(i<4)details.add(new DetailItem());
		}
		navList.setAdapter(new PartnerAdapter(this,navs));
		
		detailList.setAdapter(new PartnerAdapter(this, details));
		detailList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent = new Intent();
				intent.setClass(PartnersActivity.this, ShopMapActivity3.class);
				startActivity(intent);
			}
		});
	}
   
 class DetailItem{
	 String url;
	 String id;
	 public DetailItem(){
		 
	 }
 }
 class PartnerAdapter extends BaseAdapter{
    Context context;
    List<DetailItem> items = new ArrayList<DetailItem>();
    PartnerAdapter(Context context,List<DetailItem> items){
    	this.context = context;
    	this.items = items;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView ==null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.partner_detail_item, null);
		}
		ImageView item = (ImageView) convertView.findViewById(R.id.item);
		item.setImageDrawable(context.getResources().getDrawable(R.drawable.logo2));
		return convertView;
	}
	 
 }
}
