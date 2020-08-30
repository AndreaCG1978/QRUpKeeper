package com.boxico.android.kn.qrupkeeper.util.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.boxico.android.kn.qrupkeeper.dtos.NombreGenerico;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class LoadNombresGenericosWorker extends Worker {
    final WorkerParameters myWorkerParams;

    public LoadNombresGenericosWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myWorkerParams = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {
        Result r = Result.failure();
        Call<List<NombreGenerico>> call;
        Response<List<NombreGenerico>> response;
        try {
            call = ConstantsAdmin.nombresGenericosService.getNombresGenericos(ConstantsAdmin.tokenIplan);
            response = call.execute();
            ConstantsAdmin.nombresGenericos = new ArrayList<NombreGenerico>(response.body());
            r = Result.success();
        }catch(Exception exc){
            exc.printStackTrace();
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

