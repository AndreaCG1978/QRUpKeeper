package com.boxico.android.kn.qrlocationtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.boxico.android.kn.qrlocationtracker.ddbb.DataBaseManager;
import com.boxico.android.kn.qrlocationtracker.util.ConstantsAdmin;
import com.google.zxing.Result;

public class MainActivity extends FragmentActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private Button turnOnQRCam;
    private View viewQRCam;
    private boolean cameraIsOn = false;
    private Location location = null;
    private double latitude;
    private double longitude;
    private GsmCellLocation cellLocation;



    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeDataBase();
        this.configureWidgets();
        this.chargeExamples();
        final TelephonyManager telMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        this.getPermissions(telMgr, lm);
    }


    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

/*
    float[] disResultado = new float[2];

                Location.distanceBetween( mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude(),
                        radio.getCenter().latitude,
            radio.getCenter().longitude,
    disResultado);

                if(disResultado[0] > radio.getRadius()){
        t.setText(t.getText() + "- ESTA AFUERA DEL RADIO");
    } else {
        t.setText(t.getText() + "- ESTA ADENTRO DEL RADIO");
    }
    */


    private void startQRReader() {
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);  // It's opensorce api, so it work only with setContentView(...)
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    private void configureWidgets() {
        viewQRCam = (View) findViewById(R.id.view);
        turnOnQRCam = (Button) findViewById(R.id.TurnOnQRCam);
        turnOnQRCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRReader();
            }
        });
    }

    private void initializeDataBase(){
        DataBaseManager mDBManager = DataBaseManager.getInstance(this);
        ConstantsAdmin.inicializarBD(mDBManager);
        ConstantsAdmin.createBD(mDBManager);
        ConstantsAdmin.finalizarBD(mDBManager);
    }


    public void getPermissions(TelephonyManager telMgr, LocationManager lM) {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

            getPermissions(telMgr, lM);
            return;
        }
        try {
            location = lM.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location == null) {
                lM.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
            } else {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
            cellLocation = (GsmCellLocation) telMgr.getCellLocation();
        } catch (Exception ignored) {
        }
    }



    private void chargeExamples(){
        long itemSize = ConstantsAdmin.getItemSize(this);
        if(itemSize == 1){
            ConstantsAdmin.deleteAll(this);
        }
        if(itemSize == 0) {
            ItemDto it = new ItemDto();

            // SPB
            it.setName("SPB");
            it.setDescription("Servicion Penitenciario Bonaerense");
            it.setIdentification("54-esq-5-departamento-3-dor");
            it.setLatitude(-34.9008839);
            it.setLongitude(-57.9632206);
            ConstantsAdmin.createItem(it, this);

            // MI CASA
            it = new ItemDto();
            it.setName("Mi Casa");
            it.setDescription("Mi Casa en Arturo Segui");
            it.setIdentification("43-e-3-y-4-duplex-de-2-dor");
            it.setLatitude(-34.888900);
            it.setLongitude(-58.104036);
            ConstantsAdmin.createItem(it, this);
        }

    }

    @Override
    public void handleResult(Result result) {
        String newEntry = result.getText();
        if (result.toString().length() != 0) {

            mScannerView.stopCamera();
            cameraIsOn = false;

            finish();  //It's necessary to operate the buttons, after using setContentView(...) more than once in the same activity
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "QR code is broken", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (cameraIsOn) {
            mScannerView.stopCamera();
            setContentView(R.layout.activity_main);
            cameraIsOn = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (cameraIsOn) {
            mScannerView.stopCamera();
            setContentView(R.layout.activity_main);
            cameraIsOn = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (cameraIsOn) {
            mScannerView.stopCamera();
            setContentView(R.layout.activity_main);
            cameraIsOn = false;
        }
    }


}
