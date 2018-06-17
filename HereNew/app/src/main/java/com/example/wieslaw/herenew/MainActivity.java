package com.example.wieslaw.herenew;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;

public class MainActivity extends AppCompatActivity {
    MapFragmentView m_mapFragmentView;
    public static Activity act;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Search for the Map Fragment
        act = this;
        final MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.mapfragment);
        // initialize the Map Fragment and
        // retrieve the map that is associated to the fragment
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(
                    OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    // now the map is ready to be used
                    Map map = mapFragment.getMap();
                    m_mapFragmentView = new MapFragmentView(act);
                } else {
                    System.out.println("ERROR: Cannot initialize MapFragment");
                }
            }
        });
    }
}
