package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FormService {

    String API_ROUTE = "/app_datacenter_log/datacenterforms.php";

    @GET(API_ROUTE)
    Call<List<DatacenterForm>> getForms(@Query("nroForm") String nroForm);





    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<DatacenterForm> saveForm(@Field("description") String description,
                                   @Field("nroForm") String nroForm,
                                   @Field("inspectorId") Integer inspectorId,
                                   @Field("datacenterId") Integer datacenterId);






}
