package com.boxico.android.kn.qrupkeeper.util;


import android.content.Context;
import android.database.Cursor;


import com.boxico.android.kn.qrupkeeper.ItemDto;

import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;

import java.util.ArrayList;
import java.util.List;

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

   // public static final String URL = "http://192.168.1.42/";
    public static final String URL = "http://172.16.2.37/";
    public static final String TABLE_TABLERO_TGBT = "tablero_tgbt";
    public static final String TABLE_TABLERO_CRAC = "tablero_crac";
    public static final String TABLE_TABLERO_INUPS = "tablero_inups";
    public static final String TABLE_TABLERO_AIRECHILLER = "tablero_airechiller";
    public static final String TABLE_LOAD_UPS = "load_ups";
    public static final String KEY_KWR = "kwr";
    public static final String KEY_KWS = "kws";
    public static final String KEY_KWT = "kwt";
    public static final String KEY_PAR = "par";
    public static final String KEY_PAS = "pas";
    public static final String KEY_PAT = "pat";
    public static final String KEY_ALARM ="alarm" ;

    public static String currentInspectorConstant = "currentInspector";



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
		TableroTGBT item = null;
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		Cursor cursor = dbm.cursorTableroTGBT();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
			name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
			kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));

		//	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroTGBT(itemId, name, kwr, kws, kwt, par, pas, pat);
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
        TableroAireChiller item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroAireChiller();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroAireChiller(itemId, name, kwr, kws, kwt, par, pas, pat);
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
        TableroCrac item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroCrac();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroCrac(itemId, name, kwr, kws, kwt, par, pas, pat);
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
        TableroInUps item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroInUps();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroInUps(itemId, name, kwr, kws, kwt, par, pas, pat);
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
        LoadUPS item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorLoadUps();
        ArrayList<AbstractArtefactDto> items = new ArrayList<AbstractArtefactDto>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            alarm = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ALARM));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new LoadUPS(itemId, name, par, pas, pat, String.valueOf(alarm));
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

/*
    public static void deleteDataBackUp(Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteDataBackUp();
        dbm.close();
    }*/
}
