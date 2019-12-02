package com.boxico.android.kn.qrupkeeper;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;


import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.boxico.android.kn.qrupkeeper.util.DataBackUp;
import com.boxico.android.kn.qrupkeeper.util.ItemArrayAdapter;
import com.boxico.android.kn.qrupkeeper.util.ItemService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class MainActivity extends FragmentActivity implements ZXingScannerView.ResultHandler{

    private static final String REQUESTING_LOCATION_UPDATES_KEY = "locationUpdatesKey";
    private ZXingScannerView mScannerView;
    private Button turnOnQRCam;
    private Button goToButton;
    private View viewQRCam;
    private boolean cameraIsOn = false;
    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private final int PERMISSIONS_REQUEST_ACCESS_CAMERA = 102;
    private Location mLastKnownLocation = null;
    private boolean mLocationPermissionGranted = false;
    private boolean mCameraPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback locationCallback;
    //andrea
   // private Location location = null;
    private double latitude;
    private EditText radioEntry = null;
    private boolean requestingLocationUpdates = true;
    private LocationRequest mLocationRequest;
    private double longitude;
  //Andrea
    //  private GsmCellLocation cellLocation;
    private String urlGoTo = null;
    private TextView info = null;
  //  private TextView verCoordenadas = null;
    private Button showIsClose;
    private Button searchButton;
    private Button addItem;
    private ListView listItemView;
    private ArrayAdapter<ItemDto> itemAdapter;
    private TextView currentLatLon = null;
    private View popupInputDialogView = null;
    //andrea
  /*  private TelephonyManager telMgr;
    LocationManager lm;*/
    private EditText nameEditText;
    private EditText entrySearch;
    private EditText descEditText;
    private EditText identEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button buttonSaveData;
    private Button buttonCancel;
    private MainActivity me;
    private ItemDto selectedItem;
    private ItemService itemService = null;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        me = this;
        int idForm = -1;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            idForm = bundle.getInt("CF");
        }
     //   this.initializeDataBase();

        switch(idForm) {
            case 1:
                openForm(idForm);
                break;
            case 2:
                openForm(idForm);
                break;
            case 3:
                openForm(idForm);
                break;

            default:
                // code block
        }

        this.configureWidgets();

        this.initializeGettingLocation();
        updateValuesFromBundle(savedInstanceState);
        this.getLocationPermission();
        this.getCameraPermission();
    //    this.initializeService();
     //   this.chargeItems();
    }


    private void initializeService(){
        GsonBuilder gsonB = new GsonBuilder();
        gsonB.setLenient();
        Gson gson = gsonB.create();
        // INTERCEPTOR 1
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor2 = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException
            {
                okhttp3.Request.Builder ongoing = chain.request().newBuilder();
                ongoing.addHeader("Content-Type", "application/x-www-form-urlencoded");
                ongoing.addHeader("Accept", "application/json");

                return chain.proceed(ongoing.build());
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(interceptor2).build();
        Retrofit retrofit = new Retrofit.Builder()
               // .baseUrl("http://172.16.2.37/")
                .baseUrl("http://192.168.1.41")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



      //  client.interceptors().add(interceptor);
        itemService = retrofit.create(ItemService.class);


    }





    public static void createAdapter() {
        // loading CAs from an InputStream
        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)

        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
            Certificate ca;

            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());


        // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

        // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

        // Tell the URLConnection to use a SocketFactory from our SSLContext
            URL url = new URL("https://certs.cac.washington.edu/CAtest/");
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.setSSLSocketFactory(context.getSocketFactory());
            InputStream in = urlConnection.getInputStream();
      //      copyInputStreamToOutputStream(in, System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.security.cert.CertificateException e) {
            e.printStackTrace();
        } finally {
         //   caInput.close();
        }

    }


    private void updateItem(ItemDto item){
        Call<ResponseBody> call = null;
        final MainActivity me = this;
        try {
            call = itemService.updateItem(item.getId(), item.getName(), item.getDescription());
            //call = itemService.updateItem(item.getId(), item);
            //  call = itemService.saveItem(item);
        }catch(Exception exc){
            exc.printStackTrace();
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    currentLatLon.setText("Successful");
                }else{
                    currentLatLon.setText("Errorrrrrrrr");
                }
                me.refreshItemList();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                me.refreshItemList();
            }

        });

    }

    private void saveItem(ItemDto item){
        Call<ResponseBody> call = null;
        try {
            call = itemService.saveItem(item.getName(), item.getDescription());
          //  call = itemService.saveItem(item);
        }catch(Exception exc){
            exc.printStackTrace();
        }
        final MainActivity me = this;
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    currentLatLon.setText("Successful");
                }else{
                    currentLatLon.setText("Errorrrrrrrr");
                }
                me.refreshItemList();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
            }

        });

    }


    private void searchItem(String text){
        Call<List<ItemDto>> call = null;
        try {
            call = itemService.getItems(text);
            //  call = itemService.saveItem(item);
        }catch(Exception exc){
            exc.printStackTrace();
        }
        final MainActivity me = this;
        call.enqueue(new Callback<List<ItemDto>>() {
            @Override
            public void onResponse(Call<List<ItemDto>> call, Response<List<ItemDto>> response) {
                if(response.isSuccessful()) {
                    currentLatLon.setText("Successful");
                }else{
                    currentLatLon.setText("Errorrrrrrrr");
                }
                me.refreshItemList();
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {

                t.printStackTrace();
            }

        });

    }



    private void getItems() {
       // Call< List<ItemDto> > call = itemService.getItems("2");
        Call< List<ItemDto> > call = itemService.getAllItems();
        call.enqueue(new Callback<List<ItemDto>>() {
            @Override
            public void onResponse(Call<List<ItemDto>> call, Response<List<ItemDto>> response) {
                currentLatLon.setText(" ");
                for(ItemDto item : response.body()) {
                    // titles.add(post.getTitle());
                    item.getName();
                    currentLatLon.setText(currentLatLon.getText() + " - " + item.getName());
                }
//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {
                call.cancel();
                currentLatLon.setText("ERRRORRRRR");
            }
        });
    }





    private void initializeGettingLocation(){
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(1000);
        // andrea
        /*telMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);*/
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //andrea
        //     this.getPermissions();
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    mLastKnownLocation = location;
                    //updateCurrentLocation();
                    latitude = mLastKnownLocation.getLatitude();
                    longitude = mLastKnownLocation.getLongitude();
                //    currentLatLon.setText("Coordenada actual:(" + latitude + "," + longitude + ")");
         //           refreshItemList();
                }
            };
        };
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        // Update the value of requestingLocationUpdates from the Bundle.
        if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
            requestingLocationUpdates = savedInstanceState.getBoolean(
                    REQUESTING_LOCATION_UPDATES_KEY);
        }

        // ...

        // Update UI to match restored state
     //   updateUI();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
                requestingLocationUpdates);
        // ...
        super.onSaveInstanceState(outState);
    }


    private void stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            startLocationUpdates();
        }
        if (cameraIsOn) {
            mScannerView.stopCamera();
            setContentView(R.layout.activity_main);
            cameraIsOn = false;
        }
    }

    private void startLocationUpdates() {
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

/*
    private void updateLocationUI() {

        try {
            if (mLocationPermissionGranted) {
                updateCurrentLocation();
            } else {
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            //    Log.e("Exception: %s", e.getMessage());
        }
    }
*/

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void getCameraPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            mCameraPermissionGranted = true;

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_ACCESS_CAMERA);
        }
    }

