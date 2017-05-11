package a.accelerationsensorexample;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView mTextView, X, Y, Z, orientationText;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    boolean youmaypass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Define sensor type
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                X = (TextView) stub.findViewById(R.id.textViewX);
                Y = (TextView) stub.findViewById(R.id.textViewY);
                Z = (TextView) stub.findViewById(R.id.textViewZ);
                orientationText = (TextView) stub.findViewById(R.id.textView4);
                youmaypass = true;
            }
        });


    }


    // The program goes here everytime the sensor values change
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && youmaypass==true) {
            float[] values = event.values;
            // Movement
            float x = values[0];
            float y = values[1];
            float z = values[2];

            // Show the raw acceleration data
            X.setText(String.format("%.5f", values[0]));
            Y.setText(String.format("%.5f", values[1]));
            Z.setText(String.format("%.5f", values[2]));

            // Show orientation
            if(x > 9 || x < -9)
                orientationText.setText("X-axis is vertically");

            else if(y > 9 || y < -9)
                orientationText.setText("Y-axis is vertically");

            else if(z > 9 || z < -9)
                orientationText.setText("Z-axis is vertically");

            else
                orientationText.setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
