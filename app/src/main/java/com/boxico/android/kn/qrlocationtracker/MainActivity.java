package com.boxico.android.kn.qrlocationtracker;


import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.boxico.android.kn.qrlocationtracker.ddbb.DataBaseManager;
import com.boxico.android.kn.qrlocationtracker.util.ConstantsAdmin;
import com.boxico.android.kn.qrlocationtracker.util.DataBackUp;
import com.boxico.android.kn.qrlocationtracker.util.ItemArrayAdapter;
import com.google.android.gms.maps.model.LatLng;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends FragmentActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private Button turnOnQRCam;
    private Button goToButton;
    private View viewQRCam;
    private boolean cameraIsOn = false;
    private Location location = null;
    private double latitude;
    private EditText radioEntry = null;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double longitude;
    private GsmCellLocation cellLocation;
  //  private static final String diference = "0.0005";
    private String urlGoTo = null;
    private TextView info = null;
    private TextView verCoordenadas = null;
    private Button showIsClose;
    private Button addItem;
    private ListView listItemView;
    private ArrayAdapter<ItemDto> itemAdapter;
    private TextView currentLatLon = null;
    private View popupInputDialogView = null;

    private TelephonyManager telMgr;
    LocationManager lm;
    private EditText nameEditText;
    private EditText descEditText;
    private EditText identEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button buttonSaveData;
    private Button buttonCancel;
    private MainActivity me;
    private ItemDto selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        me = this;
        //    initMainActivityControls();
        this.initializeDataBase();
        this.configureWidgets();
        this.chargeExamples();
        telMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        this.getPermissions();

    }

    private void configureWidgets() {
        viewQRCam = (View) findViewById(R.id.view);
        turnOnQRCam = (Button) findViewById(R.id.TurnOnQRCam);
        currentLatLon = (TextView) findViewById(R.id.currentLatLon);
        turnOnQRCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRReader();
            }
        });
        goToButton = (Button) findViewById(R.id.goToButton);
        goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResult();
            }
        });
        info = (TextView) findViewById(R.id.info);
        addItem = (Button) findViewById(R.id.addItem);
        DataBackUp dbu = ConstantsAdmin.getDataBackUp(this);
        if(dbu == null){
            dbu = new DataBackUp();

        }

        radioEntry = (EditText) findViewById(R.id.radio);
        radioEntry.setText(dbu.getRadio());
        if(dbu != null){
            goToButton.setText(dbu.getUrl());
            urlGoTo = dbu.getUrl();
         //   info.setText("Distancia hasta el cartel: " + dbu.getDistance() + " metros. Punto donde se captura=(" + dbu.getLatitude() + "," + dbu.getLongitude() + "). Punto Origen=(" + dbu.getLatitudeOrigin() + "," + dbu.getLongitudeOrigin() + ") ");
        }
        verCoordenadas = (TextView) findViewById(R.id.textCurrentLocation);

        showIsClose = (Button) findViewById(R.id.showIsClose);
        showIsClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIsClose();
            }
        });
        listItemView = (ListView) findViewById(R.id.itemList);
        listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = itemAdapter.getItem(position);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("Modificar Item.");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);

                // Init popup dialog view and it's ui controls.
                initPopupViewControls();

                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupInputDialogView);

                // Create AlertDialog and show.
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                // When user click the save user data button in the popup dialog.
                buttonSaveData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Get user data from popup dialog editeext.
                        String name = nameEditText.getText().toString();
                        String desc = descEditText.getText().toString();
                        String ident = identEditText.getText().toString();
                        String lat = latitudeEditText.getText().toString();
                        String lon = longitudeEditText.getText().toString();

                        if(selectedItem == null){
                            selectedItem = new ItemDto();
                        }

                        // SPB
                        selectedItem.setName(name);
                        selectedItem.setDescription(desc);
                        selectedItem.setIdentification(ident);
                        selectedItem.setLatitude(Double.valueOf(lat));
                        selectedItem.setLongitude(Double.valueOf(lon));
                        ConstantsAdmin.createItem(selectedItem, me);


                        // Crear Item y actualizar Adapter.

                        alertDialog.cancel();
                        refreshItemList();
                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
        listItemView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id)
            {
                final ItemDto itemToDelete = itemAdapter.getItem(pos);
                AlertDialog.Builder builder = new AlertDialog.Builder(me);
                builder.setMessage(R.string.msj_delete_item)
                        .setCancelable(true)
                        .setPositiveButton(R.string.label_yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteItem(itemToDelete);
                                refreshItemList();

                            }
                        })
                        .setNegativeButton(R.string.label_no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                return false;
            }
        });


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("Alta Item.");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);

                // Init popup dialog view and it's ui controls.
                initPopupViewControls();

                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupInputDialogView);

                // Create AlertDialog and show.
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                // When user click the save user data button in the popup dialog.
                buttonSaveData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Get user data from popup dialog editeext.
                        String name = nameEditText.getText().toString();
                        String desc = descEditText.getText().toString();
                        String ident = identEditText.getText().toString();
                        String lat = latitudeEditText.getText().toString();
                        String lon = longitudeEditText.getText().toString();

                        ItemDto it = new ItemDto();

                        // SPB
                        it.setName(name);
                        it.setDescription(desc);
                        it.setIdentification(ident);
                        it.setLatitude(new Double(lat));
                        it.setLongitude(new Double(lon));
                        ConstantsAdmin.createItem(it, me);


                        // Crear Item y actualizar Adapter.

                        alertDialog.cancel();
                        refreshItemList();
                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });

    }

    private void deleteItem(ItemDto item){
        ConstantsAdmin.deleteItem(item, this);
    }

    private void refreshItemList() {
        List list = ConstantsAdmin.getItems(this);
        itemAdapter.clear();
        if (list != null){
            for (Object object : list) {
                itemAdapter.insert((ItemDto) object, itemAdapter.getCount());
            }
        }

    }
