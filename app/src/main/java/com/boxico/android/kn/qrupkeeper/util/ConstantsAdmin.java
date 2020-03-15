package com.boxico.android.kn.qrupkeeper.util;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;


import com.boxico.android.kn.qrupkeeper.MainActivity;
import com.boxico.android.kn.qrupkeeper.R;
import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.AireAcond;
import com.boxico.android.kn.qrupkeeper.dtos.AireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.AireCrac;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ConstantsAdmin {

	public static final String KEY_ROWID = "rowId" ;
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "description";
//	public static final String KEY_IDENTIFICATION = "identification";
    public static final String ENTER = "\n";

	public static final String DATABASE_NAME = "QRLocationTrackerDB";
	public static final int DATABASE_VERSION = 1;
	public static final String TAG = "DataBaseManager";
 /*   public static final String TABLE_GOTO_URL = "tableGoToUrl";
    public static final String KEY_URL = "url";
	public static final String KEY_DISTANCE = "distance";
    public static final String KEY_LONGITUDE_ORIGIN = "longitudeOrigin" ;
    public static final String KEY_LATITUDE_ORIGIN = "latitudeOrigin" ;
    public static final String KEY_RADIO = "radio";*/
    public static final String URL = "http://192.168.0.8";
   //public static final String URL = "http://192.168.1.42/";
 //   public static final String URL = "http://172.16.2.37/";
    public static final String TABLE_TABLERO_TGBT = "tablero_tgbt";
    public static final String TABLE_TABLERO_CRAC = "tablero_crac";
    public static final String TABLE_TABLERO_INUPS = "tablero_inups";
    public static final String TABLE_TABLERO_AIRECHILLER = "tablero_airechiller";
    public static final String TABLE_LOGIN = "tabla_login";
    public static final String TABLE_LOAD_UPS = "load_ups";
    public static final String TABLE_GRUPOELECTROGENO = "grupoelectrogeno";
    public static final String TABLE_AIRECRAC = "aire_crac" ;
    public static final String TABLE_AIRECHILLER = "aire_chiller" ;
    public static final String TABLE_INCENDIO = "incendio" ;
    public static final String TABLE_PRESOSTATO = "presostato" ;
    public static final String TABLE_AIREACOND = "aire_acond" ;
    public static final String TABLE_TABLEROPDR = "tablero_pdr" ;
    public static final String TABLE_PRESURIZACIONESCALERA = "presurizacion_escalera" ;
    public static final String TABLE_PRESURIZACIONCANIERIA = "presurizacion_canieria" ;
    public static final String TABLE_ESTRACTORAIRE = "estractor_aire" ;
    public static final String KEY_KWR = "kwr";
    public static final String KEY_KWS = "kws";
    public static final String KEY_KWT = "kwt";
    public static final String KEY_PAR = "par";
    public static final String KEY_PAS = "pas";
    public static final String KEY_PAT = "pat";
    public static final String KEY_ALARM ="alarm" ;
    public static final String KEY_CODE = "codigo";
    public static final String TABLE_FORMS ="tabla_forms" ;
    public static final String KEY_NROFORM = "nroForm";
    public static final String KEY_IDREMOTEDB = "idRemoteDB";
    public static final String KEY_DATACENTERNAME = "datacenterName";
    public static final String KEY_DATACENTERID = "datacenterId";
    public static final String KEY_PASSWORD = "contrasenia";
    public static final String KEY_AUTO ="autom" ;
    public static final String KEY_CARGADORBAT = "cargadorbat";
    public static final String KEY_NIVELCOMB75 = "nivelcomb75";
    public static final String KEY_PRECALENT = "precalent";
    public static final String KEY_PERCENTCOMB = "percentcomb";
    public static final String KEY_TEMPERATURA = "temperatura";
    public static final String KEY_FUNCIONAOK = "funcionaOk";
    public static final String KEY_OUT = "val_out" ;
    public static final String KEY_COMP1_LOAD = "comp1Load" ;
    public static final String KEY_COMP2_LOAD = "comp2Load" ;
    public static final String KEY_COMP1_OK = "comp1Ok" ;
    public static final String KEY_COMP2_OK = "comp2Ok" ;
    public static final String KEY_ENERGIAA_OK = "energiaAOk" ;
    public static final String KEY_ENERGIAB_OK = "energiaBOk" ;
    public static final String KEY_PRESION = "presion" ;
    public static final String KEY_AIRE_PRESION = "airePresion" ;
    public static final String KEY_AGUA_PRESION = "aguaPresion" ;
    public static final String KEY_AIRE_OK = "aireOk" ;
    public static final String KEY_AGUA_OK = "aguaOk" ;
    public static final String KEY_POTTOTRA = "pottotRA" ;
    public static final String KEY_POTTOTRB = "pottotRB" ;
    public static final String KEY_ARRANQUE = "arranque" ;
    public static final String KEY_CORREAS = "correas" ;
    public static final String KEY_ENGRASE = "engrase" ;
    public static final String KEY_LIMPIEZA = "limpieza" ;
    public static final String KEY_TIEMPO = "tiempo" ;
    public static final String KEY_ENCENDIDO ="encendido" ;
    public static final String ID_QR = "CF" ;
    public static final String TITLE_TABLEROTGBT = "Tablero TGBT";
    public static final String TITLE_TABLEROAIRECHILLER  = "Tablero Aire/Chiller";
    public static final String TITLE_TABLEROCRAC = "Tablero Crac";
    public static final String TITLE_TABLEROINUPS = "Tablero In UPS";
    public static final String TITLE_LOADUPS = "Load UPS";
    public static final String TITLE_GRUPOELECTROGENO = "Grupo Electrógeno";
    public static final String TITLE_AIRECRAC = "Aire Crac";
    public static final String TITLE_AIRECHILLER = "Aire Chiller";
    public static final String TITLE_INCENDIO = "Incendio";
    public static final String TITLE_PRESOSTATO = "Presostato";
    public static final String TITLE_AIREACONDICIONADO = "Aire Acondicionado";
    public static final String TITLE_TABLEROPDR = "Tablero PDR";
    public static final String TITLE_PRESURIZACIONESCALERA = "Presurización Escalera";
    public static final String TITLE_ESTRACTORAIRE = "Estractor Aire";
    public static final String TITLE_PRESURIZACIONCANIERIA = "Presurización Cañeria";
    public static final String PATTERN_DATE_HOUR = "dd/MM/yyyy HH:mm";
    public static final int EJECUTAR_SELEC_DATACENTER = 1;
    public static final int EJECUTAR_EDIT_FORM = 2;
    public static final int EJECUTAR_SCAN = 3;
    public static final int EJECUTAR_NUEVO_FORM = 4;
    public static final int EJECUTAR_GENERAR_CSV = 5;
    public static final String COMA = ",";
    public static final String fileEsteticoCSV = "reporteIplan.csv";
    public static final int ACTIVITY_CHOOSE_FILE=1;
    public static final String PUNTO_COMA = ";";


    public static String currentInspectorConstant = "currentInspector";
    public static String currentDatacenterConstant = "currentDatacenter";
    public static String KEY_USER = "usuario";
    public static String mensaje;


    public static void inicializarBD(DataBaseManager mDBManager){
		mDBManager.open();
	}

	public static void upgradeBD(DataBaseManager mDBManager){
		mDBManager.upgradeDB();
	}

	public static void createBD(DataBaseManager mDBManager){
		mDBManager.createBD();
	}

	public static void finalizarBD(DataBaseManager mDBManager){
		if(mDBManager != null){
			mDBManager.close();
		}
	}

	public static long createTableroTGBT(TableroTGBT item, Context ctx) {
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		long id = dbm.createTableroTGBT(item);
		dbm.close();
		return id;
	}



    public static long createTableroAireChiller(TableroAireChiller item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createTableroAIRECHILLER(item);
        dbm.close();
        return id;
    }

    public static String getArtefactType(int code){
        String result = "";
        switch (code){
            case 101:
                result = ConstantsAdmin.TITLE_TABLEROTGBT;
                break;
            case 102:
                result = ConstantsAdmin.TITLE_TABLEROAIRECHILLER;
                break;
            case 103:
                result = ConstantsAdmin.TITLE_TABLEROCRAC;
                break;
            case 104:
                result = ConstantsAdmin.TITLE_TABLEROINUPS;
                break;
            case 105:
                result = ConstantsAdmin.TITLE_LOADUPS;
                break;
            case 106:
                result = ConstantsAdmin.TITLE_GRUPOELECTROGENO;
                break;
            case 107:
                result = ConstantsAdmin.TITLE_AIRECRAC;
                break;
            case 108:
                result = ConstantsAdmin.TITLE_AIRECHILLER;
                break;
            case 109:
                result = ConstantsAdmin.TITLE_INCENDIO;
                break;
            case 110:
                result = ConstantsAdmin.TITLE_PRESOSTATO;
                break;
            case 111:
                result = ConstantsAdmin.TITLE_AIREACONDICIONADO;
                break;
            case 112:
                result = ConstantsAdmin.TITLE_TABLEROPDR;
                break;
            case 113:
                result = ConstantsAdmin.TITLE_PRESURIZACIONESCALERA;
                break;
            case 114:
                result = ConstantsAdmin.TITLE_ESTRACTORAIRE;
                break;
            case 115:
                result = ConstantsAdmin.TITLE_PRESURIZACIONCANIERIA;
                break;
        }
        return result;
    }



    public static long createTableroCrac(TableroCrac item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createTableroCRAC(item);
        dbm.close();
        return id;
    }



    public static long createTableroInUps(TableroInUps item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createTableroINUPS(item);
        dbm.close();
        return id;
    }



    public static long createLoadUps(LoadUPS item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createLoadUPS(item);
        dbm.close();
        return id;
    }

    public static long createGrupoElectrogeno(GrupoElectrogeno item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createGrupoElectrogeno(item);
        dbm.close();
        return id;
    }


    public static long createAireCrac(AireCrac item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createAireCrac(item);
        dbm.close();
        return id;
    }


    public static long createAireChiller(AireChiller item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createAireChiller(item);
        dbm.close();
        return id;
    }


    public static long createIncendio(Incendio item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createIncendio(item);
        dbm.close();
        return id;
    }


    public static long createPresostato(Presostato item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createPresostato(item);
        dbm.close();
        return id;
    }

    public static long createAireAcond(AireAcond item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createAireAcond(item);
        dbm.close();
        return id;
    }

    public static long createTableroPDR(TableroPDR item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createTableroPDR(item);
        dbm.close();
        return id;
    }

    public static long createPresurizacionEscalera(PresurizacionEscalera item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createPresurizacionEscalera(item);
        dbm.close();
        return id;
    }

    public static long createEstractorAire(EstractorAire item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createEstractorAire(item);
        dbm.close();
        return id;
    }

    public static long createPresurizacionCanieria(PresurizacionCanieria item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createPresurizacionCanieria(item);
        dbm.close();
        return id;
    }

    public static long createForm(DatacenterForm item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long id = dbm.createForm(item);
        dbm.close();
        return id;
    }

    public static void createLogin(Inspector item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createLogin(item);
        dbm.close();

    }

    public static void deleteTablero(AbstractArtefactDto t, Context ctx){
        switch (t.getCode()){
            case 101:
                deleteTableroTGBT((TableroTGBT) t, ctx);
                break;
            case 102:
                deleteTableroAireChiller((TableroAireChiller) t, ctx);
                break;
            case 103:
                deleteTableroCrac((TableroCrac) t, ctx);
                break;
            case 104:
                deleteTableroInUps((TableroInUps) t, ctx);
                break;
            case 105:
                deleteLoadUps((LoadUPS) t, ctx);
                break;
            case 106:
                deleteGrupoElectrogeno((GrupoElectrogeno) t, ctx);
                break;
            case 107:
                deleteAireCrac((AireCrac) t, ctx);
                break;
            case 108:
                deleteAireChiller((AireChiller) t, ctx);
                break;
            case 109:
                deleteIncendio((Incendio) t, ctx);
                break;
            case 110:
                deletePresostato((Presostato) t, ctx);
                break;
            case 111:
                deleteAireAcond((AireAcond) t, ctx);
                break;
            case 112:
                deleteTableroPDR((TableroPDR) t, ctx);
            case 113:
                deletePresurizacionEscalera((PresurizacionEscalera) t, ctx);
                break;
            case 114:
                deleteEstractorAire((EstractorAire) t, ctx);
                break;
            case 115:
                deletePresurizacionCanieria((PresurizacionCanieria) t, ctx);
                break;


        }

    }


    public static void deleteTableroTGBT(TableroTGBT item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroTGBT(item.getId());
        dbm.close();
    }

    public static void deleteTableroAireChiller(TableroAireChiller item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroAireChiller(item.getId());
        dbm.close();
    }


    public static void deleteTableroCrac(TableroCrac item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroCrac(item.getId());
        dbm.close();
    }

    public static void deleteTableroInUps(TableroInUps item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroInUps(item.getId());
        dbm.close();
    }

    public static void deleteLoadUps(LoadUPS item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteLoadUps(item.getId());
        dbm.close();
    }

    public static void deleteGrupoElectrogeno(GrupoElectrogeno item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteGrupoElectrogeno(item.getId());
        dbm.close();
    }

    public static void deleteAireCrac(AireCrac item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteAireCrac(item.getId());
        dbm.close();
    }


    public static void deleteAireChiller(AireChiller item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteAireChiller(item.getId());
        dbm.close();
    }

    public static void deleteIncendio(Incendio item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteIncendio(item.getId());
        dbm.close();
    }

    public static void deletePresostato(Presostato item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deletePresostato(item.getId());
        dbm.close();
    }

    public static void deleteAireAcond(AireAcond item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteAireAcond(item.getId());
        dbm.close();
    }

    public static void deleteTableroPDR(TableroPDR item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroPDR(item.getId());
        dbm.close();
    }

    public static void deletePresurizacionEscalera(PresurizacionEscalera item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deletePresurizacionEscalera(item.getId());
        dbm.close();
    }

    public static void deleteEstractorAire(EstractorAire item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteEstractorAire(item.getId());
        dbm.close();
    }


    public static void deletePresurizacionCanieria(PresurizacionCanieria item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deletePresurizacionCanieria(item.getId());
        dbm.close();
    }

    public static void deleteForm(DatacenterForm item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteForm(item.getId());
        dbm.close();
    }

    public static void deleteLogin(Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteLogin();
        dbm.close();
    }


/*
    public static long getItemSize(Context ctx){
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		long size = dbm.tableItemSize();
		dbm.close();
		return size;

	}
*/
	public static void deleteAll(Context ctx) {
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		dbm.deleteAll();
		dbm.close();
	}


	public static ArrayList<AbstractArtefactDto> getTablerosTGBT(Context ctx) {
		int itemId;
		String name;
		String kws;
        String kwr;
        String kwt;
        String pas;
        String par;
        String pat;
        String desc;
        int codigo;
        int idRemoteDB;
		TableroTGBT item = null;
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		Cursor cursor = dbm.cursorTableroTGBT();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB  = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
			name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
			kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
		//	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroTGBT(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB, desc);
			items.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		dbm.close();
		return items;
	}

    public static ArrayList<AbstractArtefactDto> getTablerosAireChiller(Context ctx) {
        int itemId;
        String name;
        String kws;
        String kwr;
        String kwt;
        String pas;
        String par;
        String pat;
        int codigo;
        int idRemoteDB;
        String desc;
        TableroAireChiller item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroAireChiller();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroAireChiller(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getTablerosCrac(Context ctx) {
        int itemId;
        String name;
        String kws;
        String kwr;
        String kwt;
        String pas;
        String par;
        String pat;
        int codigo;
        int idRemoteID;
        String desc;
        TableroCrac item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroCrac();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteID = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroCrac(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteID, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getTablerosInUps(Context ctx) {
        int itemId;
        String name;
        String kws;
        String kwr;
        String kwt;
        String pas;
        String par;
        String pat;
        int codigo;
        int idRemoteDB;
        String desc;
        TableroInUps item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroInUps();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroInUps(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getLoadUps(Context ctx) {
        int itemId;
        String name;
        String pas;
        String par;
        String pat;
        int alarm;
        int codigo;
        int idRemoteDB;
        String desc;
        LoadUPS item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorLoadUps();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            alarm = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ALARM));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new LoadUPS(itemId, name, par, pas, pat, String.valueOf(alarm), codigo,-1, idRemoteDB, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getGruposElectrogeno(Context ctx) {
        int itemId;
        String name;
        String temperatura;
        String percentComb;
        int auto;
        int alarm;
        int nivelComb;
        int precalent;
        int cargadorBat;
        int codigo;
        int idRemoteDB;
        String desc;
        GrupoElectrogeno item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorGrupoElectrogeno();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            temperatura = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TEMPERATURA));
            percentComb = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PERCENTCOMB));
            alarm = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ALARM));
            auto = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AUTO));
            nivelComb = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NIVELCOMB75));
            precalent = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PRECALENT));
            cargadorBat = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CARGADORBAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new GrupoElectrogeno(itemId, name, codigo, -1, idRemoteDB, percentComb, temperatura, String.valueOf(nivelComb), String.valueOf(alarm), String.valueOf(auto), String.valueOf(precalent), String.valueOf(cargadorBat), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getAireCrac(Context ctx) {
        int itemId;
        String name;
        String temperatura;
        int funciona_ok;
        int codigo;
        int idRemoteDB;
        String desc;
        AireCrac item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorAireCrac();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            temperatura = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TEMPERATURA));
            funciona_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new AireCrac(itemId, name, codigo, -1, idRemoteDB, temperatura, String.valueOf(funciona_ok), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getAireChiller(Context ctx) {
        int itemId;
        String name;
        String comp1Load;
        String comp2Load;
        String out;
        int comp1_ok;
        int comp2_ok;
        int codigo;
        int idRemoteDB;
        String desc;
        AireChiller item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorAireChiller();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            comp1Load = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP1_LOAD));
            comp2Load = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP2_LOAD));
            comp1_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP1_OK));
            comp2_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP2_OK));
            out = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_OUT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new AireChiller(itemId, name, codigo, -1, idRemoteDB, String.valueOf(comp1_ok), String.valueOf(comp2_ok), comp1Load, comp2Load, out, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getIncendio(Context ctx) {
        int itemId;
        String name;
        int energiaAOk;
        int energiaBOk;
        String presion;
        int funciona_ok;
        int codigo;
        int idRemoteDB;
        String desc;
        Incendio item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorIncendio();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            energiaAOk = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENERGIAA_OK));
            energiaBOk = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENERGIAB_OK));
            funciona_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            presion = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PRESION));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new Incendio(itemId, name, codigo, -1, idRemoteDB, String.valueOf(funciona_ok), presion, String.valueOf(energiaAOk), String.valueOf(energiaBOk), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getPresostato(Context ctx) {
        int itemId;
        String name;
        int agua_Ok;
        int aire_Ok;
        String agua_presion;
        String aire_presion;
        int codigo;
        int idRemoteDB;
        String desc;
        Presostato item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorPresostato();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            agua_Ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AGUA_OK));
            aire_Ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AIRE_OK));
            aire_presion = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AIRE_PRESION));
            agua_presion = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AGUA_PRESION));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new Presostato(itemId, name, codigo, -1, idRemoteDB, String.valueOf(aire_Ok), aire_presion, String.valueOf(agua_Ok), agua_presion, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getAireAcond(Context ctx) {
        int itemId;
        String name;
        int funciona_Ok;
        String temperatura;
        int codigo;
        int idRemoteDB;
        String desc;
        AireAcond item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorAireAcond();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            funciona_Ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            temperatura = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TEMPERATURA));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new AireAcond(itemId, name, codigo, -1, idRemoteDB, String.valueOf(funciona_Ok), temperatura, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getTableroPDR(Context ctx) {
        int itemId;
        String name;
        String pottotA;
        String pottotB;
        int codigo;
        int idRemoteDB;
        String desc;
        TableroPDR item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroPDR();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            pottotA = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_POTTOTRA));
            pottotB = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_POTTOTRB));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroPDR(itemId, name, codigo, -1, idRemoteDB, pottotA, pottotB, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getPresurizacionEscalera(Context ctx) {
        int itemId;
        String name;
        String arranque;
        String correas;
        String engrase;
        String funcionamiento;
        String limpieza;
        String tiempo;
        int codigo;
        int idRemoteDB;
        String desc;
        PresurizacionEscalera item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorPresurizacionEscalera();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            arranque = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ARRANQUE));
            correas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CORREAS));
            engrase = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENGRASE));
            funcionamiento = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            limpieza = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LIMPIEZA));
            tiempo = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TIEMPO));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new PresurizacionEscalera(itemId, name, codigo, -1, idRemoteDB, arranque, tiempo, funcionamiento, engrase, limpieza, correas, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }



    public static ArrayList<AbstractArtefactDto> getEstractorAire(Context ctx) {
        int itemId;
        String name;
        String arranque;
        String correas;
        String engrase;
        String funcionamiento;
        String limpieza;
        int codigo;
        int idRemoteDB;
        String desc;
        EstractorAire item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorEstractorAire();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            arranque = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ARRANQUE));
            correas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CORREAS));
            engrase = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENGRASE));
            funcionamiento = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            limpieza = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LIMPIEZA));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new EstractorAire(itemId, name, codigo, -1, idRemoteDB,  arranque, funcionamiento, engrase, limpieza, correas, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getPresurizacionCanieria(Context ctx) {
        int itemId;
        String name;
        int codigo;
        int alarma;
        int encendido;
        int idRemoteDB;
        String desc;
        PresurizacionCanieria item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorPresurizacionCanieria();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            alarma = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ALARM));
            encendido = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENCENDIDO));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new PresurizacionCanieria(itemId, name, codigo, -1, idRemoteDB,  String.valueOf(alarma), String.valueOf(encendido), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static DatacenterForm getForm(Context ctx) {
        int itemId;
        String nroForm;
        String desc;
        String dcname = null;
        int datacenterId;
        DatacenterForm item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorForms();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            nroForm = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NROFORM));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            dcname = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DATACENTERNAME));
            datacenterId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DATACENTERID));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new DatacenterForm();
            item.setNroForm(nroForm);
            item.setDescription(desc);
            item.setId(itemId);
            item.setDatacenterName(dcname);
            item.setDatacenterId(datacenterId);
        }
        cursor.close();
        dbm.close();
        return item;
    }


    public static Inspector getLogin(Context ctx) {
        int itemId;
        String usr;
        String psw;
        Inspector item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorLogin();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            usr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_USER));
            psw = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PASSWORD));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new Inspector();
            item.setUsr(usr);
            item.setPsw(psw);
            item.setId(itemId);
        }
        cursor.close();
        dbm.close();
        return item;
    }


    public static void exportarCSVEstetico(MainActivity context, String separador, ArrayList listArtefacts, DatacenterForm form){
		/*Asociacion canStore;
		Boolean boolValue;
		String msg;*/
        String body;
        try
        {

            if (ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (!ActivityCompat.shouldShowRequestPermissionRationale(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }

            //		canStore = comprobarSDCard(context);
	/*		boolValue = (Boolean)canStore.getKey();
			msg = (String) canStore.getValue();
			if(boolValue){*/
            body = obtenerCSVdeFormulario(context, separador, listArtefacts, form);
            almacenarArchivo(fileEsteticoCSV, body);
            mensaje = context.getString(R.string.mensaje_exito_exportar_csv);
		/*	}else{
				mensaje = msg;
			}*/

        }catch (Exception e) {
            mensaje = context.getString(R.string.error_exportar_csv);
        }
    }

    private static String obtenerPath(String nombreDirectorio){
        String path = Environment.getExternalStorageDirectory().toString();
        return path + File.separator + nombreDirectorio;
    }


    private static void almacenarArchivo(String nombreArchivo, String body) throws IOException {
        String path = obtenerPath(ConstantsAdmin.folderCSV);

        File dir = new File(path);
        dir.mkdir();

        File file = new File(dir.getPath(), nombreArchivo);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
        }


        PrintWriter writer = new PrintWriter(file);
        writer.append(body);
        writer.flush();
        writer.close();
    }


    private static String obtenerCSVdeFormulario(MainActivity context, String separador, ArrayList<AbstractArtefactDto> listArtefacts, DatacenterForm form){
        StringBuilder result = new StringBuilder();
        AbstractArtefactDto artTemp;
        result.append(form.getNroForm());
        result.append(ENTER);
        result.append(ENTER);
        // RECUPERO Artefactos
        // List<AbstractArtefactDto> artefactos = obtenerArtefactos(context, mDBManager);
        for (AbstractArtefactDto art : listArtefacts) {
            result.append(obtenerStringEsteticoArtefactos(art, context, separador));
        }
        return result.toString();
    }


    private static String obtenerStringEsteticoArtefactos(AbstractArtefactDto art, MainActivity context, String separador){
        StringBuilder result = new StringBuilder();

        switch (art.getCode()){
            case 101:
                result.append(TITLE_TABLEROTGBT).append(separador);
                break;
            case 102:
                result.append(TITLE_TABLEROAIRECHILLER).append(separador);
                break;
            case 103:
                result.append(TITLE_TABLEROCRAC).append(separador);
                break;
            case 104:
                result.append(TITLE_TABLEROINUPS).append(separador);
                break;
            case 105:
                result.append(TITLE_LOADUPS).append(separador);
                break;
            case 106:
                result.append(TITLE_GRUPOELECTROGENO).append(separador);
                break;
            case 107:
                result.append(TITLE_AIRECRAC).append(separador);
                break;
            case 108:
                result.append(TITLE_AIRECHILLER).append(separador);
                break;
            case 109:
                result.append(TITLE_INCENDIO).append(separador);
                break;
            case 110:
                result.append(TITLE_PRESOSTATO).append(separador);
                break;
            case 111:
                result.append(TITLE_AIREACONDICIONADO).append(separador);
                break;
            case 112:
                result.append(TITLE_TABLEROPDR).append(separador);
                break;
            case 113:
                result.append(TITLE_PRESURIZACIONESCALERA).append(separador);
                break;
            case 114:
                result.append(TITLE_ESTRACTORAIRE).append(separador);
                break;
            case 115:
                result.append(TITLE_PRESURIZACIONCANIERIA).append(separador);
                break;
            default:
                break;
        }
        if(art.getDescription() != null && !art.getDescription().equals("")){
            result.append(art.getDescription()).append(separador);
        }else{
            result.append(separador);
        }

        switch (art.getCode()){
            case 101:
                TableroTGBT a1 = (TableroTGBT) art;
                result.append(context.getResources().getString(R.string.kwr) + a1.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws) + a1.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt) + a1.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel) + a1.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel) + a1.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel) + a1.getPat()).append(separador);
                break;
            case 102:
                TableroAireChiller a2 = (TableroAireChiller) art;
                result.append(context.getResources().getString(R.string.kwr) + a2.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws) + a2.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt) + a2.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel) + a2.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel) + a2.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel) + a2.getPat()).append(separador);
                break;
            case 103:
                TableroCrac a3 = (TableroCrac) art;
                result.append(context.getResources().getString(R.string.kwr) + a3.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws) + a3.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt) + a3.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel) + a3.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel) + a3.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel) + a3.getPat()).append(separador);
                break;
            case 104:
                TableroInUps a4 = (TableroInUps) art;
                result.append(context.getResources().getString(R.string.kwr) + a4.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws) + a4.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt) + a4.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel) + a4.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel) + a4.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel) + a4.getPat()).append(separador);
                break;
            case 105:
                LoadUPS a5 = (LoadUPS) art;
                result.append(context.getResources().getString(R.string.arLabel) + a5.getPercent_r()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel) + a5.getPercent_s()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel) + a5.getPercent_t()).append(separador);
                break;
            case 106:
                GrupoElectrogeno a6 = (GrupoElectrogeno) art;
                result.append(TITLE_GRUPOELECTROGENO).append(separador);
                break;
            case 107:
                result.append(TITLE_AIRECRAC).append(separador);
                break;
            case 108:
                result.append(TITLE_AIRECHILLER).append(separador);
                break;
            case 109:
                result.append(TITLE_INCENDIO).append(separador);
                break;
            case 110:
                result.append(TITLE_PRESOSTATO).append(separador);
                break;
            case 111:
                result.append(TITLE_AIREACONDICIONADO).append(separador);
                break;
            case 112:
                result.append(TITLE_TABLEROPDR).append(separador);
                break;
            case 113:
                result.append(TITLE_PRESURIZACIONESCALERA).append(separador);
                break;
            case 114:
                result.append(TITLE_ESTRACTORAIRE).append(separador);
                break;
            case 115:
                result.append(TITLE_PRESURIZACIONCANIERIA).append(separador);
                break;
            default:
                break;
        }


        String PIPE = " | ";

        result.append(separador);

        result.append(ENTER);


        return result.toString();

    }


    public static final String folderCSV = "IPLAN-App";

    public static String obtenerPathDeArchivo(String fileName){
        String result;
        result = obtenerPath(folderCSV) + File.separator + fileName;
        return result;
    }


    public static void copyFiles(String srcPath, Uri dst, ContentResolver cr) throws IOException {
        File src = new File(srcPath);
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = getFileOutputStreamFromUri(cr, dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally {
            in.close();
        }
    }

    private static FileOutputStream getFileOutputStreamFromUri(ContentResolver cr, Uri uri){
        OutputStream os = null;
        FileOutputStream fos = null;
        try {
            //	fos = cr.openInputStream(uri);
            os = cr.openOutputStream(uri);
            fos = (FileOutputStream)os;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fos;

    }

/*
    public static void deleteDataBackUp(Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteDataBackUp();
        dbm.close();
    }*/
}
