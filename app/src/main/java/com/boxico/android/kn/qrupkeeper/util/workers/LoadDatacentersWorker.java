package com.boxico.android.kn.qrupkeeper.util.workers;

import android.content.Context;

import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import retrofit2.Call;
import retrofit2.Response;

public class LoadDatacentersWorker extends Worker {
    final WorkerParameters myWorkerParams;

    public LoadDatacentersWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myWorkerParams = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {
        Result r = Result.failure();
        Call<List<DataCenter>> call;
        Response<List<DataCenter>> response;
        DataCenter dc = null;
        try {
            call = ConstantsAdmin.datacenterService.getDatacenters(ConstantsAdmin.tokenIplan);
            response = call.execute();
            ConstantsAdmin.allDatacenters = new ArrayList<>(response.body());
            r = Result.success();
        }catch (Exception exc){
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

