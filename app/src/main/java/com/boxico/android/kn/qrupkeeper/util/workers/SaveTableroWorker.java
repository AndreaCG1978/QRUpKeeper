package com.boxico.android.kn.qrupkeeper.util.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class SaveTableroWorker extends Worker {
    final WorkerParameters myWorkerParams;

    public SaveTableroWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myWorkerParams = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {
        Result r = Result.failure();
        Call<ResponseBody>  call;
     //   boolean exito = false;
        Call<AbstractArtefactDto>  callInsert;
        Response<AbstractArtefactDto> resp;
        AbstractArtefactDto t = ConstantsAdmin.selectedArtefact;
        if(t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1){// ES UN FORMULARIO EXISTENTE
            try {
                switch (t.getCode()){
                    case 105:
                        call = ConstantsAdmin.tableroService.updateLoadUps(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getPar(), t.getPas(), t.getPat(), t.getAlarma(), ConstantsAdmin.tokenIplan);
                        break;
                    default:
                        call = ConstantsAdmin.tableroService.updateTablero(t.getIdRemoteDB(), t.getName(), t.getCode(), t.getDescription(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat(), ConstantsAdmin.tokenIplan);
                        break;
                }
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
               //     exito = true;
                    r = Result.success();
                }

            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO
            try {
                callInsert = ConstantsAdmin.tableroService.saveTablero(t.getName(), t.getCode(), t.getDescription(), t.getKwr(), t.getKws(), t.getKwt(), t.getPar(), t.getPas(), t.getPat(), ConstantsAdmin.currentForm.getId(), t.getAlarma(), ConstantsAdmin.tokenIplan);
                resp = callInsert.execute();
                if(resp != null){
                    AbstractArtefactDto a = resp.body();
                    ConstantsAdmin.selectedArtefact.setIdRemoteDB(a.getId());
             //       exito = true;
                    r = Result.success();
                }
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return r;
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

