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

public class DeleteTableroWorker extends Worker {
    final WorkerParameters myWorkerParams;

    public DeleteTableroWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myWorkerParams = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {
        Result r = Result.failure();
        Call<ResponseBody> call;
        Response<ResponseBody> resp;
        AbstractArtefactDto t = ConstantsAdmin.selectedArtefact;
        if (t.getIdRemoteDB() != 0 && t.getIdRemoteDB() != -1) {
            try {
                call = ConstantsAdmin.tableroService.deleteTablero(t.getIdRemoteDB(), t.getIdRemoteDB(), String.valueOf(t.getCode()), ConstantsAdmin.tokenIplan);
                resp = call.execute();
                if (resp != null && resp.body() != null) {
                    r = Result.success();
                }

            } catch (Exception exc) {
                r = Result.success();
                exc.printStackTrace();
            }

        }
        return r;
    }

}

