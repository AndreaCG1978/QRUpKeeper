package com.boxico.android.kn.qrupkeeper.util;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;


import com.boxico.android.kn.qrupkeeper.ItemDto;

import com.boxico.android.kn.qrupkeeper.MainActivity;
import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.loader.content.CursorLoader;

public class ConstantsAdmin {
	public static final String TABLE_ITEM = "tableItem";
	public static final String KEY_ROWID = "rowId" ;
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_IDENTIFICATION = "identification";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_LONGITUDE = "longitude";

	public static final String DATABASE_NAME = "QRLocationTrackerDB";
	public static final int DATABASE_VERSION = 1;
	public static final String TAG = "DataBaseManager";
    public static final String TABLE_GOTO_URL = "tableGoToUrl";
    public static final String KEY_URL = "url";
	public static final String KEY_DISTANCE = "distance";
    public static final String KEY_LONGITUDE_ORIGIN = "longitudeOrigin" ;
    public static final String KEY_LATITUDE_ORIGIN = "latitudeOrigin" ;
    public static final String KEY_RADIO = "radio";

  //  public static final String URL = "http://192.168.1.42/";
    public static final String URL = "http://172.16.2.37/";
    public static final String TABLE_TABLERO_TGBT = "tablero_tgbt";
    public static final String TABLE_TABLERO_CRAC = "tablero_crac";
    public static final String TABLE_TABLERO_INUPS = "tablero_inups";
    public static final String TABLE_TABLERO_AIRECHILLER = "tablero_airechiller";
    public static final String TABLE_LOGIN = "tabla_login";
    public static final String TABLE_LOAD_UPS = "load_ups";
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


    public static String currentInspectorConstant = "currentInspector";
    public static String KEY_USER = "usuario";


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
                result = "Tableros TGBT";
                break;
            case 102:
                result = "Tableros Aire/Chiller";
                break;
            case 103:
                result = "Tableros Crac";
                break;
            case 104:
                result = "Tableros In-UPS";
                break;
            case 105:
                result = "Load UPS";
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



    public static long getItemSize(Context ctx){
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		long size = dbm.tableItemSize();
		dbm.close();
		return size;

	}

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
		//	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroTGBT(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB);
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
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroAireChiller(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB);
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
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroCrac(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteID);
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
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroInUps(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB);
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

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new LoadUPS(itemId, name, par, pas, pat, String.valueOf(alarm), codigo,-1, idRemoteDB);
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


/*
    public static void deleteDataBackUp(Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteDataBackUp();
        dbm.close();
    }*/
}
