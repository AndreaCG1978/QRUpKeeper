package com.boxico.android.kn.qrupkeeper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.boxico.android.kn.qrupkeeper.util.InspectorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.FragmentActivity;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends FragmentActivity {

    private EditText userEntry = null;
    private EditText passEntry = null;
    private Button buttonLogin = null;
    private InspectorService inspectorService = null;
    private String pswText;
    private String usrText;
    private LoginActivity me;
    private CheckBox saveLogin = null;
    private ImageButton hiddeShowPass;
    private boolean isShowingPass = false;
    private ProgressDialog dialog = null;


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
                // .baseUrl("http://172.16.2.37/")
                .baseUrl(ConstantsAdmin.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        inspectorService = retrofit.create(InspectorService.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        me = this;
        setContentView(R.layout.login);
        this.configureWidgets();
        this.initializeDataBase();
        this.initializeService();
        this.initializeLogin();
    }

    private void initializeDataBase(){
        DataBaseManager mDBManager = DataBaseManager.getInstance(this);
        ConstantsAdmin.inicializarBD(mDBManager);
        ConstantsAdmin.createBD(mDBManager);
        ConstantsAdmin.finalizarBD(mDBManager);
    }


    private void initializeLogin() {
        Inspector inspTemp = ConstantsAdmin.getLogin(this);
        if(inspTemp != null){
            userEntry.setText(inspTemp.getUsr());
            passEntry.setText(inspTemp.getPsw());
            saveLogin.setChecked(true);
        }

    }


    private void configureWidgets() {
        buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonCancel = findViewById(R.id.buttonCancel);
        userEntry = findViewById(R.id.usrEntry);
        passEntry = findViewById(R.id.passEntry);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        saveLogin = findViewById(R.id.checkSaveLogin);
        hiddeShowPass = findViewById(R.id.imagenShowPassword);
        hiddeShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowingPass = !isShowingPass;
                if(isShowingPass){
                    passEntry.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    hiddeShowPass.setBackgroundResource(R.drawable.showpass);
                }else{
                    passEntry.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    hiddeShowPass.setBackgroundResource(R.drawable.hidepass);
                }
            }
        });
    }

    private void createAlertDialog(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message).setTitle(title);
        builder.setCancelable(true);

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void loadInspectorInfo() {
        final LoginActivity me = this;
        Call<List<Inspector>> call = null;
        Response<List<Inspector>> response;
        ArrayList<Inspector> inspectors;

        try {
            ConstantsAdmin.mensaje = null;
         //   this.inicializarConexionServidor();
            call = inspectorService.getInspectors(usrText, pswText, ConstantsAdmin.tokenIplan);
            response = call.execute();
            if(response.body() != null){
                inspectors = new ArrayList<>(response.body());
                if(inspectors.size() == 1){
                    Inspector currentInspector = inspectors.get(0);
                    Intent intent = new Intent(me, MainActivity.class);
                    intent.putExtra(ConstantsAdmin.currentInspectorConstant, currentInspector);
                    if(saveLogin.isChecked()){
                        ConstantsAdmin.createLogin(currentInspector,me);
                    }else{
                        ConstantsAdmin.deleteLogin(me);
                    }
                    startActivity(intent);
                }else{
                    //createAlertDialog(getResources().getString(R.string.login_error), getResources().getString(R.string.atencion) );
                    ConstantsAdmin.mensaje = getResources().getString(R.string.login_error);

                }
            }else{
                ConstantsAdmin.mensaje = getResources().getString(R.string.conexion_server_error);
              /*  String error;
                if(response.message() != null) {
                    error = response.message();
                }else{
                    error = "Body is null";
                }
                ConstantsAdmin.mensaje = error;*/
            }
        }catch(Exception exc){
/*            String error;
            error = exc.getMessage() + "\n";
            if(exc.getCause() != null){
                error = error + exc.getCause().toString();
            }
            for(int i=0; i< exc.getStackTrace().length; i++){
                error = error +  exc.getStackTrace()[i].toString()+ "\n";
            }
            ConstantsAdmin.mensaje = error;*/
            ConstantsAdmin.mensaje = getResources().getString(R.string.conexion_server_error);
            if(call != null) {
                call.cancel();
            }

        }

/*

        call.enqueue(new Callback<List<Inspector>>() {
          //  List list = new ArrayList();
            @Override
            public void onResponse(Call<List<Inspector>> call, Response<List<Inspector>> response) {
                try {
                    for(Inspector item : response.body()) {
                        currentInspector = item;
                    }
                    if(currentInspector != null){// Se logueo correctamente
                        Intent intent = new Intent(me, MainActivity.class);
                        intent.putExtra(ConstantsAdmin.currentInspectorConstant, currentInspector);
                        if(saveLogin.isChecked()){
                            ConstantsAdmin.createLogin(currentInspector,me);
                        }else{
                            ConstantsAdmin.deleteLogin(me);
                        }
                        startActivity(intent);
                    }else{
                        createAlertDialog(getResources().getString(R.string.login_error), getResources().getString(R.string.atencion) );
                        buttonLogin.setEnabled(true);
                        buttonLogin.setTextColor(Color.WHITE);
                    }

                }catch (Exception exc){
                    //createAlertDialog(getResources().getString(R.string.login_error),getResources().getString(R.string.atencion));
                    String error;
                    error = exc.getMessage() + "\n";
                    if(exc.getCause() != null){
                        error = error + exc.getCause().toString();
                    }
                    for(int i=0; i< exc.getStackTrace().length; i++){
                        error = error +  exc.getStackTrace()[i].toString()+ "\n";
                    }
                    //createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion) );
                    createAlertDialog(error,getResources().getString(R.string.atencion));
                    call.cancel();
                    buttonLogin.setEnabled(true);
                    buttonLogin.setTextColor(Color.WHITE);

                }finally {
                    dialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<List<Inspector>> call, Throwable t) {
                String error;
                error = t.getMessage() + "\n";
                if(t.getCause() != null){
                    error = error + t.getCause().toString();
                }
                for(int i=0; i< t.getStackTrace().length; i++){
                    error = error +  t.getStackTrace()[i].toString()+ "\n";
                }
                //createAlertDialog(getResources().getString(R.string.conexion_server_error), getResources().getString(R.string.atencion) );
                createAlertDialog(error,getResources().getString(R.string.atencion));
                call.cancel();
                buttonLogin.setEnabled(true);
                buttonLogin.setTextColor(Color.WHITE);
                dialog.cancel();
            }
        });
*/
    }

    private void inicializarConexionServidor() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        // From https://www.washington.edu/itconnect/security/ca/load-der.crt
        InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
        } finally {
            caInput.close();
        }

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
        URL url = new URL(ConstantsAdmin.URL);
        HttpsURLConnection urlConnection =
                (HttpsURLConnection)url.openConnection();
        urlConnection.setSSLSocketFactory(context.getSocketFactory());
        InputStream in = urlConnection.getInputStream();
        //copyInputStreamToOutputStream(in, System.out);

    }


    private class LoginUserTask extends AsyncTask<Long, Integer, Integer> {


        @Override
        protected Integer doInBackground(Long... params) {

            try {
                publishProgress(1);

                loadInspectorInfo();


            } catch (Exception e) {
/*                String error;
                error = e.getMessage() + "\n";
                if(e.getCause() != null){
                    error = error + e.getCause().toString();
                }
                for(int i=0; i< e.getStackTrace().length; i++){
                    error = error +  e.getStackTrace()[i].toString()+ "\n";
                }
                ConstantsAdmin.mensaje = error;*/
                ConstantsAdmin.mensaje = getResources().getString(R.string.conexion_server_error);
            }
            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
            dialog = ProgressDialog.show(me, "",
                    getResources().getString(R.string.login_progress), true);
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(ConstantsAdmin.mensaje != null){
                createAlertDialog(ConstantsAdmin.mensaje,getResources().getString(R.string.atencion));
                ConstantsAdmin.mensaje = null;
                buttonLogin.setEnabled(true);
                buttonLogin.setTextColor(Color.WHITE);

            }
            dialog.cancel();

        }
    }




    private void loginUser() {
        usrText = userEntry.getText().toString();
        pswText = passEntry.getText().toString();
        if(!usrText.equals("")&&(!pswText.equals(""))){
            buttonLogin.setEnabled(false);
            buttonLogin.setTextColor(Color.GRAY);
            new LoginUserTask().execute();
        }else{
            createAlertDialog(getResources().getString(R.string.login_error), getResources().getString(R.string.atencion));
        }
    }


}
