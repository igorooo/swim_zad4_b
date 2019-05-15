package com.example.swim_zad4_b;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Seismograph extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSrgMgr = null;
    GraphView graph;
    TextView tv;
    ImageView img;
    long time, imgChange, delay = 200;
    LineGraphSeries<DataPoint> series;

    int maxDataPoints = 50;

    double alfa, x, y, prev_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seismograph);
        alfa = 1;

        mSrgMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        time = SystemClock.currentThreadTimeMillis();
        imgChange = 0;

        DataPoint[] startpoints = new DataPoint[maxDataPoints];

        for(int i = 0; i < maxDataPoints; i ++){
            startpoints[i] = new DataPoint(i,0);
        }




        //tv = (TextView) findViewById(R.id.tvSeis);
        graph = (GraphView) findViewById(R.id.graph);
        img = (ImageView) findViewById(R.id.imgSeis);

        series = new LineGraphSeries<DataPoint>(startpoints);
        graph.addSeries(series);
        x = maxDataPoints;

        //tv.setText(Integer.toString((int)time));

        prev_y = 0;


        graph.getViewport().setMinY(-40.0);
        graph.getViewport().setMaxY(40.0);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        //graph.getGridLabelRenderer().setVerticalLabelsVisible(false);





    }

    @Override
    protected void onResume() {
        super.onResume();

        final Sensor sens = mSrgMgr.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        mSrgMgr.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSrgMgr.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {




        // DISABLED tv.setText(Integer.toString((int)time));

        if(SystemClock.currentThreadTimeMillis() - 5  > time) {

            y = alfa*(Math.abs(event.values[0]) + Math.abs(event.values[1]) + Math.abs(event.values[2]) - 10);
            alfa *= -1;

            series.appendData(new DataPoint(x, y ),false,50, true);
            x++;
            //graph.removeAllSeries();
            graph.addSeries(series);
            time = SystemClock.currentThreadTimeMillis();

            if(Math.abs(y) > 5){
                if(Math.abs(y) > 10){
                    img.setImageResource(R.drawable.ic_danger);
                }

                else{
                    img.setImageResource(R.drawable.ic_warning);
                }

                imgChange = SystemClock.currentThreadTimeMillis();

            }

            if( Math.abs(y) < 5 && SystemClock.currentThreadTimeMillis() - delay > imgChange){
                img.setImageResource(R.drawable.ic_ok);
            }



        }



        prev_y = y;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updatePoints(double Y){




    }
}
