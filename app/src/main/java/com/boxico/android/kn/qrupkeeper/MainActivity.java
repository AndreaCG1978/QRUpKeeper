package com.boxico.android.kn.qrupkeeper;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
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

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;


import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.AireAcond;
import com.boxico.android.kn.qrupkeeper.dtos.AireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.AireCrac;
import com.boxico.android.kn.qrupkeeper.dtos.ArtefactoValorTope;
import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.EstractorAire;
import com.boxico.android.kn.qrupkeeper.dtos.GrupoElectrogeno;
import com.boxico.android.kn.qrupkeeper.dtos.Incendio;
import com.boxico.android.kn.qrupkeeper.dtos.Incendio2;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.NombreGenerico;
import com.boxico.android.kn.qrupkeeper.dtos.Presostato;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionCanieria;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionEscalera;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroPDR;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;
import com.boxico.android.kn.qrupkeeper.util.ArtefactosValoresTopeService;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.boxico.android.kn.qrupkeeper.util.DatacenterService;
import com.boxico.android.kn.qrupkeeper.util.ExpandableListFragment;
import com.boxico.android.kn.qrupkeeper.util.FormService;
import com.boxico.android.kn.qrupkeeper.util.InputFilterMaximo;
import com.boxico.android.kn.qrupkeeper.util.InputFilterMinimo;
import com.boxico.android.kn.qrupkeeper.util.NombresGenericosService;
import com.boxico.android.kn.qrupkeeper.util.TableroService;

import com.boxico.android.kn.qrupkeeper.util.workers.DeleteTableroWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.LoadDatacentersWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.LoadValoresTopesWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.LoginWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveAireAcondWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveAireChillerWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveAireCrahWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveAllWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveEstractorAireWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveGrupoElectrogenoWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveIncendio2Worker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveIncendioWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SavePresostatoWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SavePresurizacionCanieriaWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SavePresurizacionEscaleraWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveTableroPDR2Worker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveTableroPDRWorker;
import com.boxico.android.kn.qrupkeeper.util.workers.SaveTableroWorker;
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

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.allDatacenters;
import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.currentDatacenter;
import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.currentForm;
import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.currentInspector;
import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.dataCenterID;

public class MainActivity extends ExpandableListFragment implements ZXingScannerView.ResultHandler{

     private ZXingScannerView mScannerView;

    private boolean cameraIsOn = false;
    private final int PERMISSIONS_REQUEST_ACCESS_CAMERA = 102;
    private final int PERMISSIONS_WRITE_STORAGE = 101;
    private int mGroupSelected = -1;
    private int mChildSelected = -1;
    private List<String> mySortedByElements = null;
    private static final String CLAVE = "CLAVE";
    private static final String NOMBRE = "NOMBRE";
    private LayoutInflater layoutInflater = null;
    private AlertDialog.Builder alertDialogBuilder = null;

    private Map<String, List<AbstractArtefactDto>> artefactsMap = null;
    List<NombreGenerico> nombresGenericos = null;


    private View popupInputDialogView = null;

    private Button buttonSaveData;
    private Button saveFormButton;
    private Button resetFormButton;
    private Button cancelFormButton;
    private ViewGroup contentFrame;

    private MainActivity me;

    private AbstractArtefactDto selectedArtefact;
 //   private TableroService tableroService = null;
//    private FormService formService = null;
//    private DatacenterService datacenterService = null;
//    private NombresGenericosService nombresGenericosService = null;
//    private ArtefactosValoresTopeService valoresTopesService = null;

    private Button buttonGenericName;
    private EditText tableroNom;
    private EditText pckwR;
    private EditText pckwT;
    private EditText pckwS;
    private EditText entryDescripcion;

    private EditText pcaR;
    private EditText pcaT;
    private EditText pcaS;
    private EditText descForm;
    private int idQr = -1;
   // private DataCenter currentDatacenter;
   // private Inspector currentInspector;
    private TextView tvDatacenter;
    private TextView tvInspector;
    private TextView tvForm;

    private CheckBox checkAlarma;


    private MenuItem menuItemNuevoForm;
    private MenuItem menuItemGenerateCSV;

  //  private DatacenterForm currentForm;
    private ListView listDatacentersView;
    private ArrayAdapter<DataCenter> listDatacentersAdapter;

    private ListView listGenericNamesView;
    private ArrayAdapter<String> listGenericNamesAdapter;

