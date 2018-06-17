package com.example.wieslaw.herenew;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MapFragmentView implements PositioningManager.OnPositionChangedListener {

    private com.here.android.mpa.mapping.MapFragment MapFragment;
    public static Map map;
    public static PositioningManager PosManager;
    public static Activity activity;
    public static boolean Initialized = false;
    public static int Quest_id = 1;
    public static MapMarker marker;
    public MapFragmentView(final Activity act) {
        activity = act;
        MapFragment = (com.here.android.mpa.mapping.MapFragment) act.getFragmentManager()
                .findFragmentById(R.id.mapfragment);

        MapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(
                    OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    map = MapFragment.getMap();
                    PosManager = PositioningManager.getInstance();

                    PosManager.addListener(new WeakReference<PositioningManager.OnPositionChangedListener>(
                            MapFragmentView.this));

                    if (PosManager.start(PositioningManager.LocationMethod.GPS_NETWORK)) {

                        SetPlayerIndicator();
                    } else {
                        Toast.makeText(act, "PositioningManager.start: failed, exiting", Toast.LENGTH_LONG).show();

                    }
                    map.setZoomLevel(17);

                    Initialized = true;


                } else {
                    System.out.println("ERROR: Cannot initialize MapFragment");
                    Initialized = false;
                }
            }
        });
        MapFragment.getMapGesture().addOnGestureListener(new GestureListenerMap(activity));
        setFirstQuest();
    }


    @Override
    public void onPositionUpdated(PositioningManager.LocationMethod locationMethod, GeoPosition geoPosition, boolean b) {
        //MapFragment.getMap().setCenter(geoPosition.getCoordinate(), Map.Animation.LINEAR);

    }


    @Override
    public void onPositionFixChanged(PositioningManager.LocationMethod locationMethod, PositioningManager.LocationStatus locationStatus) {

    }


    private void ConfMapGesures() {
  /* MapFragment.getMapGesture().setPanningEnabled(false);
   MapFragment.getMapGesture().setDoubleTapEnabled(false);
   MapFragment.getMapGesture().setTwoFingerPanningEnabled(false);
   MapFragment.getMapGesture().setTwoFingerTapEnabled(false);
   MapFragment.getMapGesture().setPinchEnabled(false);*/
    }

    private void SetPlayerIndicator() {
        Resources res = activity.getResources();
        Drawable drawable = res.getDrawable(R.mipmap.player);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        Image i = new Image();

        i.setBitmap(bitmap);
        ConfMapGesures();
        MapFragment.getMap().getPositionIndicator().setMarker(i);
        MapFragment.getMap().getPositionIndicator().setVisible(true);
    }

    private void setFirstQuest(){
        Image marker_img = new Image();
            try {
                marker_img.setImageResource(R.drawable.quest);
                marker = new MapMarker(new GeoCoordinate(52.5446846, 19.6843503), marker_img);
                map.addMapObject(marker);

            }catch (Exception ex){
                ex.printStackTrace();
            }

    }
    public static void setFinishQuestFirst(){
        map.removeMapObject(marker);
        Image marker_img = new Image();
        try {
            marker_img.setImageResource(R.drawable.quest);
            marker = new MapMarker(new GeoCoordinate(52.6446846, 19.6843503), marker_img);
            map.addMapObject(marker);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}

