package com.boxico.android.kn.qrupkeeper.ddbb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import com.boxico.android.kn.qrupkeeper.dtos.AireAcond;
import com.boxico.android.kn.qrupkeeper.dtos.AireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.AireCrac;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.EstractorAire;
import com.boxico.android.kn.qrupkeeper.dtos.GrupoElectrogeno;
import com.boxico.android.kn.qrupkeeper.dtos.Incendio;
import com.boxico.android.kn.qrupkeeper.dtos.Incendio2;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.dtos.LoadUPS;
import com.boxico.android.kn.qrupkeeper.dtos.Presostato;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionCanieria;
import com.boxico.android.kn.qrupkeeper.dtos.PresurizacionEscalera;
import com.boxico.android.kn.qrupkeeper.dtos.TableroAireChiller;
import com.boxico.android.kn.qrupkeeper.dtos.TableroCrac;
import com.boxico.android.kn.qrupkeeper.dtos.TableroInUps;
import com.boxico.android.kn.qrupkeeper.dtos.TableroPDR;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;


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

	public void createTableroTGBT(TableroTGBT item) {

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
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());

		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_TABLERO_TGBT, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_TGBT, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
		
	}


	public void createTableroAIRECHILLER(TableroAireChiller item) {
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
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_TABLERO_AIRECHILLER, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_AIRECHILLER, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createTableroCRAC(TableroCrac item) {

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
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_TABLERO_CRAC, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_CRAC, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}




	public void createTableroINUPS(TableroInUps item) {

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
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_TABLERO_INUPS, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLERO_INUPS, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createLoadUPS(LoadUPS item) {
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
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_LOAD_UPS, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_LOAD_UPS, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}

	public void createGrupoElectrogeno(GrupoElectrogeno item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getAlarma().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_ALARM, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_ALARM, 0);
		}
		if(item.getAuto().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_AUTO, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_AUTO, 0);
		}
        if(item.getCargadorbat().equals("1")){
            initialValues.put(ConstantsAdmin.KEY_CARGADORBAT, 1);
        }else{
            initialValues.put(ConstantsAdmin.KEY_CARGADORBAT, 0);
        }
  /*      if(item.getNivelcomb75().equals("1")){
            initialValues.put(ConstantsAdmin.KEY_NIVELCOMB75, 1);
        }else{
            initialValues.put(ConstantsAdmin.KEY_NIVELCOMB75, 0);
        }*/
        if(item.getPrecalent().equals("1")){
            initialValues.put(ConstantsAdmin.KEY_PRECALENT, 1);
        }else{
            initialValues.put(ConstantsAdmin.KEY_PRECALENT, 0);
        }
		initialValues.put(ConstantsAdmin.KEY_PERCENTCOMB, item.getPercent_comb());
		initialValues.put(ConstantsAdmin.KEY_TEMPERATURA, item.getTemperatura());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_GRUPOELECTROGENO, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_GRUPOELECTROGENO, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createAireCrac(AireCrac item) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getFunciona_ok().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_TEMPERATURA, item.getTemperatura());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_AIRECRAC, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_AIRECRAC, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}

	public void createAireChiller(AireChiller item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getComp1Ok().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_COMP1_OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_COMP1_OK, 0);
		}
		if(item.getComp2Ok().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_COMP2_OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_COMP2_OK, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_COMP1_LOAD, item.getComp1Load());
		initialValues.put(ConstantsAdmin.KEY_COMP2_LOAD, item.getComp2Load());
		initialValues.put(ConstantsAdmin.KEY_PPRIM, item.getPprim());
		initialValues.put(ConstantsAdmin.KEY_PSEC, item.getPsec());
		initialValues.put(ConstantsAdmin.KEY_OUT, item.getAtr_out());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_AIRECHILLER, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_AIRECHILLER, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createIncendio(Incendio item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getEnergiaAOk().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_ENERGIAA_OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_ENERGIAA_OK, 0);
		}
		if(item.getEnergiaBOk().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_ENERGIAB_OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_ENERGIAB_OK, 0);
		}
		if(item.getFunciona_ok().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_PRESION, item.getPresion());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_INCENDIO, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_INCENDIO, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createIncendio2(Incendio2 item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getEnergiaAOk().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_ENERGIAA_OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_ENERGIAA_OK, 0);
		}
		if(item.getFm200Ok().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_FM200OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_FM200OK, 0);
		}
		if(item.getFunciona_ok().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_INCENDIO2, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_INCENDIO2, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}

	public void createPresostato(Presostato item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getAguaOk().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_AGUA_OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_AGUA_OK, 0);
		}
		if(item.getAireOk().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_AIRE_OK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_AIRE_OK, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_AGUA_PRESION, item.getAguaPresion());
		initialValues.put(ConstantsAdmin.KEY_AIRE_PRESION, item.getAirePresion());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_PRESOSTATO, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_PRESOSTATO, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createAireAcond(AireAcond item) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getFunciona_ok().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_TEMPERATURA, item.getTemperatura());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_AIREACOND, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_AIREACOND, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}



	public void createTableroPDR(TableroPDR item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_POTTOTRA, item.getPottotRA());
		initialValues.put(ConstantsAdmin.KEY_POTTOTRB, item.getPottotRB());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_TABLEROPDR, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLEROPDR, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}

	public void createTableroPDR2(TableroPDR item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_POTTOTRA, item.getPottotRA());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_TABLEROPDR2, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_TABLEROPDR2, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}



	public void createPresurizacionEscalera(PresurizacionEscalera item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_ARRANQUE, item.getArranque());
		initialValues.put(ConstantsAdmin.KEY_CORREAS, item.getCorreas());
		initialValues.put(ConstantsAdmin.KEY_ENGRASE, item.getEngrase());
		initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, item.getFuncionamiento());
		initialValues.put(ConstantsAdmin.KEY_LIMPIEZA, item.getLimpieza());
		initialValues.put(ConstantsAdmin.KEY_TIEMPO, item.getTiemp());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_PRESURIZACIONESCALERA, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_PRESURIZACIONESCALERA, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}



	public void createEstractorAire(EstractorAire item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		initialValues.put(ConstantsAdmin.KEY_ARRANQUE, item.getArranque());
		initialValues.put(ConstantsAdmin.KEY_CORREAS, item.getCorreas());
		initialValues.put(ConstantsAdmin.KEY_ENGRASE, item.getEngrase());
		initialValues.put(ConstantsAdmin.KEY_FUNCIONAOK, item.getFuncionamiento());
		initialValues.put(ConstantsAdmin.KEY_LIMPIEZA, item.getLimpieza());
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_ESTRACTORAIRE, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_ESTRACTORAIRE, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createPresurizacionCanieria(PresurizacionCanieria item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NAME, item.getName());
		initialValues.put(ConstantsAdmin.KEY_IDREMOTEDB, item.getIdRemoteDB());
		if(item.getAlarma().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_ALARM, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_ALARM, 0);
		}
		if(item.getEncendido().equals("1")){
			initialValues.put(ConstantsAdmin.KEY_ENCENDIDO, 1);
		}else{
			initialValues.put(ConstantsAdmin.KEY_ENCENDIDO, 0);
		}
		initialValues.put(ConstantsAdmin.KEY_CODE, item.getCode());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		if(item.getId() == -1 ){
			mDb.insert(ConstantsAdmin.TABLE_PRESURIZACIONCANIERIA, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_PRESURIZACIONCANIERIA, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}
	}


	public void createForm(DatacenterForm item) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_NROFORM, item.getNroForm());
		initialValues.put(ConstantsAdmin.KEY_DESCRIPTION, item.getDescription());
		initialValues.put(ConstantsAdmin.KEY_DATACENTERNAME, item.getDatacenterName());
		initialValues.put(ConstantsAdmin.KEY_DATACENTERID, item.getDatacenterId());
		initialValues.put(ConstantsAdmin.KEY_ROWID, item.getId());
	//	if(item.getId() == -1 ){
		mDb.insert(ConstantsAdmin.TABLE_FORMS, null, initialValues);
	/*	}else{
			mDb.update(ConstantsAdmin.TABLE_FORMS, initialValues, ConstantsAdmin.KEY_ROWID + "=" + item.getId() , null);
		}*/
	}

	public void createLogin(Inspector item) {
		//long returnValue = item.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(ConstantsAdmin.KEY_USER, item.getUsr());
		initialValues.put(ConstantsAdmin.KEY_PASSWORD, item.getPsw());
		initialValues.put(ConstantsAdmin.KEY_ROWID, 1);
		if(sizeLogin()==0){
			mDb.insert(ConstantsAdmin.TABLE_LOGIN, null, initialValues);
		}else{
			mDb.update(ConstantsAdmin.TABLE_LOGIN, initialValues, ConstantsAdmin.KEY_ROWID + "= 1" , null);
		}

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
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_TGBT, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}


	public void deleteTableroAireChiller(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_AIRECHILLER, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteTableroCrac(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_CRAC, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}


	public void deleteTableroInUps(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLERO_INUPS, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}


	public void deleteLoadUps(int id){
		mDb.delete(ConstantsAdmin.TABLE_LOAD_UPS, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

    public void deleteGrupoElectrogeno(int id){
        mDb.delete(ConstantsAdmin.TABLE_GRUPOELECTROGENO, ConstantsAdmin.KEY_ROWID + "=" + id, null);
    }

	public void deleteAireCrac(int id){
		mDb.delete(ConstantsAdmin.TABLE_AIRECRAC, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteAireChiller(int id){
		mDb.delete(ConstantsAdmin.TABLE_AIRECHILLER, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteIncendio(int id){
		mDb.delete(ConstantsAdmin.TABLE_INCENDIO, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteIncendio2(int id){
		mDb.delete(ConstantsAdmin.TABLE_INCENDIO2, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deletePresostato(int id){
		mDb.delete(ConstantsAdmin.TABLE_PRESOSTATO, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteAireAcond(int id){
		mDb.delete(ConstantsAdmin.TABLE_AIREACOND, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteTableroPDR(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLEROPDR, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteTableroPDR2(int id){
		mDb.delete(ConstantsAdmin.TABLE_TABLEROPDR2, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deletePresurizacionEscalera(int id){
		mDb.delete(ConstantsAdmin.TABLE_PRESURIZACIONESCALERA, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteEstractorAire(int id){
		mDb.delete(ConstantsAdmin.TABLE_ESTRACTORAIRE, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deletePresurizacionCanieria(int id){
		mDb.delete(ConstantsAdmin.TABLE_PRESURIZACIONCANIERIA, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

    public void deleteForm(int id){
		mDb.delete(ConstantsAdmin.TABLE_FORMS, ConstantsAdmin.KEY_ROWID + "=" + id, null);
	}

	public void deleteLogin(){
		mDb.delete(ConstantsAdmin.TABLE_LOGIN, ConstantsAdmin.KEY_ROWID + "= 1", null);
	}

/*
	public long tableItemSize(){
    	 long result;
    	 SQLiteStatement s = mDb.compileStatement(DataBaseHelper.SIZE_ITEM);
    	 result = s.simpleQueryForLong();
    	 return result;
     }
*/
	private long sizeLogin(){
		long result;
		SQLiteStatement s = mDb.compileStatement(DataBaseHelper.SIZE_LOGIN);
		result = s.simpleQueryForLong();
		return result;
	}

/*
	public Cursor cursorItems(double lat1, double long1, String diference) {

		String selection =" (abs(abs(" + ConstantsAdmin.KEY_LATITUDE + ")- abs(?))<"+diference+") AND (abs(abs(" + ConstantsAdmin.KEY_LONGITUDE + ")- abs(?))<"+diference+")";


		String[] selectionArgs = { String.valueOf(lat1), String.valueOf(long1) };


		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_ITEM, null, selection, selectionArgs, null, null, null, null );

		}
		return c;
	}
*/

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

    public Cursor cursorGrupoElectrogeno() {
        Cursor c = null;
        if(mDb.isOpen()){
            c = mDb.query(ConstantsAdmin.TABLE_GRUPOELECTROGENO, null, null, null, null, null, null, null );
        }
        return c;
    }


	public Cursor cursorAireCrac() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_AIRECRAC, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorAireChiller() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_AIRECHILLER, null, null, null, null, null, null, null );
		}
		return c;
	}


	public Cursor cursorIncendio() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_INCENDIO, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorIncendio2() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_INCENDIO2, null, null, null, null, null, null, null );
		}
		return c;
	}


	public Cursor cursorPresostato() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_PRESOSTATO, null, null, null, null, null, null, null );
		}
		return c;
	}


	public Cursor cursorAireAcond() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_AIREACOND, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorTableroPDR() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_TABLEROPDR, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorTableroPDR2() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_TABLEROPDR2, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorPresurizacionEscalera() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_PRESURIZACIONESCALERA, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorEstractorAire() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_ESTRACTORAIRE, null, null, null, null, null, null, null );
		}
		return c;
	}

	public Cursor cursorPresurizacionCanieria() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_PRESURIZACIONCANIERIA, null, null, null, null, null, null, null );
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

	public Cursor cursorLogin() {
		Cursor c = null;
		if(mDb.isOpen()){
			c = mDb.query(ConstantsAdmin.TABLE_LOGIN, null, null, null, null, null, null, null );
		}
		return c;
	}





}
