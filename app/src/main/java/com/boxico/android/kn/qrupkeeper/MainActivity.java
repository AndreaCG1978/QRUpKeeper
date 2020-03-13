package com.boxico.android.kn.qrupkeeper;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.graphics.Color;
import android.graphics.Typeface;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;


import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.AireAcond;
import com.boxico.android.kn.qrupkeeper.dtos.AireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.AireCrac;
import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.EstractorAire;
import com.boxico.android.kn.qrupkeeper.dtos.GrupoElectrogeno;
import com.boxico.android.kn.qrupkeeper.dtos.Incendio;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.Presostato;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionCanieria;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionEscalera;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroPDR;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.boxico.android.kn.qrupkeeper.util.DatacenterService;
import com.boxico.android.kn.qrupkeeper.util.ExpandableListFragment;
import com.boxico.android.kn.qrupkeeper.util.FormService;
import com.boxico.android.kn.qrupkeeper.util.QrScannerUtil;
import com.boxico.android.kn.qrupkeeper.util.TableroService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;


import java.io.IOException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

public class MainActivity extends ExpandableListFragment implements ZXingScannerView.ResultHandler{

   // private static final String REQUESTING_LOCATION_UPDATES_KEY = "locationUpdatesKey";
    private ZXingScannerView mScannerView;

    private boolean cameraIsOn = false;
 //   private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private final int PERMISSIONS_REQUEST_ACCESS_CAMERA = 102;
    private final int PERMISSIONS_WRITE_STORAGE = 101;
   /* private Location mLastKnownLocation = null;
    private boolean mLocationPermissionGranted = false;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback locationCallback;*/
    //andrea
   // private Location location = null;
    private boolean mCameraPermissionGranted = false;
//    private double latitude;

 //   private EditText radioEntry = null;
/*    private boolean requestingLocationUpdates = true;
    private LocationRequest mLocationRequest;
    private double longitude;
*/
    private int mGroupSelected = -1;
    private int mChildSelected = -1;
    private List<String> mySortedByElements = null;
    private static final String CLAVE = "CLAVE";
    private static final String NOMBRE = "NOMBRE";
    private LayoutInflater layoutInflater = null;
    private AlertDialog.Builder alertDialogBuilder = null;

    private Map<String, List<AbstractArtefactDto>> artefactsMap = null;
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
 //   private Button buttonCancel;
    private Button saveFormButton;
    private Button storeDataButton;
    private Button resetFormButton;
    private Button cancelFormButton;
    private Button turnOnQRCam;
    private Button loadDatacenterButton;
    private ViewGroup contentFrame;

    private MainActivity me;

    private AbstractArtefactDto selectedArtefact;
    private TableroService tableroService = null;
    private FormService formService = null;
 //   private InspectorService inspectorService = null;
    private DatacenterService datacenterService = null;

    private EditText tableroNom;
    private EditText pckwR;
    private EditText pckwT;
    private EditText pckwS;
    private EditText entryDescripcion;

    private EditText pcaR;
    private EditText pcaT;
    private EditText pcaS;
    private TextView nameForm;
    private EditText descForm;
    private int idQr = -1;
    private DataCenter currentDatacenter;
    private Inspector currentInspector;
    private TextView tvDatacenter;
    private TextView tvInspector;
    private TextView tvForm;

    private CheckBox checkAlarma;

  //  private MenuItem menuItemSelectDatacenter;
    private MenuItem menuItemNuevoForm;
    private MenuItem menuItemGenerateCSV;
  //  private MenuItem menuItemEditForm;
 //   private MenuItem menuItemScanQr;

   // private static String currentInspectorConstant = "currentInspector";


    private DatacenterForm currentForm;
    private ListView listDatacentersView;
    private ArrayAdapter<DataCenter> listDatacentersAdapter;

    private ListView listArtefactsView;
    private ArrayAdapter<AbstractArtefactDto> listArtefactsAdapter;
    private ArrayList<AbstractArtefactDto> listArtefacts;
    private List allDatacenters = null;
    private EditText percent_comb;
    private EditText temperatura;
    private CheckBox checkAuto;
    private CheckBox checkNivelcomb75;
    private CheckBox checkPrecalent;
    private CheckBox checkCargadorbat;
    private CheckBox funcionaOk;
    private EditText comp1Load;
    private EditText comp2Load;
    private CheckBox comp1Ok;
    private CheckBox comp2Ok;
    private EditText out;
    private EditText presion;
    private CheckBox energiaAOk;
    private CheckBox energiaBOk;
    private EditText airePresion;
    private EditText aguaPresion;
    private CheckBox aireOk;
    private CheckBox aguaOk;
    private EditText pottotRA;
    private EditText pottotRB;
    private CheckBox arranque;
    private CheckBox correas;
    private CheckBox engrase;
    private CheckBox funcionamiento;
    private CheckBox limpieza;
    private CheckBox tiempo;
    private CheckBox encendido;
  //  private boolean newArtefactSaved;
    private int idQrSaved;
    private int idRemoteSaved;
    private int idQrDeleted;
    private String separadorExcel = null;
    //  private ArtefactsCount artefactsCount = null;


/*


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
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //artefactsCount = new ArtefactsCount();
        setContentView(R.layout.activity_main);
        me = this;
        idQr = -1;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
         /*   if(bundle.get(ConstantsAdmin.ID_QR) != null) {
                idQr = bundle.getInt(ConstantsAdmin.ID_QR);
            }
            if(bundle.get(ConstantsAdmin.currentDatacenterConstant) != null){
                currentDatacenter = (DataCenter)bundle.get(ConstantsAdmin.currentDatacenterConstant);
            }*/
            if(bundle.get(ConstantsAdmin.currentInspectorConstant) != null){
                currentInspector = (Inspector)bundle.get(ConstantsAdmin.currentInspectorConstant);
            }
        }
        layoutInflater = this.getLayoutInflater();
        this.initializeService();
        this.configureWidgets();
        this.initPopupViewControlsDatacenterList();
        this.loadDatacenterInListView();
        this.refreshItemListFromDB();


      /*  if(idQr != 0 && idQr != -1) {
            this.openEntrySpecifyForm();
        }

*/

