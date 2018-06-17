package com.example.wieslaw.herenew;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.widget.Toast;

import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;

import java.util.List;

public class GestureListenerMap implements MapGesture.OnGestureListener {
    private Activity act;
    public GestureListenerMap(Activity activity){
        act = activity;
    }

    @Override
    public void onPanStart() {

    }

    @Override
    public void onPanEnd() {

    }

    @Override
    public void onMultiFingerManipulationStart() {

    }

    @Override
    public void onMultiFingerManipulationEnd() {

    }

    @Override
    public boolean onMapObjectsSelected(List<ViewObject> list) {
        for (ViewObject viewObject : list) {
            if (viewObject.getBaseType() == ViewObject.Type.USER_OBJECT) {
                MapObject mapObject = (MapObject) viewObject;

                if (mapObject.getType() == MapObject.Type.MARKER) {
                    if(MapFragmentView.Quest_id==1){
                        TextQuest.tx = "quest nr 1";
                        Intent i = new Intent(act,TextQuest.class);
                        act.startActivity(i);
                    }


                }
            }

        }
        return false;
    }
    @Override
    public boolean onTapEvent(PointF pointF) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(PointF pointF) {
        return false;
    }

    @Override
    public void onPinchLocked() {

    }

    @Override
    public boolean onPinchZoomEvent(float v, PointF pointF) {
        return false;
    }

    @Override
    public void onRotateLocked() {

    }

    @Override
    public boolean onRotateEvent(float v) {
        return false;
    }

    @Override
    public boolean onTiltEvent(float v) {
        return false;
    }

    @Override
    public boolean onLongPressEvent(PointF pointF) {
        return false;
    }

    @Override
    public void onLongPressRelease() {

    }

    @Override
    public boolean onTwoFingerTapEvent(PointF pointF) {
        return false;
    }
}
