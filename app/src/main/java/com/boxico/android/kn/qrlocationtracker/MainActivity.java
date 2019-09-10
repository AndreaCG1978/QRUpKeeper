package com.boxico.android.kn.qrlocationtracker;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.boxico.android.kn.qrlocationtracker.ddbb.DataBaseManager;
import com.boxico.android.kn.qrlocationtracker.util.ConstantsAdmin;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeDataBase();
        this.chargeExamples();
    }

    private void initializeDataBase(){
        DataBaseManager mDBManager = DataBaseManager.getInstance(this);
        ConstantsAdmin.inicializarBD(mDBManager);
        ConstantsAdmin.createBD(mDBManager);
        ConstantsAdmin.finalizarBD(mDBManager);
    }


    private void chargeExamples(){
        ItemDto it = new ItemDto();
        it.setName("SPB");
        it.setDescription("Servicion Penitenciario Bonaerense");
        it.setIdentification("spb");
        it.setLatitude(-34.9008839);
        it.setLongitude(-57.9632206);

    }
}
