package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.ItemDto;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
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
    Call<List<AbstractArtefactDto>> getTablero(@Query("name") String name, @Query("codigo") String codigo, @Query("idForm") int idForm);


    @GET(API_ROUTE)
    Call<List<TableroTGBT>> getAllTableros();


    @POST(API_ROUTE)
    @FormUrlEncoded
  //  @Headers("Content-Type: application/json")
     Call<ResponseBody> saveTablero(@Field("name") String name,
                                    @Field("codigo") int code,
                                    @Field("kwr") String kwr,
                                    @Field("kws") String kws,
                                    @Field("kwt") String kwt,
                                    @Field("par") String ar,
                                    @Field("pas") String as,
                                    @Field("pat") String at,
                                    @Field("idForm") Integer idForm,
                                    @Field("alarm") String alarm);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateTablero(@Field("id") int id,
                                     @Field("name") String name,
                                     @Field("codigo") int code,
                                     @Field("kwr") String kwr,
                                     @Field("kws") String kws,
                                     @Field("kwt") String kwt,
                                     @Field("par") String ar,
                                     @Field("pas") String as,
                                     @Field("pat") String at);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateLoadUps(@Field("id") int id,
                                     @Field("name") String name,
                                     @Field("codigo") int code,
                                     @Field("percent_r") String ar,
                                     @Field("percent_s") String as,
                                     @Field("percent_t") String at,
                                     @Field("alarma") String alarm);


    @DELETE(API_ROUTE + "/{id}/")
    Call<ResponseBody> deleteTablero(@Path("id") int itemId,@Query("id") int id, @Query("codigo") String code);

}
