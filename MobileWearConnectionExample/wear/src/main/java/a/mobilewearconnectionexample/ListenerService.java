package a.mobilewearconnectionexample;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;


public class ListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        if (messageEvent.getPath().equals("/data_path")) {
            final String data = new String(messageEvent.getData());
            Intent dataIntent = new Intent();
            dataIntent.setAction(Intent.ACTION_SEND);
            dataIntent.putExtra("data", data);
            LocalBroadcastManager.getInstance(this).sendBroadcast(dataIntent);
        }
        else {
            super.onMessageReceived(messageEvent);
        }
    }
}

