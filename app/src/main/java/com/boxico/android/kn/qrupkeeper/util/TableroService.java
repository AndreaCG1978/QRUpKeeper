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
                                    @Field("codigo") int code,
                                    @Field("kwr") String kwr,
                                    @Field("kws") String kws,
                                    @Field("kwt") String kwt,
                                    @Field("par") String ar,
                                    @Field("pas") String as,
                                    @Field("pat") String at,
                                    @Field("idForm") Integer idForm);

}
