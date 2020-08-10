package com.example.dan.myperfitlife;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FirstFragment extends Fragment implements SensorEventListener {

    View myView;
    private SensorManager sensorManager;
    private TextView count;
    private TextView calorie;
    private TextView distance;
    boolean activityRunning;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        count = (TextView) myView.findViewById(R.id.count);
        calorie = (TextView) myView.findViewById(R.id.calorie);
        distance = (TextView) myView.findViewById(R.id.distance);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        return myView;
    }
    public void onResume () {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener((SensorEventListener) this, countSensor,SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(getActivity(), "Count sensor not available", Toast.LENGTH_LONG).show();
        }
    }


@Override
public void onPause () {
    super.onPause();
    activityRunning = false;
}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (activityRunning) {
            count.setText(String.valueOf(sensorEvent.values[0]));
            calorie.setText(String.valueOf(sensorEvent.values[0]/20));
            distance.setText(String.valueOf(sensorEvent.values[0]/2000));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
