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


// This example shows how to use acceleration and gyroscope sensors.


public class MainActivity extends Activity implements SensorEventListener {

    private TextView mTextView, X, Y, Z, orientationText, gyrox, gyroy, gyroz;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer, senGyroscope;
    boolean youmaypass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Define sensor types
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senGyroscope = senSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this, senGyroscope, senSensorManager.SENSOR_DELAY_NORMAL);



        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                X = (TextView) stub.findViewById(R.id.textViewX);
                Y = (TextView) stub.findViewById(R.id.textViewY);
                Z = (TextView) stub.findViewById(R.id.textViewZ);
                gyrox = (TextView) stub.findViewById(R.id.textViewGyroX);
                gyroy = (TextView) stub.findViewById(R.id.textViewGyroY);
                gyroz = (TextView) stub.findViewById(R.id.textViewGyroZ);

                orientationText = (TextView) stub.findViewById(R.id.textView4);
                youmaypass = true;
            }
        });


    }


    // The program goes here everytime the sensor values change
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Acceleration sensor measures the acceleration applied to the device, including the force of gravity. The data is in m/s^2.
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

        // Gyroscope shows the device's rotation speed around its XYZ axis, data is in rad/s.
        else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE && youmaypass == true) {
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            gyrox.setText(String.format("%.5f", axisX));
            gyroy.setText(String.format("%.5f", axisY));
            gyroz.setText(String.format("%.5f", axisZ));
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
