package a.exercise_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView textviewlatitude1, textviewlatitude, textviewlongitude1, textviewlongitude;
    private Button notificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                textviewlatitude1 = (TextView) stub.findViewById(R.id.latitudetext);
                textviewlatitude = (TextView) stub.findViewById(R.id.latitude);
                textviewlongitude1 = (TextView) stub.findViewById(R.id.longitudetext);
                textviewlongitude = (TextView) stub.findViewById(R.id.longitude);
                notificationButton = (Button) stub.findViewById(R.id.button);
            }
        });
    }
}
