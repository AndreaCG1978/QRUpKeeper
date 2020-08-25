package com.boxico.android.kn.qrupkeeper.util.workers;

import android.content.Context;

import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.currentDatacenter;
import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.currentForm;
import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.currentInspector;
import static com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin.dataCenterID;

public class SaveAllWorker extends Worker {
    final WorkerParameters myWorkerParams;

    public SaveAllWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myWorkerParams = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {
        Result r = Result.failure();
        Call<ResponseBody> call;
        Call<DatacenterForm> callInsert;
        if(currentForm.getId() != -1 && currentForm.getId() != 0){// ES UN FORMULARIO EXISTENTE
            try {
/*                int dataCenterID = currentForm.getDatacenterId();
                if(ConstantsAdmin.currentDatacenter != null){
                    dataCenterID = ConstantsAdmin.currentDatacenter.getId();
                    loadInfoForm(false);
                }*/
                call = ConstantsAdmin.formService.updateForm(currentForm.getId(), currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), dataCenterID, currentForm.getFecha(), ConstantsAdmin.tokenIplan);
                Response<ResponseBody> respuesta = call.execute();
                if(respuesta != null && respuesta.body() != null){
                    r = Result.success();
                }

            }catch(Exception exc){
                exc.printStackTrace();
            }

        }else{// ES UN NUEVO FORMULARIO

            try {
                callInsert = ConstantsAdmin.formService.saveForm(currentForm.getDescription(), currentForm.getNroForm(),currentInspector.getId(), currentDatacenter.getId(), currentForm.getFecha(), ConstantsAdmin.tokenIplan);
                Response<DatacenterForm> resp;
                resp = callInsert.execute();
                if(resp != null && resp.body()!= null){
                    DatacenterForm df = resp.body();

                    if(currentForm == null){
                        currentForm = new DatacenterForm();
                    }
                    currentForm.setId(df.getId());
                    r = Result.success();

                }
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }
        /*if(exito) {
            ConstantsAdmin.deleteForm(currentForm, this);
            ConstantsAdmin.createForm(currentForm, this);
        }*/


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

