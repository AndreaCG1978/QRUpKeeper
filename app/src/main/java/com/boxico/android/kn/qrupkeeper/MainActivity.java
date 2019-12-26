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

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.boxico.android.kn.qrupkeeper.util.DataBackUp;
import com.boxico.android.kn.qrupkeeper.util.DatacenterService;
import com.boxico.android.kn.qrupkeeper.util.FormService;
import com.boxico.android.kn.qrupkeeper.util.InspectorService;
import com.boxico.android.kn.qrupkeeper.util.ItemArrayAdapter;
import com.boxico.android.kn.qrupkeeper.util.ItemService;
import com.boxico.android.kn.qrupkeeper.util.TableroService;
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
import java.sql.Date;
import java.sql.Timestamp;
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
    private Button loadDatacenterButton;
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
 //   private EditText radioEntry = null;
    private boolean requestingLocationUpdates = true;
    private LocationRequest mLocationRequest;
    private double longitude;
  //Andrea
    //  private GsmCellLocation cellLocation;
 //   private String urlGoTo = null;
 //   private TextView info = null;
  //  private TextView verCoordenadas = null;
  //  private Button showIsClose;
 //   private Button searchButton;
  //  private Button addItem;
  //  private ListView listItemView;
  //  private ArrayAdapter<ItemDto> itemAdapter;
 //   private TextView currentLatLon = null;
    private View popupInputDialogView = null;
    //andrea
  /*  private TelephonyManager telMgr;
    LocationManager lm;*/
  /*  private EditText nameEditText;
 //   private EditText entrySearch;
    private EditText descEditText;
    private EditText identEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;*/
    private Button buttonSaveData;
    private Button buttonCancel;
    private Button saveFormButton;
    private Button storeDataButton;
    private Button resetFormButton;
    private Button cancelFormButton;
    private MainActivity me;
    private ItemDto selectedItem;
    private AbstractArtefactDto selectedArtefact;
    private ItemService itemService = null;
    private TableroService tableroService = null;
    private FormService formService = null;
    private InspectorService inspectorService = null;
    private DatacenterService datacenterService = null;

    private EditText tableroNom;
    private EditText pckwR;
    private EditText pckwT;
    private EditText pckwS;

    private EditText pcaR;
    private EditText pcaT;
    private EditText pcaS;
    private EditText nroForm;
    private EditText descForm;
    private int idQr = -1;
    private DataCenter currentDatacenter;
    private Inspector currentInspector;
    private TextView tvDatacenter;
    private TextView tvInspector;
    private TextView tvForm;

    private CheckBox checkAlarma;
    private static String currentDatacenterConstant = "currentDatacenter";
   // private static String currentInspectorConstant = "currentInspector";


    private DatacenterForm currentForm;
    private ListView listDatacentersView;
    private ArrayAdapter<DataCenter> listDatacentersAdapter;

    private ListView listArtefactsView;
    private ArrayAdapter<AbstractArtefactDto> listArtefactsAdapter;
    private ArrayList<AbstractArtefactDto> listArtefacts;

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
        idQr = -1;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.get("CF") != null) {
                idQr = bundle.getInt("CF");
            }
            if(bundle.get(currentDatacenterConstant) != null){
                currentDatacenter = (DataCenter)bundle.get(currentDatacenterConstant);
            }
            if(bundle.get(ConstantsAdmin.currentInspectorConstant) != null){
                currentInspector = (Inspector)bundle.get(ConstantsAdmin.currentInspectorConstant);
            }
        }
        this.initializeService();
        this.configureWidgets();
        this.initializeDataBase();
        this.refreshItemListFromDB();
        if(idQr != 0 && idQr != -1) {
            this.openEntrySpecifyForm();
        }



