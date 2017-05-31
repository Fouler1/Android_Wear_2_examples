package a.complicationexample;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.wearable.complications.ProviderUpdateRequester;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private EditText numberfield;
    private Button sendbutton;
    static String text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);
        numberfield = (EditText) findViewById(R.id.numberfield);
        sendbutton = (Button) findViewById(R.id.sendbutton);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                numberfield = (EditText) stub.findViewById(R.id.numberfield);
                sendbutton = (Button) stub.findViewById(R.id.sendbutton);

                sendbutton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        text = numberfield.getText().toString();
                        numberfield.setText("");
                        startUpdate();
                    }
                });
            }
        });
    }

    public String getMessage() {
        return text;
    }

    public void startUpdate() {
        ComponentName componentName =
                new ComponentName(getApplicationContext(), CustomComplicationProviderService.class);

        ProviderUpdateRequester providerUpdateRequester =
                new ProviderUpdateRequester(getApplicationContext(), componentName);

        providerUpdateRequester.requestUpdateAll();
    }
}
