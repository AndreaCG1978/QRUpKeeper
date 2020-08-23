package com.boxico.android.kn.qrupkeeper.util.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.currentForm;

public class SaveAireCrahWorker extends Worker {
    final WorkerParameters myWorkerParams;

    public SaveAireCrahWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myWorkerParams = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {
        Result r = Result.failure();
        Call<ResponseBody>  call;

        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        AbstractArtefactDto t = ConstantsAdmin.selectedArtefact;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                call = ConstantsAdmin.tableroService.updateAireCrac(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), ConstantsAdmin.tokenIplan);
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    //      exito = true;
                    r = Result.success();
                }

            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = ConstantsAdmin.tableroService.saveAireCrac(t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    ConstantsAdmin.selectedArtefact.setIdRemoteDB(a.getId());
                    //    exito = true;
                    r = Result.success();
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return r;

/*
        Call<ResponseBody>  call;
        boolean exito = false;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                //          call = tableroService.updateGrupoElectrogeno(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPercent_comb(), t.getTemperatura(), t.getNivelcomb75(), t.getAuto(), t.getPrecalent(), t.getCargadorbat(), t.getAlarma());
                call = ConstantsAdmin.tableroService.updateAireCrac(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), ConstantsAdmin.tokenIplan);
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    exito = true;
                }

            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = ConstantsAdmin.tableroService.saveAireCrac(t.getName(), t.getCode(), t.getDescription(), t.getFunciona_ok(), t.getTemperatura(), currentForm.getId(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    selectedArtefact.setIdRemoteDB(a.getId());
                    exito = true;


                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }



*/

    }



  /*
        @Override
        public Result doWork() {
            // Do the work here--in this case, upload the images.

            uploadImages()

            // Indicate whether the task finished successfully with the Result
            return Result.success()
        }*/
}

