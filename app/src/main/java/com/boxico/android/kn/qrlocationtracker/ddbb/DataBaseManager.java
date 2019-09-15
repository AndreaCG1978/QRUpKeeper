package com.boxico.android.kn.qrlocationtracker.ddbb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;



import com.boxico.android.kn.qrlocationtracker.ItemDto;
import com.boxico.android.kn.qrlocationtracker.util.ConstantsAdmin;



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


    public Cursor cursorItems(double lat1, double lat2, double long1, double long2, double diference) {
//        String sortOrder = ConstantsAdmin.KEY_APELLIDO + " COLLATE LOCALIZED ASC";

        String selection ="(" + ConstantsAdmin.KEY_LATITUDE + " between ? and ?) AND (" + ConstantsAdmin.KEY_LONGITUDE + " between ? and ?)";


        //select * from points where lat between ? and ? and lon between ? and ?

		String[] selectionArgs = { String.valueOf(lat1), String.valueOf(lat2),String.valueOf(long1),String.valueOf(long2) };

		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_ITEM, null, selection, selectionArgs, null, null, null, null );
		}
		return c;
    }

	public Cursor cursorItems(double lat1, double long1, double diference) {


	//	String selection ="( (abs(abs(" + ConstantsAdmin.KEY_LATITUDE + ")- abs(?))<?) AND (abs(abs(" + ConstantsAdmin.KEY_LONGITUDE + ")- abs(?))<?))";
		String selection =" (abs(abs(" + ConstantsAdmin.KEY_LATITUDE + ")- abs(?))<0.001) AND (abs(abs(" + ConstantsAdmin.KEY_LONGITUDE + ")- abs(?))<0.001)";
	//	String selection = null;
		//String[] selectionArgs = { String.valueOf(lat1), "0.0001" ,String.valueOf(long1),"0.0001"};
		String[] selectionArgs = { String.valueOf(lat1), String.valueOf(long1) };
		//String[] selectionArgs = null;

		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_ITEM, null, selection, selectionArgs, null, null, null, null );

		}
		return c;
	}


     

}