/*
    private void getOnlyDeviceLocation() {

        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();

                        } else {
                            //mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }

    }
*/


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
     //   updateLocationUI();
    }


    private void openForm(int idForm){

            // Create a AlertDialog Builder.
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            // Set title, icon, can not cancel properties.
            alertDialogBuilder.setTitle("Carga de datos");
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

                    //it.setId(7);
                    it.setName(name);
                    it.setDescription(desc);
                     /*   it.setIdentification(ident);
                        it.setLatitude(new Double(lat));
                        it.setLongitude(new Double(lon));*/
                    //   ConstantsAdmin.createItem(it, me);
               //     saveItem(it);



                    // Crear Item y actualizar Adapter.

                    alertDialog.cancel();
                 //   refreshItemList();
                }
            });
            buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                }
            });
    }



    private void configureWidgets() {
        viewQRCam = (View) findViewById(R.id.viewQR);
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
        searchButton = (Button) findViewById(R.id.searchButton);
        entrySearch = (EditText) findViewById(R.id.entrySearch);
        radioEntry = (EditText) findViewById(R.id.radio);
        radioEntry.setText(dbu.getRadio());
        if(dbu != null){
            goToButton.setText(dbu.getUrl());
            urlGoTo = dbu.getUrl();
         //   info.setText("Distancia hasta el cartel: " + dbu.getDistance() + " metros. Punto donde se captura=(" + dbu.getLatitude() + "," + dbu.getLongitude() + "). Punto Origen=(" + dbu.getLatitudeOrigin() + "," + dbu.getLongitudeOrigin() + ") ");
        }
   //     verCoordenadas = (TextView) findViewById(R.id.textCurrentLocation);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searchItem(entrySearch.getText().toString());
                refreshItemList();
            }
        });
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


                        updateItem(selectedItem);
                //        ConstantsAdmin.createItem(selectedItem, me);


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
                return true;
            }
        });


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                selectedItem = null;
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

                        //it.setId(7);
                        it.setName(name);
                        it.setDescription(desc);
                     /*   it.setIdentification(ident);
                        it.setLatitude(new Double(lat));
                        it.setLongitude(new Double(lon));*/
                     //   ConstantsAdmin.createItem(it, me);
                        saveItem(it);



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
     //   ConstantsAdmin.deleteItem(item, this);
        Call<ResponseBody> call = null;
        final MainActivity me = this;
        try {
            call = itemService.deleteItem(item.getId(), item.getId());
            //call = itemService.updateItem(item.getId(), item);
            //  call = itemService.saveItem(item);
        }catch(Exception exc){
            exc.printStackTrace();
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    currentLatLon.setText("Successful");
                }else{
                    currentLatLon.setText("Errorrrrrrrr");
                }
                me.refreshItemList();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                me.refreshItemList();
            }

        });
    }

    private void refreshItemList() {
      /*  List list = ConstantsAdmin.getItems(this);
        itemAdapter.clear();
        if (list != null){
            for (Object object : list) {
                itemAdapter.insert((ItemDto) object, itemAdapter.getCount());
            }
        }*/


        final MainActivity me = this;
        Call< List<ItemDto> > call = null;
        if(entrySearch.getText() != null && !entrySearch.getText().toString().equals("")){
            call = itemService.getItems(entrySearch.getText().toString());
        }else{
            call = itemService.getAllItems();
        }


        call.enqueue(new Callback<List<ItemDto>>() {
            List list = new ArrayList();
            @Override
            public void onResponse(Call<List<ItemDto>> call, Response<List<ItemDto>> response) {
                for(ItemDto item : response.body()) {
                    list.add(item);
                }
                itemAdapter = new ItemArrayAdapter(me, R.layout.row_item, R.id.textItem, list);
                listItemView.setAdapter(itemAdapter);
//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {
                call.cancel();
                currentLatLon.setText("ERRRORRRRR");
            }
        });


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
          /*  identEditText.setText(selectedItem.getIdentification());
            latitudeEditText.setText(String.valueOf(selectedItem.getLatitude()));
            longitudeEditText.setText(String.valueOf(selectedItem.getLongitude()));*/
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

        try {
            radio = radioEntry.getText().toString();

            items = ConstantsAdmin.getItems(this, latitude, longitude, radio);
            Iterator iterator = items.iterator();
            while(iterator.hasNext()){
                item = (ItemDto) iterator.next();
             /*   meters = distance(latitude, item.getLatitude(),longitude,item.getLongitude(), 0.0,0.0);
                result = result + "**" +item.getName() + "(" + item.getLatitude() + "," + item.getLongitude() + ") DISTANCIA=" + meters + "\n";*/
            }

            info.setText(result.toUpperCase());



        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }


    }

