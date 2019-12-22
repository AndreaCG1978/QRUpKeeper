package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.dtos.DatacenterForm;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;

import java.sql.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface FormService {

    String API_ROUTE = "/app_datacenter_log/datacenterforms.php";

    @GET(API_ROUTE)
    Call<List<DatacenterForm>> getForms(@Query("nroForm") String nroForm);



    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
    Call<ResponseBody> updateForm(@Field("id") long id,@Field("description") String description,
                                  @Field("nroForm") String nroForm,
                                  @Field("inspectorId") Integer inspectorId,
                                  @Field("datacenterId") Integer datacenterId,
                                  @Field("fecha") String fecha);

    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> saveForm(@Field("description") String description,
                                   @Field("nroForm") String nroForm,
                                   @Field("inspectorId") Integer inspectorId,
                                   @Field("datacenterId") Integer datacenterId,
                                   @Field("fecha") String fecha);






}
