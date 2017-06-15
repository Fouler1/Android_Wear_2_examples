package ims.fhj.at.testnavigator;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;



public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int MY_PERMISSIONS_REQUEST_USE_GPS = 100;
    private TextView mTextView, longi, latti;
    public GoogleApiClient mGoogleApiClient;
    public Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                longi = (TextView) stub.findViewById(R.id.mLongitudeText);
                latti = (TextView) stub.findViewById(R.id.mLatitudeText);
            }
        });
        // Making GoogleApiClient which allows us to use API.
        if (mGoogleApiClient == null) {
        Log.d("MgoogleClient", "Was empty");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
}
    //onStart we connect to API
    protected void onStart() {
        Log.d("Onstart","Connecting to API");
        mGoogleApiClient.connect();
        super.onStart();
    }
    //onStop we close to connection
    protected void onStop() {
        mGoogleApiClient.disconnect();
        Log.d("onStop","Client disconnected succesfully");
        super.onStop();
    }

    // onConnected we ask the permission for Coarse_Location from the user if its needed.

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_USE_GPS);
            }
            Log.d("Permission","Asked");
            return;
        }
        // Here we get our location and print it on the screen.
        Log.d("GetLastLocation","Starting To ask Location");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        Log.d("LastLocation","is:" +mLastLocation);
        if (mLastLocation != null) {
            UpdateUI();
        }
    }

    public void UpdateUI()
    {
        latti.setText(String.valueOf(mLastLocation.getLatitude()));
        longi.setText(String.valueOf(mLastLocation.getLongitude()));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
