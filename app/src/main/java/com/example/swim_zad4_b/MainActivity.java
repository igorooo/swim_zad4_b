package com.example.swim_zad4_b;

import android.app.KeyguardManager;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.location.LocationManager;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvAccel, tvBaro, tvFinger, tvGyro, tvGeo, tvHall, tvHR, tvProx, tvLight, tvGPS;
    Button AccelB, BaroB, FingerB, GyroB, GeoB, HallB, HRB,ProxB, LightB, GPSB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAccel = (TextView) findViewById(R.id.tvAccel);
        tvBaro = (TextView) findViewById(R.id.tvBarometer);
        tvFinger = (TextView) findViewById(R.id.tvFingerprint);
        tvGyro = (TextView) findViewById(R.id.tvGyro);
        tvGeo = (TextView) findViewById(R.id.tvGeo);
        tvHall = (TextView) findViewById(R.id.tvHall);
        tvHR = (TextView) findViewById(R.id.tvHR);
        tvProx = (TextView) findViewById(R.id.tvProximity);
        tvLight = (TextView) findViewById(R.id.tvLight);
        tvGPS = (TextView) findViewById(R.id.tvGPS);

        AccelB = (Button) findViewById(R.id.AccelButton);
        BaroB = (Button) findViewById(R.id.BaroButton);
        FingerB = (Button) findViewById(R.id.FingerButton);
        GyroB = (Button) findViewById(R.id.GyroButton);
        GeoB = (Button) findViewById(R.id.GeoButton);
        HallB = (Button) findViewById(R.id.HallButton);
        HRB = (Button) findViewById(R.id.HRButton);
        ProxB = (Button) findViewById(R.id.ProximityButton);
        LightB = (Button) findViewById(R.id.LightButton);
        GPSB = (Button) findViewById(R.id.GpsButton);


        AccelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        BaroB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        FingerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        GyroB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        GeoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        HallB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        HRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        ProxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        LightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });

        GPSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SetEnableInfo();



    }



    private void SetEnableInfo(){

        final SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        final FingerprintManager fpm = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        final KeyguardManager kgm = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);


        boolean enb1,enb2;
        boolean enabled = !sm.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty();
        tvAccel.setText(getString(R.string.accelStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvAccel.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.AccelButton).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_PRESSURE).isEmpty();
        tvBaro.setText(getString(R.string.baroStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvBaro.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.BaroButton).setEnabled(enabled);

        enabled = fpm.isHardwareDetected();     //FingerprintManagerCompat.from(this).hasEnrolledFingerprints(); //FingerPrint here
        enb1 = fpm.hasEnrolledFingerprints();
        enb2 = kgm.isKeyguardSecure();
        tvFinger.setText(getString(R.string.fingerStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvFinger.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.FingerButton).setEnabled(enabled);
        if(!enb1){
            tvFinger.setText(getString(R.string.fingerStatus) + " brak zaresjtrowanych odciskow");
            tvFinger.setTextColor(Color.YELLOW);
            FingerB.setEnabled(false);
        }
        if(!enb2){
            tvFinger.setText(getString(R.string.fingerStatus) + " Keyguard secure not enabled");
            tvFinger.setTextColor(Color.YELLOW);
            FingerB.setEnabled(false);
        }



        enabled = !sm.getSensorList(Sensor.TYPE_GYROSCOPE).isEmpty();
        tvGyro.setText(getString(R.string.gyroStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvGyro.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.LightButton).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR).isEmpty();
        tvGeo.setText(getString(R.string.lightStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvGeo.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.GeoButton).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).isEmpty();
        tvHall.setText(getString(R.string.hallStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvHall.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.HallButton).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_HEART_RATE).isEmpty();
        tvHR.setText(getString(R.string.hrStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvHR.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.HRButton).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_PROXIMITY).isEmpty();
        tvProx.setText(getString(R.string.proxStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvProx.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.ProximityButton).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_LIGHT).isEmpty();
        tvLight.setText(getString(R.string.lightStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvLight.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.LightButton).setEnabled(enabled);

        final LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        tvGPS.setText(getString(R.string.gpsStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tvGPS.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.GpsButton).setEnabled(enabled);

    }

    public final void startAktywnosci(final View v){
        Intent in;

        in = new Intent();
        if(v.getId() == R.id.GpsButton){
            in = new Intent(this,GPS.class);
        }

        else if(v.getId() == R.id.FingerButton){
            in = new Intent(this, FingerPrint.class);
        }

        else {
            in = new Intent(this, ASensor.class);

            if(v.getId() == R.id.LightButton){
                in.putExtra("sensorType",Sensor.TYPE_LIGHT);
            }

            else if(v.getId() == R.id.AccelButton){
                in.putExtra("sensorType",Sensor.TYPE_ACCELEROMETER);
            }

            else if(v.getId() == R.id.GyroButton){
                in.putExtra("sensorType",Sensor.TYPE_GYROSCOPE);
            }

            else if(v.getId() == R.id.GeoButton){
                in.putExtra("sensorType",Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
            }

            else if(v.getId() == R.id.HallButton){
                in.putExtra("sensorType",Sensor.TYPE_MAGNETIC_FIELD);
            }

            else if(v.getId() == R.id.HRButton){
                in.putExtra("sensorType",Sensor.TYPE_HEART_RATE);
            }

            else if(v.getId() == R.id.ProximityButton){
                in.putExtra("sensorType",Sensor.TYPE_PROXIMITY);
            }
        }

        startActivity(in);
    }
}
