package com.boxico.android.kn.qrlocationtracker.ddbb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import androidx.loader.content.CursorLoader;

import com.boxico.android.kn.qrlocationtracker.ItemDto;
import com.boxico.android.kn.qrlocationtracker.util.ConstantsAdmin;

import java.util.List;

public class DataBaseManager {
   
    
	private DataBaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private Context mCtx;
	private boolean isOpened = false;

	private static final DataBaseManager instanciaUnica = new DataBaseManager();

	private DataBaseManager() {
	 	super();
	}

	public void createBD(){
		mDbHelper.onCreate(mDb);
	}

	public static DataBaseManager getInstance(Context ctx) {
	 	instanciaUnica.setmCtx(ctx);
	 	return instanciaUnica;
	}

	public void upgradeDB(){
		mDbHelper.onUpgrade(mDb, 1, 2);
	}

	private void setmCtx(Context mCtx) {
		this.mCtx = mCtx;
	}

    public void open() throws SQLException {
		if(!this.isOpened) {
			mDbHelper = new DataBaseHelper(mCtx);
			mDb = mDbHelper.getWritableDatabase();
			this.isOpened = true;
		}
	}

	public void deleteAll(){
		mDbHelper.deleteAll(mDb);
	}

	public void close() {
		if(this.isOpened) {
			mDbHelper.close();
			this.isOpened = false;
		}
    }
     
    public long createItem(ItemDto item) {
    	 long returnValue = item.getId();
         ContentValues initialValues = new ContentValues();
         initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
         initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
         initialValues.put(ConstantsAdmin.KEY_IDENTIFICATION, item.getIdentification());
		 initialValues.put(ConstantsAdmin.KEY_LATITUDE, item.getLatitude());
		 initialValues.put(ConstantsAdmin.KEY_LONGITUDE, item.getLongitude());
    	 if(item.getId() == -1 ){
			 returnValue = mDb.insert(ConstantsAdmin.TABLE_ITEM, null, initialValues);
    	 }else{
			 mDb.update(ConstantsAdmin.TABLE_ITEM, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
    	 }
    	 return returnValue;
	}

	public void deleteItem(long id){
		mDb.delete(ConstantsAdmin.TABLE_ITEM, ConstantsAdmin.KEY_ROWID + "=" + String.valueOf(id), null);
	}

     public long tableItemSize(){
    	 long result;
    	 SQLiteStatement s = mDb.compileStatement(DataBaseHelper.SIZE_ITEM);
    	 result = s.simpleQueryForLong();
    	 return result;
     }

/*
    public CursorLoader cursorLoaderItems(Context context, double lat1, double lat2, double long1, double long2) {
//        String sortOrder = ConstantsAdmin.KEY_APELLIDO + " COLLATE LOCALIZED ASC";

        String querySelectionItemsBetween ="(" + ConstantsAdmin.KEY_LATITUDE + " between " + lat1 + " and " + lat2 + ") AND (" + ConstantsAdmin.KEY_LONGITUDE + " between " + long1 + " and "+ long2 +")";
        //select * from points where lat between ? and ? and lon between ? and ?

        return new CursorLoader( context, null, null, querySelectionItemsBetween, null, null)
        {
            @Override
            public Cursor loadInBackground()
            {
                Cursor c = null;
                if(mDb.isOpen()){
                    c = mDb.query(ConstantsAdmin.TABLE_ITEM, getProjection(), getSelection(), getSelectionArgs(), null, null, getSortOrder(), null );
                }
                return c;
            }
        };

    }
*/


     

}