/*
    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            currentLatLon.setText("Coordenada actual:(" + latitude + "," + longitude + ")");
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
*/




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
  //      updateCurrentLocation();
        //andrea
     //   lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }
/*
    private void updateCurrentLocation(){
        try {
           // mLastKnownLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            getOnlyDeviceLocation();
            latitude = mLastKnownLocation.getLatitude();
            longitude = mLastKnownLocation.getLongitude();

        //andrea
      //      cellLocation = (GsmCellLocation) telMgr.getCellLocation();
        } catch (SecurityException ignored) {
        }
    }*/

    private void chargeItems(){
/*        long itemSize = ConstantsAdmin.getItemSize(this);
        List list = new ArrayList();
        if(itemSize == 0) {
            ItemDto it = new ItemDto();

            // SPB
            it.setName("SPB");
            it.setDescription("Servicion Penitenciario Bonaerense");
            ConstantsAdmin.createItem(it, this);
        }
        list = ConstantsAdmin.getItems(this);
        itemAdapter = new ItemArrayAdapter(this, R.layout.row_item, R.id.textItem, list);
        listItemView.setAdapter(itemAdapter);*/
        final MainActivity me = this;
        Call< List<ItemDto> > call = itemService.getAllItems();
        call.enqueue(new Callback<List<ItemDto>>() {
            List list = new ArrayList();
            @Override
            public void onResponse(Call<List<ItemDto>> call, Response<List<ItemDto>> response) {
                  for(ItemDto item : response.body()) {
                      list.add(item);
                  }
                  itemAdapter = new ItemArrayAdapter(me, R.layout.row_item, R.id.textItem, list);
                  listItemView.setAdapter(itemAdapter);
//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {
                call.cancel();
                currentLatLon.setText("ERRRORRRRR");
            }
        });

    }

    @Override
    public void handleResult(Result result) {
        String newEntry = result.getText();
        int idResult = -1;
        try {
            idResult = new Integer(newEntry);
        }catch (Exception exc){

        }
        this.getResults(newEntry);
        mScannerView.stopCamera();
        cameraIsOn = false;

        finish();  //It's necessary to operate the buttons, after using setContentView(...) more than once in the same activity
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("CF", idResult);

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
           //     updateCurrentLocation();
                items = ConstantsAdmin.getItems(this, latitude, longitude, String.valueOf(dbu.getRadio()));
                if(items != null && !items.isEmpty()){
                    item = (ItemDto) items.get(0);
                //    meters = meterDistanceBetweenPoints(latitude, longitude,item.getLatitude(),item.getLongitude());
                /*    meters = distance(latitude, item.getLatitude(),longitude,item.getLongitude(), 0.0,0.0);
                    urlGoTo = newEntry + "/" + item.getIdentification();
                    dbu.setLatitudeOrigin(item.getLatitude());
                    dbu.setLongitudeOrigin(item.getLongitude());*/
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






/*
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
*/


    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
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
