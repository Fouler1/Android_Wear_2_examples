package ims.fhj.at.testspeech;

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

    public EditText Speechformer;
    private TextView mTextView;
    private static final int SPEECH_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                Speechformer = (EditText) stub.findViewById(R.id.eText);
            }
        });
    }

    public void Clicked(View v)
    {
        displaySpeechRecognizer();
    }

    // Creates intent that will start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Log.d("DisplaySpeech","alotus");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        Log.d("DisplaySpeech","Recognizer");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

// This is where you process the intent and extract the speech text from the intent to a spokenText variable.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d("Activityresult","Confirmed");
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            //spokenText is now put in to a TextView where u can see what you've spoken
            Speechformer.setText(spokenText);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