//        this.initializeGettingLocation();
//        updateValuesFromBundle(savedInstanceState);
//        this.getLocationPermission();
        this.getCameraPermission();
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
                .baseUrl(ConstantsAdmin.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



      //  client.interceptors().add(interceptor);
      //  itemService = retrofit.create(ItemService.class);
        tableroService = retrofit.create(TableroService.class);
        inspectorService = retrofit.create(InspectorService.class);
        datacenterService = retrofit.create(DatacenterService.class);
        formService = retrofit.create(FormService.class);



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

/*
    private void updateItem(ItemDto item){
        Call<ResponseBody> call = null;
        final MainActivity me = this;
        try {
            call = itemService.updateItem(item.getId(), item.getName(), item.getDescription());

        }catch(Exception exc){
            exc.printStackTrace();
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 me.refreshItemList();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                me.refreshItemList();
            }

        });

    }
*/

/*
    private void saveItem(ItemDto item){
        Call<ResponseBody> call = null;
        try {
            call = itemService.saveItem(item.getName(), item.getDescription());

        }catch(Exception exc){
            exc.printStackTrace();
        }
        final MainActivity me = this;
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                me.refreshItemList();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
            }

        });

    }
*/
/*
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
                me.refreshItemList();
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {

                t.printStackTrace();
            }

        });

    }

*/

    private void getItems() {
       // Call< List<ItemDto> > call = itemService.getItems("2");
        Call< List<ItemDto> > call = itemService.getAllItems();
        call.enqueue(new Callback<List<ItemDto>>() {
            @Override
            public void onResponse(Call<List<ItemDto>> call, Response<List<ItemDto>> response) {
              //  currentLatLon.setText(" ");
                for(ItemDto item : response.body()) {
                    // titles.add(post.getTitle());
                    item.getName();
                //    currentLatLon.setText(currentLatLon.getText() + " - " + item.getName());
                }
//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {
                call.cancel();
              //  currentLatLon.setText("ERRRORRRRR");
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
    /*    if (requestingLocationUpdates) {
            startLocationUpdates();
        }*/
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


    private void openEntryForm() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        initPopupViewControlsForms();
        alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
        alertDialogBuilder.setCancelable(true);


        // Set the inflated layout view object to the AlertDialog builder.
        alertDialogBuilder.setView(popupInputDialogView);

        // Create AlertDialog and show.
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // When user click the save user data button in the popup dialog.
        saveFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if(currentForm == null){
                            currentForm = new DatacenterForm();
                        }
                        loadInfoForm();
                        storeArtefactsInRemoteDB();

                alertDialog.cancel();
            }
        });
        cancelFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }

    private void openEntrySpecifyForm(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        switch (idQr){
            case 101:
                alertDialogBuilder.setTitle("Tablero TGBT");
                initPopupViewControlsTablero();
                break;
            case 102:
                alertDialogBuilder.setTitle("Tablero Aire/Chiller");
                initPopupViewControlsTablero();
                break;
            case 103:
                alertDialogBuilder.setTitle("Tablero Crac");
                initPopupViewControlsTablero();
                break;
            case 104:
                alertDialogBuilder.setTitle("Tablero In UPS");
                initPopupViewControlsTablero();
                break;
            case 105:
                alertDialogBuilder.setTitle("Load UPS");
                initPopupViewControlsUPS();
                break;
            default:
                break;
        }

        alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
        alertDialogBuilder.setCancelable(true);


        // Set the inflated layout view object to the AlertDialog builder.
        alertDialogBuilder.setView(popupInputDialogView);

        // Create AlertDialog and show.
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // When user click the save user data button in the popup dialog.
        buttonSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (idQr){
                    case 101:
                        if(selectedArtefact == null) {
                            selectedArtefact = new TableroTGBT();
                        }
                        loadInfoTablero((TableroTGBT)selectedArtefact);
                      //  saveTableroTGBT(t, currentForm);
                        saveTableroTGBT((TableroTGBT)selectedArtefact);
                        break;
                    case 102:
                        if(selectedArtefact == null) {
                            selectedArtefact = new TableroAireChiller();
                        }
                        loadInfoTablero((TableroAireChiller)selectedArtefact);
                        saveTableroAireChiller((TableroAireChiller)selectedArtefact);
                        break;
                    case 103:
                        if(selectedArtefact == null) {
                            selectedArtefact = new TableroCrac();
                        }
                        loadInfoTablero((TableroCrac)selectedArtefact);
                        saveTableroCrac((TableroCrac)selectedArtefact);
                        break;
                    case 104:
                        if(selectedArtefact == null) {
                            selectedArtefact = new TableroInUps();
                        }
                        loadInfoTablero((TableroInUps)selectedArtefact);
                        saveTableroInUps((TableroInUps)selectedArtefact);
                        break;
                    case 105:
                        if(selectedArtefact == null) {
                            selectedArtefact = new LoadUPS();
                        }
                   //     loadInfoUps();
                    //    saveLoadUPSInLocalDB();
                        break;
                    default:
                        break;
                }

                //it.setName(name);


                //     saveItem(it);



                // Crear Item y actualizar Adapter.

                alertDialog.cancel();
         //       refreshItemList();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }



    private void createAlertDialog(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message).setTitle(title);

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();

    }

    private void loadDatacenterInfo(){
        Call< List<DataCenter> > call = null;
        call = datacenterService.getDatacenters(String.valueOf(idQr));



        call.enqueue(new Callback<List<DataCenter>>() {
            List list = new ArrayList();
            @Override
            public void onResponse(Call<List<DataCenter>> call, Response<List<DataCenter>> response) {
                for(DataCenter item : response.body()) {
                    //  list.add(item);
                    currentDatacenter = item;
                    tvDatacenter.setText("DATACENTER: " + currentDatacenter.getName());
                }
            /*    itemAdapter = new ItemArrayAdapter(me, R.layout.row_item, R.id.textItem, list);
                listItemView.setAdapter(itemAdapter);*/
//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DataCenter>> call, Throwable t) {
                call.cancel();
                //      currentLatLon.setText("ERRRORRRRR");
            }
        });


    }
/*
    private void loadInspectorInfo(){
        final MainActivity me = this;
        Call< List<Inspector> > call = null;
        call = inspectorService.getInspectors(String.valueOf(idQr), null);

        call.enqueue(new Callback<List<Inspector>>() {
            List list = new ArrayList();
            @Override
            public void onResponse(Call<List<Inspector>> call, Response<List<Inspector>> response) {
                for(Inspector item : response.body()) {

                    currentInspector = item;
                    tvInspector.setText("TECNICO: " + currentInspector.getUsr());
                }

            }

            @Override
            public void onFailure(Call<List<Inspector>> call, Throwable t) {
                call.cancel();

            }
        });

    }
*/
    private void loadInfoTablero(TableroTGBT t){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        selectedArtefact.setCode(idQr);

     /*   t.setDatacenterId(1);
        t.setInspectorId(1);
        t.setNroForm("00000001");*/


    }

    private void loadInfoTablero(TableroAireChiller t){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoTablero(TableroCrac t){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoTablero(TableroInUps t){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoUps(){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setPercent_r(pcaR.getText().toString());
        selectedArtefact.setPercent_s(pcaS.getText().toString());
        selectedArtefact.setPercent_t(pcaT.getText().toString());
        selectedArtefact.setCode(idQr);
        if(checkAlarma.isChecked()){
            selectedArtefact.setAlarma("1");
        }else{
            selectedArtefact.setAlarma("0");
        }

    }



    private void loadInfoForm(){
        if(currentDatacenter != null){
            currentForm.setDatacenterId(currentDatacenter.getId());
            currentForm.setDatacenterName(currentDatacenter.getName());
        }
        currentForm.setDescription(descForm.getText().toString());
        currentForm.setNroForm(nroForm.getText().toString());
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        currentForm.setFecha(fechaActual.toString());

    }


    private class PrivateTaskSaveAll extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                saveAllInRemoteBD();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    "Guardando la información...", false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            createAlertDialog("Se han registrado los datos con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskDeleteTablero extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                //   saveAllInRemoteBD();
             //   deleteTableroTGBTInRemoteDB((TableroTGBT) selectedArtefact);
                deleteTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.deleteTablero(selectedArtefact, me);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    "Guardando la información...", false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            createAlertDialog("Se han eliminado el artefacto con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }





    private class PrivateTaskSaveTableroTGBT extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
             //   saveAllInRemoteBD();
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    "Guardando la información...", false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveTableroAireChiller extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                //   saveAllInRemoteBD();
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, me);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    "Guardando la información...", false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            createAlertDialog("Se han registrado el Tablero Aire/Chiller con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }


    private class PrivateTaskSaveTableroCrac extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                //   saveAllInRemoteBD();
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroCrac((TableroCrac) selectedArtefact, me);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    "Guardando la información...", false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            createAlertDialog("Se han registrado el Tablero Crac con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveTableroInUps extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                //   saveAllInRemoteBD();
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroInUps((TableroInUps) selectedArtefact, me);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    "Guardando la información...", false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            createAlertDialog("Se han registrado el Tablero In Ups con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }


    private void saveAllInRemoteBD(){
        // SALVO EL FORMULARIO
        Call<ResponseBody> call = null;
        boolean noSeGuardoEnBDRemota = true;
        if(currentForm.getId() != -1 && currentForm.getId() != 0){// ES UN FORMULARIO EXISTENTE
            call = null;
            try {
                int dataCenterID = currentForm.getDatacenterId();
                if(currentDatacenter != null){
                    dataCenterID = currentDatacenter.getId();
                }
                call = formService.updateForm(currentForm.getId(), currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), dataCenterID, currentForm.getFecha());
                noSeGuardoEnBDRemota = false;
                //  call = itemService.saveItem(item);
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            call = null;
            try {
                call = formService.saveForm(currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), currentDatacenter.getId(), currentForm.getFecha());
                //  call = itemService.saveItem(item);
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(noSeGuardoEnBDRemota){
            Call< List<DatacenterForm> > callDF = null;
            callDF = formService.getForms(currentForm.getNroForm());
            Response<List<DatacenterForm>> resp = null;
            try {
                resp = callDF.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resp != null){
                for(DatacenterForm item : resp.body()) {
                    currentForm.setId(item.getId());
                }

            }

        }
        ConstantsAdmin.deleteForm(currentForm, this);
        ConstantsAdmin.createForm(currentForm, this);
        if(noSeGuardoEnBDRemota){

            Iterator<AbstractArtefactDto> iterator = listArtefacts.iterator();
            AbstractArtefactDto a;
            while (iterator.hasNext()){
                a = iterator.next();
                a.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(a);
                switch (a.getCode()){
                    case 101:
                      //  saveTableroTGBT((TableroTGBT) a);
                        //saveTableroInRemoteDB(a);
                        ConstantsAdmin.createTableroTGBT((TableroTGBT) a, this);
                        break;
                    case 102:
                    //    saveTableroAireChillerInRemoteDB((TableroAireChiller)a);
                        ConstantsAdmin.createTableroAireChiller((TableroAireChiller) a, this);
                        break;
                    case 103:
                        ConstantsAdmin.createTableroCrac((TableroCrac) a, this);
                        //saveTableroCrac((TableroCrac)a);
                        break;
                    case 104:
                        //saveTableroInUPS((TableroInUps)a);
                        ConstantsAdmin.createTableroInUps((TableroInUps) a, this);
                        break;
                    case 105:
                        break;
                }

            }
            //listArtefacts = new ArrayList<>();
            //listArtefactsAdapter.clear();
        }


    }

    private void saveTableroAireChiller(TableroAireChiller t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveTableroAireChiller().execute();


        }else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }
    }

    private void saveTableroCrac(TableroCrac t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveTableroCrac().execute();


        }else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }
    }


    private void saveTableroInUps(TableroInUps t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveTableroInUps().execute();


        }else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }
    }

    private void saveTableroTGBT(TableroTGBT t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
          selectedArtefact = t;
          new PrivateTaskSaveTableroTGBT().execute();


        }else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }




    }
/*
    private void saveTableroTGBTInRemoteDB(TableroTGBT t) {
        Call<ResponseBody>  call = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                call = tableroService.updateTablero(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                call = tableroService.saveTablero(t.getName(), t.getCode(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat(), currentForm.getId());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }
            Call< List<TableroTGBT> > callDF = null;
            callDF = tableroService.getTablero(t.getName(),String.valueOf(t.getCode()),currentForm.getId());
            Response<List<TableroTGBT>> resp = null;
            try {
                resp = callDF.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resp != null){
                for(TableroTGBT item : resp.body()) {
                    t.setIdRemoteDB(item.getId());
                }
            }

        }

    }
*/

    private void deleteTableroInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                call = tableroService.deleteTablero(t.getIdRemoteDB(),t.getIdRemoteDB(), String.valueOf(t.getCode()));
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }

    }

    private void saveTableroInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                call = tableroService.updateTablero(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                call = tableroService.saveTablero(t.getName(), t.getCode(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat(), currentForm.getId());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }
            Call< List<AbstractArtefactDto> > callDF = null;
            callDF = tableroService.getTablero(t.getName(),String.valueOf(t.getCode()),currentForm.getId());
            Response<List<AbstractArtefactDto>> resp = null;
            try {
                resp = callDF.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resp != null){
                for(AbstractArtefactDto item : resp.body()) {
                    t.setIdRemoteDB(item.getId());
                }
            }

        }

    }

    private void saveTableroAireChillerInLocalDB(){
        ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, this);
    }

    private void saveTableroCracInLocalDB(){
        ConstantsAdmin.createTableroCrac((TableroCrac)selectedArtefact, this);
    }



    private void saveTableroInUPSInLocalDB(){
        ConstantsAdmin.createTableroInUps((TableroInUps)selectedArtefact, this);
    }

    private void resetAll(){
        listArtefacts = new ArrayList<>();
        currentForm = null;
        currentDatacenter = null;
        ConstantsAdmin.deleteAll(this);
        refreshItemListFromDB();
    }

    private void saveLoadUPSInLocalDB(){
        ConstantsAdmin.createLoadUps((LoadUPS)selectedArtefact, this);
    }

    private void configureWidgets() {
     //   viewQRCam = (View) findViewById(R.id.viewQR);
        turnOnQRCam = (Button) findViewById(R.id.TurnOnQRCam);
        loadDatacenterButton = (Button) findViewById(R.id.loadDatacenter);
      //  currentLatLon = (TextView) findViewById(R.id.currentLatLon);

    //    turnOnQRCam.setText("Seleccione un Datacenter");

        loadDatacenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loadDatacenterList();

            }
        });
        storeDataButton = (Button) findViewById(R.id.storeData);
        storeDataButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if((currentDatacenter == null && currentForm == null) || (currentForm != null && currentForm.getDatacenterName() == null)) {
                    createAlertDialog("Debe seleccionar un data center", "Atención");
                }else{

                    storeArtefacts();
                }
            }
        });

        resetFormButton = (Button) findViewById(R.id.resetForm);
        resetFormButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(me);
                builder.setMessage("Asegurese de guardar toda la información correctamente. Continua?")
                        .setCancelable(true)
                        .setPositiveButton(R.string.label_yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                resetAll();
                                //refreshItemList();

                            }
                        })
                        .setNegativeButton(R.string.label_no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });

        turnOnQRCam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startQRReader();
            }
        });

     //   goToButton = (Button) findViewById(R.id.goToButton);
    /*    goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResult();
            }
        });*/
     //   info = (TextView) findViewById(R.id.info);
    //    addItem = (Button) findViewById(R.id.addItem);
  /*      DataBackUp dbu = ConstantsAdmin.getDataBackUp(this);
        if(dbu == null){
            dbu = new DataBackUp();

        }*/
        tvDatacenter = (TextView) findViewById(R.id.currentDatacenter);
        if(currentDatacenter != null){
            tvDatacenter.setText("DATACENTER: " + currentDatacenter.getName());
        }else if(currentForm != null && currentForm.getDatacenterName() != null){
            tvDatacenter.setText("DATACENTER: " + currentForm.getDatacenterName());
        }
        tvInspector = (TextView) findViewById(R.id.currentInspector);
        if(currentInspector != null){
            tvInspector.setText("TECNICO: " + currentInspector.getDescription());
        }
        tvForm =  (TextView) findViewById(R.id.currentForm);
        if(currentForm != null){
            if(currentForm.getDatacenterName()!= null && !currentForm.getDatacenterName().equals("")){
                tvForm.setText("FORM ACTUAL: " + currentForm.getNroForm() +"(" + currentForm.getDatacenterName() +")");
            }else{
                tvForm.setText("FORM ACTUAL: " + currentForm.getNroForm());
            }

        }
        listArtefactsView = (ListView) findViewById(R.id.listArtefacts);
        listArtefacts = new ArrayList<>();
        listArtefactsAdapter = new ArrayAdapter(me, R.layout.row_item, R.id.textItem, listArtefacts);
        listArtefactsView.setAdapter(listArtefactsAdapter);
        listArtefactsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedArtefact = listArtefactsAdapter.getItem(position);
                idQr = selectedArtefact.getCode();
                openEntrySpecifyForm();
            }
        });
        listArtefactsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id)
            {
                selectedArtefact = listArtefactsAdapter.getItem(pos);
                AlertDialog.Builder builder = new AlertDialog.Builder(me);
                builder.setMessage(R.string.msj_delete_item)
                        .setCancelable(true)
                        .setPositiveButton(R.string.label_yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteArtefact();
                                //refreshItemList();

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


   /*     searchButton = (Button) findViewById(R.id.searchButton);
        entrySearch = (EditText) findViewById(R.id.entrySearch);
        radioEntry = (EditText) findViewById(R.id.radio);
        radioEntry.setText(dbu.getRadio());*/
  /*      if(dbu != null){
            goToButton.setText(dbu.getUrl());
            urlGoTo = dbu.getUrl();
        }*/


   /*     searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        listItemView = (ListView) findViewById(R.id.itemList);*/
  /*      listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
*/
/*
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
        });*/

    }

    private void storeArtefacts(){
        openEntryForm();
    }

    private void storeArtefactsInRemoteDB() {
       // this.saveForm();
        new PrivateTaskSaveAll().execute();
        /*Iterator<AbstractArtefactDto> iterator = listArtefacts.iterator();
        AbstractArtefactDto a;
        while (iterator.hasNext()){
            a = iterator.next();
            switch (a.getCode()){
                case 101:
                 //   saveTableroTGBT((TableroTGBT)a);
                    break;
                case 102:
               //     saveTableroAireChiller((TableroAireChiller)a);
                    break;
                case 103:
              //      saveTableroCrac((TableroCrac)a);
                    break;
                case 104:
                //    saveTableroInUPS((TableroInUps)a);
                    break;
                case 105:
                    break;
            }

        }*/
        //listArtefacts = new ArrayList<>();
        //listArtefactsAdapter.clear();
      //  refreshItemList();
        //createAlertDialog("Se han registrado los datos con éxito!", "Salut!");

    }



    private void deleteArtefact() {
        if(currentForm != null && currentForm.getId()!= 0 && currentForm.getId() != -1) {
            //     ConstantsAdmin.deleteTableroTGBT((TableroTGBT) selectedArtefact, this);
            new PrivateTaskDeleteTablero().execute();
        }else{
            listArtefacts.remove(selectedArtefact);
            refreshItemListFromLocalList();
        }
    }

    private void loadDatacenterList() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        initPopupViewControlsDatacenterList();
        alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
        alertDialogBuilder.setCancelable(true);


        // Set the inflated layout view object to the AlertDialog builder.
        alertDialogBuilder.setView(popupInputDialogView);

        // Create AlertDialog and show.
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                loadDatacenterInListView();
            }
        });
        alertDialog.show();
        listDatacentersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentDatacenter = listDatacentersAdapter.getItem(position);
                tvDatacenter.setText("DATACENTER: " + currentDatacenter.getName());
                if(currentForm != null) {
                    tvForm.setText(tvForm.getText() + "*");
                }
                alertDialog.cancel();
            }
        });

    }

    private void loadDatacenterInListView() {
        Call< List<DataCenter> > call = null;
        call = datacenterService.getDatacenters();
        call.enqueue(new Callback<List<DataCenter>>() {
            List list = new ArrayList();
            @Override
            public void onResponse(Call<List<DataCenter>> call, Response<List<DataCenter>> response) {
                for(DataCenter item : response.body()) {
                    list.add(item);
                }
                listDatacentersAdapter = new ArrayAdapter(me, R.layout.row_datacenter, R.id.textItem, list);
                listDatacentersView.setAdapter(listDatacentersAdapter);
//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DataCenter>> call, Throwable t) {
                call.cancel();
                //      currentLatLon.setText("ERRRORRRRR");
            }
        });



    }

    private void initPopupViewControlsDatacenterList() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.datacenters_list_view, null);
        listDatacentersView = (ListView) popupInputDialogView.findViewById(R.id.listDatacenters);

    }

    /*

    private void deleteItem(ItemDto item){
        Call<ResponseBody> call = null;
        final MainActivity me = this;
        try {
            call = itemService.deleteItem(item.getId(), item.getId());
        }catch(Exception exc){
            exc.printStackTrace();
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                me.refreshItemList();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                me.refreshItemList();
            }

        });
    }
*/
    private void refreshItemListFromLocalList() {
        listArtefactsAdapter.clear();
        if (listArtefacts != null){
            for (AbstractArtefactDto object : listArtefacts) {
                listArtefactsAdapter.insert(object, listArtefactsAdapter.getCount());

            }
        }

    }

    private void refreshItemListFromDB() {
        ArrayList<AbstractArtefactDto> allItems = new ArrayList();
        ArrayList<AbstractArtefactDto> list = null;
        list = ConstantsAdmin.getTablerosTGBT(this);
        allItems.addAll(list);
        list = ConstantsAdmin.getTablerosAireChiller(this);
        allItems.addAll(list);
        list = ConstantsAdmin.getTablerosCrac(this);
        allItems.addAll(list);
        list = ConstantsAdmin.getTablerosInUps(this);
        allItems.addAll(list);
        list = ConstantsAdmin.getLoadUps(this);
        allItems.addAll(list);

        listArtefacts = new ArrayList<>();

        listArtefactsAdapter.clear();
        if (allItems != null){
            for (AbstractArtefactDto object : allItems) {
                listArtefactsAdapter.insert(object, listArtefactsAdapter.getCount());
                listArtefacts.add(object);
            }
        }
        currentForm = ConstantsAdmin.getForm(this);
        if(currentForm != null){
            if(currentForm.getDatacenterName()!= null && !currentForm.getDatacenterName().equals("")){
                tvForm.setText("FORM ACTUAL: " + currentForm.getNroForm() +"(" + currentForm.getDatacenterName() +")");
            }else{
                tvForm.setText("FORM ACTUAL: " + currentForm.getNroForm());
            }
        }else{
            tvForm.setText("");
        }
        if(currentDatacenter != null){
            tvDatacenter.setText("DATACENTER: " + currentDatacenter.getName());
        }else if(currentForm != null && currentForm.getDatacenterName() != null){
            tvDatacenter.setText("DATACENTER: " + currentForm.getDatacenterName());
        }else{
            tvDatacenter.setText("");
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

    private void initPopupViewControlsTablero()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.tablero_layout, null);
        tableroNom = (EditText) popupInputDialogView.findViewById(R.id.itemId);
        pckwR = (EditText) popupInputDialogView.findViewById(R.id.PCKWR);
        pckwS = (EditText) popupInputDialogView.findViewById(R.id.PCKWS);
        pckwT = (EditText) popupInputDialogView.findViewById(R.id.PCKWT);
        pcaR = (EditText) popupInputDialogView.findViewById(R.id.PCAR);
        pcaS = (EditText) popupInputDialogView.findViewById(R.id.PCAS);
        pcaT = (EditText) popupInputDialogView.findViewById(R.id.PCAT);
        if(selectedArtefact != null){
            tableroNom.setText(selectedArtefact.getName());
            pckwR.setText(selectedArtefact.getKwr());
            pckwS.setText(selectedArtefact.getKws());
            pckwT.setText(selectedArtefact.getKwt());
            pcaR.setText(selectedArtefact.getPar());
            pcaS.setText(selectedArtefact.getPas());
            pcaT.setText(selectedArtefact.getPat());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
        buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }


    private void initPopupViewControlsForms()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.form_layout, null);
        descForm = (EditText) popupInputDialogView.findViewById(R.id.formDescEntry);
        nroForm = (EditText) popupInputDialogView.findViewById(R.id.formNumberEntry);
        saveFormButton = popupInputDialogView.findViewById(R.id.saveFormButton);
        cancelFormButton = popupInputDialogView.findViewById(R.id.cancelFormButton);
        if(currentForm != null && currentForm.getId() != 0 && currentForm.getId() != -1){
            descForm.setText(currentForm.getDescription());
            nroForm.setText(currentForm.getNroForm());

        }
    }

    private void initPopupViewControlsUPS()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.ups_layout, null);
        tableroNom = (EditText) popupInputDialogView.findViewById(R.id.itemId);
        pcaR = (EditText) popupInputDialogView.findViewById(R.id.percentR);
        pcaS = (EditText) popupInputDialogView.findViewById(R.id.percentS);
        pcaT = (EditText) popupInputDialogView.findViewById(R.id.percentT);
        checkAlarma = (CheckBox) popupInputDialogView.findViewById(R.id.checkAlarma);
        if(selectedArtefact != null){
            tableroNom.setText(selectedArtefact.getName());
            if(selectedArtefact.getAlarma().equals("1")){
                checkAlarma.setChecked(true);
            }else{
                checkAlarma.setChecked(false);
            }
            pcaR.setText(selectedArtefact.getPercent_r());
            pcaS.setText(selectedArtefact.getPercent_s());
            pcaT.setText(selectedArtefact.getPercent_t());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
        buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

/*
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

            }

            info.setText(result.toUpperCase());



        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }


    }*/

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
    /*    mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);  // It's opensorce api, so it work only with setContentView(...)
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();*/
        idQr = 104;
        selectedArtefact = null;
        this.openEntrySpecifyForm();
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // your code
            this.finishAffinity();
        }
        return true;
        //return super.onKeyDown(keyCode, event);
    }


