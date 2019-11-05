package com.boxico.android.kn.qrlocationtracker.util;
import com.boxico.android.kn.qrlocationtracker.ItemDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ItemService {

    String API_ROUTE = "/items.php";

    @GET(API_ROUTE)
    Call< List<ItemDto> > getItems(@Query("id") String id);


    @GET(API_ROUTE)
    Call< List<ItemDto> > getAllItems();


    @POST(API_ROUTE)
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8",
            "Cache-Control: max-age=640000"
    })
    Call<ItemDto> saveItem(@Body ItemDto item);


    @POST(API_ROUTE)
    @FormUrlEncoded

     Call<ItemDto> saveItem(@Field("name") String name,
                        @Field("description") String description);

    /*Call<ResponseBody> saveItem(@Field("id") long id, @Field("name") String name,
                                @Field("description") String descr);*/



}
