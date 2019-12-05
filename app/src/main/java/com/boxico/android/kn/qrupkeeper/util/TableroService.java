package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.ItemDto;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;

import java.sql.Timestamp;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TableroService {

    String API_ROUTE = "/app_datacenter_log/tableros.php";

    @GET(API_ROUTE)
    Call<List<TableroTGBT>> getTableros(@Query("name") String name);


    @GET(API_ROUTE)
    Call<List<TableroTGBT>> getAllTableros();


    @POST(API_ROUTE)
    @FormUrlEncoded
  //  @Headers("Content-Type: application/json")
     Call<ResponseBody> saveTablero(@Field("name") String name,
                                 @Field("description") String description,
                                 @Field("nroForm") String nroForm,
                                 @Field("kwr") Float kwr,
                                 @Field("kws") Float kws,
                                 @Field("kwt") Float kwt,
                                 @Field("ar") Float ar,
                                 @Field("as") Float as,
                                 @Field("at") Float at,
                                 @Field("inspectorId") Integer inspectorId,
                                 @Field("datacenterId") Integer datacenterId,
                                 @Field("datetime") Timestamp datetime,
                                    @Field("type") int type);

    /*Call<ResponseBody> saveItem(@Field("id") long id, @Field("name") String name,
                                @Field("description") String descr);*/


/*
    @PUT(API_ROUTE + "/{id}/")
    Call<ItemDto> updateItem(@Path("id") long id,@Body ItemDto item);*/


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
    Call<ResponseBody> updateItem(@Field("id") long id, @Field("name") String name, @Field("description") String desc);


    /*Call<ResponseBody> saveItem(@Field("id") long id, @Field("name") String name,
                                @Field("description") String descr);*/


    @DELETE(API_ROUTE + "/{id}/")
    Call<ResponseBody> deleteItem(@Path("id") long itemId, @Query("id") long id);




}
