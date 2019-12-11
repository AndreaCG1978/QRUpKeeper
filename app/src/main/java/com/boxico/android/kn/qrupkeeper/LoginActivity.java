package com.boxico.android.kn.qrupkeeper;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.boxico.android.kn.qrupkeeper.util.DatacenterService;
import com.boxico.android.kn.qrupkeeper.util.InspectorService;
import com.boxico.android.kn.qrupkeeper.util.TableroService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends FragmentActivity {

    private EditText userEntry = null;
    private EditText passEntry = null;
    private Button buttonLogin = null;
    private Button buttonCancel = null;
    private InspectorService inspectorService = null;
    private Inspector currentInspector = null;


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
        inspectorService = retrofit.create(InspectorService.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.configureWidgets();
        this.initializeService();
    }



    private void configureWidgets() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        userEntry = (EditText) findViewById(R.id.usrEntry);
        passEntry = (EditText) findViewById(R.id.passEntry);
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

    private void loadInspectorInfo(String usrText, String pswText){
        final LoginActivity me = this;
        Call<List<Inspector>> call = null;
        //call = inspectorService.getInspectorsUsrPsw(usrText, pswText);
        call = inspectorService.getInspectors(usrText, pswText);
        //call = inspectorService.getInspectors(usrText);
        call.enqueue(new Callback<List<Inspector>>() {
            List list = new ArrayList();
            @Override
            public void onResponse(Call<List<Inspector>> call, Response<List<Inspector>> response) {
                for(Inspector item : response.body()) {
                    currentInspector = item;
                }
            }

            @Override
            public void onFailure(Call<List<Inspector>> call, Throwable t) {
                call.cancel();
            }
        });

    }

    private void loginUser() {
        String usrText = userEntry.getText().toString();
        String pswText = passEntry.getText().toString();
        Inspector inspector = null;
        if(!usrText.equals("")&&(!pswText.equals("")) && pswText.length() == 4){
            loadInspectorInfo(usrText, pswText);
            if(currentInspector != null){// Se logueo correctamente
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                if(currentInspector != null){
                    intent.putExtra(ConstantsAdmin.currentInspectorConstant, currentInspector);
                }
                startActivity(intent);
            }else{
                createAlertDialog("Usuario o Contraseña incorrecta", "Atención!" );
            }


        }else{
            createAlertDialog("Usuario o Contraseña incorrecta", "Atención!" );
        }
    }


}
