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
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
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
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
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
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
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
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
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
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ALARM + " integer, "
            + ConstantsAdmin.KEY_PAR + " text, "
            + ConstantsAdmin.KEY_PAS + " text, "
            + ConstantsAdmin.KEY_PAT + " text); ";

    private static final String DATABASE_CREATE_GRUPOELECTROGENO = "create table if not exists " + ConstantsAdmin.TABLE_GRUPOELECTROGENO +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ALARM + " integer, "
            + ConstantsAdmin.KEY_AUTO + " text, "
            + ConstantsAdmin.KEY_CARGADORBAT + " text, "
            + ConstantsAdmin.KEY_PERCENTCOMB + " text, "
            + ConstantsAdmin.KEY_PRECALENT + " text, "
            + ConstantsAdmin.KEY_TEMPERATURA + " text); ";


    private static final String DATABASE_CREATE_AIRE_CRAC = "create table if not exists " + ConstantsAdmin.TABLE_AIRECRAC +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_FUNCIONAOK + " text, "
            + ConstantsAdmin.KEY_TEMPERATURA + " text); ";

    private static final String DATABASE_CREATE_AIRE_CHILLER = "create table if not exists " + ConstantsAdmin.TABLE_AIRECHILLER +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_COMP1_OK + " text, "
            + ConstantsAdmin.KEY_COMP2_OK + " text, "
            + ConstantsAdmin.KEY_COMP1_LOAD + " text, "
            + ConstantsAdmin.KEY_COMP2_LOAD + " text, "
            + ConstantsAdmin.KEY_PPRIM + " text, "
            + ConstantsAdmin.KEY_PSEC + " text, "
            + ConstantsAdmin.KEY_OUT + " text); ";

    private static final String DATABASE_CREATE_INCENDIO = "create table if not exists " + ConstantsAdmin.TABLE_INCENDIO +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ENERGIAA_OK + " text, "
            + ConstantsAdmin.KEY_ENERGIAB_OK + " text, "
            + ConstantsAdmin.KEY_FUNCIONAOK + " text, "
            + ConstantsAdmin.KEY_PRESION + " text); ";

    private static final String DATABASE_CREATE_INCENDIO2 = "create table if not exists " + ConstantsAdmin.TABLE_INCENDIO2 +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ENERGIAA_OK + " text, "
            + ConstantsAdmin.KEY_FM200OK + " text, "
            + ConstantsAdmin.KEY_FUNCIONAOK + " text); ";


    private static final String DATABASE_CREATE_PRESOSTATO = "create table if not exists " + ConstantsAdmin.TABLE_PRESOSTATO +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_AGUA_OK + " text, "
            + ConstantsAdmin.KEY_AIRE_OK + " text, "
            + ConstantsAdmin.KEY_AIRE_PRESION + " text, "
            + ConstantsAdmin.KEY_AGUA_PRESION + " text); ";

    private static final String DATABASE_CREATE_AIREACOND = "create table if not exists " + ConstantsAdmin.TABLE_AIREACOND +
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_FUNCIONAOK + " text, "
            + ConstantsAdmin.KEY_TEMPERATURA + " text); ";

    private static final String DATABASE_CREATE_TABLEROPDR = "create table if not exists " + ConstantsAdmin.TABLE_TABLEROPDR+
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_POTTOTRA + " text, "
            + ConstantsAdmin.KEY_POTTOTRB + " text); ";

    private static final String DATABASE_CREATE_TABLEROPDR2 = "create table if not exists " + ConstantsAdmin.TABLE_TABLEROPDR2+
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_POTTOTRA + " text); ";

    private static final String DATABASE_CREATE_PRESURIZACIONESCALERA = "create table if not exists " + ConstantsAdmin.TABLE_PRESURIZACIONESCALERA+
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ARRANQUE + " text, "
            + ConstantsAdmin.KEY_CORREAS + " text, "
            + ConstantsAdmin.KEY_ENGRASE + " text, "
            + ConstantsAdmin.KEY_FUNCIONAOK + " text, "
            + ConstantsAdmin.KEY_LIMPIEZA + " text, "
            + ConstantsAdmin.KEY_TIEMPO + " text); ";

    private static final String DATABASE_CREATE_ESTRACTORAIRE = "create table if not exists " + ConstantsAdmin.TABLE_ESTRACTORAIRE+
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ARRANQUE + " text, "
            + ConstantsAdmin.KEY_CORREAS + " text, "
            + ConstantsAdmin.KEY_ENGRASE + " text, "
            + ConstantsAdmin.KEY_FUNCIONAOK + " text, "
            + ConstantsAdmin.KEY_LIMPIEZA + " text); ";

    private static final String DATABASE_CREATE_PRESURIZACIONCANIERIA = "create table if not exists " + ConstantsAdmin.TABLE_PRESURIZACIONCANIERIA+
            "(" + ConstantsAdmin.KEY_ROWID +" integer primary key autoincrement, "
            + ConstantsAdmin.KEY_NAME + " text, "
            + ConstantsAdmin.KEY_DESCRIPTION + " text, "
            + ConstantsAdmin.KEY_CODE + " integer, "
            + ConstantsAdmin.KEY_IDREMOTEDB + " integer, "
            + ConstantsAdmin.KEY_ALARM + " text, "
            + ConstantsAdmin.KEY_ENCENDIDO + " text); ";

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
         db.execSQL(DATABASE_CREATE_GRUPOELECTROGENO);
         db.execSQL(DATABASE_CREATE_AIRE_CRAC);
         db.execSQL(DATABASE_CREATE_AIRE_CHILLER);
         db.execSQL(DATABASE_CREATE_INCENDIO);
         db.execSQL(DATABASE_CREATE_INCENDIO2);
         db.execSQL(DATABASE_CREATE_PRESOSTATO);
         db.execSQL(DATABASE_CREATE_AIREACOND);
         db.execSQL(DATABASE_CREATE_TABLEROPDR);
         db.execSQL(DATABASE_CREATE_TABLEROPDR2);
         db.execSQL(DATABASE_CREATE_PRESURIZACIONESCALERA);
         db.execSQL(DATABASE_CREATE_ESTRACTORAIRE);
         db.execSQL(DATABASE_CREATE_PRESURIZACIONCANIERIA);
         db.execSQL(DATABASE_CREATE_FORMS);
         db.execSQL(DATABASE_CREATE_LOGIN);
     }

     public void deleteAll(SQLiteDatabase db) {
        // db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_ITEM + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         //db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_GOTO_URL + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_TGBT );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_AIRECHILLER );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_CRAC );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLERO_INUPS );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_LOAD_UPS );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_GRUPOELECTROGENO );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_AIRECRAC );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_AIRECHILLER );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_INCENDIO );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_INCENDIO2 );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_PRESOSTATO );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_AIREACOND );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLEROPDR );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_TABLEROPDR2 );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_PRESURIZACIONESCALERA );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_ESTRACTORAIRE );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_PRESURIZACIONCANIERIA );
         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_FORMS);
