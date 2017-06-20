package ims.fhj.at.testwristgestures;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView, gesturebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                gesturebox = (TextView) stub.findViewById(R.id.tv_Gesturebox);
            }
        });
    }

    @Override /* KeyEvent.Callback */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_NAVIGATE_NEXT:
                // Do something that advances a user View to the next item in an ordered list.
                gesturebox.setText("Navigate_Next");
                break;
            case KeyEvent.KEYCODE_NAVIGATE_PREVIOUS:
                gesturebox.setText("Navigate_Previous");
                // Do something that advances a user View to the previous item in an ordered list.
                break;
        }
        // If you did not handle it, let it be handled by the next possible element as deemed by the Activity.
        return super.onKeyDown(keyCode, event);
    }










}