//        this.initializeGettingLocation();
//        updateValuesFromBundle(savedInstanceState);
//        this.getLocationPermission();
        this.getCameraPermission();
    }

    private void habilitarDeshabilitarItemMenu(){
        if(currentForm == null || currentForm.getId() == -1 ){
            menuItemNuevoForm.setEnabled(false);
            menuItemGenerateCSV.setEnabled(false);
        }else{
            menuItemNuevoForm.setEnabled(true);
            menuItemGenerateCSV.setEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item;
        super.onCreateOptionsMenu(menu);

        item = menu.add(0, ConstantsAdmin.EJECUTAR_SELEC_DATACENTER,0, R.string.menu_seleccionar_datacenter);
        item.setIcon(R.drawable.datacentericon);

        item = menu.add(0, ConstantsAdmin.EJECUTAR_EDIT_FORM,0, R.string.menu_edit_form);
        item.setIcon(R.drawable.formicon);

        item = menu.add(0, ConstantsAdmin.EJECUTAR_SCAN,0, R.string.menu_scan_qr);
        item.setIcon(R.drawable.qricon);

        menuItemNuevoForm = menu.add(0, ConstantsAdmin.EJECUTAR_NUEVO_FORM,0, R.string.menu_nuevo_form);
        item.setIcon(R.drawable.newformicon);

        menuItemGenerateCSV = menu.add(0, ConstantsAdmin.EJECUTAR_GENERAR_CSV,0, R.string.menu_generate_csv);



        this.habilitarDeshabilitarItemMenu();

        return true;
    }


    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case ConstantsAdmin.EJECUTAR_SELEC_DATACENTER:
                this.loadDatacenterList();
            case ConstantsAdmin.EJECUTAR_EDIT_FORM:
                this.openEntryForm();
            case ConstantsAdmin.EJECUTAR_SCAN:
                this.startQRReader();
            case ConstantsAdmin.EJECUTAR_NUEVO_FORM:
                this.nuevoFormulario();
            case ConstantsAdmin.EJECUTAR_GENERAR_CSV:
                this.askForWriteStoragePermission();
            default:
                break;

        }
        //return super.onMenuItemSelected(featureId, item);
        return true;
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
                .baseUrl(ConstantsAdmin.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        tableroService = retrofit.create(TableroService.class);
     //   inspectorService = retrofit.create(InspectorService.class);
        datacenterService = retrofit.create(DatacenterService.class);
        formService = retrofit.create(FormService.class);
    }




    @Override
    protected void onResume() {
        super.onResume();
        if (cameraIsOn) {
            mScannerView.stopCamera();
            setContentView(R.layout.activity_main);
            cameraIsOn = false;
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


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mCameraPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mCameraPermissionGranted = true;
                }
            }
        }

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
                        loadInfoForm(true);
                        storeArtefactsInRemoteDB();
                //        onButton(true, turnOnQRCam);


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
        boolean codigoValido = true;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        switch (idQr){
            case 101:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROTGBT);
                initPopupViewControlsTablero();
                break;
            case 102:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROAIRECHILLER);
                initPopupViewControlsTablero();
                break;
            case 103:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROCRAC);
                initPopupViewControlsTablero();
                break;
            case 104:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROINUPS);
                initPopupViewControlsTablero();
                break;
            case 105:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_LOADUPS);
                initPopupViewControlsUPS();
                break;
            case 106:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_GRUPOELECTROGENO);
                initPopupViewControlsGrupoElectrogeno();
                break;
            case 107:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_AIRECRAC);
                initPopupViewControlsAireCrac();
                break;
            case 108:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_AIRECHILLER);
                initPopupViewControlsAireChiller();
                break;
            case 109:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_INCENDIO);
                initPopupViewControlsIncendio();
                break;
            case 110:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_PRESOSTATO);
                initPopupViewControlsPresostato();
                break;
            case 111:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_AIREACONDICIONADO);
                initPopupViewControlsAireAcond();
                break;
            case 112:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROPDR);
                initPopupViewControlsTableroPDR();
                break;
            case 113:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_PRESURIZACIONESCALERA);
                initPopupViewControlsPresurizacionEscalera();
                break;
            case 114:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_ESTRACTORAIRE);
                initPopupViewControlsEstractorAire();
                break;
            case 115:
                alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_PRESURIZACIONCANIERIA);
                initPopupViewControlsPresurizacionCanieria();
                break;
            default:
                codigoValido = false;
                break;
        }
        if(codigoValido){
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
                    boolean okData = checkCompleteArtefact();
                    if(okData){
                        switch (idQr){
                            case 101:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new TableroTGBT();
                                }
                                loadInfoTablero();
                                //  saveTableroTGBT(t, currentForm);
                                saveTableroTGBT((TableroTGBT)selectedArtefact);
                                break;
                            case 102:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new TableroAireChiller();
                                }
                                loadInfoTablero();
                                saveTableroAireChiller((TableroAireChiller)selectedArtefact);
                                break;
                            case 103:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new TableroCrac();
                                }
                                loadInfoTablero();
                                saveTableroCrac((TableroCrac)selectedArtefact);
                                break;
                            case 104:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new TableroInUps();
                                }
                                loadInfoTablero();
                                saveTableroInUps((TableroInUps)selectedArtefact);
                                break;
                            case 105:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new LoadUPS();
                                }
                                loadInfoUps();
                                saveLoadUps((LoadUPS)selectedArtefact);
                                break;
                            case 106:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new GrupoElectrogeno();
                                }
                                loadInfoGrupoElectrogeno();
                                saveGrupoElectrogeno((GrupoElectrogeno)selectedArtefact);
                                break;
                            case 107:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new AireCrac();
                                }
                                loadInfoAireCrac();
                                saveAireCrac((AireCrac)selectedArtefact);
                                break;
                            case 108:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new AireChiller();
                                }
                                loadInfoAireChiller();
                                saveAireChiller((AireChiller) selectedArtefact);
                                break;
                            case 109:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new Incendio();
                                }
                                loadInfoIncendio();
                                saveIncendio((Incendio)selectedArtefact);
                                break;
                            case 110:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new Presostato();
                                }
                                loadInfoPresostato();
                                savePresostato((Presostato)selectedArtefact);
                                break;
                            case 111:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new AireAcond();
                                }
                                loadInfoAireAcond();
                                saveAireAcond((AireAcond)selectedArtefact);
                                break;
                            case 112:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new TableroPDR();
                                }
                                loadInfoTableroPDR();
                                saveTableroPDR((TableroPDR)selectedArtefact);
                                break;
                            case 113:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new PresurizacionEscalera();
                                }
                                loadInfoPresurizacionEscalera();
                                savePresurizacionEscalera((PresurizacionEscalera)selectedArtefact);
                                break;
                            case 114:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new EstractorAire();
                                }
                                loadInfoEstractorAire();
                                saveEstractorAire((EstractorAire)selectedArtefact);
                                break;
                            case 115:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new PresurizacionCanieria();
                                }
                                loadInfoPresurizacionCanieria();
                                savePresurizacionCanieria((PresurizacionCanieria)selectedArtefact);
                                break;
                            default:
                                break;
                        }


                        alertDialog.cancel();

                    }else{
                        //  createAlertDialog("Debe ingresar al menos el Nombre del elemento.","Atención!");
                        createAlertDialog(getResources().getString(R.string.agregar_nombre),getResources().getString(R.string.atencion));
                    }

                }


            });
        }else{
            createAlertDialog(getResources().getString(R.string.qrInvalido), getResources().getString(R.string.atencion));
        }


     /*   buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });*/
    }

    private boolean checkCompleteArtefact() {
        boolean result = true;
        if (currentDatacenter.getCantMaxArtefact(idQr) > 1){
            result = !tableroNom.getText().toString().equals("");
        }
        return result;
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
/*
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
                    tvDatacenter.setVisibility(View.VISIBLE);
                    tvDatacenter.setText("░ DATACENTER: " + currentDatacenter.getName());
             //       onButton(true, loadDatacenterButton);
                }

//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DataCenter>> call, Throwable t) {
                call.cancel();
                //      currentLatLon.setText("ERRRORRRRR");
            }
        });


    }
    */

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
/*
    private void loadInfoTablero(TableroTGBT t){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact.setCode(idQr);



    }
*/

/*
    private void loadInfoTablero(TableroAireChiller t){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact.setCode(idQr);
    }*/

/*

    private void loadInfoTablero(TableroCrac t){
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact.setCode(idQr);
    }
*/
    private void loadInfoTablero(){
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setName(tableroNom.getText().toString());
        }else{
            selectedArtefact.setName(selectedArtefact.getClass().toString());
        }
        selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoGrupoElectrogeno(){
        GrupoElectrogeno grupo = (GrupoElectrogeno) selectedArtefact;
        //grupo.setName(tableroNom.getText().toString());
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            grupo.setName(tableroNom.getText().toString());
        }else{
            grupo.setName(grupo.getClass().toString());
        }
        grupo.setTemperatura(temperatura.getText().toString());
        grupo.setPercent_comb(percent_comb.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(checkAlarma.isChecked()){
            grupo.setAlarma("1");
        }else{
            grupo.setAlarma("0");
        }
        if(checkAuto.isChecked()){
            grupo.setAuto("1");
        }else{
            grupo.setAuto("0");
        }
        if(checkCargadorbat.isChecked()){
            grupo.setCargadorbat("1");
        }else{
            grupo.setCargadorbat("0");
        }
        if(checkNivelcomb75.isChecked()){
            grupo.setNivelcomb75("1");
        }else{
            grupo.setNivelcomb75("0");
        }
        if(checkPrecalent.isChecked()){
            grupo.setPrecalent("1");
        }else{
            grupo.setPrecalent("0");
        }
        selectedArtefact = grupo;
        selectedArtefact.setCode(idQr);
    }


    private void loadInfoAireCrac(){
        AireCrac item = (AireCrac) selectedArtefact;
      //  item.setName(tableroNom.getText().toString());
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
        item.setTemperatura(temperatura.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(funcionaOk.isChecked()){
            item.setFunciona_ok("1");
        }else{
            item.setFunciona_ok("0");
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoAireChiller(){
        AireChiller item = (AireChiller) selectedArtefact;
      //  item.setName(tableroNom.getText().toString());
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
        item.setComp1Load(comp1Load.getText().toString());
        item.setComp2Load(comp2Load.getText().toString());
        item.setAtr_out(out.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(comp1Ok.isChecked()){
            item.setComp1Ok("1");
        }else{
            item.setComp1Ok("0");
        }
        if(comp2Ok.isChecked()){
            item.setComp2Ok("1");
        }else{
            item.setComp2Ok("0");
        }

        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoIncendio(){
        Incendio item = (Incendio) selectedArtefact;
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
        //item.setName(tableroNom.getText().toString());
        item.setPresion(presion.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(energiaAOk.isChecked()){
            item.setEnergiaAOk("1");
        }else{
            item.setEnergiaAOk("0");
        }
        if(energiaBOk.isChecked()){
            item.setEnergiaBOk("1");
        }else{
            item.setEnergiaBOk("0");
        }
        if(funcionaOk.isChecked()){
            item.setFunciona_ok("1");
        }else{
            item.setFunciona_ok("0");
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoPresostato(){
        Presostato item = (Presostato) selectedArtefact;
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
        //item.setName(tableroNom.getText().toString());
        item.setAirePresion(airePresion.getText().toString());
        item.setAguaPresion(aguaPresion.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(aireOk.isChecked()){
            item.setAireOk("1");
        }else{
            item.setAireOk("0");
        }
        if(aguaOk.isChecked()){
            item.setAguaOk("1");
        }else{
            item.setAguaOk("0");
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoAireAcond(){
        AireAcond item = (AireAcond) selectedArtefact;
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
     //   item.setName(tableroNom.getText().toString());
        item.setTemperatura(temperatura.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(funcionaOk.isChecked()){
            item.setFunciona_ok("1");
        }else{
            item.setFunciona_ok("0");
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }


    private void loadInfoTableroPDR(){
        TableroPDR item = (TableroPDR) selectedArtefact;
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
    //    item.setName(tableroNom.getText().toString());
        item.setPottotRA(pottotRA.getText().toString());
        item.setPottotRB(pottotRB.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoPresurizacionEscalera(){
        PresurizacionEscalera item = (PresurizacionEscalera) selectedArtefact;
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
       // item.setName(tableroNom.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(arranque.isChecked()){
            item.setArranque("1");
        }else{
            item.setArranque("0");
        }
        if(correas.isChecked()){
            item.setCorreas("1");
        }else{
            item.setCorreas("0");
        }
        if(engrase.isChecked()){
            item.setEngrase("1");
        }else{
            item.setEngrase("0");
        }
        if(funcionamiento.isChecked()){
            item.setFuncionamiento("1");
        }else{
            item.setFuncionamiento("0");
        }
        if(limpieza.isChecked()){
            item.setLimpieza("1");
        }else{
            item.setLimpieza("0");
        }
        if(tiempo.isChecked()){
            item.setTiemp("1");
        }else{
            item.setTiemp("0");
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoEstractorAire(){
        EstractorAire item = (EstractorAire) selectedArtefact;
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
       // item.setName(tableroNom.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(arranque.isChecked()){
            item.setArranque("1");
        }else{
            item.setArranque("0");
        }
        if(correas.isChecked()){
            item.setCorreas("1");
        }else{
            item.setCorreas("0");
        }
        if(engrase.isChecked()){
            item.setEngrase("1");
        }else{
            item.setEngrase("0");
        }
        if(funcionamiento.isChecked()){
            item.setFuncionamiento("1");
        }else{
            item.setFuncionamiento("0");
        }
        if(limpieza.isChecked()){
            item.setLimpieza("1");
        }else{
            item.setLimpieza("0");
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }


    private void loadInfoPresurizacionCanieria(){
        PresurizacionCanieria item = (PresurizacionCanieria) selectedArtefact;
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            item.setName(tableroNom.getText().toString());
        }else{
            item.setName(item.getClass().toString());
        }
       // item.setName(tableroNom.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(checkAlarma.isChecked()){
            item.setAlarma("1");
        }else{
            item.setAlarma("0");
        }
        if(encendido.isChecked()){
            item.setEncendido("1");
        }else{
            item.setEncendido("0");
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }



    private void loadInfoUps(){
        if(tableroNom.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setName(tableroNom.getText().toString());
        }else{
            selectedArtefact.setName(tableroNom.getClass().toString());
        }
     //   selectedArtefact.setName(tableroNom.getText().toString());
        selectedArtefact.setPercent_r(pcaR.getText().toString());
        selectedArtefact.setPercent_s(pcaS.getText().toString());
        selectedArtefact.setPercent_t(pcaT.getText().toString());
        if(entryDescripcion.getText() != null && !entryDescripcion.getText().equals("")) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact.setCode(idQr);
        if(checkAlarma.isChecked()){
            selectedArtefact.setAlarma("1");
        }else{
            selectedArtefact.setAlarma("0");
        }

    }



    private void loadInfoForm(boolean fromEdition){
        if(currentDatacenter != null){
            currentForm.setDatacenterId(currentDatacenter.getId());
            currentForm.setDatacenterName(currentDatacenter.getName());
        }
        if(fromEdition) {
            currentForm.setDescription(descForm.getText().toString());
        }
        Timestamp fechaActualCompleta = new Timestamp(System.currentTimeMillis());

        String pattern = ConstantsAdmin.PATTERN_DATE_HOUR;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date fechaActual = new Date(System.currentTimeMillis());
        String date = simpleDateFormat.format(fechaActual);
        currentForm.setNroForm(currentInspector.getDescription() +" " + getResources().getString(R.string.stringIn)+ " " + ConstantsAdmin.ENTER + currentDatacenter.getName() + " (" + date + ")");
        currentForm.setFecha(fechaActualCompleta.toString());

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
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
         //   createAlertDialog("Se han registrado los datos con éxito!", "Salut!");
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
                deleteTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.deleteTablero(selectedArtefact, me);
                idQrDeleted = selectedArtefact.getCode();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveLoadUps extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createLoadUps((LoadUPS)selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
       //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }


    private class PrivateTaskSaveGrupoElectrogeno extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveGrupoElectrogenoInRemoteDB(selectedArtefact);
                ConstantsAdmin.createGrupoElectrogeno((GrupoElectrogeno)selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveAireCrac extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveAireCracInRemoteDB(selectedArtefact);
                ConstantsAdmin.createAireCrac((AireCrac) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }


    private class PrivateTaskSaveAireChiller extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveAireChillerInRemoteDB(selectedArtefact);
                ConstantsAdmin.createAireChiller((AireChiller) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveIncendio extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveIncendioInRemoteDB(selectedArtefact);
                ConstantsAdmin.createIncendio((Incendio) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSavePresostato extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                savePresostatoInRemoteDB(selectedArtefact);
                ConstantsAdmin.createPresostato((Presostato) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveAireAcond extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveAireAcondInRemoteDB(selectedArtefact);
                ConstantsAdmin.createAireAcond((AireAcond) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveTableroPDR extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroPDRInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroPDR((TableroPDR) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSavePresurizacionEscalera extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                savePresurizacionEscaleraInRemoteDB(selectedArtefact);
                ConstantsAdmin.createPresurizacionEscalera((PresurizacionEscalera) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }

    private class PrivateTaskSaveEstractorAire extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                saveEstractorAireInRemoteDB(selectedArtefact);
                ConstantsAdmin.createEstractorAire((EstractorAire) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }


    private class PrivateTaskSavePresurizacionCanieria extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                savePresurizacionCanieriaInRemoteDB(selectedArtefact);
                ConstantsAdmin.createPresurizacionCanieria((PresurizacionCanieria) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }



        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //     createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
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
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
        //    createAlertDialog("Se han registrado el tablero TGBT con éxito!", "Salut!");
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
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
         //   createAlertDialog("Se han registrado el Tablero Aire/Chiller con éxito!", "Salut!");
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
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroCrac((TableroCrac) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
    //        createAlertDialog("Se han registrado el Tablero Crac con éxito!", "Salut!");
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
                selectedArtefact.setIdForm(currentForm.getId());
                saveTableroInRemoteDB(selectedArtefact);
                ConstantsAdmin.createTableroInUps((TableroInUps) selectedArtefact, me);
                idQrSaved = idQr;
                idRemoteSaved = selectedArtefact.getIdRemoteDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.guardando_info), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
       //     createAlertDialog("Se han registrado el Tablero In Ups con éxito!", "Salut!");
            refreshItemListFromDB();
            //  finish();

        }
    }


    private void saveAllInRemoteBD(){
        // SALVO EL FORMULARIO
        Call<ResponseBody> call = null;
        Call<DatacenterForm> callInsert = null;
        if(currentForm.getId() != -1 && currentForm.getId() != 0){// ES UN FORMULARIO EXISTENTE
            try {
                int dataCenterID = currentForm.getDatacenterId();
                if(currentDatacenter != null){
                    dataCenterID = currentDatacenter.getId();
                    //currentForm.setDatacenterName(currentDatacenter.getName());
                   // currentForm.setDatacenterId(dataCenterID);s
                    loadInfoForm(false);
                }
                call = formService.updateForm(currentForm.getId(), currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), dataCenterID, currentForm.getFecha());
                call.execute();
                //  call = itemService.saveItem(item);
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            //call = null;
            try {
                callInsert = formService.saveForm(currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), currentDatacenter.getId(), currentForm.getFecha());
                //  call = itemService.saveItem(item);
                Response<DatacenterForm> resp = null;
                resp = callInsert.execute();
                if(resp != null){
                    DatacenterForm df = (DatacenterForm)resp.body();

                    if(currentForm == null){
                        currentForm = new DatacenterForm();
                    }
                    currentForm.setId(df.getId());

                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }


        ConstantsAdmin.deleteForm(currentForm, this);
        ConstantsAdmin.createForm(currentForm, this);
    }

    private void saveTableroAireChiller(TableroAireChiller t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveTableroAireChiller().execute();


        }
        /*else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }*/
    }

    private void saveTableroCrac(TableroCrac t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveTableroCrac().execute();


        }
        /*else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }*/
    }


    private void saveTableroInUps(TableroInUps t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveTableroInUps().execute();


        }
        /*else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }*/
    }



    private void saveTableroTGBT(TableroTGBT t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
          selectedArtefact = t;
          new PrivateTaskSaveTableroTGBT().execute();


        }

        /*else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }*/
    }

    private void saveLoadUps(LoadUPS t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveLoadUps().execute();


        }

        /*else if(selectedArtefact.getId() == -1){
            if(!listArtefacts.contains(selectedArtefact)){
                listArtefacts.add(selectedArtefact);
            }
            this.refreshItemListFromLocalList();
        }*/
    }

    private void saveGrupoElectrogeno(GrupoElectrogeno t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveGrupoElectrogeno().execute();

        }
    }

    private void saveAireCrac(AireCrac t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveAireCrac().execute();

        }
    }


    private void saveAireChiller(AireChiller t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveAireChiller().execute();

        }
    }


    private void saveIncendio(Incendio t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveIncendio().execute();

        }
    }

    private void savePresostato(Presostato t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSavePresostato().execute();

        }
    }


    private void saveAireAcond(AireAcond t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveAireAcond().execute();

        }
    }

    private void saveTableroPDR(TableroPDR t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveTableroPDR().execute();

        }
    }


    private void savePresurizacionEscalera(PresurizacionEscalera t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSavePresurizacionEscalera().execute();

        }
    }

    private void saveEstractorAire(EstractorAire t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSaveEstractorAire().execute();

        }
    }

    private void savePresurizacionCanieria(PresurizacionCanieria t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            new PrivateTaskSavePresurizacionCanieria().execute();

        }
    }


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

    private void saveGrupoElectrogenoInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }


        }

    }

    private void saveAireChillerInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updateAireChiller(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getComp1Ok(),t.getComp1Load(), t.getComp2Ok(), t.getComp2Load(), t.getAtr_out());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.saveAireChiller(t.getName(), t.getCode(), t.getDescription(), t.getComp1Ok(), t.getComp1Load(), t.getComp2Ok(), t.getComp2Load(), t.getAtr_out(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }

    private void savePresostatoInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updatePresostato(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getAguaOk(), t.getAireOk(), t.getAguaPresion(), t.getAirePresion());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.savePresostato(t.getName(), t.getCode(), t.getDescription(), t.getAguaOk(), t.getAireOk(), t.getAguaPresion(), t.getAirePresion(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }


    private void saveAireAcondInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updateAireAcond(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.saveAireAcond(t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }


    private void saveTableroPDRInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updateTableroPDR(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPottotRA(), t.getPottotRB());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.saveTableroPDR(t.getName(), t.getCode(), t.getDescription(), t.getPottotRA(), t.getPottotRB(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }


    private void saveIncendioInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updateIncendio(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getEnergiaAOk(), t.getEnergiaBOk(), t.getFunciona_ok(), t.getPresion());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.saveIncendio(t.getName(), t.getCode(), t.getDescription(), t.getEnergiaAOk(), t.getEnergiaBOk(), t.getFunciona_ok(), t.getPresion(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }


    private void savePresurizacionEscaleraInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;

        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updatePresurizacionEscalera(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza(), t.getTiemp());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.savePresurizacionEscalera(t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza(), t.getTiemp(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }


    private void saveEstractorAireInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updateEstractorAire(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.saveEstractorAire(t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }


    private void savePresurizacionCanieriaInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updatePresurizacionCanieria(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getAlarma(), t.getEncendido());
                call.execute();
                
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = tableroService.savePresurizacionCanieria(t.getName(), t.getCode(), t.getDescription(), t.getAlarma(), t.getEncendido(), currentForm.getId());

                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }


    private void saveAireCracInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
      //          call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = tableroService.updateAireCrac(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura());
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = tableroService.saveAireCrac(t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), currentForm.getId());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }

    }

    private void saveTableroInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp = null;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                switch (t.getCode()){
                    case 105:
                        call = tableroService.updateLoadUps(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPar(), t.getPas(), t.getPat(), t.getAlarma());
                        break;
                    default:
                        call = tableroService.updateTablero(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat());
                        break;
                }
                call.execute();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = tableroService.saveTablero(t.getName(), t.getCode(), t.getDescription(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat(), currentForm.getId(), t.getAlarma());
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = (AbstractArtefactDto) resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());


                }
            }catch(Exception exc){
                exc.printStackTrace();
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
        tvDatacenter.setVisibility(View.VISIBLE);
        tvInspector.setVisibility(View.VISIBLE);
        ConstantsAdmin.deleteAll(this);
        refreshItemListFromDB();
    }

    private void saveLoadUPSInLocalDB(){
        ConstantsAdmin.createLoadUps((LoadUPS)selectedArtefact, this);
    }


    private void recargarLista() {
        DataBaseManager mDBManager = DataBaseManager.getInstance(this);
       /* ConstantsAdmin.inicializarBD(mDBManager);
        ConstantsAdmin.cargarContrasenia(this, mDBManager);
        ConstantsAdmin.cargarCategoriasProtegidas(this, mDBManager);
        ConstantsAdmin.finalizarBD(mDBManager);*/

        this.getExpandableListView().setVisibility(View.VISIBLE);
        List<Map<String, String>> groupData = new ArrayList<>();
        List<List<Map<String, String>>> childData = new ArrayList<>();

 //       mGroupSelected = -1;
 //       mChildSelected = -1;
        List<AbstractArtefactDto> listaArfs;
        artefactsMap = obtenerArtefactosOrganizados(this, mDBManager);

        mySortedByElements = new ArrayList<>();
        mySortedByElements.addAll(artefactsMap.keySet());
        Collections.sort(mySortedByElements);
        Map<String, String> curGroupMap;
        List<Map<String, String>> children;
        Map<String, String> curChildMap;
        Iterator<AbstractArtefactDto> itArfs;
        AbstractArtefactDto arf;
        int cantidadTotal = 0;
        int indexGroup = 0;
        int indexChild = 0;
        for (String key : mySortedByElements) {
            listaArfs = artefactsMap.get(key);
            curGroupMap = new HashMap<>();
            groupData.add(curGroupMap);
            curGroupMap.put(CLAVE, key);
            indexChild = 0;
            if(idQrDeleted == Integer.valueOf(key) && !listaArfs.isEmpty()){
                mGroupSelected = indexGroup;
                idQrDeleted = -1;
            }
            children = new ArrayList<>();
            itArfs = listaArfs.iterator();
            while (itArfs.hasNext()) {
                arf = itArfs.next();
                if(idQrSaved == arf.getCode() && idRemoteSaved == arf.getIdRemoteDB()){
                    mGroupSelected = indexGroup;
                    mChildSelected = indexChild;
                    idQrSaved = -1;
                    idRemoteSaved = -1;
                }

                curChildMap = new HashMap<>();
                children.add(curChildMap);
                if (arf.getName() != null) {
                    curChildMap.put(NOMBRE, arf.getName());
                } else {
                    curChildMap.put(NOMBRE, "");
                }
                //curChildMap.put(NOMBRE, per.getNombres());
                cantidadTotal++;
                indexChild++;
            }
            indexGroup++;
            childData.add(children);
        }

//        cantReg.setText("(" + cantidadTotal + ")");


        setListAdapter(new SimpleExpandableListAdapter(
                               this,
                               groupData,
                               0,
                               null,
                               new int[]{},
                               childData,
                               0,
                               null,
                               new int[]{}
                       ) {
                           @Override
                           public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                               String clave = mySortedByElements.get(groupPosition);
                               final AbstractArtefactDto arf = (AbstractArtefactDto) artefactsMap.get(clave).toArray()[childPosition];
                              // selectedArtefact = arf;
                               final View v = super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
                               TextView text = v.findViewById(R.id.textItem);
                               text.setText(arf.toString());
                               final int gp = groupPosition;
                               final int cp = childPosition;



                               v.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       idQr = arf.getCode();
                                       selectedArtefact = arf;
                                       mGroupSelected = gp;
                                       mChildSelected = cp;
                                       openEntrySpecifyForm();
                                   }
                               });
                               v.setOnLongClickListener(new View.OnLongClickListener() {
                                   public boolean onLongClick(View v) {
                                       AlertDialog.Builder builder = new AlertDialog.Builder(me);
                                       builder.setMessage(R.string.msj_delete_item)
                                               .setCancelable(true)
                                               .setPositiveButton(R.string.label_yes, new DialogInterface.OnClickListener() {
                                                   public void onClick(DialogInterface dialog, int id) {
                                                       deleteArtefact(arf);
                                                    //   mGroupSelected = gp;
                                                       mGroupSelected = -1;
                                                       //refreshItemList();

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
                               return v;
                           }

                           @Override
                           public View newChildView(boolean isLastChild, ViewGroup parent) {
                               if (layoutInflater == null) {
                                   layoutInflater = getLayoutInflater();
                               }
                               return layoutInflater.inflate(R.layout.row_item, parent, false);
                           }

                           public View newGroupView(boolean isExpanded, ViewGroup parent) {
                               if (layoutInflater == null) {
                                   layoutInflater = getLayoutInflater();
                               }

                               return layoutInflater.inflate(R.layout.row_label, parent, false);
                           }

                           @Override
                           public void onGroupExpanded(int groupPosition) {
                               super.onGroupExpanded(groupPosition);
                               mGroupSelected = groupPosition;
                           }

                           public void onGroupCollapsed(int groupPosition) {
                               super.onGroupCollapsed(groupPosition);
                               mGroupSelected = -1;
                           }

                           public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                               final View v = super.getGroupView(groupPosition, isExpanded, convertView, parent);
                               TextView textName = v.findViewById(R.id.textName);
                               TextView textCantidad = v.findViewById(R.id.textCantidad);



                               String labelCode, label, temp;
                               temp = mySortedByElements.get(groupPosition);
                               labelCode = temp.toUpperCase();
                               int code = new Integer(labelCode);
                               label = ConstantsAdmin.getArtefactType(code);
                               textName.setText(label.toUpperCase());
                               if (((ExpandableListView) parent).isGroupExpanded(groupPosition)) {
                                   textName.setTextColor(Color.WHITE);
                               } else {
                                   textName.setTextColor(Color.LTGRAY);

                               }
                               //	textName.setTextColor(getResources().getColor(R.color.color_negro));
                               textName.setTypeface(Typeface.SANS_SERIF);
                           //    textCantidad.setTextColor(getResources().getColor(R.color.color_gris_claro));
                               textCantidad.setTypeface(Typeface.SANS_SERIF);
                               int cantidadActual = artefactsMap.get(temp).size();
                               int cantMax = currentDatacenter.getCantMaxArtefact(code);
                               textCantidad.setText(cantidadActual + "/" + cantMax);
                               if(cantidadActual < cantMax){
                                   textCantidad.setTextColor(Color.GRAY);
                               }else if(cantidadActual > cantMax){
                                   textCantidad.setTextColor(Color.RED);
                                   textName.setTextColor(Color.RED);
                               }else{
                                   textCantidad.setTextColor(Color.BLACK);
                                  // textName.setTextColor(Color.RED);
                               }
                               return v;
                           }
                       }
        );

     /*   if(newArtefactSaved){
            mGroupSelected = selectedArtefact.getCode() - 101;
   //         mGroupSelected = this.getExpandableListView().getCount() - 1;
            newArtefactSaved = false;
        }*/
        if (mGroupSelected != -1 && mGroupSelected < this.getExpandableListAdapter().getGroupCount()) {
            this.getExpandableListView().expandGroup(mGroupSelected);
            this.getExpandableListView().setSelectedGroup(mGroupSelected);
            if (mChildSelected != -1) {
                this.getExpandableListView().setSelectedChild(mGroupSelected, mChildSelected, true);
            }

        }


    }

    private Map<String, List<AbstractArtefactDto>> obtenerArtefactosOrganizados(MainActivity mainActivity, DataBaseManager mDBManager) {
            // TODO Auto-generated method stub
       //     List<AbstractArtefactDto> artefacts = obtenerPersonas(context, mDBManager);
            Iterator<AbstractArtefactDto> it = listArtefacts.iterator();
            AbstractArtefactDto arf;
            Map<String, List<AbstractArtefactDto>> organizadosPorCategoria = null;
            organizadosPorCategoria = new HashMap<>();
            List<AbstractArtefactDto> listTemp;
            String codigo;
            while(it.hasNext()){
                arf = it.next();

                // ORGANIZO POR CATEGORIA
                codigo = String.valueOf(arf.getCode());
                listTemp = new ArrayList<>();
                if (organizadosPorCategoria.containsKey(codigo)) {
                    listTemp = organizadosPorCategoria.get(codigo);
                }
                listTemp.add(arf);
                organizadosPorCategoria.put(codigo, listTemp);
            }

            return organizadosPorCategoria;

    }


    private void configureWidgets() {
     //   viewQRCam = (View) findViewById(R.id.viewQR);
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        this.getExpandableListView().setDividerHeight(7);
        contentFrame = (ViewGroup) findViewById(R.id.content_frame);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(me);

                    builder.setMessage(getResources().getString(R.string.seleccione_datacenter));
                    AlertDialog dialog = builder.create();
                    dialog.setCancelable(true);
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            loadDatacenterList();
                        }
                    });
                    dialog.show();
                }else{

                    storeArtefacts();
                }
            }
        });

        resetFormButton = (Button) findViewById(R.id.resetForm);
        resetFormButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               nuevoFormulario();
            }
        });

        turnOnQRCam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentForm == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(me);

