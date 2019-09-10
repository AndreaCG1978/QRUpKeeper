package com.boxico.android.kn.qrlocationtracker.util;


import com.boxico.android.kn.qrlocationtracker.ddbb.DataBaseManager;

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


}