/*
    private void initMainActivityControls()
    {
        if(openInputPopupDialogButton == null)
        {
            openInputPopupDialogButton = (Button)findViewById(R.id.button_popup_overlay_input_dialog);
        }

        if(userDataListView == null)
        {
            userDataListView = (ListView)findViewById(R.id.listview_user_data);
        }
    }
*/

    private void initPopupViewControls()
    {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.add_item_layout, null);

        // Get user input edittext and button ui controls in the popup dialog.
        nameEditText = (EditText) popupInputDialogView.findViewById(R.id.itemName);
        descEditText = (EditText) popupInputDialogView.findViewById(R.id.descItem);
        identEditText = (EditText) popupInputDialogView.findViewById(R.id.identItem);
        latitudeEditText = (EditText) popupInputDialogView.findViewById(R.id.latitudeItem);
        longitudeEditText = (EditText) popupInputDialogView.findViewById(R.id.longitudeItem);
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
        buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
        if(selectedItem != null){
            nameEditText.setText(selectedItem.getName());
            descEditText.setText(selectedItem.getDescription());
            identEditText.setText(selectedItem.getIdentification());
            latitudeEditText.setText(String.valueOf(selectedItem.getLatitude()));
            longitudeEditText.setText(String.valueOf(selectedItem.getLongitude()));
        }else{
            latitudeEditText.setText(String.valueOf(latitude));
            longitudeEditText.setText(String.valueOf(longitude));
        }


    }


    private void showIsClose() {
        List items;
        ItemDto item;
        Iterator<ItemDto> it;
        LatLng markPoint, currentLocation;
        double meters = 0;
        String radio = null;
        String result = "";
        DataBackUp dbu = ConstantsAdmin.getDataBackUp(this);
        if(dbu == null){
            dbu = new DataBackUp();
        }
        urlGoTo = "https://www.google.com/";
        try {
            radio = radioEntry.getText().toString();
            dbu.setRadio(radio);
            updateCurrentLocation();
            items = ConstantsAdmin.getItems(this, latitude, longitude, String.valueOf(dbu.getRadio()));
            Iterator iterator = items.iterator();
            while(iterator.hasNext()){
                item = (ItemDto) iterator.next();
                meters = distance(latitude, item.getLatitude(),longitude,item.getLongitude(), 0.0,0.0);
                result = result + item.getName() + "";
            }
            if(items != null && !items.isEmpty()){
                item = (ItemDto) items.get(0);
                //meters = meterDistanceBetweenPoints(latitude, longitude,item.getLatitude(),item.getLongitude());
                meters = distance(latitude, item.getLatitude(),longitude,item.getLongitude(), 0.0,0.0);
                dbu.setLatitudeOrigin(item.getLatitude());
                dbu.setLongitudeOrigin(item.getLongitude());

            }

            dbu.setUrl(urlGoTo);
            dbu.setDistance(meters);
            dbu.setLatitude(latitude);
            dbu.setLongitude(longitude);



        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }

        //goToButton.setText(urlGoTo);
        ConstantsAdmin.deleteDataBackUp(this);
        ConstantsAdmin.createDataBackUp(dbu, this);
        info.setText("Distancia hasta el cartel: " + dbu.getDistance() + " metros. Punto donde se captura=(" + dbu.getLatitude() + "," + dbu.getLongitude() + "). Punto Origen=(" + dbu.getLatitudeOrigin() + "," + dbu.getLongitudeOrigin() + ") ");

    }


    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            currentLatLon.setText("(" + latitude + "," + longitude + ")");
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


    private void showCurrentLocation() {
        updateCurrentLocation();
        verCoordenadas.setText("COORDENADA ACTUAL:(" + latitude + "," + longitude + ")");
    }


    private void goToResult() {
        if(urlGoTo != null){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(urlGoTo));
            startActivity(i);

        }
    }

    private void initializeDataBase(){
        DataBaseManager mDBManager = DataBaseManager.getInstance(this);
        ConstantsAdmin.inicializarBD(mDBManager);
        ConstantsAdmin.createBD(mDBManager);
        ConstantsAdmin.finalizarBD(mDBManager);
    }


    public void getPermissions() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

            getPermissions();
            return;
        }
        updateCurrentLocation();
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }

    private void updateCurrentLocation(){
        try {
            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
       //     if (location == null) {

       //     } else {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
       //     }

            cellLocation = (GsmCellLocation) telMgr.getCellLocation();
        } catch (SecurityException ignored) {
        }
    }

    private void chargeExamples(){
        long itemSize = ConstantsAdmin.getItemSize(this);
        List list = new ArrayList();
       /* if(itemSize == 2){
            ConstantsAdmin.deleteAll(this);
        }*/
        if(itemSize == 0) {
            ItemDto it = new ItemDto();

            // SPB
            it.setName("SPB");
            it.setDescription("Servicion Penitenciario Bonaerense");
            it.setIdentification("54-esq-5-departamento-3-dor");
            it.setLatitude(-34.901527);
            it.setLongitude(-57.964265);
            ConstantsAdmin.createItem(it, this);

            // MI CASA
            it = new ItemDto();
            it.setName("Mi Casa");
            it.setDescription("Mi Casa en Arturo Segui");
            it.setIdentification("43-e-3-y-4-duplex-de-2-dor");
            it.setLatitude(-34.888789);
            it.setLongitude(-58.103940);
            ConstantsAdmin.createItem(it, this);
        }
        list = ConstantsAdmin.getItems(this);
        itemAdapter = new ItemArrayAdapter(this, R.layout.row_item, R.id.textItem, list);
        listItemView.setAdapter(itemAdapter);




    }

    @Override
    public void handleResult(Result result) {
        String newEntry = result.getText();
        this.getResults(newEntry);
        mScannerView.stopCamera();
        cameraIsOn = false;

        finish();  //It's necessary to operate the buttons, after using setContentView(...) more than once in the same activity
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);

    }



    private void getResults(String newEntry) {
        List items;
        ItemDto item;
        Iterator<ItemDto> it;
        LatLng markPoint, currentLocation;
        double meters = 0;
        DataBackUp dbu = null;
        urlGoTo = "https://www.google.com/";
        try {
            if(newEntry != null && newEntry.length() != 0){
                dbu = ConstantsAdmin.getDataBackUp(this);
                if(dbu == null){
                    dbu = new DataBackUp();
                }
              /*  LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                latitude = location.getLatitude();
                longitude = location.getLongitude();*/
                updateCurrentLocation();
                items = ConstantsAdmin.getItems(this, latitude, longitude, String.valueOf(dbu.getRadio()));
                if(items != null && !items.isEmpty()){
                    item = (ItemDto) items.get(0);
                //    meters = meterDistanceBetweenPoints(latitude, longitude,item.getLatitude(),item.getLongitude());
                    meters = distance(latitude, item.getLatitude(),longitude,item.getLongitude(), 0.0,0.0);
                    urlGoTo = newEntry + "/" + item.getIdentification();
                    dbu.setLatitudeOrigin(item.getLatitude());
                    dbu.setLongitudeOrigin(item.getLongitude());
                }

            }
            dbu.setUrl(urlGoTo);
            dbu.setDistance(meters);
            dbu.setLatitude(latitude);
            dbu.setLongitude(longitude);

        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }

        //goToButton.setText(urlGoTo);
        ConstantsAdmin.deleteDataBackUp(this);
        ConstantsAdmin.createDataBackUp(dbu, this);

    }


    private double meterDistanceBetweenPoints(double lat_a, double lng_a, double lat_b, double lng_b) {
        double pk = (double) (180.f/Math.PI);

        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
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
