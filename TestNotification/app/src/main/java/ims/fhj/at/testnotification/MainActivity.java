package ims.fhj.at.testnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView mTextView;
    Ringtone mRingtone;
    public int notificationId = 1;
    MediaPlayer mediaPlayer = new MediaPlayer();
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);

            }
        });




    }
    public void notifyMe(View view)
    {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.action_item_icon_background)
                        .setContentTitle("Notification accuired")
                        .setContentText("Here's an example content text");
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
        Log.d("Notify","Sent");
        Context context = this;
        mediaPlayer = MediaPlayer.create(context, R.raw.num1);
        mediaPlayer.start();


    }

    public void alarmplay()
    {


        Log.d("music","playing");

    }
    public void stopMusic(View view)
    {
        mediaPlayer.stop();
        Log.d("music","Stopped");
    }
}
