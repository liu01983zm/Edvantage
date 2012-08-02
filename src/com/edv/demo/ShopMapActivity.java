package com.edv.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.EditText;
//http://topic.csdn.net/u/20100302/15/b25366e0-b928-4026-9532-2285d0399985.html
public class ShopMapActivity extends MapActivity{
	MapView mapView;
	private MapController mapController;
	private Geocoder geoCoder;
    private GeoPoint geoPoint;
    GeoItemizedOverlay mapOverlay;
    List<Overlay> listOfOverlays;
   @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
        geoCoder = new Geocoder(this, Locale.getDefault());
        
		 mapController = mapView.getController();
	     
		 listOfOverlays = mapView.getOverlays();

		 Drawable drawable = this.getResources().getDrawable(R.drawable.pinsmall);
	        //加入地圖標籤
	     mapOverlay = new GeoItemizedOverlay(drawable);

	     
	     GeoPoint point1 = new GeoPoint(19240000, -99120000);
	     GeoPoint point2 = new GeoPoint(35410000, 139460000);
	     // 设置初始地图的中心位置
	     GeoPoint geoBeijing=new GeoPoint((int)(39.95*1000000), (int)(116.37*1000000));
	         
	     OverlayItem bjItem = new OverlayItem(geoBeijing, "JQQ", "");//Bei jing Welcome you !!

	     OverlayItem overlayitem1 = new OverlayItem(point1, "DHZ", "");//what are you doing now ?
	     overlayitem1.setMarker(drawable);
	     OverlayItem overlayitem2 = new OverlayItem(point2, "SRH", "");//where are you from ?
	     overlayitem1.setMarker(drawable);

	     mapOverlay.addOverlay(overlayitem1);
	     mapOverlay.addOverlay(overlayitem2);
	     mapOverlay.addOverlay(bjItem);
	     
	     listOfOverlays.clear();
	     listOfOverlays.add(mapOverlay);
	     mapController.animateTo(geoBeijing);
	     mapController.setCenter(geoBeijing);
	     mapController.setZoom(9);
	}
   public class GeoItemizedOverlay  extends ItemizedOverlay<OverlayItem> {
	   private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	   
	   public GeoItemizedOverlay(Drawable defaultMarker) {
		  // super( defaultMarker);//boundCenterBottom(defaultMarker)
		   super( boundCenterBottom(defaultMarker));
		}
	   @Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return mOverlays.get(i);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return mOverlays.size();
		}
		public void addOverlay(OverlayItem overlay) {
			mOverlays.add(overlay);
			populate();
		}
       @Override
       public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
           super.draw(canvas, mapView, shadow);
          // Bitmap bmp = BitmapFactory.decodeResource( getResources(), R.drawable.pinsmall); 
           Projection projection = mapView.getProjection();
           for (int index = size() - 1; index >= 0; index--) {
        	   OverlayItem overLayItem = getItem(index);
        	   /* 题目 */
        	   String title = overLayItem.getTitle();
        	   /* 简介 */
        	   String snippet = overLayItem.getSnippet();
        	   /* 象素点取得转换    //—translate the GeoPoint to screen pixels—*/
        	   Point point = projection.toPixels(overLayItem.getPoint(), null);

        	   /* 目标城市圈出来 */
        	   Paint paintCircle = new Paint();
        	   paintCircle.setColor(Color.YELLOW);
        	   
        	   canvas.drawCircle(point.x, point.y, 5, paintCircle);

        	   /* 文字设置 */
        	   Paint paintText = new Paint();
        	   paintText.setColor(Color.RED);
//        	   paintText.setAntiAlias(true);
//        	   paintText.setFakeBoldText(true);
//        	   paintText.setTextSize(25);
        	   paintText.setAntiAlias(true);
        	   paintText.setFakeBoldText(false);
        	   paintText.setTextSize(14);
        	 //Draw the specified bitmap, with its top/left corner at (x,y), using the specified paint, transformed by the current matrix.
        	   //canvas.drawBitmap(bmp, point.x, point.y, null);
        	   //Draw the text, with origin at (x,y), using the specified paint.
        	   canvas.drawText(title+snippet, point.x, point.y, paintText);
        	   }
           return true;
       }

	
   }
  
   private void searchOnMap(String search) {
	// ** brings up Google Maps with results, but unable to pass data back to my app - no go
//	            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + search.replace(" ", "+")));
//	            this.startActivityForResult(intent, MAP_ACTIVITY);

	// ** works with address only; NO BUSINESS search - no go
//	            Geocoder geoCoder = new Geocoder(this);
//	            try {
//	                        List<Address> results = geoCoder.getFromLocationName(search,MAX_SEARCH_RESULTS);
//	                        int a=results.size();
//	                } catch (IOException e) {
//	                        e.printStackTrace();
//	                }

	// ** works like a charm! The only unsure is the map key. I generated
	//for http://localhost; see how far we get
	                StringBuilder responseBuilder = new StringBuilder();
	                try {
	                URL url = new URL("http://ajax.googleapis.com/ajax/services/search/local?v=1.0&q="
	                                 + URLEncoder.encode(search, "UTF-8")
	                                 +
	"&key=ABQIAAAArsnSCSicZxq21ogV4Uu_JxT2yXp_ZAY8_ufC3CFXhHIE1NvwkxQ3l8j5e79VEU4_ht1ROH0YrZtWuw");

	                BufferedReader in = new BufferedReader(new InputStreamReader
	(url.openStream()));
	            String inputLine;
	                        while ((inputLine = in.readLine()) != null) {
	                            responseBuilder.append(inputLine);
	                        }
	                in.close();
	                } catch (MalformedURLException me) {
	                        me.printStackTrace();
	                } catch (UnsupportedEncodingException ue) {
	                        ue.printStackTrace();
	                } catch (IOException ie) {
	                        ie.printStackTrace();
	                }

	                try {
	                        JSONObject json = new JSONObject(responseBuilder.toString());
	                        int a = json.length();
	                } catch (JSONException e) {
	                        e.printStackTrace();
	                }
	    }
@Override
protected boolean isRouteDisplayed() {
	// TODO Auto-generated method stub
	return false;
}
}
