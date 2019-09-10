package com.boxico.android.kn.qrlocationtracker.ddbb;


import android.content.ContentValues;
import android.content.Context;
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

/*	public CursorLoader cursorLoaderCategoriasPersonalesActivasPorNombre(String paramNombre, Context context) {
		String selection = null;
		if(paramNombre != null && !paramNombre.equals("")){
			// result = mDb.query(ConstantsAdmin.TABLA_CATEGORIA, new String[] {ConstantsAdmin.KEY_ROWID, ConstantsAdmin.KEY_NOMBRE_CATEGORIA, ConstantsAdmin.KEY_CATEGORIA_ACTIVA, ConstantsAdmin.KEY_CATEGORIA_TIPO_DATO_EXTRA},"(" + ConstantsAdmin.KEY_NOMBRE_CATEGORIA + " LIKE '%" + paramNombre + "%') AND (" + ConstantsAdmin.KEY_CATEGORIA_ACTIVA + " = 1)", null, null, null, null);
			selection  = " LIKE '%" + paramNombre + "%') AND (" + ConstantsAdmin.KEY_CATEGORIA_ACTIVA + " = 1)";
		}else{
			selection  = "(" + ConstantsAdmin.KEY_CATEGORIA_ACTIVA + " = 1)";
		}

		return new CursorLoader( context, null, new String[] {ConstantsAdmin.KEY_ROWID, ConstantsAdmin.KEY_NOMBRE_CATEGORIA, ConstantsAdmin.KEY_CATEGORIA_ACTIVA, ConstantsAdmin.KEY_CATEGORIA_TIPO_DATO_EXTRA}, selection, null, null)
		{
			@Override
			public Cursor loadInBackground()
			{
				// You better know how to get your database.
				// You can use any query that returns a cursor.
				Cursor c = null;
				if(mDb.isOpen()){
					c = mDb.query(ConstantsAdmin.TABLA_CATEGORIA_PERSONALES, getProjection(), getSelection(), getSelectionArgs(), null, null, getSortOrder(), null );
				}
				return c;
			}
		};

	}
*/

     

}
