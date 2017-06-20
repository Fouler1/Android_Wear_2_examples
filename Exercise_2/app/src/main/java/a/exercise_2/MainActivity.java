package a.exercise_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    public int notificationId = 1;
    String gesture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mTextView.setText("Käännä rannetta sisään ja ulospäin, jolloin luodaan ilmoituksia");
            }
        });
    }

    @Override /* KeyEvent.Callback */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_NAVIGATE_NEXT:
                mTextView.setText("Navigate_Next");
                gesture = "Next";
                Log.d("Gesture","Next");
                sendNotification();
                break;
            case KeyEvent.KEYCODE_NAVIGATE_PREVIOUS:
                mTextView.setText("Navigate_Previous");
                gesture = "Previous";
                Log.d("Gesture","Previous");
                sendNotification();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void sendNotification() {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.action_item_icon_background)
                        .setContentTitle("Wrist gesture detected")
                        .setContentText(gesture);
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
        Log.d("Notify","Sent");
    }

}
