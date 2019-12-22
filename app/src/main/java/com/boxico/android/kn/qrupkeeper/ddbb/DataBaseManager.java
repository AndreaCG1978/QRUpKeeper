package com.boxico.android.kn.qrupkeeper.ddbb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;



import com.boxico.android.kn.qrupkeeper.ItemDto;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.boxico.android.kn.qrupkeeper.util.DataBackUp;


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
     /*
    public long createItem(ItemDto item) {
    	 long returnValue = item.getId();
         ContentValues initialValues = new ContentValues();
         initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
         initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());

    	 if(item.getId() == -1 ){
			 returnValue = mDb.insert(ConstantsAdmin.TABLE_ITEM, null, initialValues);
    	 }else{
			 mDb.update(ConstantsAdmin.TABLE_ITEM, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
    	 }
    	 return returnValue;
	}
*/

	public long createTableroTGBT(TableroTGBT item) {
		long returnValue = item.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_KWR, item.getKwr());
		initialValues.put(ConstantsAdmin.KEY_KWS, item.getKws());
		initialValues.put(ConstantsAdmin.KEY_KWT, item.getKwt());
		initialValues.put(ConstantsAdmin.KEY_PAR, item.getPar());
		initialValues.put(ConstantsAdmin.KEY_PAS, item.getPas());
		initialValues.put(ConstantsAdmin.KEY_PAT, item.getPat());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());

		if(item.getId() == -1 ){
			returnValue = mDb.insert(ConstantsAdmin.TABLE_TABLERO_TGBT, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_TGBT, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
		return returnValue;
	}


	public long createTableroAIRECHILLER(TableroAireChiller item) {
		long returnValue = item.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_KWR, item.getKwr());
		initialValues.put(ConstantsAdmin.KEY_KWS, item.getKws());
		initialValues.put(ConstantsAdmin.KEY_KWT, item.getKwt());
		initialValues.put(ConstantsAdmin.KEY_PAR, item.getPar());
		initialValues.put(ConstantsAdmin.KEY_PAS, item.getPas());
		initialValues.put(ConstantsAdmin.KEY_PAT, item.getPat());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		if(item.getId() == -1 ){
			returnValue = mDb.insert(ConstantsAdmin.TABLE_TABLERO_AIRECHILLER, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_AIRECHILLER, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
		return returnValue;
	}


	public long createTableroCRAC(TableroCrac item) {
		long returnValue = item.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_KWR, item.getKwr());
		initialValues.put(ConstantsAdmin.KEY_KWS, item.getKws());
		initialValues.put(ConstantsAdmin.KEY_KWT, item.getKwt());
		initialValues.put(ConstantsAdmin.KEY_PAR, item.getPar());
		initialValues.put(ConstantsAdmin.KEY_PAS, item.getPas());
		initialValues.put(ConstantsAdmin.KEY_PAT, item.getPat());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		if(item.getId() == -1 ){
			returnValue = mDb.insert(ConstantsAdmin.TABLE_TABLERO_CRAC, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_CRAC, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
		return returnValue;
	}




	public long createTableroINUPS(TableroInUps item) {
		long returnValue = item.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_KWR, item.getKwr());
		initialValues.put(ConstantsAdmin.KEY_KWS, item.getKws());
		initialValues.put(ConstantsAdmin.KEY_KWT, item.getKwt());
		initialValues.put(ConstantsAdmin.KEY_PAR, item.getPar());
		initialValues.put(ConstantsAdmin.KEY_PAS, item.getPas());
		initialValues.put(ConstantsAdmin.KEY_PAT, item.getPat());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		if(item.getId() == -1 ){
			returnValue = mDb.insert(ConstantsAdmin.TABLE_TABLERO_INUPS, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_INUPS, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
		return returnValue;
	}


	public long createLoadUPS(LoadUPS item) {
		long returnValue = item.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getAlarma().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_ALARM, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_ALARM, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_PAR, item.getPercent_r());
		initialValues.put(ConstantsAdmin.KEY_PAS, item.getPercent_s());
		initialValues.put(ConstantsAdmin.KEY_PAT, item.getPercent_t());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		if(item.getId() == -1 ){
			returnValue = mDb.insert(ConstantsAdmin.TABLE_LOAD_UPS, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_LOAD_UPS, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
		return returnValue;
	}

	public long createForm(DatacenterForm item) {
		long returnValue = item.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NROFORM, item.getNroForm());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		initialValues.put(ConstantsAdmin.KEY_ROWID, item.getId());
	//	if(item.getId() == -1 ){
			returnValue = mDb.insert(ConstantsAdmin.TABLE_FORMS, null, initialValues);
	/*	}else{
			mDb.update(ConstantsAdmin.TABLE_FORMS, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}*/
		return returnValue;
	}


	/*
	public void createDataBackUp(DataBackUp dbu) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_URL, dbu.getUrl());
		initialValues.put(ConstantsAdmin.KEY_DISTANCE, dbu.getDistance());
		initialValues.put(ConstantsAdmin.KEY_LATITUDE, dbu.getLatitude());
		initialValues.put(ConstantsAdmin.KEY_LONGITUDE, dbu.getLongitude());
		initialValues.put(ConstantsAdmin.KEY_LATITUDE_ORIGIN, dbu.getLatitudeOrigin());
		initialValues.put(ConstantsAdmin.KEY_LONGITUDE_ORIGIN, dbu.getLongitudeOrigin());
		initialValues.put(ConstantsAdmin.KEY_RADIO, dbu.getRadio());
		mDb.insert(ConstantsAdmin.TABLE_GOTO_URL, null, initialValues);
	}
*/
	public void deleteTableroTGBT(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_TGBT, ConstantsAdmin.KEY_ROWID + "=" + String.valueOf(id), null);
	}


	public void deleteTableroAireChiller(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_AIRECHILLER, ConstantsAdmin.KEY_ROWID + "=" + String.valueOf(id), null);
	}

	public void deleteTableroCrac(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_CRAC, ConstantsAdmin.KEY_ROWID + "=" + String.valueOf(id), null);
	}


	public void deleteTableroInUps(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_INUPS, ConstantsAdmin.KEY_ROWID + "=" + String.valueOf(id), null);
	}


	public void deleteLoadUps(int id){
		mDb.delete(ConstantsAdmin.TABLE_LOAD_UPS, ConstantsAdmin.KEY_ROWID + "=" + String.valueOf(id), null);
	}

	public void deleteForm(int id){
		mDb.delete(ConstantsAdmin.TABLE_FORMS, ConstantsAdmin.KEY_ROWID + "=" + String.valueOf(id), null);
	}



	public long tableItemSize(){
    	 long result;
    	 SQLiteStatement s = mDb.compileStatement(DataBaseHelper.SIZE_ITEM);
    	 result = s.simpleQueryForLong();
    	 return result;
     }


	public Cursor cursorItems(double lat1, double long1, String diference) {

		String selection =" (abs(abs(" + ConstantsAdmin.KEY_LATITUDE + ")- abs(?))<"+diference+") AND (abs(abs(" + ConstantsAdmin.KEY_LONGITUDE + ")- abs(?))<"+diference+")";

		//String[] selectionArgs = { String.valueOf(lat1), "0.0001" ,String.valueOf(long1),"0.0001"};
		String[] selectionArgs = { String.valueOf(lat1), String.valueOf(long1) };
		//String[] selectionArgs = null;

		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_ITEM, null, selection, selectionArgs, null, null, null, null );

		}
		return c;
	}


	public Cursor cursorTableroTGBT() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_TABLERO_TGBT, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorTableroAireChiller() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_TABLERO_AIRECHILLER, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorTableroCrac() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_TABLERO_CRAC, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorTableroInUps() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_TABLERO_INUPS, null, null, null, null, null, null, null );
		}
		return c;
	}


	public Cursor cursorLoadUps() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_LOAD_UPS, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorForms() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_FORMS, null, null, null, null, null, null, null );
		}
		return c;
	}




}
