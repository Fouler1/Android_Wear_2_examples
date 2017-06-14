package ims.fhj.at.exercise_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private TextView mTextView;
    TextView Speechformer;
    final static int SPEECH_REQUEST_CODE = 1;
    String compare = "aasi";

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
            if (spokenText == compare) {
                Speechformer.setText(spokenText);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }






}
