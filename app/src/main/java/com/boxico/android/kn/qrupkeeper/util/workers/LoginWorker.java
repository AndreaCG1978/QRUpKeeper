package com.boxico.android.kn.qrupkeeper.util.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.boxico.android.kn.qrupkeeper.dtos.Inspector;
import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class LoginWorker extends Worker {
    final WorkerParameters myWorkerParams;

    public LoginWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myWorkerParams = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {
        Result r = Result.failure();
        Call<List<Inspector>> call = null;
        Response<List<Inspector>> response;
        ArrayList<Inspector> inspectors;

        try {
          //  ConstantsAdmin.mensaje = null;
            //   this.inicializarConexionServidor();
            String usrText = myWorkerParams.getInputData().getString("usrText");
            String pswText = myWorkerParams.getInputData().getString("pswText");
            call = ConstantsAdmin.inspectorService.getInspectors(usrText, pswText, ConstantsAdmin.tokenIplan);
            response = call.execute();
            if(response.body() != null){
                ConstantsAdmin.inspectors = new ArrayList<>(response.body());
                r = Result.success();
               /* if(inspectors.size() == 1){
                    Inspector currentInspector = inspectors.get(0);
                    Intent intent = new Intent(me, MainActivity.class);
                    intent.putExtra(ConstantsAdmin.currentInspectorConstant, currentInspector);
                    if(saveLogin.isChecked()){
                        ConstantsAdmin.createLogin(currentInspector,me);
                    }else{
                        ConstantsAdmin.deleteLogin(me);
                    }
                    startActivity(intent);
                }else{
                    //createAlertDialog(getResources().getString(R.string.login_error), getResources().getString(R.string.atencion) );
                    ConstantsAdmin.mensaje = getResources().getString(R.string.login_error);

                }*/
            }
            /*else{
                ConstantsAdmin.mensaje = getResources().getString(R.string.conexion_server_error);

            }*/
        }catch(Exception exc){
/*            String error;
            error = exc.getMessage() + "\n";
            if(exc.getCause() != null){
                error = error + exc.getCause().toString();
            }
            for(int i=0; i< exc.getStackTrace().length; i++){
                error = error +  exc.getStackTrace()[i].toString()+ "\n";
            }
            ConstantsAdmin.mensaje = error;*/
 //           ConstantsAdmin.mensaje = getResources().getString(R.string.conexion_server_error);
            if(call != null) {
                call.cancel();
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

