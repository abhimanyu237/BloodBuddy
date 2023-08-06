package com.example.bloodbuddy;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FetchData extends AsyncTask<Object,String,String> {

    String googleNearByPlacesData;
    GoogleMap googleMap;
    String url;

    /**
     * @param s
     * @deprecated
     */
    @Override
    protected void onPostExecute(String s) {
        try{
            JSONObject jsonObject=new JSONObject(s);
            JSONArray jsonArray=jsonObject.getJSONArray("results");


            for(int i=0;i<jsonArray.length();++i){
                JSONObject jsonObject1= jsonArray.getJSONObject(i);
                JSONObject getLocation=jsonObject1.getJSONObject("geometry").getJSONObject("location");

                String lat = getLocation.getString("lat");
                String lng = getLocation.getString("lng");

                JSONObject getName= jsonArray.getJSONObject(i);
                String name = getName.getString("name");

                LatLng latlng= new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(name);
                markerOptions.position(latlng);
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param objects
     * @deprecated
     */

    @Override
    protected String doInBackground(@NonNull Object... objects) {

        try{

            googleMap=(GoogleMap) objects[0];
            String nearbyPlaceUrl=(String) objects[1];
            DownloadURL downloadURL=new DownloadURL();
            googleNearByPlacesData=downloadURL.retireveUrl(nearbyPlaceUrl);
        } catch (IOException e){
            e.printStackTrace();
        }

        return googleNearByPlacesData;
    }
}
