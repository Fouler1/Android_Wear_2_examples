package ims.fhj.at.exercise_1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView mTextView;
    TextView Speechformer;
    final static int SPEECH_REQUEST_CODE = 1;
    final static int MY_PERMISSIONS_REQUEST_BODY_SENSORS = 2;
    String storedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                Speechformer = (TextView) stub.findViewById(R.id.tv_heart_rate);
            }
        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.BODY_SENSORS)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BODY_SENSORS},
                        MY_PERMISSIONS_REQUEST_BODY_SENSORS);
            }
        }
    }


    public void Clicked(View v)
    {
        displaySpeechRecognizer();
    }

    private void displaySpeechRecognizer() {
        Log.d("DisplaySpeech","alotus");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        Log.d("DisplaySpeech","Recognizer");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d("Activityresult","Confirmed");
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            if (spokenText.contains("play")) {
                GetHeartRate();
                //Speechformer.setText(storedValue);
            }
            else
            {
                Speechformer.setText("Wrong Password");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void GetHeartRate()
    {


        SensorManager mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            String msg = "" + (int)sensorEvent.values[0];
            storedValue = msg;
            Speechformer.setText(storedValue);
    }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
