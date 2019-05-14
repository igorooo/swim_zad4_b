package com.example.swim_zad4_b;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ASensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSrgMgr = null;
    private int mSensorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asensor);

        mSrgMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorType = getIntent().getIntExtra("sensorType", Sensor.TYPE_LIGHT);

        if(mSensorType == Sensor.TYPE_LIGHT){
            setTitle(R.string.lightStatus);
        }

        else if(mSensorType == Sensor.TYPE_ACCELEROMETER){
            setTitle(R.string.accelStatus);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Sensor sens = mSrgMgr.getSensorList(mSensorType).get(0);
        mSrgMgr.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSrgMgr.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        TextView tv = (TextView) findViewById(R.id.txt_data);
        StringBuilder sb = new StringBuilder();

        if(mSensorType == Sensor.TYPE_LIGHT){

            sb.append("Ambient light level: ");
            sb.append(event.values[0]);
            sb.append(" lux");
        }


        else if(mSensorType == Sensor.TYPE_ACCELEROMETER){

            sb.append("X acceleration: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" m/s\u00B2\nY acceleration: ");
            sb.append(String.format("%7.4f", event.values[1]));
            sb.append(" m/s\u00B2\nZ acceleration: ");
            sb.append(String.format("%7.4f", event.values[2]));
            sb.append(" m/s\u00B2");
        }

        else if(mSensorType == Sensor.TYPE_PRESSURE){

            sb.append("Pressure: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" hPa");
        }

        else if(mSensorType == Sensor.TYPE_GYROSCOPE){

            sb.append("Angular speed around the X: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" rad/s\nAngular speed around the Y: ");
            sb.append(String.format("%7.4f", event.values[1]));
            sb.append(" rad/s\nAngular speed around the Z: ");
            sb.append(String.format("%7.4f", event.values[2]));
            sb.append(" rad/s");
        }


        else if(mSensorType == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR){

            sb.append("Rotation vector component along the x axis (x * sin(θ/2)): ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append("\nRotation vector component along the y axis (y * sin(θ/2)): ");
            sb.append(String.format("%7.4f", event.values[1]));
            sb.append("\nRotation vector component along the z axis (z * sin(θ/2)): ");
            sb.append(String.format("%7.4f", event.values[2]));

        }


        else if(mSensorType == Sensor.TYPE_MAGNETIC_FIELD){

            sb.append("Geomagnetic field strength along the X ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" \u00B5T\nGeomagnetic field strength along the Y ");
            sb.append(String.format("%7.4f", event.values[1]));
            sb.append(" \u00B5T\nGeomagnetic field strength along the Z ");
            sb.append(String.format("%7.4f", event.values[2]));
            sb.append(" \u00B5T");
        }

        else if(mSensorType == Sensor.TYPE_HEART_RATE){

            sb.append("Heart rate: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" beates per minute");
        }

        else if(mSensorType == Sensor.TYPE_PROXIMITY){

            sb.append("Proximity: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" cm");
        }



        tv.setText(sb);

        tv = (TextView) findViewById(R.id.txt_status);

        StringBuilder sb2 = new StringBuilder();

        sb2.append("\nAccuracy: ");
        sb2.append(event.accuracy == 3 ? "High" : event.accuracy == 2 ? "Medium" : "Low");
        tv.setText(sb2);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
