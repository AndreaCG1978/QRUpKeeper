package com.boxico.android.kn.qrlocationtracker.util;


import android.content.Context;
import android.database.Cursor;


import com.boxico.android.kn.qrlocationtracker.ItemDto;

import com.boxico.android.kn.qrlocationtracker.ddbb.DataBaseManager;

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

	public static long createItem(ItemDto item, Context ctx) {
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		long id = dbm.createItem(item);
		dbm.close();
		return id;
	}


	public static void deleteItem(ItemDto item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteItem(item.getId());
        dbm.close();
    }

    public static void createDataBackUp(DataBackUp dbu, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createDataBackUp(dbu);
        dbm.close();
    }

	public static long getItemSize(Context ctx){
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		long size = dbm.tableItemSize();
		dbm.close();
		return size;

	}

    public static long getDataBackUpSize(Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        long size = dbm.tableUrlSize();
        dbm.close();
        return size;

    }


	public static void deleteAll(Context ctx) {
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		dbm.deleteAll();
		dbm.close();
	}


	public static List getItems(Context ctx, double lat1, double long1, String diference) {
		long itemId;
		String name;
		String description;
		String identification;
		double latitude;
		double longitude;
		ItemDto item = null;
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		Cursor cursor = dbm.cursorItems(lat1, long1, diference);
		List items = new ArrayList<>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			itemId = cursor.getLong(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
			name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
			description = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
			identification = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDENTIFICATION));
			latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LATITUDE));
			longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LONGITUDE));
			item = new ItemDto(itemId, name, description, identification, latitude, longitude);
			items.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		dbm.close();
		return items;
	}


    public static List getItems(Context ctx) {
        long itemId;
        String name;
        String description;
        String identification;
        double latitude;
        double longitude;
        ItemDto item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorItems();
        List items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getLong(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            description = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            identification = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDENTIFICATION));
            latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LATITUDE));
            longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LONGITUDE));
            item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static DataBackUp getDataBackUp(Context ctx) {
        String url = null;
        double distance = 0;
        double latitude, longitude, latitudeO, longitudeO;
        String radio = null;
        DataBackUp dbu = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorDataBackUp();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            url = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_URL));
            distance = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DISTANCE));
            latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LATITUDE));
			longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LONGITUDE));
            latitudeO = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LATITUDE_ORIGIN));
            longitudeO = cursor.getDouble(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LONGITUDE_ORIGIN));
            radio = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_RADIO));

            dbu = new DataBackUp(url, distance,latitude, longitude, latitudeO, longitudeO, radio);

        }
        cursor.close();
        dbm.close();
        return dbu;
    }


    public static void deleteDataBackUp(Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteDataBackUp();
        dbm.close();
    }
}