    private ArrayList<AbstractArtefactDto> listArtefacts;
  //  private List<DataCenter> allDatacenters = null;
    private EditText percent_comb;
    private EditText temperatura;
    private CheckBox checkAuto;
    private CheckBox checkPrecalent;
    private CheckBox checkCargadorbat;
    private CheckBox funcionaOk;
    private EditText comp1Load;
    private EditText comp2Load;
    private CheckBox comp1Ok;
    private CheckBox comp2Ok;
    private EditText out;
    private EditText pprim;
    private EditText psec;
    private EditText presion;
    private CheckBox energiaAOk;
    private CheckBox energiaBOk;
    private CheckBox fm200Ok;
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
    private int idQrSaved;
    private int idRemoteSaved;
    private int idQrDeleted;
    private String separadorExcel = null;
    private String selectedGenericName;
    private Drawable alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        me = this;
        idQr = -1;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            if(bundle.get(ConstantsAdmin.currentInspectorConstant) != null){
                currentInspector = (Inspector)bundle.get(ConstantsAdmin.currentInspectorConstant);
            }
        }
        layoutInflater = this.getLayoutInflater();
        this.initializeService();
        this.configureWidgets();
        this.initPopupViewControlsDatacenterList();
        this.initNombresGenericos();
        this.initValoresTopes();
        this.getCameraPermission();



        try {
           // this.refreshItemListFromDB();
            this.loadDatacenterInListView();
        }catch(Exception exc){
            String error;
            error = exc.getMessage() + "\n";
            if(exc.getCause() != null){
                error = error + exc.getCause().toString();
            }
            for(int i=0; i< exc.getStackTrace().length; i++){
                error = error +  exc.getStackTrace()[i].toString()+ "\n";
            }

            createAlertDialog(error, "");
        }


    }

    private void initNombresGenericos() {
        new PrivateTaskLoadNombresGenericos().execute();
    }

    private void initValoresTopes() {

      //  new PrivateTaskLoadValoresTopes().execute();
        Data inputData = new Data.Builder().build();


        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(LoadValoresTopesWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                           // ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, me);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                          //  idQrSaved = idQr;
                         //   idRemoteSaved = selectedArtefact.getIdRemoteDB();
                         //   refreshItemListFromDB();

                        }
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                          //  selectedArtefact = null;
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                         //   createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                        }
                    }
                });
        WorkManager.getInstance(this).enqueue(request);

    }


    private void habilitarDeshabilitarItemMenu(){
        if(menuItemGenerateCSV != null && menuItemNuevoForm != null){
            if(currentForm == null || currentForm.getId() == -1 ){
                menuItemNuevoForm.setEnabled(false);
                menuItemGenerateCSV.setEnabled(false);
            }else{
                menuItemNuevoForm.setEnabled(true);
                menuItemGenerateCSV.setEnabled(true);
            }

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
                break;
            case ConstantsAdmin.EJECUTAR_EDIT_FORM:
                this.openEntryForm();
                break;
            case ConstantsAdmin.EJECUTAR_SCAN:
                this.startQRReader();
                break;
            case ConstantsAdmin.EJECUTAR_NUEVO_FORM:
                this.nuevoFormulario();
                break;
            case ConstantsAdmin.EJECUTAR_GENERAR_CSV:
                this.askForWriteStoragePermission();
                break;
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

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(interceptor2).connectTimeout(100, TimeUnit.SECONDS).readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsAdmin.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        if(ConstantsAdmin.tableroService == null){
            ConstantsAdmin.tableroService = retrofit.create(TableroService.class);
        }
        if(ConstantsAdmin.datacenterService == null) {
            ConstantsAdmin.datacenterService = retrofit.create(DatacenterService.class);
        }
        if(ConstantsAdmin.formService == null){
            ConstantsAdmin.formService = retrofit.create(FormService.class);
        }
        if(ConstantsAdmin.nombresGenericosService == null) {
            ConstantsAdmin.nombresGenericosService = retrofit.create(NombresGenericosService.class);
        }
        if(ConstantsAdmin.valoresTopesService == null){
            ConstantsAdmin.valoresTopesService = retrofit.create(ArtefactosValoresTopeService.class);
        }

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
         //   mCameraPermissionGranted = true;

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
     //   mCameraPermissionGranted = false;

        if (requestCode == PERMISSIONS_REQUEST_ACCESS_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        //        mCameraPermissionGranted = true;
            }
        }
        if (requestCode == PERMISSIONS_WRITE_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.exportItems();
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

    private void openArtefactView(){
        boolean codigoValido = true;
        int cantArtefactosMax = currentDatacenter.getCantMaxArtefact(idQr);
        AlertDialog.Builder alertDialogBuilder = null;
        if(cantArtefactosMax > 0) {
            alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            switch (idQr) {
                case 101:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROTGBT);
                    initPopupTablero();
                    break;
                case 102:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROAIRECHILLER);
                    initPopupTablero();
                    break;
                case 103:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROCRAC);
                    initPopupTablero();
                    break;
                case 104:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROINUPS);
                    initPopupTablero();
                    break;
                case 105:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_LOADUPS);
                    initPopupUPS();
                    break;
                case 106:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_GRUPOELECTROGENO);
                    initPopupGrupoElectrogeno();
                    break;
                case 107:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_AIRECRAC);
                    initPopupAireCrac();
                    break;
                case 108:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_AIRECHILLER);
                    initPopupAireChiller();
                    break;
                case 109:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_INCENDIO);
                    initPopupIncendio();
                    break;
                case 110:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_PRESOSTATO);
                    initPopupPresostato();
                    break;
                case 111:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_AIREACONDICIONADO);
                    initPopupAireAcond();
                    break;
                case 112:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROPDR);
                    initPopupTableroPDR();
                    break;
                case 113:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_PRESURIZACIONESCALERA);
                    initPopupPresurizacionEscalera();
                    break;
                case 114:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_ESTRACTORAIRE);
                    initPopupEstractorAire();
                    break;
                case 115:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_PRESURIZACIONCANIERIA);
                    initPopupPresurizacionCanieria();
                    break;
                case 116:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_INCENDIO2);
                    initPopupIncendio2();
                    break;
                case 117:
                    alertDialogBuilder.setTitle(ConstantsAdmin.TITLE_TABLEROPDR2);
                    initPopupTableroPDR2();
                    break;
                default:
                    codigoValido = false;
                    break;
            }
        }else{
            codigoValido = false;
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
                            case 116:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new Incendio2();
                                }
                                loadInfoIncendio2();
                                saveIncendio2((Incendio2)selectedArtefact);
                                break;
                            case 117:
                                if(selectedArtefact == null) {
                                    selectedArtefact = new TableroPDR();
                                }
                                loadInfoTableroPDR2();
                                saveTableroPDR2((TableroPDR) selectedArtefact);
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



    }

    private boolean checkCompleteArtefact() {
        boolean result = true;
        if (currentDatacenter.getCantMaxArtefact(idQr) > 1){
            result = !tableroNom.getText().toString().equals("") || selectedGenericName != null;
        }
        return result;
    }

    private void createAlertDialog(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        if(title != null){
            builder.setMessage(message).setTitle(title);
        }

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();

    }

    private void loadInfoTablero(){
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                selectedArtefact.setName(tableroNom.getText().toString());
            }else{
                selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            selectedArtefact.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
        }

        selectedArtefact.setKwr(pckwR.getText().toString());
        selectedArtefact.setKws(pckwS.getText().toString());
        selectedArtefact.setKwt(pckwT.getText().toString());
        selectedArtefact.setPar(pcaR.getText().toString());
        selectedArtefact.setPas(pcaS.getText().toString());
        selectedArtefact.setPat(pcaT.getText().toString());
        if(entryDescripcion.getText() != null ) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoGrupoElectrogeno(){
        GrupoElectrogeno grupo = (GrupoElectrogeno) selectedArtefact;
        //grupo.setName(tableroNom.getText().toString());
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                selectedArtefact.setName(tableroNom.getText().toString());
            }else{
                selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            selectedArtefact.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
        }
        grupo.setTemperatura(temperatura.getText().toString());
        grupo.setPercent_comb(percent_comb.getText().toString());
        if(entryDescripcion.getText() != null ) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                selectedArtefact.setName(tableroNom.getText().toString());
            }else{
                selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            selectedArtefact.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
        }
        item.setTemperatura(temperatura.getText().toString());
        if(entryDescripcion.getText() != null ) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                selectedArtefact.setName(tableroNom.getText().toString());
            }else{
                selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            selectedArtefact.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
        }
        item.setComp1Load(comp1Load.getText().toString());
        item.setComp2Load(comp2Load.getText().toString());
        item.setAtr_out(out.getText().toString());
        item.setPprim(pprim.getText().toString());
        item.setPsec(psec.getText().toString());
        if(entryDescripcion.getText() != null) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        item.setPresion(presion.getText().toString());
        if(entryDescripcion.getText() != null) {
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

    private void loadInfoIncendio2(){
        Incendio2 item = (Incendio2) selectedArtefact;
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }

        if(entryDescripcion.getText() != null) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        if(energiaAOk.isChecked()){
            item.setEnergiaAOk("1");
        }else{
            item.setEnergiaAOk("0");
        }
        if(fm200Ok.isChecked()){
            item.setFm200Ok("1");
        }else{
            item.setFm200Ok("0");
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        item.setAirePresion(airePresion.getText().toString());
        item.setAguaPresion(aguaPresion.getText().toString());
        if(entryDescripcion.getText() != null ) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        item.setTemperatura(temperatura.getText().toString());
        if(entryDescripcion.getText() != null ) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        item.setPottotRA(pottotRA.getText().toString());
        item.setPottotRB(pottotRB.getText().toString());
        if(entryDescripcion.getText() != null ) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoTableroPDR2(){
        TableroPDR item = (TableroPDR) selectedArtefact;
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        item.setPottotRA(pottotRA.getText().toString());
        if(entryDescripcion.getText() != null ) {
            selectedArtefact.setDescription(entryDescripcion.getText().toString());
        }
        selectedArtefact = item;
        selectedArtefact.setCode(idQr);
    }

    private void loadInfoPresurizacionEscalera(){
        PresurizacionEscalera item = (PresurizacionEscalera) selectedArtefact;
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        if(entryDescripcion.getText() != null ) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        if(entryDescripcion.getText() != null ) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                item.setName(tableroNom.getText().toString());
            }else{
                item.setName(item.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            item.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            item.setName(item.getClass().getSimpleName());
        }
        if(entryDescripcion.getText() != null ) {
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        if(!tieneNombreGenerico){
            if(tableroNom.getText() != null && !tableroNom.getText().toString().equals("")) {
                selectedArtefact.setName(tableroNom.getText().toString());
            }else{
                selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
            }
        }else if(selectedGenericName!= null){
            selectedArtefact.setName(selectedGenericName);
            selectedGenericName = null;
        }else{
            selectedArtefact.setName(selectedArtefact.getClass().getSimpleName());
        }

        selectedArtefact.setPercent_r(pcaR.getText().toString());
        selectedArtefact.setPercent_s(pcaS.getText().toString());
        selectedArtefact.setPercent_t(pcaT.getText().toString());
        if(entryDescripcion.getText() != null ) {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        Date fechaActual = new Date(System.currentTimeMillis());
        String date = simpleDateFormat.format(fechaActual);
        currentForm.setNroForm(currentInspector.getDescription() +" " + getResources().getString(R.string.stringIn)+ " " + ConstantsAdmin.ENTER + currentDatacenter.getName() + " (" + date + ")");
        currentForm.setFecha(fechaActualCompleta.toString());

    }


    private class PrivateTaskSaveAll extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                exito = saveAllInRemoteBD();

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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }
/*
    private class PrivateTaskDeleteTablero extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                exito = deleteTableroInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.deleteTablero(selectedArtefact, me);
                    idQrDeleted = selectedArtefact.getCode();
                }

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
            if(exito) {
                refreshItemListFromDB();
            }else{
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            //  finish();

        }
    }
*/
    /*
    private class PrivateTaskSaveLoadUps extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveTableroInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createLoadUps((LoadUPS) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;


                }

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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }
*/
/*
    private class PrivateTaskSaveGrupoElectrogeno extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveGrupoElectrogenoInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createGrupoElectrogeno((GrupoElectrogeno) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*
    private class PrivateTaskSaveAireCrac extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveAireCracInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createAireCrac((AireCrac) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }
*/
/*

    private class PrivateTaskSaveAireChiller extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveAireChillerInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createAireChiller((AireChiller) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*

    private class PrivateTaskSaveIncendio extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveIncendioInRemoteDB(selectedArtefact);
                if(exito){
                    ConstantsAdmin.createIncendio((Incendio) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }

*/

/*
    private class PrivateTaskSaveIncendio2 extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveIncendio2InRemoteDB(selectedArtefact);
                if(exito){
                    ConstantsAdmin.createIncendio2((Incendio2) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*

    private class PrivateTaskSavePresostato extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = savePresostatoInRemoteDB(selectedArtefact);
                if(exito){
                    ConstantsAdmin.createPresostato((Presostato) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }

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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*

    private class PrivateTaskSaveAireAcond extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveAireAcondInRemoteDB(selectedArtefact);
                if(exito){
                    ConstantsAdmin.createAireAcond((AireAcond) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }


 */


/*
    private class PrivateTaskSaveTableroPDR extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveTableroPDRInRemoteDB(selectedArtefact);
                if(exito){
                    ConstantsAdmin.createTableroPDR((TableroPDR) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*


    private class PrivateTaskSaveTableroPDR2 extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveTableroPDR2InRemoteDB(selectedArtefact);
                if(exito){
                    ConstantsAdmin.createTableroPDR2((TableroPDR) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*

    private class PrivateTaskSavePresurizacionEscalera extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = savePresurizacionEscaleraInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createPresurizacionEscalera((PresurizacionEscalera) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }
*/

/*
    private class PrivateTaskSaveEstractorAire extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveEstractorAireInRemoteDB(selectedArtefact);
                if(exito){
                    ConstantsAdmin.createEstractorAire((EstractorAire) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*
    private class PrivateTaskSavePresurizacionCanieria extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = savePresurizacionCanieriaInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createPresurizacionCanieria((PresurizacionCanieria) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }
*/


/*
    private class PrivateTaskSaveTableroTGBT extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveTableroInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }

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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }*/

/*

    private class PrivateTaskSaveTableroAireChiller extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveTableroInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }

*/

/*
    private class PrivateTaskSaveTableroCrac extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveTableroInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createTableroCrac((TableroCrac) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }
*/

/*
    private class PrivateTaskSaveTableroInUps extends AsyncTask<Long, Integer, Integer> {
        ProgressDialog dialog = null;
        boolean exito = false;
        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                selectedArtefact.setIdForm(currentForm.getId());
                exito = saveTableroInRemoteDB(selectedArtefact);
                if(exito) {
                    ConstantsAdmin.createTableroInUps((TableroInUps) selectedArtefact, me);
                    idQrSaved = idQr;
                    idRemoteSaved = selectedArtefact.getIdRemoteDB();
                }else{
                    selectedArtefact = null;
                }
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
            if(!exito) {
                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
            }
            refreshItemListFromDB();
            //  finish();

        }
    }
*/

    private boolean saveAllInRemoteBD(){
        // SALVO EL FORMULARIO
        Call<ResponseBody> call;
        boolean exito = false;
        Call<DatacenterForm> callInsert;
        if(currentForm.getId() != -1 && currentForm.getId() != 0){// ES UN FORMULARIO EXISTENTE
            try {
                dataCenterID = currentForm.getDatacenterId();
                if(currentDatacenter != null){
                    dataCenterID = currentDatacenter.getId();
                    //currentForm.setDatacenterName(ConstantsAdmin.currentDatacenter.getName());
                   // currentForm.setDatacenterId(dataCenterID);s
                    loadInfoForm(false);
                }
                call = ConstantsAdmin.formService.updateForm(currentForm.getId(), currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), dataCenterID, currentForm.getFecha(), ConstantsAdmin.tokenIplan);
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
                //  call = itemService.saveItem(item);
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            //call = null;
            try {
                callInsert = ConstantsAdmin.formService.saveForm(currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), currentDatacenter.getId(), currentForm.getFecha(), ConstantsAdmin.tokenIplan);
                //  call = itemService.saveItem(item);
                Response<DatacenterForm> resp;
                resp = callInsert.execute();
                if(resp != null && resp.body()!= null){
                    DatacenterForm df = resp.body();

                    if(currentForm == null){
                        currentForm = new DatacenterForm();
                    }
                    currentForm.setId(df.getId());
                    exito = true;

                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }
        if(exito) {
            ConstantsAdmin.deleteForm(currentForm, this);
            ConstantsAdmin.createForm(currentForm, this);
        }
        return exito;
    }

    private void saveTableroAireChiller(TableroAireChiller t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
    //        new PrivateTaskSaveTableroAireChiller().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveTableroWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);




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
        //    new PrivateTaskSaveTableroCrac().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveTableroWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                ConstantsAdmin.createTableroCrac((TableroCrac) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);




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
        //    new PrivateTaskSaveTableroInUps().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveTableroWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                ConstantsAdmin.createTableroInUps((TableroInUps) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);




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
       //   new PrivateTaskSaveTableroTGBT().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveTableroWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);



          //  boolean exito = saveTableroInRemoteDB(selectedArtefact);







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
            //new PrivateTaskSaveLoadUps().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveTableroWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                ConstantsAdmin.createLoadUps((LoadUPS) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);



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
           // new PrivateTaskSaveGrupoElectrogeno().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveGrupoElectrogenoWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                ConstantsAdmin.createGrupoElectrogeno((GrupoElectrogeno) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }

    private void saveAireCrac(AireCrac t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
        //    new PrivateTaskSaveAireCrac().execute();

            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveAireCrahWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                             //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createAireCrac((AireCrac) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);


        }
    }


    private void saveAireChiller(AireChiller t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
          //  new PrivateTaskSaveAireChiller().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveAireChillerWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createAireChiller((AireChiller) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }


    private void saveIncendio(Incendio t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
        //    new PrivateTaskSaveIncendio().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveIncendioWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createIncendio((Incendio) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);
        }
    }

    private void saveIncendio2(Incendio2 t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
            //new PrivateTaskSaveIncendio2().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveIncendio2Worker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createIncendio2((Incendio2) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }


    private void savePresostato(Presostato t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
//            new PrivateTaskSavePresostato().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SavePresostatoWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createPresostato((Presostato) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }


    private void saveAireAcond(AireAcond t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
//            new PrivateTaskSaveAireAcond().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveAireAcondWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createAireAcond((AireAcond) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);


        }
    }

    private void saveTableroPDR(TableroPDR t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
//            new PrivateTaskSaveTableroPDR().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveTableroPDRWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createTableroPDR((TableroPDR) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }

    private void saveTableroPDR2(TableroPDR t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
       //     new PrivateTaskSaveTableroPDR2().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveTableroPDR2Worker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createTableroPDR2((TableroPDR) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }


    private void savePresurizacionEscalera(PresurizacionEscalera t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
          //  new PrivateTaskSavePresurizacionEscalera().execute();

            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SavePresurizacionEscaleraWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createPresurizacionEscalera((PresurizacionEscalera) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }

    private void saveEstractorAire(EstractorAire t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
       //     new PrivateTaskSaveEstractorAire().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveEstractorAireWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createEstractorAire((EstractorAire) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);
        }
    }

    private void savePresurizacionCanieria(PresurizacionCanieria t) {
        if(currentForm != null && currentForm.getId() != -1 && currentForm.getId()!= 0){//YA ESTA REGISTRADO EL FORMULARIO
            selectedArtefact = t;
   //         new PrivateTaskSavePresurizacionCanieria().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SavePresurizacionCanieriaWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                //   ConstantsAdmin.createTableroTGBT((TableroTGBT) selectedArtefact, me);
                                ConstantsAdmin.createPresurizacionCanieria((PresurizacionCanieria) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                idQrSaved = idQr;
                                idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }
    }

/*
    private boolean deleteTableroInRemoteDB(AbstractArtefactDto t) {
        boolean exito = false;
        Call<ResponseBody>  call;
        Response<ResponseBody> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                call = ConstantsAdmin.tableroService.deleteTablero(t.getIdRemoteDB(),t.getIdRemoteDB(), String.valueOf(t.getCode()), ConstantsAdmin.tokenIplan);
                resp = call.execute();
                if(resp != null && resp.body() != null){
                    exito = true;
                }

            }catch(Exception exc){
                exc.printStackTrace();
            }

        }
        return exito;

    }
*/

/*
    private boolean saveGrupoElectrogenoInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call = null;
        boolean exito = false;
        Call<AbstractArtefactDto>  callInsert = null;
        Response<AbstractArtefactDto> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                call = ConstantsAdmin.tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), ConstantsAdmin.tokenIplan);
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = ConstantsAdmin.tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;

                }
            }catch(Exception exc){
                exc.printStackTrace();
            }


        }
        return exito;

    }*/
/*
    private boolean saveAireChillerInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        Response<ResponseBody> respuesta;
        boolean exito = false;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateAireChiller(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getComp1Ok(),t.getComp1Load(), t.getComp2Ok(), t.getComp2Load(), t.getAtr_out(), t.getPprim(), t.getPsec(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.saveAireChiller(t.getName(), t.getCode(), t.getDescription(), t.getComp1Ok(), t.getComp1Load(), t.getComp2Ok(), t.getComp2Load(), t.getAtr_out(), t.getPprim(), t.getPsec(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;

    }
*/

/*
    private boolean savePresostatoInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        Response<AbstractArtefactDto> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updatePresostato(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getAguaOk(), t.getAireOk(), t.getAguaPresion(), t.getAirePresion(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body()!= null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.savePresostato(t.getName(), t.getCode(), t.getDescription(), t.getAguaOk(), t.getAireOk(), t.getAguaPresion(), t.getAirePresion(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }
*/

/*

    private boolean saveAireAcondInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateAireAcond(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.saveAireAcond(t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }*/

/*


    private boolean saveTableroPDRInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateTableroPDR(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPottotRA(), t.getPottotRB(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.saveTableroPDR(t.getName(), t.getCode(), t.getDescription(), t.getPottotRA(), t.getPottotRB(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }*/

/*

    private boolean saveTableroPDR2InRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateTableroPDR2(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPottotRA(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{
            try {
                callInsert = ConstantsAdmin.tableroService.saveTableroPDR2(t.getName(), t.getCode(), t.getDescription(), t.getPottotRA(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }
*/

/*
    private boolean saveIncendioInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateIncendio(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getEnergiaAOk(), t.getEnergiaBOk(), t.getFunciona_ok(), t.getPresion(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.saveIncendio(t.getName(), t.getCode(), t.getDescription(), t.getEnergiaAOk(), t.getEnergiaBOk(), t.getFunciona_ok(), t.getPresion(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }*/


/*

    private boolean saveIncendio2InRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateIncendio2(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getEnergiaAOk(), t.getFm200Ok(), t.getFunciona_ok(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.saveIncendio2(t.getName(), t.getCode(), t.getDescription(), t.getEnergiaAOk(), t.getFm200Ok(), t.getFunciona_ok(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }
*/

/*


    private boolean savePresurizacionEscaleraInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updatePresurizacionEscalera(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza(), t.getTiemp(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.savePresurizacionEscalera(t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza(), t.getTiemp(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;

                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }
*/

/*

    private boolean saveEstractorAireInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        Response<AbstractArtefactDto> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateEstractorAire(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.saveEstractorAire(t.getName(), t.getCode(), t.getDescription(), t.getArranque(), t.getCorreas(), t.getEngrase(), t.getFuncionamiento(), t.getLimpieza(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;

                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }
*/

/*

    private boolean savePresurizacionCanieriaInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        boolean exito = false;
        Response<ResponseBody> respuesta;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updatePresurizacionCanieria(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getAlarma(), t.getEncendido(), ConstantsAdmin.tokenIplan);
                respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }
                
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                //call = tableroService.saveGrupoElectrogeno(t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma(), t.getIdForm());
                callInsert = ConstantsAdmin.tableroService.savePresurizacionCanieria(t.getName(), t.getCode(), t.getDescription(), t.getAlarma(), t.getEncendido(), currentForm.getId(), ConstantsAdmin.tokenIplan);

                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;

                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;
    }
*/

/*

    private boolean saveAireCracInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        boolean exito = false;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
      //          call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateAireCrac(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), ConstantsAdmin.tokenIplan);
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }

            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = ConstantsAdmin.tableroService.saveAireCrac(t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;

    }*/

/*

    private boolean saveTableroInRemoteDB(AbstractArtefactDto t) {
        Call<ResponseBody>  call;
        boolean exito = false;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                switch (t.getCode()){
                    case 105:
                        call = ConstantsAdmin.tableroService.updateLoadUps(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPar(), t.getPas(), t.getPat(), t.getAlarma(), ConstantsAdmin.tokenIplan);
                        break;
                    default:
                        call = ConstantsAdmin.tableroService.updateTablero(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat(), ConstantsAdmin.tokenIplan);
                        break;
                }
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }

            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = ConstantsAdmin.tableroService.saveTablero(t.getName(), t.getCode(), t.getDescription(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat(), currentForm.getId(), t.getAlarma(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return exito;

    }*/
/*
    private void saveTableroAireChillerInLocalDB(){
        ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, this);
    }

    private void saveTableroCracInLocalDB(){
        ConstantsAdmin.createTableroCrac((TableroCrac)selectedArtefact, this);
    }



    private void saveTableroInUPSInLocalDB(){
        ConstantsAdmin.createTableroInUps((TableroInUps)selectedArtefact, this);
    }
*/
    private void resetAll(){
        listArtefacts = new ArrayList<>();
        currentForm = null;
        currentDatacenter = null;
        tvDatacenter.setVisibility(View.VISIBLE);
        tvInspector.setVisibility(View.VISIBLE);
        ConstantsAdmin.deleteAll(this);
        refreshItemListFromDB();
    }
/*
    private void saveLoadUPSInLocalDB(){
        ConstantsAdmin.createLoadUps((LoadUPS)selectedArtefact, this);
    }
*/

    private void recargarLista() {
       // DataBaseManager mDBManager = DataBaseManager.getInstance(this);
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
        artefactsMap = obtenerArtefactosOrganizados();

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
        int indexChild;
        for (String key : mySortedByElements) {
            listaArfs = artefactsMap.get(key);
            curGroupMap = new HashMap<>();
            groupData.add(curGroupMap);
            curGroupMap.put(CLAVE, key);
            indexChild = 0;
            if(idQrDeleted == Integer.parseInt(key) && !listaArfs.isEmpty()){
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
                                       openArtefactView();
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
                               int code = Integer.parseInt(labelCode);
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

    private Map<String, List<AbstractArtefactDto>> obtenerArtefactosOrganizados() {
            // TODO Auto-generated method stub
       //     List<AbstractArtefactDto> artefacts = obtenerPersonas(context, mDBManager);
            Iterator<AbstractArtefactDto> it = listArtefacts.iterator();
            AbstractArtefactDto arf;
            Map<String, List<AbstractArtefactDto>> organizadosPorCategoria;
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
        alerta = ContextCompat.getDrawable(this, R.drawable.custom_alert);
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        this.getExpandableListView().setDividerHeight(7);
        contentFrame = findViewById(R.id.content_frame);
        Button turnOnQRCam = findViewById(R.id.TurnOnQRCam);
        Button reportButton = findViewById(R.id.GenerateReport);
        Button loadDatacenterButton = findViewById(R.id.loadDatacenter);
      //  currentLatLon = (TextView) findViewById(R.id.currentLatLon);

    //    turnOnQRCam.setText("Seleccione un Datacenter");

        loadDatacenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatacenterList();
            }
        });
        Button storeDataButton = findViewById(R.id.storeData);
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

        resetFormButton = findViewById(R.id.resetForm);
        resetFormButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               nuevoFormulario();
            }
        });

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForWriteStoragePermission();
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


        tvDatacenter = findViewById(R.id.currentDatacenter);
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
        tvInspector = findViewById(R.id.currentInspector);
        if(currentInspector != null){
            tvInspector.setVisibility(View.VISIBLE);
            tvInspector.setText("░ "+getResources().getString(R.string.tecnico)+": " + currentInspector.getDescription());
        }
        tvForm = findViewById(R.id.currentForm);
        if(currentForm != null){
            tvForm.setVisibility(View.VISIBLE);
            tvForm.setText("░ " + currentForm.getNroForm());
            tvInspector.setVisibility(View.GONE);
            tvDatacenter.setVisibility(View.GONE);

           // onButton(true, storeDataButton);
            //storeDataButton.setTextColor(Color.BLACK);
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
    //    new PrivateTaskSaveAll().execute();

        if(currentForm.getId() != -1 && currentForm.getId() != 0){// ES UN FORMULARIO EXISTENTE
            ConstantsAdmin.dataCenterID = currentForm.getDatacenterId();
            if(ConstantsAdmin.currentDatacenter != null){
                dataCenterID = ConstantsAdmin.currentDatacenter.getId();
                loadInfoForm(false);
            }
        }
        Data inputData = new Data.Builder().build();


        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SaveAllWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            ConstantsAdmin.deleteForm(currentForm, me);
                            ConstantsAdmin.createForm(currentForm, me);
                        }
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                            //selectedArtefact = null;
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                        }
                    }
                });
        WorkManager.getInstance(this).enqueue(request);


    }



    private void deleteArtefact(AbstractArtefactDto arf) {
        if(currentForm != null && currentForm.getId()!= 0 && currentForm.getId() != -1) {
            //     ConstantsAdmin.deleteTableroTGBT((TableroTGBT) selectedArtefact, this);
            selectedArtefact = arf;
    //        new PrivateTaskDeleteTablero().execute();
            selectedArtefact.setIdForm(currentForm.getId());

            ConstantsAdmin.currentForm = currentForm;
            ConstantsAdmin.selectedArtefact = selectedArtefact;
            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(DeleteTableroWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                ConstantsAdmin.deleteTablero(selectedArtefact, me);
                                idQrDeleted = selectedArtefact.getCode();
                                refreshItemListFromDB();




                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                //selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);
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
/*
    private class PrivateTaskLoadValoresTopes extends AsyncTask<Long, Integer, Integer> {
        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                loadValoresTopesFromRemoteDB();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        @Override
        protected void onPostExecute(Integer result) {
        }
    }

*/


    private class PrivateTaskLoadNombresGenericos extends AsyncTask<Long, Integer, Integer> {
        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                loadNombresGenericosFromRemoteDB();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        @Override
        protected void onPostExecute(Integer result) {
        }
    }

/*

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
            refreshItemListFromDB();
            //        createAlertDialog("Se han registrado el Tablero Crac con éxito!", "Salut!");
          //  refreshItemListFromDB();
            //  finish();

        }
    }

*/
    private void loadNombresGenericosFromRemoteDB(){
        Call< List<NombreGenerico> > call;
        Response<List<NombreGenerico>> response;
        try {
            call = ConstantsAdmin.nombresGenericosService.getNombresGenericos(ConstantsAdmin.tokenIplan);
            response = call.execute();
            nombresGenericos = new ArrayList<NombreGenerico>(response.body());
        }catch(Exception exc){
            exc.printStackTrace();
        }

    }
/*
    private void loadValoresTopesFromRemoteDB(){
        Call< List<ArtefactoValorTope> > call;
        Response<List<ArtefactoValorTope>> response;
        try {
            call = ConstantsAdmin.valoresTopesService.getValoresTopes(ConstantsAdmin.tokenIplan);
            response = call.execute();
            ConstantsAdmin.valoresTopes = new ArrayList<ArtefactoValorTope>(response.body());
        }catch(Exception exc){
            exc.printStackTrace();
        }

    }

*//*
    private void loadDatacentersFromRemoteDB(){
        Call< List<DataCenter> > call;
        Response<List<DataCenter>> response;
        DataCenter dc = null;
        try {
            call = ConstantsAdmin.datacenterService.getDatacenters(ConstantsAdmin.tokenIplan);
            response = call.execute();
            allDatacenters = new ArrayList<>(response.body());
            listDatacentersAdapter = new ArrayAdapter(me, R.layout.row_datacenter, R.id.textItem, allDatacenters);
            listDatacentersView.setAdapter(listDatacentersAdapter);
            if(currentDatacenter == null && currentForm != null){
                Iterator<DataCenter> it = allDatacenters.iterator();
                boolean ok = false;
                while(!ok && it.hasNext()){
                    dc = it.next();
                    ok = dc.getId()== currentForm.getDatacenterId();
                }
                if(ok){
                    currentDatacenter = dc;
                }

            }
                //  call = itemService.saveItem(item);
            }catch(Exception exc){
                exc.printStackTrace();
            }

    }
*/
    private void loadDatacenterInListView() {
        if(allDatacenters == null){
         //   new PrivateTaskLoadDatacenters().execute();

            Data inputData = new Data.Builder().build();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(LoadDatacentersWorker.class)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {

                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {

                                DataCenter dc = null;
                                // ConstantsAdmin.createTableroAireChiller((TableroAireChiller) selectedArtefact, me);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                listDatacentersAdapter = new ArrayAdapter(me, R.layout.row_datacenter, R.id.textItem, allDatacenters);
                                listDatacentersView.setAdapter(listDatacentersAdapter);
                                if(currentDatacenter == null && currentForm != null){
                                    Iterator<DataCenter> it = allDatacenters.iterator();
                                    boolean ok = false;
                                    while(!ok && it.hasNext()){
                                        dc = it.next();
                                        ok = dc.getId()== currentForm.getDatacenterId();
                                    }
                                    if(ok){
                                        currentDatacenter = dc;
                                    }

                                }
                                //  idQrSaved = idQr;
                                //   idRemoteSaved = selectedArtefact.getIdRemoteDB();
                                refreshItemListFromDB();

                            }
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.FAILED) {
                                //  selectedArtefact = null;
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                //   createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion));
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(request);

        }else {
            listDatacentersAdapter = new ArrayAdapter(me, R.layout.row_datacenter, R.id.textItem, allDatacenters);
            listDatacentersView.setAdapter(listDatacentersAdapter);
        }
        if(currentDatacenter == null && currentForm != null){
            DataCenter dc = null;
            Iterator<DataCenter> it = allDatacenters.iterator();
            boolean ok = false;
            while(!ok && it.hasNext()){
                dc = it.next();
                ok = dc.getId() ==currentForm.getDatacenterId();
            }
            if(ok) {
                currentDatacenter = dc;
            }
        }


    }

    private void initPopupViewControlsDatacenterList() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.datacenters_list_view, null);
        listDatacentersView = popupInputDialogView.findViewById(R.id.listDatacenters);

    }

    private void initPopupViewGenericNameList() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.nombres_genericos, null);
        listGenericNamesView = popupInputDialogView.findViewById(R.id.listGenericNames);
        alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
        alertDialogBuilder.setCancelable(true);


        // Set the inflated layout view object to the AlertDialog builder.
        alertDialogBuilder.setView(popupInputDialogView);


        // Create AlertDialog and show.
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                int cantMax = currentDatacenter.getCantMaxArtefact(idQr);
                NombreGenerico ng = getNombreGenericoWithCode(idQr);
                String name = null;
                List<String> names = new ArrayList<>();
                for(int i=1; i<= cantMax; i++){
                    if(ng != null) {
                        name = ng.getPrefijo() + i;
                    }else{
                        name = String.valueOf(i);
                    }
                    names.add(name);
                }
                listGenericNamesAdapter = new ArrayAdapter(me, R.layout.row_nombre_generico, R.id.textItem, names);
                listGenericNamesView.setAdapter(listGenericNamesAdapter);
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

        listGenericNamesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedGenericName = listGenericNamesAdapter.getItem(position);
                buttonGenericName.setText(selectedGenericName);
                alertDialog.cancel();
            }
        });


    }

    private NombreGenerico getNombreGenericoWithCode(int idQr) {
        boolean encontrado = false;
        NombreGenerico ng = null;
        Iterator<NombreGenerico> it = nombresGenericos.iterator();
        while (!encontrado && it.hasNext()){
            ng = it.next();
            encontrado = ng.getCodigo() == idQr;
        }
        return ng;
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
        ArrayList<AbstractArtefactDto> list;
        list = ConstantsAdmin.getTablerosTGBT(this);
   //     artefactsCount.setCantTableroTGBT(list.size());
        ArrayList<AbstractArtefactDto> allItems = new ArrayList<>(list);
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
        list = ConstantsAdmin.getIncendio2(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getPresostato(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getAireAcond(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getTableroPDR(this);

        allItems.addAll(list);
        list = ConstantsAdmin.getTableroPDR2(this);

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
        this.habilitarDeshabilitarItemMenu();


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


    private ArrayList recuperarValoresTopes() {
        ArrayList<ArtefactoValorTope> valores = new ArrayList<>();
        Iterator<ArtefactoValorTope> it = ConstantsAdmin.valoresTopes.iterator();
        ArtefactoValorTope avt = null;
        while (it.hasNext()){
            avt = it.next();
            if(avt.getCodigo() == idQr && avt.getDatacenterId() == currentDatacenter.getId() ){
                valores.add(avt);
            }

        }
        return valores;
    }


    private boolean verificarNombreGenerico(int idQr) {
        boolean respuesta = false;
        NombreGenerico ng = null;
        Iterator<NombreGenerico> it = nombresGenericos.iterator();
        while (it.hasNext() && !respuesta){
            ng = it.next();
            respuesta = ng.getCodigo() == idQr;
        }
        return respuesta;
    }

    private void initPopupViewControlsForms()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.form_layout, null);
        descForm = popupInputDialogView.findViewById(R.id.formDescEntry);
        TextView nameForm = popupInputDialogView.findViewById(R.id.formName);
        saveFormButton = popupInputDialogView.findViewById(R.id.saveFormButton);
        cancelFormButton = popupInputDialogView.findViewById(R.id.cancelFormButton);
        if(currentForm != null ){
            descForm.setText(currentForm.getDescription());
            nameForm.setText("░ " + currentForm.getNroForm());
        }else{
            nameForm.setText("░ " + getResources().getString(R.string.nuevo_form));
        }
    }

    private void initPopupTablero()
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            pckwR.setText(selectedArtefact.getKwr());
            pckwS.setText(selectedArtefact.getKws());
            pckwT.setText(selectedArtefact.getKwt());
            pcaR.setText(selectedArtefact.getPar());
            pcaS.setText(selectedArtefact.getPas());
            pcaT.setText(selectedArtefact.getPat());

        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnTablero();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
      //  buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnTablero(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO KWR
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pckwR.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pckwR.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pckwR.setText(pckwR.getText());
                    }
                    break;
                case 2:// SE TRATA DEL CAMPO AR
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo2);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pcaR.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pcaR.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pcaR.setText(pcaR.getText());
                    }
                    break;
                case 3:// SE TRATA DEL CAMPO KWS
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo3);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pckwS.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pckwS.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pckwS.setText(pckwS.getText());
                    }
                    break;
                case 4:// SE TRATA DEL CAMPO AS
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo4);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pcaS.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pcaS.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});

                        }
                        txt.setText(label);
                        pcaS.setText(pcaS.getText());
                    }
                    break;
                case 5:// SE TRATA DEL CAMPO KWT
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo5);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pckwT.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pckwT.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});

                        }
                        txt.setText(label);
                        pckwT.setText(pckwT.getText());
                    }
                    break;
                case 6:// SE TRATA DEL CAMPO AT
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo6);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pcaT.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pcaT.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});

                        }
                        txt.setText(label);
                        pcaT.setText(pcaT.getText());
                    }
                    break;
                default:
                    break;


            }

        }

    }

    private boolean validarTope(String tope) {
        boolean valido = false;
        try{
            Double.parseDouble(tope);
            valido = true;
        }catch (Exception exc){
            valido = false;
        }
        return valido;
    }


    private void initPopupUPS()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.ups_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        pcaR = popupInputDialogView.findViewById(R.id.percentR);
        pcaS = popupInputDialogView.findViewById(R.id.percentS);
        pcaT = popupInputDialogView.findViewById(R.id.percentT);
        checkAlarma = popupInputDialogView.findViewById(R.id.checkAlarma);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
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
        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnUPS();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
      //  buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnUPS(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO R%
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pcaR.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pcaR.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pcaR.setText(pcaR.getText());
                    }
                    break;
                case 2:// SE TRATA DEL CAMPO AS
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo2);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pcaS.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pcaS.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pcaS.setText(pcaS.getText());
                    }
                    break;
                case 3:// SE TRATA DEL CAMPO AT
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo3);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pcaT.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pcaT.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pcaT.setText(pcaT.getText());
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private void initPopupGrupoElectrogeno()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.grupoelectrogeno_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        percent_comb = popupInputDialogView.findViewById(R.id.percent_comb);
        temperatura = popupInputDialogView.findViewById(R.id.temperatura);
        checkAlarma = popupInputDialogView.findViewById(R.id.checkAlarma);
        checkAuto = popupInputDialogView.findViewById(R.id.checkAuto);
        checkPrecalent = popupInputDialogView.findViewById(R.id.precalent);
        checkCargadorbat = popupInputDialogView.findViewById(R.id.cargadorbat);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
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
        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnGrupoElectrogeno();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
    //    buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnGrupoElectrogeno(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            percent_comb.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            percent_comb.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        percent_comb.setText(percent_comb.getText());
                    }
                    break;
                case 2:// SE TRATA DEL CAMPO Temperatura
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo2);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            temperatura.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            temperatura.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        temperatura.setText(temperatura.getText());
                    }
                    break;
                default:
                    break;
            }
        }
    }



    private void initPopupAireCrac()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.airecrac_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        temperatura = popupInputDialogView.findViewById(R.id.temperatura);
        funcionaOk = popupInputDialogView.findViewById(R.id.funcionaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
            }

            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getFunciona_ok().equals("1")){
                funcionaOk.setChecked(true);
            }else{
                funcionaOk.setChecked(false);
            }
            temperatura.setText(selectedArtefact.getTemperatura());
        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnAireCrac();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnAireCrac(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            temperatura.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            temperatura.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        temperatura.setText(temperatura.getText());
                    }
                    break;
                default:
                    break;
            }
        }
    }



    private void initPopupAireChiller()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.airechiller_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        comp1Load = popupInputDialogView.findViewById(R.id.comp1load);
        comp2Load = popupInputDialogView.findViewById(R.id.comp2load);
        out = popupInputDialogView.findViewById(R.id.out);
        pprim = popupInputDialogView.findViewById(R.id.pprim);
        psec = popupInputDialogView.findViewById(R.id.psec);
        comp1Ok = popupInputDialogView.findViewById(R.id.comp1Ok);
        comp2Ok = popupInputDialogView.findViewById(R.id.comp2Ok);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
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
            pprim.setText(selectedArtefact.getPprim());
            psec.setText(selectedArtefact.getPsec());
        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnAireChiller();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
      //  buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnAireChiller(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            comp1Load.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            comp1Load.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        comp1Load.setText(comp1Load.getText());
                    }
                    break;
                case 2:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo2);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            comp2Load.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            comp2Load.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        comp2Load.setText(comp2Load.getText());
                    }
                    break;

                case 3:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo3);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pprim.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pprim.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pprim.setText(pprim.getText());
                    }
                    break;
                case 4:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo4);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            psec.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            psec.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        psec.setText(psec.getText());
                    }
                    break;
                case 5:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo5);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            out.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            out.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        out.setText(out.getText());
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private void initPopupIncendio()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.incendio_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        presion = popupInputDialogView.findViewById(R.id.presion);
        energiaAOk = popupInputDialogView.findViewById(R.id.energiaAOk);
        energiaBOk = popupInputDialogView.findViewById(R.id.energiaBOk);
        funcionaOk = popupInputDialogView.findViewById(R.id.funcionaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
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
        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnIncendio();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnIncendio(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            presion.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            presion.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        presion.setText(presion.getText());
                    }
                    break;
                default:
                    break;
            }
        }
    }




    private void initPopupIncendio2()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.incendio2_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        energiaAOk = popupInputDialogView.findViewById(R.id.energiaAOk);
        fm200Ok = popupInputDialogView.findViewById(R.id.fm200Ok);
        funcionaOk = popupInputDialogView.findViewById(R.id.funcionaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }

        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getEnergiaAOk().equals("1")){
                energiaAOk.setChecked(true);
            }else{
                energiaAOk.setChecked(false);
            }
            if(selectedArtefact.getFm200Ok().equals("1")){
                fm200Ok.setChecked(true);
            }else{
                fm200Ok.setChecked(false);
            }
            if(selectedArtefact.getFunciona_ok().equals("1")){
                funcionaOk.setChecked(true);
            }else{
                funcionaOk.setChecked(false);
            }

        }
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
        //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void initPopupPresostato()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.presostato_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        airePresion = popupInputDialogView.findViewById(R.id.airePresion);
        aguaPresion = popupInputDialogView.findViewById(R.id.aguaPresion);
        aireOk = popupInputDialogView.findViewById(R.id.aireOk);
        aguaOk = popupInputDialogView.findViewById(R.id.aguaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
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
            airePresion.setText(selectedArtefact.getAirePresion());
            aguaPresion.setText(selectedArtefact.getAguaPresion());

        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnPresostato();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnPresostato(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            airePresion.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            airePresion.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        airePresion.setText(airePresion.getText());
                    }
                    break;

                case 2:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo2);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            aguaPresion.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            aguaPresion.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        aguaPresion.setText(aguaPresion.getText());
                    }
                    break;
                default:
                    break;
            }
        }
    }



    private void initPopupAireAcond()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.aireacond_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        temperatura = popupInputDialogView.findViewById(R.id.temperatura);
        funcionaOk = popupInputDialogView.findViewById(R.id.funcionaOk);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            if(selectedArtefact.getFunciona_ok().equals("1")){
                funcionaOk.setChecked(true);
            }else{
                funcionaOk.setChecked(false);
            }
            temperatura.setText(selectedArtefact.getTemperatura());
        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnAireAcond();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
   //     buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnAireAcond(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            temperatura.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            temperatura.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        temperatura.setText(temperatura.getText());
                    }
                    break;

                default:
                    break;
            }
        }
    }



    private void initPopupTableroPDR()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.tableropdr_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        pottotRA = popupInputDialogView.findViewById(R.id.pottotRA);
        pottotRB = popupInputDialogView.findViewById(R.id.pottotRB);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
            }
            entryDescripcion.setText(selectedArtefact.getDescription());

            pottotRA.setText(selectedArtefact.getPottotRA());
            pottotRB.setText(selectedArtefact.getPottotRB());
        }else{
            selectedGenericName = null;
        }
        this.cargarValoresTopesEnTableroPDR();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
     //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnTableroPDR(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pottotRA.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pottotRA.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pottotRA.setText(pottotRA.getText());
                    }
                    break;
                case 2:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo2);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pottotRB.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pottotRB.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pottotRB.setText(pottotRB.getText());
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private void initPopupTableroPDR2()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.tableropdr_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        pottotRA = popupInputDialogView.findViewById(R.id.pottotRA);
        TextView pottotRBLabel = popupInputDialogView.findViewById(R.id.pottotRBLabel);
        pottotRB = popupInputDialogView.findViewById(R.id.pottotRB);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
            }
            entryDescripcion.setText(selectedArtefact.getDescription());
            pottotRA.setText(selectedArtefact.getPottotRA());

        }else{
            selectedGenericName = null;
        }
        pottotRB.setVisibility(View.GONE);
        pottotRBLabel.setVisibility(View.GONE);
        this.cargarValoresTopesEnTableroPDR2();
        buttonSaveData = popupInputDialogView.findViewById(R.id.buttonSaveData);
        //   buttonCancel = popupInputDialogView.findViewById(R.id.buttonCancel);
    }

    private void cargarValoresTopesEnTableroPDR2(){
        ArrayList misValores = this.recuperarValoresTopes();
        Iterator<ArtefactoValorTope> it = misValores.iterator();
        ArtefactoValorTope avt = null;
        String label = "";
        TextView txt = null;
        boolean validoTope;
        while(it.hasNext()){
            avt = it.next();
            switch (avt.getIndexCampo()){
                case 1:// SE TRATA DEL CAMPO Comb
                    validoTope = this.validarTope(avt.getTope());
                    if(validoTope) {
                        txt = popupInputDialogView.findViewById(R.id.idcampo1);
                        txt.setVisibility(View.VISIBLE);
                        if (avt.getMinomax() == 0) {
                            label = "(" + getResources().getString(R.string.minlabel) + " " + avt.getTope() + ")";
                            pottotRA.setFilters(new InputFilter[]{new InputFilterMinimo(avt.getTope(), txt, alerta)});
                        } else {
                            label = "(" + getResources().getString(R.string.maxlabel) + " " + avt.getTope() + ")";
                            pottotRA.setFilters(new InputFilter[]{new InputFilterMaximo(avt.getTope(), txt, alerta)});
                        }
                        txt.setText(label);
                        pottotRA.setText(pottotRA.getText());
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private void initPopupPresurizacionEscalera()
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
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


    private void initPopupEstractorAire()
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
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
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


    private void initPopupPresurizacionCanieria()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        popupInputDialogView = layoutInflater.inflate(R.layout.presurizacioncanieria_layout, null);
        tableroNom = popupInputDialogView.findViewById(R.id.itemId);
        checkAlarma = popupInputDialogView.findViewById(R.id.checkAlarma);
        encendido = popupInputDialogView.findViewById(R.id.encendido);
        entryDescripcion = popupInputDialogView.findViewById(R.id.entryDescripcion);
        boolean tieneNombreGenerico = this.verificarNombreGenerico(idQr);
        buttonGenericName = popupInputDialogView.findViewById(R.id.genericName);
        if(currentDatacenter.getCantMaxArtefact(idQr) == 1){
            popupInputDialogView.findViewById(R.id.itemIdLabel).setVisibility(View.GONE);
            tableroNom.setVisibility(View.GONE);
            buttonGenericName.setVisibility(View.GONE);
        }else if(tieneNombreGenerico){
            buttonGenericName.setVisibility(View.VISIBLE);
            buttonGenericName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopupViewGenericNameList();
                }
            });
            tableroNom.setVisibility(View.GONE);
        }else{
            buttonGenericName.setVisibility(View.GONE);
            tableroNom.setVisibility(View.VISIBLE);
        }
        if(selectedArtefact != null){
            if(!tieneNombreGenerico) {
                tableroNom.setText(selectedArtefact.getName());
            }else{
                buttonGenericName.setText(selectedArtefact.getName());
                selectedGenericName = selectedArtefact.getName();
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


        idQr = 106;
        selectedArtefact = null;
        this.openArtefactView();

/*
       if(!cameraIsOn){
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

/*
    private void getPermissions() {
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
    }*/
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

    @Override
    public void handleResult(Result result) {
        String newEntry = result.getText();
        boolean okCodigoInt = true;
        int idResult = -1;
        try {
            idResult = Integer.parseInt(newEntry);
        }catch (Exception exc){
            okCodigoInt = false;
        }
    //    this.getResults(newEntry);
        if(okCodigoInt){
            mScannerView.stopCamera();
            contentFrame.removeAllViews();
            cameraIsOn = false;
            idQr = idResult;
            this.openArtefactView();
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

            Long[] params = new Long[1];
            params[0] = 1L;
            separadorExcel = ConstantsAdmin.COMA;
            new ExportCSVEsteticoTask().execute(params);
            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath());
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                    .addCategory(Intent.CATEGORY_OPENABLE)
                    .setDataAndType(uri, "text/csv")
                    .putExtra(Intent.EXTRA_TITLE, ConstantsAdmin.fileEsteticoCSV);

            startActivityForResult(intent, ConstantsAdmin.ACTIVITY_CHOOSE_FILE);

            /*
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
            builder.show();*/
        }else{
            createAlertDialog(getResources().getString(R.string.mensaje_sin_artefactos), getResources().getString(R.string.atencion));
        }

    }

    private class ExportCSVEsteticoTask extends AsyncTask<Long, Integer, Integer>{
        final ProgressDialog dialog = null;

        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);
                ConstantsAdmin.mensaje = null;
                ConstantsAdmin.exportarCSVEstetico(me, separadorExcel,listArtefacts, currentForm);


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

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == ConstantsAdmin.ACTIVITY_CHOOSE_FILE)
        {
            if(ConstantsAdmin.mensaje != null){
                createAlertDialog(ConstantsAdmin.mensaje, getResources().getString(R.string.atencion));
            }else {
                this.saveCSVFile(intent);
                createAlertDialog(getResources().getString(R.string.mensaje_exito_exportar_csv), "");
            }

        }
    }

    private void saveCSVFile(Intent intent){
        Uri uri;
        if(intent != null){
            uri = intent.getData();
            //	String filePath = getRealPathFromURI(uri);
            //String filePath = intent.getData().getPath();
            String fileTempPath = ConstantsAdmin.obtenerPathDeArchivo(ConstantsAdmin.fileEsteticoCSV);
            ConstantsAdmin.copyFiles(fileTempPath,uri, this.getContentResolver());
        }


    }






}