/*
    private void goToResult() {
        if(urlGoTo != null){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(urlGoTo));
            startActivity(i);

        }
    }
*/
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
/*
        list = ConstantsAdmin.getItems(this);
        itemAdapter = new ItemArrayAdapter(this, R.layout.row_item, R.id.textItem, list);
        listItemView.setAdapter(itemAdapter);*/




    }

    @Override
    public void handleResult(Result result) {
        String newEntry = result.getText();
        int idResult = -1;
        try {
            idResult = new Integer(newEntry);
        }catch (Exception exc){

        }
    //    this.getResults(newEntry);
        mScannerView.stopCamera();
        cameraIsOn = false;

        finish();  //It's necessary to operate the buttons, after using setContentView(...) more than once in the same activity
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("CF", idResult);
        if(currentDatacenter != null){
            intent.putExtra(currentDatacenterConstant, currentDatacenter);
        }
        if(currentInspector != null){
            intent.putExtra(ConstantsAdmin.currentInspectorConstant, currentInspector);
        }
        startActivity(intent);

    }
/*
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

                items = ConstantsAdmin.getItems(this, latitude, longitude, String.valueOf(dbu.getRadio()));
                if(items != null && !items.isEmpty()){
                    item = (ItemDto) items.get(0);

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


*/



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
  //      stopLocationUpdates();
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
