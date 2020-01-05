package com.boxico.android.kn.qrupkeeper.ddbb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;

class DataBaseHelper extends SQLiteOpenHelper {



    private static final String DATABASE_CREATE_TABLERO_TGBT = "create table if not exists " + ConstantsAdmin.TABLE_TABLERO_TGBT +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_KWR + " text, "
            + ConstantsAdmin.KEY_KWS + " text, "
            + ConstantsAdmin.KEY_KWT + " text, "
            + ConstantsAdmin.KEY_PAR + " text, "
            + ConstantsAdmin.KEY_PAS + " text, "
            + ConstantsAdmin.KEY_PAT + " text); ";

    private static final String DATABASE_CREATE_TABLERO_AIRECHILLER = "create table if not exists " + ConstantsAdmin.TABLE_TABLERO_AIRECHILLER +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_KWR + " text, "
            + ConstantsAdmin.KEY_KWS + " text, "
            + ConstantsAdmin.KEY_KWT + " text, "
            + ConstantsAdmin.KEY_PAR + " text, "
            + ConstantsAdmin.KEY_PAS + " text, "
            + ConstantsAdmin.KEY_PAT + " text); ";


    private static final String DATABASE_CREATE_TABLERO_CRAC = "create table if not exists " + ConstantsAdmin.TABLE_TABLERO_CRAC +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_KWR + " text, "
            + ConstantsAdmin.KEY_KWS + " text, "
            + ConstantsAdmin.KEY_KWT + " text, "
            + ConstantsAdmin.KEY_PAR + " text, "
            + ConstantsAdmin.KEY_PAS + " text, "
            + ConstantsAdmin.KEY_PAT + " text); ";


    private static final String DATABASE_CREATE_TABLERO_INUPS = "create table if not exists " + ConstantsAdmin.TABLE_TABLERO_INUPS +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_KWR + " text, "
            + ConstantsAdmin.KEY_KWS + " text, "
            + ConstantsAdmin.KEY_KWT + " text, "
            + ConstantsAdmin.KEY_PAR + " text, "
            + ConstantsAdmin.KEY_PAS + " text, "
            + ConstantsAdmin.KEY_PAT + " text); ";


    private static final String DATABASE_CREATE_LOAD_UPS = "create table if not exists " + ConstantsAdmin.TABLE_LOAD_UPS +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ALARM + " integer, "
            + ConstantsAdmin.KEY_PAR + " text, "
            + ConstantsAdmin.KEY_PAS + " text, "
            + ConstantsAdmin.KEY_PAT + " text); ";


    private static final String DATABASE_CREATE_FORMS = "create table if not exists " + ConstantsAdmin.TABLE_FORMS +
            "(" + ConstantsAdmin.KEY_ROWID +" integer, "
            + ConstantsAdmin.KEY_NROFORM + " text, "
            + ConstantsAdmin.KEY_DATACENTERID + " integer, "
            + ConstantsAdmin.KEY_DATACENTERNAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text); ";

    private static final String DATABASE_CREATE_LOGIN = "create table if not exists " + ConstantsAdmin.TABLE_LOGIN +
            "(" + ConstantsAdmin.KEY_ROWID +" integer, "
            + ConstantsAdmin.KEY_USER + " text, "
            + ConstantsAdmin.KEY_PASSWORD + " text); ";


    private static final String DATABASE_CREATE_ITEMS = "create table if not exists " + ConstantsAdmin.TABLE_ITEM +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_IDENTIFICATION + " text, "
            + ConstantsAdmin.KEY_LATITUDE + " real, "
            + ConstantsAdmin.KEY_LONGITUDE + " real);";


    private static final String DATABASE_CREATE_GOTOURL = "create table if not exists " + ConstantsAdmin.TABLE_GOTO_URL +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_DISTANCE + " real, "
            + ConstantsAdmin.KEY_LATITUDE + " real, "
            + ConstantsAdmin.KEY_LONGITUDE + " real, "
            + ConstantsAdmin.KEY_LATITUDE_ORIGIN + " real, "
            + ConstantsAdmin.KEY_LONGITUDE_ORIGIN + " real, "
            + ConstantsAdmin.KEY_RADIO + " text default 0.0005, "
            + ConstantsAdmin.KEY_URL + " text);";

	public DataBaseHelper(Context context) {
         super(context, ConstantsAdmin.DATABASE_NAME, null, ConstantsAdmin.DATABASE_VERSION);
    }

	 @Override
     public void onCreate(SQLiteDatabase db) {

	   //  db.execSQL(DATABASE_CREATE_ITEMS);
       //  db.execSQL(DATABASE_CREATE_GOTOURL);
         db.execSQL(DATABASE_CREATE_TABLERO_TGBT);
         db.execSQL(DATABASE_CREATE_TABLERO_AIRECHILLER);
         db.execSQL(DATABASE_CREATE_TABLERO_CRAC);
         db.execSQL(DATABASE_CREATE_TABLERO_INUPS);
         db.execSQL(DATABASE_CREATE_LOAD_UPS);
         db.execSQL(DATABASE_CREATE_FORMS);
         db.execSQL(DATABASE_CREATE_LOGIN);
     }

     public void deleteAll(SQLiteDatabase db) {
        // db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_ITEM + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         //db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_GOTO_URL + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_TGBT + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_AIRECHILLER + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_CRAC + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_INUPS + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_LOAD_UPS + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_FORMS + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_LOGIN + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         onCreate(db);
     }
	 
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w(ConstantsAdmin.TAG, "Upgrading database from version " + oldVersion + " to "
                 + newVersion + ", which will destroy all old data");
         //db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_ITEM);
         //db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_GOTO_URL);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_TABLERO_TGBT);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_TABLERO_AIRECHILLER);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_TABLERO_CRAC);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_TABLERO_INUPS);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_LOAD_UPS);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_FORMS);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_LOGIN);
         onCreate(db);
     }

     public static final String SIZE_ITEM = "select count(" + ConstantsAdmin.KEY_ROWID +") from " + ConstantsAdmin.TABLE_ITEM + "  where " + ConstantsAdmin.KEY_ROWID + " > 0";
    public static final String SIZE_LOGIN = "select count(" + ConstantsAdmin.KEY_ROWID +") from " + ConstantsAdmin.TABLE_LOGIN + "  where " + ConstantsAdmin.KEY_ROWID + " > 0";
     public static final String SIZE_DATABACKUP = "select count(" + ConstantsAdmin.KEY_ROWID +") from " + ConstantsAdmin.TABLE_GOTO_URL + "  where " + ConstantsAdmin.KEY_ROWID + " > 0";


    /*public void deleteDataBackUp(SQLiteDatabase mDb) {
        mDb.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_GOTO_URL + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");

    }*/
}