// 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(getResources().getString(R.string.completar_datos)).setTitle(getResources().getString(R.string.atencion));

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
                    AlertDialog dialog = builder.create();
                    dialog.setCancelable(true);
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            if(currentDatacenter== null) {
                                loadDatacenterList();
                            }else{
                                openEntryForm();
                            }
                        }
                    });
                    dialog.show();
                }else{
                    startQRReader();
                }

            }
        });


        tvDatacenter = (TextView) findViewById(R.id.currentDatacenter);
        if(currentDatacenter != null){
            tvDatacenter.setVisibility(View.VISIBLE);
            tvDatacenter.setText("░ "+getResources().getString(R.string.datacenter)+": " + currentDatacenter.getName());
          //  onButton(true, loadDatacenterButton);
        }else if(currentForm != null && currentForm.getDatacenterName() != null){
            tvDatacenter.setVisibility(View.VISIBLE);
            tvDatacenter.setText("░ "+getResources().getString(R.string.datacenter)+": " + currentForm.getDatacenterName());
          //  onButton(true, loadDatacenterButton);
        }else{
            tvDatacenter.setVisibility(View.GONE);
            tvDatacenter.setText("");
         //   onButton(false, loadDatacenterButton);
        }
        tvInspector = (TextView) findViewById(R.id.currentInspector);
        if(currentInspector != null){
            tvInspector.setVisibility(View.VISIBLE);
            tvInspector.setText("░ "+getResources().getString(R.string.tecnico)+": " + currentInspector.getDescription());
        }
        tvForm =  (TextView) findViewById(R.id.currentForm);
        if(currentForm != null){
            tvForm.setVisibility(View.VISIBLE);
            tvForm.setText("░ " + currentForm.getNroForm());
            tvInspector.setVisibility(View.GONE);
            tvDatacenter.setVisibility(View.GONE);

           // onButton(true, storeDataButton);
            //storeDataButton.setTextColor(Color.BLACK);
        }else{
          //  onButton(false, storeDataButton);
            //storeDataButton.setTextColor(Color.GRAY);
        }



    }

    private void nuevoFormulario() {
        AlertDialog.Builder builder = new AlertDialog.Builder(me);
        builder.setMessage(getResources().getString(R.string.alerta_nuevo_form))
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



    private void deleteArtefact(AbstractArtefactDto arf) {
        if(currentForm != null && currentForm.getId()!= 0 && currentForm.getId() != -1) {
            //     ConstantsAdmin.deleteTableroTGBT((TableroTGBT) selectedArtefact, this);
            selectedArtefact = arf;
            new PrivateTaskDeleteTablero().execute();
        }
    }




    private void loadDatacenterList() {

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
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        });
        try {
            alertDialog.show();
        }catch (Exception exc){
            exc.printStackTrace();
        }

        listDatacentersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentDatacenter = listDatacentersAdapter.getItem(position);
                tvDatacenter.setVisibility(View.VISIBLE);
                tvDatacenter.setText("░ "+getResources().getString(R.string.datacenter)+": "  + currentDatacenter.getName());
           //     onButton(true, loadDatacenterButton);
                if(currentForm != null) {
                    //tvForm.setText(tvForm.getText() + "*");
                    storeArtefactsInRemoteDB();
                }
          //      initializeArtefactsCount();
                alertDialog.cancel();
            }
        });

    }


    private class PrivateTaskLoadDatacenters extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                loadDatacentersFromRemoteDB();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.load_datacenters), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            dialog.cancel();
            //        createAlertDialog("Se han registrado el Tablero Crac con éxito!", "Salut!");
          //  refreshItemListFromDB();
            //  finish();

        }
    }


    private void loadDatacentersFromRemoteDB(){
        Call< List<DataCenter> > call = null;
        Response<List<DataCenter>> response;
        List list = new ArrayList();
        try {
            call = datacenterService.getDatacenters();
            response = call.execute();
            for(DataCenter item : response.body()) {
                list.add(item);
            }
            allDatacenters = list;
            listDatacentersAdapter = new ArrayAdapter(me, R.layout.row_datacenter, R.id.textItem, allDatacenters);
            listDatacentersView.setAdapter(listDatacentersAdapter);
            if(currentDatacenter == null && currentForm != null){
                currentDatacenter = getDatacenterId(currentForm.getDatacenterId());
            }
                //  call = itemService.saveItem(item);
            }catch(Exception exc){
                exc.printStackTrace();
            }

    }

    private void loadDatacenterInListView() {
        if(allDatacenters == null){
            new PrivateTaskLoadDatacenters().execute();
        }else {
            listDatacentersAdapter = new ArrayAdapter(me, R.layout.row_datacenter, R.id.textItem, allDatacenters);
            listDatacentersView.setAdapter(listDatacentersAdapter);
        }
        if(currentDatacenter == null && currentForm != null){
            currentDatacenter = getDatacenterId(currentForm.getDatacenterId());
        }


    }

    private void initPopupViewControlsDatacenterList() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.datacenters_list_view, null);
        listDatacentersView = popupInputDialogView.findViewById(R.id.listDatacenters);

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
*//*
    private void refreshItemListFromLocalList() {
        listArtefactsAdapter.clear();
        if (listArtefacts != null){
            for (AbstractArtefactDto object : listArtefacts) {
                listArtefactsAdapter.insert(object, listArtefactsAdapter.getCount());

            }
        }

    }

