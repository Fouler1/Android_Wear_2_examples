package a.complicationexample;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.wearable.complications.ProviderUpdateRequester;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private EditText numberfield;
    private Button sendbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberfield = (EditText) findViewById(R.id.numberfield);
        sendbutton = (Button) findViewById(R.id.sendbutton);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                numberfield = (EditText) stub.findViewById(R.id.numberfield);
                sendbutton = (Button) stub.findViewById(R.id.sendbutton);
            }
        });
    }

    public String getMessage() {
        //String text = numberfield.getText().toString();
        //numberfield.setText("");
        String text = "Hello";
        return text;
    }

    public void startUpdate(View view) {
        ComponentName componentName =
                new ComponentName(getApplicationContext(), CustomComplicationProviderService.class);

        ProviderUpdateRequester providerUpdateRequester =
                new ProviderUpdateRequester(getApplicationContext(), componentName);

// This method only updates the specific complication id, you could
// update all with providerUpdateRequester.requestUpdateAll().
        providerUpdateRequester.requestUpdateAll();
    }
}