//         db.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_LOGIN + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");
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
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_GRUPOELECTROGENO);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_AIRECRAC);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_AIRECHILLER);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_INCENDIO);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_INCENDIO2);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_PRESOSTATO);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_AIREACOND);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_TABLEROPDR);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_TABLEROPDR2);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_PRESURIZACIONESCALERA);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_ESTRACTORAIRE);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_PRESURIZACIONCANIERIA);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_FORMS);
         db.execSQL("DROP TABLE IF EXISTS " + ConstantsAdmin.TABLE_LOGIN);
         onCreate(db);
     }

   //  public static final String SIZE_ITEM = "select count(" + ConstantsAdmin.KEY_ROWID +") from " + ConstantsAdmin.TABLE_ITEM + "  where " + ConstantsAdmin.KEY_ROWID + " > 0";
    public static final String SIZE_LOGIN = "select count(" + ConstantsAdmin.KEY_ROWID +") from " + ConstantsAdmin.TABLE_LOGIN + "  where " + ConstantsAdmin.KEY_ROWID + " > 0";
   //  public static final String SIZE_DATABACKUP = "select count(" + ConstantsAdmin.KEY_ROWID +") from " + ConstantsAdmin.TABLE_GOTO_URL + "  where " + ConstantsAdmin.KEY_ROWID + " > 0";


    /*public void deleteDataBackUp(SQLiteDatabase mDb) {
        mDb.execSQL("DELETE FROM " + ConstantsAdmin.TABLE_GOTO_URL + " WHERE " + ConstantsAdmin.KEY_ROWID + " > -1");

    }*/
}
