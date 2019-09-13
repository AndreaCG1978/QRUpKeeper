package com.boxico.android.kn.qrlocationtracker;

import androidx.fragment.app.FragmentActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boxico.android.kn.qrlocationtracker.ddbb.DataBaseManager;
import com.boxico.android.kn.qrlocationtracker.util.ConstantsAdmin;

public class MainActivity extends FragmentActivity {

    private ZXingScannerView mScannerView;
    private Button turnOnQRCam;
    private View viewQRCam;

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
        long itemSize = ConstantsAdmin.getItemSize(this);
        if(itemSize == 1){
            ConstantsAdmin.deleteAll(this);
        }
        if(itemSize == 0) {
            ItemDto it = new ItemDto();
            it.setName("Depto 54 esq 5");
            it.setDescription("Departamento de calle 54 esq 5, con 3 dormitorios");
            it.setIdentification("54-esq-5-departamento-3-dor");
            it.setLatitude(-34.9008839);
            it.setLongitude(-57.9632206);

            ConstantsAdmin.createItem(it, this);
        }

    }
}
