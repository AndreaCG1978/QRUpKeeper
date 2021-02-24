package com.boxico.android.kn.qrupkeeper.util;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;


import com.boxico.android.kn.qrupkeeper.MainActivity;
import com.boxico.android.kn.qrupkeeper.R;
import com.boxico.android.kn.qrupkeeper.ddbb.DataBaseManager;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.AireAcond;
import com.boxico.android.kn.qrupkeeper.dtos.AireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.AireCrac;
import com.boxico.android.kn.qrupkeeper.dtos.ArtefactoValorTope;
import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.EstractorAire;
import com.boxico.android.kn.qrupkeeper.dtos.GrupoElectrogeno;
import com.boxico.android.kn.qrupkeeper.dtos.Incendio;
import com.boxico.android.kn.qrupkeeper.dtos.Incendio2;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.NombreGenerico;
import com.boxico.android.kn.qrupkeeper.dtos.Presostato;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionCanieria;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionEscalera;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroPDR;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ConstantsAdmin {

	public static final String KEY_ROWID = "rowId" ;
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "description";
    public static final String ENTER = "\n";

	public static final String DATABASE_NAME = "QRLocationTrackerDB";
	public static final int DATABASE_VERSION = 1;
	public static final String TAG = "DataBaseManager";
    public static final long tokenIplan = 27029085;
    // IP CASA
    public static final String URL = "http://192.168.1.44/";

    // IP IPLAN
  // public static final String URL = "http://190.210.92.89/";

    // IP TRABAJO
    //public static final String URL = "http://172.16.2.37/";
    public static final String TABLE_TABLERO_TGBT = "tablero_tgbt";
    public static final String TABLE_TABLERO_CRAC = "tablero_crac";
    public static final String TABLE_TABLERO_INUPS = "tablero_inups";
    public static final String TABLE_TABLERO_AIRECHILLER = "tablero_airechiller";
    public static final String TABLE_LOGIN = "tabla_login";
    public static final String TABLE_LOAD_UPS = "load_ups";
    public static final String TABLE_GRUPOELECTROGENO = "grupoelectrogeno";
    public static final String TABLE_AIRECRAC = "aire_crac" ;
    public static final String TABLE_AIRECHILLER = "aire_chiller" ;
    public static final String TABLE_INCENDIO = "incendio" ;
    public static final String TABLE_INCENDIO2 = "incendio2" ;
    public static final String TABLE_PRESOSTATO = "presostato" ;
    public static final String TABLE_AIREACOND = "aire_acond" ;
    public static final String TABLE_TABLEROPDR = "tablero_pdr" ;
    public static final String TABLE_TABLEROPDR2 = "tablero_pdr2" ;
    public static final String TABLE_PRESURIZACIONESCALERA = "presurizacion_escalera" ;
    public static final String TABLE_PRESURIZACIONCANIERIA = "presurizacion_canieria" ;
    public static final String TABLE_ESTRACTORAIRE = "estractor_aire" ;
    public static final String KEY_KWR = "kwr";
    public static final String KEY_KWS = "kws";
    public static final String KEY_KWT = "kwt";
    public static final String KEY_PAR = "par";
    public static final String KEY_PAS = "pas";
    public static final String KEY_PAT = "pat";
    public static final String KEY_ALARM ="alarm" ;
    public static final String KEY_CODE = "codigo";
    public static final String TABLE_FORMS ="tabla_forms" ;
    public static final String KEY_NROFORM = "nroForm";
    public static final String KEY_IDREMOTEDB = "idRemoteDB";
    public static final String KEY_DATACENTERNAME = "datacenterName";
    public static final String KEY_DATACENTERID = "datacenterId";
    public static final String KEY_PASSWORD = "contrasenia";
    public static final String KEY_AUTO ="autom" ;
    public static final String KEY_CARGADORBAT = "cargadorbat";
    public static final String KEY_NIVELCOMB75 = "nivelcomb75";
    public static final String KEY_PRECALENT = "precalent";
    public static final String KEY_PERCENTCOMB = "percentcomb";
    public static final String KEY_TEMPERATURA = "temperatura";
    public static final String KEY_FUNCIONAOK = "funcionaOk";
    public static final String KEY_OUT = "val_out" ;
    public static final String KEY_COMP1_LOAD = "comp1Load" ;
    public static final String KEY_COMP2_LOAD = "comp2Load" ;
    public static final String KEY_PPRIM = "pprim" ;
    public static final String KEY_PSEC = "psec" ;
    public static final String KEY_COMP1_OK = "comp1Ok" ;
    public static final String KEY_COMP2_OK = "comp2Ok" ;
    public static final String KEY_ENERGIAA_OK = "energiaAOk" ;
    public static final String KEY_ENERGIAB_OK = "energiaBOk" ;
    public static final String KEY_FM200OK = "fm200Ok" ;
    public static final String KEY_PRESION = "presion" ;
    public static final String KEY_AIRE_PRESION = "airePresion" ;
    public static final String KEY_AGUA_PRESION = "aguaPresion" ;
    public static final String KEY_AIRE_OK = "aireOk" ;
    public static final String KEY_AGUA_OK = "aguaOk" ;
    public static final String KEY_POTTOTRA = "pottotRA" ;
    public static final String KEY_POTTOTRB = "pottotRB" ;
    public static final String KEY_ARRANQUE = "arranque" ;
    public static final String KEY_CORREAS = "correas" ;
    public static final String KEY_ENGRASE = "engrase" ;
    public static final String KEY_LIMPIEZA = "limpieza" ;
    public static final String KEY_TIEMPO = "tiempo" ;
    public static final String KEY_ENCENDIDO ="encendido" ;
   // public static final String ID_QR = "CF" ;
    public static final String TITLE_TABLEROTGBT = "Tablero TGBT";
    public static final String TITLE_TABLEROAIRECHILLER  = "Tablero Aire/Chiller";
    public static final String TITLE_TABLEROCRAC = "Tablero Crah";
    public static final String TITLE_TABLEROINUPS = "Tablero In UPS";
    public static final String TITLE_LOADUPS = "Load UPS";
    public static final String TITLE_GRUPOELECTROGENO = "Grupo Electrógeno";
    public static final String TITLE_AIRECRAC = "Aire Crah";
    public static final String TITLE_AIRECHILLER = "Chiller";
    public static final String TITLE_INCENDIO = "Sala de Bombas";
    public static final String TITLE_INCENDIO2 = "Incendio";
    public static final String TITLE_PRESOSTATO = "Cañerias Incendio";
    public static final String TITLE_AIREACONDICIONADO = "Aire Acondicionado";
    public static final String TITLE_TABLEROPDR = "Tablero PDR";
    public static final String TITLE_TABLEROPDR2 = "T. Distribución Rack";
    public static final String TITLE_PRESURIZACIONESCALERA = "Presurización Escalera";
    public static final String TITLE_ESTRACTORAIRE = "Estractor Aire";
    public static final String TITLE_PRESURIZACIONCANIERIA = "Presurización Cañeria";
    public static final String PATTERN_DATE_HOUR = "dd/MM/yyyy HH:mm";
    public static final int EJECUTAR_SELEC_DATACENTER = 1;
    public static final int EJECUTAR_EDIT_FORM = 2;
    public static final int EJECUTAR_SCAN = 3;
    public static final int EJECUTAR_NUEVO_FORM = 4;
    public static final int EJECUTAR_GENERAR_CSV = 5;
    public static final String COMA = ",";
    public static final String fileEsteticoCSV = "reporteIplan.csv";
    public static final int ACTIVITY_CHOOSE_FILE=1;
    public static final String PUNTO_COMA = ";";



    public static final String currentInspectorConstant = "currentInspector";
    public static final String KEY_USER = "usuario";

    public static String mensaje;
    public static int maxValoresNumericos = 10;
    public static String expiredDate = "01-10-2020";
    public static InspectorService inspectorService;
    public static TableroService tableroService;
    public static ArtefactosValoresTopeService valoresTopesService;
    public static FormService formService = null;
    public static DatacenterService datacenterService = null;
    public static NombresGenericosService nombresGenericosService = null;

    public static List<ArtefactoValorTope> valoresTopes = null;
    public static DataCenter currentDatacenter;
    public static Inspector currentInspector;
    public static int dataCenterID;
    public static List<DataCenter> allDatacenters = null;
    public static List<NombreGenerico> nombresGenericos = null;


    public static ArrayList<Inspector> inspectors;
  //  public static AbstractArtefactDto temporalArtefact;
    public static AbstractArtefactDto selectedArtefact;
    public static DatacenterForm currentForm;
    public static MainActivity contextTemp;
    public static String separadorTemp;
    public static ArrayList<AbstractArtefactDto> listArtefactsTemp;
    public static boolean errorConnection = false;

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


    public static String getArtefactType(int code){
        String result = "";
        switch (code){
            case 101:
                result = ConstantsAdmin.TITLE_TABLEROTGBT;
                break;
            case 102:
                result = ConstantsAdmin.TITLE_TABLEROAIRECHILLER;
                break;
            case 103:
                result = ConstantsAdmin.TITLE_TABLEROCRAC;
                break;
            case 104:
                result = ConstantsAdmin.TITLE_TABLEROINUPS;
                break;
            case 105:
                result = ConstantsAdmin.TITLE_LOADUPS;
                break;
            case 106:
                result = ConstantsAdmin.TITLE_GRUPOELECTROGENO;
                break;
            case 107:
                result = ConstantsAdmin.TITLE_AIRECRAC;
                break;
            case 108:
                result = ConstantsAdmin.TITLE_AIRECHILLER;
                break;
            case 109:
                result = ConstantsAdmin.TITLE_INCENDIO;
                break;
            case 110:
                result = ConstantsAdmin.TITLE_PRESOSTATO;
                break;
            case 111:
                result = ConstantsAdmin.TITLE_AIREACONDICIONADO;
                break;
            case 112:
                result = ConstantsAdmin.TITLE_TABLEROPDR;
                break;
            case 113:
                result = ConstantsAdmin.TITLE_PRESURIZACIONESCALERA;
                break;
            case 114:
                result = ConstantsAdmin.TITLE_ESTRACTORAIRE;
                break;
            case 115:
                result = ConstantsAdmin.TITLE_PRESURIZACIONCANIERIA;
                break;
            case 116:
                result = ConstantsAdmin.TITLE_INCENDIO2;
                break;
            case 117:
                result = ConstantsAdmin.TITLE_TABLEROPDR2;
                break;
        }
        return result;
    }

    public static void createTableroTGBT(TableroTGBT item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createTableroTGBT(item);
        dbm.close();
    }



    public static void createTableroAireChiller(TableroAireChiller item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createTableroAIRECHILLER(item);
        dbm.close();
    }


    public static void createTableroCrac(TableroCrac item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createTableroCRAC(item);
        dbm.close();
    }



    public static void createTableroInUps(TableroInUps item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createTableroINUPS(item);
        dbm.close();
    }



    public static void createLoadUps(LoadUPS item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createLoadUPS(item);
        dbm.close();
    }

    public static void createGrupoElectrogeno(GrupoElectrogeno item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createGrupoElectrogeno(item);
        dbm.close();
    }


    public static void createAireCrac(AireCrac item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createAireCrac(item);
        dbm.close();
    }


    public static void createAireChiller(AireChiller item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createAireChiller(item);
        dbm.close();
    }


    public static void createIncendio(Incendio item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createIncendio(item);
        dbm.close();
    }

    public static void createIncendio2(Incendio2 item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createIncendio2(item);
        dbm.close();
    }


    public static void createPresostato(Presostato item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createPresostato(item);
        dbm.close();
    }

    public static void createAireAcond(AireAcond item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createAireAcond(item);
        dbm.close();
    }

    public static void createTableroPDR(TableroPDR item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createTableroPDR(item);
        dbm.close();
    }

    public static void createTableroPDR2(TableroPDR item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createTableroPDR2(item);
        dbm.close();
    }

    public static void createPresurizacionEscalera(PresurizacionEscalera item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createPresurizacionEscalera(item);
        dbm.close();
    }

    public static void createEstractorAire(EstractorAire item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createEstractorAire(item);
        dbm.close();
    }

    public static void createPresurizacionCanieria(PresurizacionCanieria item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createPresurizacionCanieria(item);
        dbm.close();
    }

    public static void createForm(DatacenterForm item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createForm(item);
        dbm.close();
    }

    public static void createLogin(Inspector item, Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.createLogin(item);
        dbm.close();

    }

    public static void deleteTablero(AbstractArtefactDto t, Context ctx){
        switch (t.getCode()){
            case 101:
                deleteTableroTGBT((TableroTGBT) t, ctx);
                break;
            case 102:
                deleteTableroAireChiller((TableroAireChiller) t, ctx);
                break;
            case 103:
                deleteTableroCrac((TableroCrac) t, ctx);
                break;
            case 104:
                deleteTableroInUps((TableroInUps) t, ctx);
                break;
            case 105:
                deleteLoadUps((LoadUPS) t, ctx);
                break;
            case 106:
                deleteGrupoElectrogeno((GrupoElectrogeno) t, ctx);
                break;
            case 107:
                deleteAireCrac((AireCrac) t, ctx);
                break;
            case 108:
                deleteAireChiller((AireChiller) t, ctx);
                break;
            case 109:
                deleteIncendio((Incendio) t, ctx);
                break;
            case 110:
                deletePresostato((Presostato) t, ctx);
                break;
            case 111:
                deleteAireAcond((AireAcond) t, ctx);
                break;
            case 112:
                deleteTableroPDR((TableroPDR) t, ctx);
                break;
            case 113:
                deletePresurizacionEscalera((PresurizacionEscalera) t, ctx);
                break;
            case 114:
                deleteEstractorAire((EstractorAire) t, ctx);
                break;
            case 115:
                deletePresurizacionCanieria((PresurizacionCanieria) t, ctx);
                break;
            case 116:
                deleteIncendio2((Incendio2) t, ctx);
                break;
            case 117:
                deleteTableroPDR2((TableroPDR) t, ctx);
                break;


        }

    }


    private static void deleteTableroTGBT(TableroTGBT item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroTGBT(item.getId());
        dbm.close();
    }

    private static void deleteTableroAireChiller(TableroAireChiller item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroAireChiller(item.getId());
        dbm.close();
    }


    private static void deleteTableroCrac(TableroCrac item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroCrac(item.getId());
        dbm.close();
    }

    private static void deleteTableroInUps(TableroInUps item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroInUps(item.getId());
        dbm.close();
    }

    private static void deleteLoadUps(LoadUPS item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteLoadUps(item.getId());
        dbm.close();
    }

    private static void deleteGrupoElectrogeno(GrupoElectrogeno item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteGrupoElectrogeno(item.getId());
        dbm.close();
    }

    private static void deleteAireCrac(AireCrac item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteAireCrac(item.getId());
        dbm.close();
    }


    private static void deleteAireChiller(AireChiller item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteAireChiller(item.getId());
        dbm.close();
    }

    private static void deleteIncendio(Incendio item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteIncendio(item.getId());
        dbm.close();
    }

    private static void deleteIncendio2(Incendio2 item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteIncendio2(item.getId());
        dbm.close();
    }

    private static void deletePresostato(Presostato item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deletePresostato(item.getId());
        dbm.close();
    }

    private static void deleteAireAcond(AireAcond item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteAireAcond(item.getId());
        dbm.close();
    }

    private static void deleteTableroPDR(TableroPDR item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroPDR(item.getId());
        dbm.close();
    }

    private static void deleteTableroPDR2(TableroPDR item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteTableroPDR2(item.getId());
        dbm.close();
    }

    private static void deletePresurizacionEscalera(PresurizacionEscalera item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deletePresurizacionEscalera(item.getId());
        dbm.close();
    }

    private static void deleteEstractorAire(EstractorAire item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteEstractorAire(item.getId());
        dbm.close();
    }


    private static void deletePresurizacionCanieria(PresurizacionCanieria item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deletePresurizacionCanieria(item.getId());
        dbm.close();
    }

    public static void deleteForm(DatacenterForm item, Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteForm(item.getId());
        dbm.close();
    }

    public static void deleteLogin(Context ctx){
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteLogin();
        dbm.close();
    }


/*
    public static long getItemSize(Context ctx){
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		long size = dbm.tableItemSize();
		dbm.close();
		return size;

	}
*/
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
        String desc;
        int codigo;
        int idRemoteDB;
		TableroTGBT item;
		DataBaseManager dbm = DataBaseManager.getInstance(ctx);
		dbm.open();
		Cursor cursor = dbm.cursorTableroTGBT();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB  = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
			name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
			kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
		//	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroTGBT(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB, desc);
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
        int codigo;
        int idRemoteDB;
        String desc;
        TableroAireChiller item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroAireChiller();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroAireChiller(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB, desc);
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
        int codigo;
        int idRemoteID;
        String desc;
        TableroCrac item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroCrac();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteID = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroCrac(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteID, desc);
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
        int codigo;
        int idRemoteDB;
        String desc;
        TableroInUps item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroInUps();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            kwr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWR));
            kws = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWS));
            kwt = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_KWT));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroInUps(itemId, name, kwr, kws, kwt, par, pas, pat, codigo,-1, idRemoteDB, desc);
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
        int codigo;
        int idRemoteDB;
        String desc;
        LoadUPS item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorLoadUps();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            par = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAR));
            pas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAS));
            pat = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PAT));
            alarm = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ALARM));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new LoadUPS(itemId, name, par, pas, pat, String.valueOf(alarm), codigo,-1, idRemoteDB, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getGruposElectrogeno(Context ctx) {
        int itemId;
        String name;
        String temperatura;
        String percentComb;
        int auto;
        int alarm;
        int precalent;
        int cargadorBat;
        int codigo;
        int idRemoteDB;
        String desc;
        GrupoElectrogeno item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorGrupoElectrogeno();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            temperatura = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TEMPERATURA));
            percentComb = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PERCENTCOMB));
            alarm = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ALARM));
            auto = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AUTO));
            precalent = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PRECALENT));
            cargadorBat = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CARGADORBAT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new GrupoElectrogeno(itemId, name, codigo, -1, idRemoteDB, percentComb, temperatura, String.valueOf(alarm), String.valueOf(auto), String.valueOf(precalent), String.valueOf(cargadorBat), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getAireCrac(Context ctx) {
        int itemId;
        String name;
        String temperatura;
        int funciona_ok;
        int codigo;
        int idRemoteDB;
        String desc;
        AireCrac item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorAireCrac();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            temperatura = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TEMPERATURA));
            funciona_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new AireCrac(itemId, name, codigo, -1, idRemoteDB, temperatura, String.valueOf(funciona_ok), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getAireChiller(Context ctx) {
        int itemId;
        String name;
        String comp1Load;
        String comp2Load;
        String out;
        String pprim;
        String psec;
        int comp1_ok;
        int comp2_ok;
        int codigo;
        int idRemoteDB;
        String desc;
        AireChiller item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorAireChiller();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            comp1Load = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP1_LOAD));
            comp2Load = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP2_LOAD));
            pprim = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PPRIM));
            psec = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PSEC));
            comp1_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP1_OK));
            comp2_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_COMP2_OK));
            out = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_OUT));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new AireChiller(itemId, name, codigo, -1, idRemoteDB, String.valueOf(comp1_ok), String.valueOf(comp2_ok), comp1Load, comp2Load, out, desc, pprim, psec);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getIncendio(Context ctx) {
        int itemId;
        String name;
        int energiaAOk;
        int energiaBOk;
        String presion;
        int funciona_ok;
        int codigo;
        int idRemoteDB;
        String desc;
        Incendio item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorIncendio();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            energiaAOk = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENERGIAA_OK));
            energiaBOk = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENERGIAB_OK));
            funciona_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            presion = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PRESION));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new Incendio(itemId, name, codigo, -1, idRemoteDB, String.valueOf(funciona_ok), presion, String.valueOf(energiaAOk), String.valueOf(energiaBOk), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getIncendio2(Context ctx) {
        int itemId;
        String name;
        int energiaAOk;
        int fm200Ok;
        int funciona_ok;
        int codigo;
        int idRemoteDB;
        String desc;
        Incendio2 item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorIncendio2();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            energiaAOk = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENERGIAA_OK));
            fm200Ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FM200OK));
            funciona_ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new Incendio2(itemId, name, codigo, -1, idRemoteDB, String.valueOf(funciona_ok), String.valueOf(energiaAOk), String.valueOf(fm200Ok), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getPresostato(Context ctx) {
        int itemId;
        String name;
        int agua_Ok;
        int aire_Ok;
        String agua_presion;
        String aire_presion;
        int codigo;
        int idRemoteDB;
        String desc;
        Presostato item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorPresostato();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            agua_Ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AGUA_OK));
            aire_Ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AIRE_OK));
            aire_presion = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AIRE_PRESION));
            agua_presion = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_AGUA_PRESION));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new Presostato(itemId, name, codigo, -1, idRemoteDB, String.valueOf(aire_Ok), aire_presion, String.valueOf(agua_Ok), agua_presion, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getAireAcond(Context ctx) {
        int itemId;
        String name;
        int funciona_Ok;
        String temperatura;
        int codigo;
        int idRemoteDB;
        String desc;
        AireAcond item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorAireAcond();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            funciona_Ok = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            temperatura = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TEMPERATURA));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new AireAcond(itemId, name, codigo, -1, idRemoteDB, String.valueOf(funciona_Ok), temperatura, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getTableroPDR(Context ctx) {
        int itemId;
        String name;
        String pottotA;
        String pottotB;
        int codigo;
        int idRemoteDB;
        String desc;
        TableroPDR item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroPDR();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            pottotA = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_POTTOTRA));
            pottotB = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_POTTOTRB));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroPDR(itemId, name, codigo, -1, idRemoteDB, pottotA, pottotB, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }

    public static ArrayList<AbstractArtefactDto> getTableroPDR2(Context ctx) {
        int itemId;
        String name;
        String pottotA;
        int codigo;
        int idRemoteDB;
        String desc;
        TableroPDR item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorTableroPDR2();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            pottotA = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_POTTOTRA));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new TableroPDR(itemId, name, codigo, -1, idRemoteDB, pottotA, "", desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getPresurizacionEscalera(Context ctx) {
        int itemId;
        String name;
        String arranque;
        String correas;
        String engrase;
        String funcionamiento;
        String limpieza;
        String tiempo;
        int codigo;
        int idRemoteDB;
        String desc;
        PresurizacionEscalera item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorPresurizacionEscalera();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            arranque = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ARRANQUE));
            correas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CORREAS));
            engrase = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENGRASE));
            funcionamiento = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            limpieza = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LIMPIEZA));
            tiempo = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_TIEMPO));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new PresurizacionEscalera(itemId, name, codigo, -1, idRemoteDB, arranque, tiempo, funcionamiento, engrase, limpieza, correas, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }



    public static ArrayList<AbstractArtefactDto> getEstractorAire(Context ctx) {
        int itemId;
        String name;
        String arranque;
        String correas;
        String engrase;
        String funcionamiento;
        String limpieza;
        int codigo;
        int idRemoteDB;
        String desc;
        EstractorAire item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorEstractorAire();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            arranque = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ARRANQUE));
            correas = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CORREAS));
            engrase = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENGRASE));
            funcionamiento = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_FUNCIONAOK));
            limpieza = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_LIMPIEZA));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new EstractorAire(itemId, name, codigo, -1, idRemoteDB,  arranque, funcionamiento, engrase, limpieza, correas, desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static ArrayList<AbstractArtefactDto> getPresurizacionCanieria(Context ctx) {
        int itemId;
        String name;
        int codigo;
        int alarma;
        int encendido;
        int idRemoteDB;
        String desc;
        PresurizacionCanieria item;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorPresurizacionCanieria();
        ArrayList<AbstractArtefactDto> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            idRemoteDB = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_IDREMOTEDB));
            name = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NAME));
            alarma = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ALARM));
            encendido = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ENCENDIDO));
            codigo = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_CODE));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new PresurizacionCanieria(itemId, name, codigo, -1, idRemoteDB,  String.valueOf(alarma), String.valueOf(encendido), desc);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        dbm.close();
        return items;
    }


    public static DatacenterForm getForm(Context ctx) {
        int itemId;
        String nroForm;
        String desc;
        String dcname;
        int datacenterId;
        DatacenterForm item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorForms();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            nroForm = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_NROFORM));
            desc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DESCRIPTION));
            dcname = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DATACENTERNAME));
            datacenterId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_DATACENTERID));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new DatacenterForm();
            item.setNroForm(nroForm);
            item.setDescription(desc);
            item.setId(itemId);
            item.setDatacenterName(dcname);
            item.setDatacenterId(datacenterId);
        }
        cursor.close();
        dbm.close();
        return item;
    }


    public static Inspector getLogin(Context ctx) {
        int itemId;
        String usr;
        String psw;
        Inspector item = null;
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        Cursor cursor = dbm.cursorLogin();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_ROWID));
            usr = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_USER));
            psw = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsAdmin.KEY_PASSWORD));

            //	item = new ItemDto(itemId, name, description, identification, latitude, longitude);
            item = new Inspector();
            item.setUsr(usr);
            item.setPsw(psw);
            item.setId(itemId);
        }
        cursor.close();
        dbm.close();
        return item;
    }


    public static void exportarCSVEstetico(MainActivity context, String separador, ArrayList listArtefacts, DatacenterForm form){
		/*Asociacion canStore;
		Boolean boolValue;
		String msg;*/
        String body;
        try
        {

            if (ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (!ActivityCompat.shouldShowRequestPermissionRationale(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }

            //		canStore = comprobarSDCard(context);
	/*		boolValue = (Boolean)canStore.getKey();
			msg = (String) canStore.getValue();
			if(boolValue){*/
            body = obtenerCSVdeFormulario(context, separador, listArtefacts, form);
            almacenarArchivo(body);
           // mensaje = context.getString(R.string.mensaje_exito_exportar_csv);
		/*	}else{
				mensaje = msg;
			}*/

        }catch (Exception e) {
        //    mensaje = context.getString(R.string.error_exportar_csv);
        }
    }


    public static void exportarCSVEstetico(){
		/*Asociacion canStore;
		Boolean boolValue;
		String msg;*/
        String body;
        try
        {

            if (ContextCompat.checkSelfPermission(contextTemp,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (!ActivityCompat.shouldShowRequestPermissionRationale(contextTemp,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(contextTemp,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }

            //		canStore = comprobarSDCard(context);
	/*		boolValue = (Boolean)canStore.getKey();
			msg = (String) canStore.getValue();
			if(boolValue){*/
            body = obtenerCSVdeFormulario(contextTemp, separadorTemp, listArtefactsTemp, currentForm);
            almacenarArchivo(body);
            // mensaje = context.getString(R.string.mensaje_exito_exportar_csv);
		/*	}else{
				mensaje = msg;
			}*/

        }catch (Exception e) {
            //    mensaje = context.getString(R.string.error_exportar_csv);
        }
    }


    private static String obtenerPath(){
        String path = Environment.getExternalStorageDirectory().toString();
        return path + File.separator + ConstantsAdmin.folderCSV;
    }


    private static void almacenarArchivo(String body) throws IOException {
        String path = obtenerPath();

        File dir = new File(path);
        dir.mkdir();

        File file = new File(dir.getPath(), ConstantsAdmin.fileEsteticoCSV);
        if(!file.exists()){
            file.createNewFile();
        }


        PrintWriter writer = new PrintWriter(file);
        writer.append(body);
        writer.flush();
        writer.close();
    }


    private static String obtenerCSVdeFormulario(MainActivity context, String separador, ArrayList<AbstractArtefactDto> listArtefacts, DatacenterForm form){
        StringBuilder result = new StringBuilder();
     //   AbstractArtefactDto artTemp;
        result.append(form.getNroForm());
        result.append(ENTER);
        result.append(ENTER);
        // RECUPERO Artefactos
        // List<AbstractArtefactDto> artefactos = obtenerArtefactos(context, mDBManager);
        for (AbstractArtefactDto art : listArtefacts) {
            result.append(obtenerStringEsteticoArtefactos(art, context, separador));
        }
        return result.toString();
    }


    private static String obtenerStringEsteticoArtefactos(AbstractArtefactDto art, MainActivity context, String separador){
        StringBuilder result = new StringBuilder();

        switch (art.getCode()){
            case 101:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_TABLEROTGBT).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_TABLEROTGBT).append(separador);
                }
                break;
            case 102:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_TABLEROAIRECHILLER).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_TABLEROAIRECHILLER).append(separador);
                }
                break;
            case 103:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_TABLEROCRAC).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_TABLEROCRAC).append(separador);
                }
                break;
            case 104:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_TABLEROINUPS).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_TABLEROINUPS).append(separador);
                }
                break;
            case 105:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_LOADUPS).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_LOADUPS).append(separador);
                }
                break;
            case 106:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_GRUPOELECTROGENO).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_GRUPOELECTROGENO).append(separador);
                }
                break;
            case 107:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_AIRECRAC).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_AIRECRAC).append(separador);
                }
                break;
            case 108:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_AIRECHILLER).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_AIRECHILLER).append(separador);
                }
                break;
            case 109:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_INCENDIO).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_INCENDIO).append(separador);
                }
                break;
            case 110:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_PRESOSTATO).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_PRESOSTATO).append(separador);
                }
                break;
            case 111:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_AIREACONDICIONADO).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_AIREACONDICIONADO).append(separador);
                }
                break;
            case 112:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_TABLEROPDR).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_TABLEROPDR).append(separador);
                }
                break;
            case 113:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_PRESURIZACIONESCALERA).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_PRESURIZACIONESCALERA).append(separador);
                }
                break;
            case 114:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_ESTRACTORAIRE).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_ESTRACTORAIRE).append(separador);
                }
                break;
            case 115:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_PRESURIZACIONCANIERIA).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_PRESURIZACIONCANIERIA).append(separador);
                }
                break;
            case 116:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_INCENDIO2).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_INCENDIO2).append(separador);
                }
                break;
            case 117:
                if(art.getName()!= null && !art.getName().equals("")) {
                    result.append(TITLE_TABLEROPDR2).append("[").append(art.getName()).append("]").append(separador);
                }else{
                    result.append(TITLE_TABLEROPDR2).append(separador);
                }
                break;
            default:
                break;
        }
        if(art.getDescription() != null && !art.getDescription().equals("")){
            result.append(context.getResources().getString(R.string.observaciones)).append(art.getDescription()).append(separador);
        }else{
            result.append(separador);
        }

        switch (art.getCode()){
            case 101:
                TableroTGBT a1 = (TableroTGBT) art;
                result.append(context.getResources().getString(R.string.kwr)).append(a1.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws)).append(a1.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt)).append(a1.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel)).append(a1.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel)).append(a1.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel)).append(a1.getPat()).append(separador);
                break;
            case 102:
                TableroAireChiller a2 = (TableroAireChiller) art;
                result.append(context.getResources().getString(R.string.kwr)).append(a2.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws)).append(a2.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt)).append(a2.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel)).append(a2.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel)).append(a2.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel)).append(a2.getPat()).append(separador);
                break;
            case 103:
                TableroCrac a3 = (TableroCrac) art;
                result.append(context.getResources().getString(R.string.kwr)).append(a3.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws)).append(a3.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt)).append(a3.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel)).append(a3.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel)).append(a3.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel)).append(a3.getPat()).append(separador);
                break;
            case 104:
                TableroInUps a4 = (TableroInUps) art;
                result.append(context.getResources().getString(R.string.kwr)).append(a4.getKwr()).append(separador);
                result.append(context.getResources().getString(R.string.kws)).append(a4.getKws()).append(separador);
                result.append(context.getResources().getString(R.string.kwt)).append(a4.getKwt()).append(separador);
                result.append(context.getResources().getString(R.string.arLabel)).append(a4.getPar()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel)).append(a4.getPas()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel)).append(a4.getPat()).append(separador);
                break;
            case 105:
                LoadUPS a5 = (LoadUPS) art;
                result.append(context.getResources().getString(R.string.arLabel)).append(a5.getPercent_r()).append(separador);
                result.append(context.getResources().getString(R.string.asLabel)).append(a5.getPercent_s()).append(separador);
                result.append(context.getResources().getString(R.string.atLabel)).append(a5.getPercent_t()).append(separador);
                break;
            case 106:
                GrupoElectrogeno a6 = (GrupoElectrogeno) art;
                result.append(context.getResources().getString(R.string.comb)).append(a6.getPercent_comb()).append(separador);
                result.append(context.getResources().getString(R.string.temperatura)).append(a6.getTemperatura()).append(separador);
                String temp = context.getResources().getString(R.string.label_no);
                if(a6.getAlarma().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.alarma)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a6.getAuto().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.autoLabel)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a6.getCargadorbat().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.cargadorBat)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a6.getPrecalent().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.preCalent)).append(":").append(temp).append(separador);

                break;
            case 107:
                AireCrac a7 = (AireCrac) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a7.getFunciona_ok().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.funcionaOk)).append(":").append(temp).append(separador);
                result.append(context.getResources().getString(R.string.temperatura)).append(a7.getTemperatura()).append(separador);
                break;
            case 108:
                AireChiller a8 = (AireChiller) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a8.getComp1Ok().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.comp1ok)).append(":").append(temp).append(separador);
                result.append(context.getResources().getString(R.string.loadLabel)).append(a8.getComp1Load()).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a8.getComp2Ok().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.comp2Ok)).append(":").append(temp).append(separador);
                result.append(context.getResources().getString(R.string.loadLabel)).append(a8.getComp2Load()).append(separador);
                result.append(context.getResources().getString(R.string.outLabel)).append(a8.getAtr_out()).append(separador);
                result.append(context.getResources().getString(R.string.pprimLabel)).append(a8.getPprim()).append(separador);
                result.append(context.getResources().getString(R.string.psecLabel)).append(a8.getPsec()).append(separador);
                break;
            case 109:
                Incendio a9 = (Incendio) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a9.getFunciona_ok().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.funcionaOk)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a9.getEnergiaAOk().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.energiaA)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a9.getEnergiaBOk().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.energiaB)).append(":").append(temp).append(separador);
                result.append(context.getResources().getString(R.string.presion)).append(a9.getPresion()).append(separador);
                break;
            case 110:
                Presostato a10 = (Presostato) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a10.getAireOk().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.aireOk)).append(":").append(temp).append(separador);
                result.append(context.getResources().getString(R.string.presion)).append(a10.getAirePresion()).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a10.getAguaOk().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.aguaOk)).append(":").append(temp).append(separador);
                result.append(context.getResources().getString(R.string.presion)).append(a10.getAguaPresion()).append(separador);

                break;
            case 111:
                AireAcond a11 = (AireAcond) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a11.getFunciona_ok().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.funcionaOk)).append(":").append(temp).append(separador);
                result.append(context.getResources().getString(R.string.temperatura)).append(a11.getTemperatura()).append(separador);
                break;
            case 112:
                TableroPDR a12 = (TableroPDR) art;
                result.append(context.getResources().getString(R.string.pottotRA)).append(a12.getPottotRA()).append(separador);
                result.append(context.getResources().getString(R.string.pottotRB)).append(a12.getPottotRB()).append(separador);
                break;
            case 113:
                PresurizacionEscalera a13 = (PresurizacionEscalera)art;
                temp = context.getResources().getString(R.string.label_no);
                if(a13.getArranque().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.arranque)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a13.getCorreas().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.correas)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a13.getEngrase().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.engrase)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a13.getFuncionamiento().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.funcionaOk)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a13.getLimpieza().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.limpieza)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a13.getTiemp().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.tiempo)).append(":").append(temp).append(separador);

                break;
            case 114:
                EstractorAire a14 = (EstractorAire) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a14.getArranque().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.arranque)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a14.getCorreas().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.correas)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a14.getEngrase().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.engrase)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a14.getFuncionamiento().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.funcionaOk)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a14.getLimpieza().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.limpieza)).append(":").append(temp).append(separador);
                break;
            case 115:
                PresurizacionCanieria a15 = (PresurizacionCanieria) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a15.getAlarma().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.alarma)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a15.getEncendido().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.encendido)).append(":").append(temp).append(separador);

                break;
            case 116:
                Incendio2 a16 = (Incendio2) art;
                temp = context.getResources().getString(R.string.label_no);
                if(a16.getFunciona_ok().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.funcionaOk)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a16.getEnergiaAOk().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.energiaA)).append(":").append(temp).append(separador);
                temp = context.getResources().getString(R.string.label_no);
                if(a16.getFm200Ok().equals("1")){
                    temp = context.getResources().getString(R.string.label_yes);
                }
                result.append(context.getResources().getString(R.string.fm200)).append(":").append(temp).append(separador);

                break;
            case 117:
                TableroPDR a17 = (TableroPDR) art;
                result.append(context.getResources().getString(R.string.pottotRA)).append(a17.getPottotRA()).append(separador);
                break;
            default:
                break;
        }


      //  String PIPE = " | ";

        result.append(separador);

        result.append(ENTER);


        return result.toString();

    }


    private static final String folderCSV = "IPLAN-App";

    public static String obtenerPathDeArchivo(String fileName){
        String result;
        result = obtenerPath() + File.separator + fileName;
        return result;
    }


    public static void copyFiles(String srcPath, Uri dst, ContentResolver cr) {
        File src = new File(srcPath);
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = getFileOutputStreamFromUri(cr, dst)) {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private static FileOutputStream getFileOutputStreamFromUri(ContentResolver cr, Uri uri){
        OutputStream os;
        FileOutputStream fos = null;
        try {
            //	fos = cr.openInputStream(uri);
            os = cr.openOutputStream(uri);
            fos = (FileOutputStream)os;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fos;

    }

/*
    public static void deleteDataBackUp(Context ctx) {
        DataBaseManager dbm = DataBaseManager.getInstance(ctx);
        dbm.open();
        dbm.deleteDataBackUp();
        dbm.close();
    }*/
}
