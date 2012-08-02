package com.edv.demo;

import java.io.IOException;
import java.util.List;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.EditText;

public class ShopMapActivity2 extends MapActivity {
	MapView mapView;
	private MapController mapController;
	private Geocoder geoCoder;
    private GeoPoint geoPoint;
    MapOverlay mapOverlay;
    List<Overlay> listOfOverlays;
    @Override
    protected void onCreate(Bundle icicle) {
    	// TODO Auto-generated method stub
    	super.onCreate(icicle);
    	setContentView(R.layout.shop_map);
		mapView = (MapView) this.findViewById(R.id.mapview);
		  //設定衛星圖
        mapView.setSatellite(false);
        //設定街道圖
        //mapView.setStreetView(true);//会造成显示很多叉
        mapView.displayZoomControls(true);
        mapView.setClickable(true);
        mapView.setEnabled(true);
        mapView.setBuiltInZoomControls(true);
        
		 mapController = mapView.getController();
	     
		 listOfOverlays = mapView.getOverlays();

		 Drawable drawable = this.getResources().getDrawable(R.drawable.pinsmall);
		 
		 GeoPoint geoBeijing=new GeoPoint((int)(39.95*1000000), (int)(116.37*1000000));
	        //加入地圖標籤
	     mapOverlay = new MapOverlay(this, geoBeijing);

	     
	     GeoPoint point1 = new GeoPoint(19240000, -99120000);
	     GeoPoint point2 = new GeoPoint(35410000, 139460000);
	     // 设置初始地图的中心位置
	     
	         
	     OverlayItem bjItem = new OverlayItem(geoBeijing, "JQQ", "");//Bei jing Welcome you !!

	     OverlayItem overlayitem1 = new OverlayItem(point1, "DHZ", "");//what are you doing now ?
	     overlayitem1.setMarker(drawable);
	     OverlayItem overlayitem2 = new OverlayItem(point2, "SRH", "");//where are you from ?
	     overlayitem1.setMarker(drawable);

//	     mapOverlay.addOverlay(overlayitem1);
//	     mapOverlay.addOverlay(overlayitem2);
//	     mapOverlay.addOverlay(bjItem);
	     
	     listOfOverlays.clear();
	     listOfOverlays.add(mapOverlay);
	     mapController.animateTo(geoBeijing);
	     mapController.setCenter(geoBeijing);
	     mapController.setZoom(9);
    }
    public void search(EditText searchInput) throws IOException{
 	   List<Address> addresses = geoCoder.getFromLocationName(searchInput.getText().toString(),5);

        if(addresses.size() > 0)
        {
     	   GeoPoint   p = new GeoPoint( (int) (addresses.get(0).getLatitude() * 1E6), 
                              (int) (addresses.get(0).getLongitude() * 1E6));

     	   mapController.animateTo(p);
     	   mapController.setZoom(12);

            MapOverlay mapOverlay = new MapOverlay(this,p);
            List<Overlay> listOfOverlays = mapView.getOverlays();
            listOfOverlays.clear();
            listOfOverlays.add(mapOverlay);

            mapView.invalidate();
            searchInput.setText("");
        }
        else
        {
                AlertDialog.Builder adb = new AlertDialog.Builder(ShopMapActivity2.this);
                adb.setTitle("Google Map");
                adb.setMessage("Please Provide the Proper Place");
                adb.setPositiveButton("Close",null);
                adb.show();
        }
    }
    class MapOverlay extends com.google.android.maps.Overlay
    {   Context context;
        GeoPoint p;
 	   public MapOverlay(Context context, GeoPoint p) {
 	      this.context = context;
 	      this.p = p;
        }
        public boolean draw(Canvas canvas, MapView mapView, 
        boolean shadow, long when) 
        {
            super.draw(canvas, mapView, shadow);                   

            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(p, screenPts);

            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(
                getResources(), R.drawable.pinsmall);            
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y-32, null);         
            return true;
        }
    } 
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