*/

    private void refreshItemListFromDB() {
        ArrayList<AbstractArtefactDto> allItems = new ArrayList();
        ArrayList<AbstractArtefactDto> list = null;
        list = ConstantsAdmin.getTablerosTGBT(this);
   //     artefactsCount.setCantTableroTGBT(list.size());
        allItems.addAll(list);
        list = ConstantsAdmin.getTablerosAireChiller(this);
     //   artefactsCount.setCantTableroAireChiller(list.size());
        allItems.addAll(list);
        list = ConstantsAdmin.getTablerosCrac(this);
      //  artefactsCount.setCantTableroCrac(list.size());
        allItems.addAll(list);
        list = ConstantsAdmin.getTablerosInUps(this);
    //    artefactsCount.setCantTableroInUps(list.size());
        allItems.addAll(list);
        list = ConstantsAdmin.getLoadUps(this);
    //    artefactsCount.setCantLoadUps(list.size());
        allItems.addAll(list);
        list = ConstantsAdmin.getGruposElectrogeno(this);
        //    artefactsCount.setCantLoadUps(list.size());
        allItems.addAll(list);
        list = ConstantsAdmin.getAireCrac(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getAireChiller(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getIncendio(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getPresostato(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getAireAcond(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getTableroPDR(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getPresurizacionEscalera(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getEstractorAire(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getPresurizacionCanieria(this);

        allItems.addAll(list);
        listArtefacts = new ArrayList<>();
        listArtefacts.addAll(allItems);

        currentForm = ConstantsAdmin.getForm(this);
        if(currentDatacenter != null && currentForm == null){
            tvDatacenter.setVisibility(View.VISIBLE);
            tvDatacenter.setText("░ "+getResources().getString(R.string.datacenter)+": "  + currentDatacenter.getName());
            tvForm.setVisibility(View.GONE);
        }else{
            tvDatacenter.setVisibility(View.GONE);
            tvDatacenter.setText("");
        }


        if(currentForm != null){
//            if(currentForm.getDatacenterName()!= null && !currentForm.getDatacenterName().equals("")){
                tvForm.setText("░ " + currentForm.getNroForm());
                tvForm.setVisibility(View.VISIBLE);
                tvInspector.setVisibility(View.GONE);
                tvDatacenter.setVisibility(View.GONE);
                currentDatacenter = getDatacenterId(currentForm.getDatacenterId());
 //           }
        }else{
            tvForm.setVisibility(View.GONE);
            tvForm.setText("");
        }

        if((currentDatacenter != null && currentForm != null) ||(currentForm != null && currentForm.getDatacenterName()!= null)){
           // turnOnQRCam.setTextColor(Color.BLACK);
         //   onButton(true, turnOnQRCam);
            resetFormButton.setVisibility(View.VISIBLE);
        }else{
        //    onButton(false, turnOnQRCam);
            resetFormButton.setVisibility(View.GONE);

        }
        recargarLista();


    }
/*
    private void onButton(boolean on, Button btn){
        if(on){
            btn.setTextColor(Color.BLACK);
            btn.setBackgroundColor(Color.LTGRAY);
        }else{
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundColor(Color.DKGRAY);

        }
    }
*/
    private DataCenter getDatacenterId(int datacenterId) {
        DataCenter dc = null;
        if(allDatacenters == null){
            loadDatacenterInListView();

        }
        Iterator<DataCenter> it = allDatacenters.iterator();
        boolean ok = false;
        while(!ok && it.hasNext()){
            dc = it.next();
            ok = dc.getId()== datacenterId;

        }
        return dc;
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
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        pckwR = popupInputDialogView.findViewById(R.id.PCKWR);
        pckwS = popupInputDialogView.findViewById(R.id.PCKWS);
        pckwT = popupInputDialogView.findViewById(R.id.PCKWT);
        pcaR = popupInputDialogView.findViewById(R.id.PCAR);
        pcaS = popupInputDialogView.findViewById(R.id.PCAS);
        pcaT = popupInputDialogView.findViewById(R.id.PCAT);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            pckwR.setText(selectedArtefact.getKwr());
            pckwS.setText(selectedArtefact.getKws());
            pckwT.setText(selectedArtefact.getKwt());
            pcaR.setText(selectedArtefact.getPar());
            pcaS.setText(selectedArtefact.getPas());
            pcaT.setText(selectedArtefact.getPat());

        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
      //  buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }


    private void initPopupViewControlsForms()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.form_layout, null);
        descForm = popupInputDialogView.findViewById(R.id.formDescEntry);
        nameForm = popupInputDialogView.findViewById(R.id.formName);
        saveFormButton = popupInputDialogView.findViewById(R.id.saveFormButton);
        cancelFormButton = popupInputDialogView.findViewById(R.id.cancelFormButton);
        if(currentForm != null ){
            descForm.setText(currentForm.getDescription());
            nameForm.setText("░ " + currentForm.getNroForm());
        }else{
            nameForm.setText("░ " + getResources().getString(R.string.nuevo_form));
        }
    }

    private void initPopupViewControlsUPS()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.ups_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        pcaR = popupInputDialogView.findViewById(R.id.percentR);
        pcaS = popupInputDialogView.findViewById(R.id.percentS);
        pcaT = popupInputDialogView.findViewById(R.id.percentT);
        checkAlarma = popupInputDialogView.findViewById(R.id.checkAlarma);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }

            entryDescripcion.setText(selectedArtefact.getDescription());
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
      //  buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void initPopupViewControlsGrupoElectrogeno()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.grupoelectrogeno_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        percent_comb = popupInputDialogView.findViewById(R.id.percent_comb);
        temperatura = popupInputDialogView.findViewById(R.id.temperatura);
        checkAlarma = popupInputDialogView.findViewById(R.id.checkAlarma);
        checkAuto = popupInputDialogView.findViewById(R.id.checkAuto);
        checkNivelcomb75 = popupInputDialogView.findViewById(R.id.nivelcomb75);
        checkPrecalent = popupInputDialogView.findViewById(R.id.precalent);
        checkCargadorbat = popupInputDialogView.findViewById(R.id.cargadorbat);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }

            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getAlarma().equals("1")){
                checkAlarma.setChecked(true);
            }else{
                checkAlarma.setChecked(false);
            }
            if(selectedArtefact.getAuto().equals("1")){
                checkAuto.setChecked(true);
            }else{
                checkAuto.setChecked(false);
            }
            if(selectedArtefact.getNivelcomb75().equals("1")){
                checkNivelcomb75.setChecked(true);
            }else{
                checkNivelcomb75.setChecked(false);
            }
            if(selectedArtefact.getPrecalent().equals("1")){
                checkPrecalent.setChecked(true);
            }else{
                checkPrecalent.setChecked(false);
            }
            if(selectedArtefact.getCargadorbat().equals("1")){
                checkCargadorbat.setChecked(true);
            }else{
                checkCargadorbat.setChecked(false);
            }
            percent_comb.setText(selectedArtefact.getPercent_comb());
            temperatura.setText(selectedArtefact.getTemperatura());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
    //    buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }


    private void initPopupViewControlsAireCrac()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.airecrac_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        temperatura = popupInputDialogView.findViewById(R.id.temperatura);
        funcionaOk = popupInputDialogView.findViewById(R.id.funcionaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }

            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getFunciona_ok().equals("1")){
                funcionaOk.setChecked(true);
            }else{
                funcionaOk.setChecked(false);
            }
            temperatura.setText(selectedArtefact.getTemperatura());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }


    private void initPopupViewControlsAireChiller()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.airechiller_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        comp1Load = popupInputDialogView.findViewById(R.id.comp1load);
        comp2Load = popupInputDialogView.findViewById(R.id.comp2load);
        out = popupInputDialogView.findViewById(R.id.out);
        comp1Ok = popupInputDialogView.findViewById(R.id.comp1Ok);
        comp2Ok = popupInputDialogView.findViewById(R.id.comp2Ok);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }

            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getComp1Ok().equals("1")){
                comp1Ok.setChecked(true);
            }else{
                comp1Ok.setChecked(false);
            }
            if(selectedArtefact.getComp2Ok().equals("1")){
                comp2Ok.setChecked(true);
            }else{
                comp2Ok.setChecked(false);
            }
            comp1Load.setText(selectedArtefact.getComp1Load());
            comp2Load.setText(selectedArtefact.getComp2Load());
            out.setText(selectedArtefact.getAtr_out());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
      //  buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void initPopupViewControlsIncendio()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.incendio_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        presion = popupInputDialogView.findViewById(R.id.presion);
        energiaAOk = popupInputDialogView.findViewById(R.id.energiaAOk);
        energiaBOk = popupInputDialogView.findViewById(R.id.energiaBOk);
        funcionaOk = popupInputDialogView.findViewById(R.id.funcionaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(selectedArtefact != null){
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
               tableroNom.setText(selectedArtefact.getName());
            }
            if(selectedArtefact.getEnergiaAOk().equals("1")){
                energiaAOk.setChecked(true);
            }else{
                energiaAOk.setChecked(false);
            }
            if(selectedArtefact.getEnergiaBOk().equals("1")){
                energiaBOk.setChecked(true);
            }else{
                energiaBOk.setChecked(false);
            }
            if(selectedArtefact.getFunciona_ok().equals("1")){
                funcionaOk.setChecked(true);
            }else{
                funcionaOk.setChecked(false);
            }
            presion.setText(selectedArtefact.getPresion());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void initPopupViewControlsPresostato()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.presostato_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        airePresion = popupInputDialogView.findViewById(R.id.airePresion);
        aguaPresion = popupInputDialogView.findViewById(R.id.aguaPresion);
        aireOk = popupInputDialogView.findViewById(R.id.aireOk);
        aguaOk = popupInputDialogView.findViewById(R.id.aguaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getAireOk().equals("1")){
                aireOk.setChecked(true);
            }else{
                aireOk.setChecked(false);
            }
            if(selectedArtefact.getAguaOk().equals("1")){
                aguaOk.setChecked(true);
            }else{
                aguaOk.setChecked(false);
            }
            aguaPresion.setText(selectedArtefact.getAguaPresion());
            airePresion.setText(selectedArtefact.getAirePresion());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void initPopupViewControlsAireAcond()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.aireacond_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        temperatura = popupInputDialogView.findViewById(R.id.temperatura);
        funcionaOk = popupInputDialogView.findViewById(R.id.funcionaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getFunciona_ok().equals("1")){
                funcionaOk.setChecked(true);
            }else{
                funcionaOk.setChecked(false);
            }
            temperatura.setText(selectedArtefact.getTemperatura());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
   //     buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void initPopupViewControlsTableroPDR()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.tableropdr_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        pottotRA = popupInputDialogView.findViewById(R.id.pottotRA);
        pottotRB = popupInputDialogView.findViewById(R.id.pottotRB);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1){
                tableroNom.setText(selectedArtefact.getName());
            }
            pottotRA.setText(selectedArtefact.getPottotRA());
            pottotRB.setText(selectedArtefact.getPottotRB());
        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void initPopupViewControlsPresurizacionEscalera()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.presurizacionescalera_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        arranque = popupInputDialogView.findViewById(R.id.arranque);
        correas = popupInputDialogView.findViewById(R.id.correas);
        engrase = popupInputDialogView.findViewById(R.id.engrase);
        funcionamiento = popupInputDialogView.findViewById(R.id.funcionamiento);
        limpieza = popupInputDialogView.findViewById(R.id.limpieza);
        tiempo = popupInputDialogView.findViewById(R.id.tiempo);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }

            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getArranque().equals("1")){
                arranque.setChecked(true);
            }else{
                arranque.setChecked(false);
            }
            if(selectedArtefact.getCorreas().equals("1")){
                correas.setChecked(true);
            }else{
                correas.setChecked(false);
            }
            if(selectedArtefact.getEngrase().equals("1")){
                engrase.setChecked(true);
            }else{
                engrase.setChecked(false);
            }
            if(selectedArtefact.getFuncionamiento().equals("1")){
                funcionamiento.setChecked(true);
            }else{
                funcionamiento.setChecked(false);
            }
            if(selectedArtefact.getLimpieza().equals("1")){
                limpieza.setChecked(true);
            }else{
                limpieza.setChecked(false);
            }
            if(selectedArtefact.getTiemp().equals("1")){
                tiempo.setChecked(true);
            }else{
                tiempo.setChecked(false);
            }

        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }


    private void initPopupViewControlsEstractorAire()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.estractoraire_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        arranque = popupInputDialogView.findViewById(R.id.arranque);
        correas = popupInputDialogView.findViewById(R.id.correas);
        engrase = popupInputDialogView.findViewById(R.id.engrase);
        funcionamiento = popupInputDialogView.findViewById(R.id.funcionamiento);
        limpieza = popupInputDialogView.findViewById(R.id.limpieza);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getArranque().equals("1")){
                arranque.setChecked(true);
            }else{
                arranque.setChecked(false);
            }
            if(selectedArtefact.getCorreas().equals("1")){
                correas.setChecked(true);
            }else{
                correas.setChecked(false);
            }
            if(selectedArtefact.getEngrase().equals("1")){
                engrase.setChecked(true);
            }else{
                engrase.setChecked(false);
            }
            if(selectedArtefact.getFuncionamiento().equals("1")){
                funcionamiento.setChecked(true);
            }else{
                funcionamiento.setChecked(false);
            }
            if(selectedArtefact.getLimpieza().equals("1")){
                limpieza.setChecked(true);
            }else{
                limpieza.setChecked(false);
            }


        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
  //      buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }


    private void initPopupViewControlsPresurizacionCanieria()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.presurizacioncanieria_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        checkAlarma = popupInputDialogView.findViewById(R.id.checkAlarma);
        encendido = popupInputDialogView.findViewById(R.id.encendido);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(currentDatacenter.getCantMaxArtefact(idQr) > 1) {
                tableroNom.setText(selectedArtefact.getName());
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getAlarma().equals("1")){
                checkAlarma.setChecked(true);
            }else{
                checkAlarma.setChecked(false);
            }
            if(selectedArtefact.getEncendido().equals("1")){
                encendido.setChecked(true);
            }else{
                encendido.setChecked(false);
            }

        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
      //  buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void startQRReader() {
     /*   if(!cameraIsOn){
            cameraIsOn = true;
            mScannerView = new ZXingScannerView(this);
            mScannerView.setResultHandler(this);
            contentFrame.addView(mScannerView);
            mScannerView.startCamera();
            selectedArtefact = null;
        }else{
            cameraIsOn = false;
            mScannerView.stopCamera();
            contentFrame.removeAllViews();
        }

        */




          idQr = 101;
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
        boolean okCodigoInt = true;
        int idResult = -1;
        try {
            idResult = new Integer(newEntry);
        }catch (Exception exc){
            okCodigoInt = false;
        }
    //    this.getResults(newEntry);
        if(okCodigoInt){
            mScannerView.stopCamera();
            contentFrame.removeAllViews();
            cameraIsOn = false;
            idQr = idResult;
            this.openEntrySpecifyForm();
         /*

            finish();  //It's necessary to operate the buttons, after using setContentView(...) more than once in the same activity
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra(ConstantsAdmin.ID_QR, idResult);
            if(currentDatacenter != null){
                intent.putExtra(ConstantsAdmin.currentDatacenterConstant, currentDatacenter);
            }
            if(currentInspector != null){
                intent.putExtra(ConstantsAdmin.currentInspectorConstant, currentInspector);
            }
            startActivity(intent);*/
        }else{
            mScannerView.stopCamera();
            cameraIsOn = false;
            contentFrame.removeAllViews();
            idQr = -1;
            this.createAlertDialog(getResources().getString(R.string.qrInvalido), getResources().getString(R.string.atencion));

        }

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



    private void exportItems(){

        if(!artefactsMap.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.mensaje_select_exportar_excel)
                    .setCancelable(true)
                    .setPositiveButton(R.string.coma_separated, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            Long[] params = new Long[1];
                            params[0] = 1L;
                            dialog.cancel();
                            separadorExcel = ConstantsAdmin.COMA;
                            new ExportCSVEsteticoTask().execute(params);
                            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath());
                            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                                    .addCategory(Intent.CATEGORY_OPENABLE)
                                    .setDataAndType(uri, "text/csv")
                                    .putExtra(Intent.EXTRA_TITLE, ConstantsAdmin.fileEsteticoCSV);

                            startActivityForResult(intent, ConstantsAdmin.ACTIVITY_CHOOSE_FILE);
                        }
                    })
                    .setNegativeButton(R.string.puntocoma_separated, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Long[] params = new Long[1];
                            params[0] = 1L;
                            dialog.cancel();
                            separadorExcel = ConstantsAdmin.PUNTO_COMA;
                            new ExportCSVEsteticoTask().execute(params);

                            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath());
                            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                                    .addCategory(Intent.CATEGORY_OPENABLE)
                                    .setDataAndType(uri, "text/csv")
                                    .putExtra(Intent.EXTRA_TITLE, ConstantsAdmin.fileEsteticoCSV);

                            startActivityForResult(intent, ConstantsAdmin.ACTIVITY_CHOOSE_FILE);


                        }
                    });
            builder.show();
        }else{
            createAlertDialog(getResources().getString(R.string.mensaje_sin_artefactos), getResources().getString(R.string.atencion));
        }

    }

    private class ExportCSVEsteticoTask extends AsyncTask<Long, Integer, Integer>{
        ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                DataBaseManager mDBManager = DataBaseManager.getInstance(me);
                ConstantsAdmin.exportarCSVEstetico(me, separadorExcel,listArtefacts);


            } catch (Exception e) {
                ConstantsAdmin.mensaje = me.getString(R.string.error_exportar_csv) ;
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            //	dialog = ProgressDialog.show(me, "",me.getString(R.string.mensaje_exportando_contactos), false);
        }

        @Override
        protected void onPostExecute(Integer result) {
            try{
                dialog.cancel();
            }catch (Exception e) {
                // TODO: handle exception
            }
            //		ConstantsAdmin.mostrarMensajeDialog(me, ConstantsAdmin.mensaje);
            //	ConstantsAdmin.mensaje = null;

        }
    }


    private void askForWriteStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_WRITE_STORAGE);


            } else {//Ya tiene el permiso...
                this.exportItems();
            }
        } else {
            this.exportItems();
        }


    }





}
