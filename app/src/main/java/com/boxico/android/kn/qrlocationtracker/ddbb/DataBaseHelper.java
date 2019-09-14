package com.boxico.android.kn.qrlocationtracker.ddbb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.boxico.android.kn.qrlocationtracker.util.ConstantsAdmin;

class DataBaseHelper extends SQLiteOpenHelper {
	
	 
	private static final String DATABASE_CREATE_PRODUCT = "create table if not exists " + ConstantsAdmin.TABLE_ITEM +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_IDENTIFICATION + " text, "
            + ConstantsAdmin.KEY_LATITUDE + " real, "
            + ConstantsAdmin.KEY_LONGITUDE + " real);";
	
  
	public DataBaseHelper(Context context) {
         super(context, ConstantsAdmin.DATABASE_NAME, null, ConstantsAdmin.DATABASE_VERSION);
    }

	 @Override
     public void onCreate(SQLiteDatabase db) {

	    db.execSQL(DATABASE_CREATE_PRODUCT);
     }

     public void deleteAll(SQLiteDatabase db) {
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_ITEM + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         //onCreate(db);
     }
	 
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w(ConstantsAdmin.TAG, "Upgrading database from version " + oldVersion + " to "
                 + newVersion + ", which will destroy all old data");
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_ITEM);
         onCreate(db);
     }

     public static final String SIZE_ITEM = "select count(" + ConstantsAdmin.KEY_ROWID +") from " + ConstantsAdmin.TABLE_ITEM + "  where " + ConstantsAdmin.KEY_ROWID + " > 0";


	 	

}
