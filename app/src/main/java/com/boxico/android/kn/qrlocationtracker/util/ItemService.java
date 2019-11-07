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
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ItemService {

    String API_ROUTE = "/items.php";

    @GET(API_ROUTE)
    Call< List<ItemDto> > getItems(@Query("id") String id);


    @GET(API_ROUTE)
    Call< List<ItemDto> > getAllItems();


    @POST(API_ROUTE)
    Call<ItemDto> saveItem(@Body ItemDto item);


    @POST(API_ROUTE)
    @FormUrlEncoded
     Call<ItemDto> saveItem(@Field("name") String name,
                        @Field("description") String description);

    /*Call<ResponseBody> saveItem(@Field("id") long id, @Field("name") String name,
                                @Field("description") String descr);*/



    @PUT(API_ROUTE + "/{id}")
    @FormUrlEncoded
    Call<ItemDto> updateItem(@Path("id") long id,
                             @Field("name") String name,
                             @Field("description") String description);

    @PATCH(API_ROUTE + "/{id}")
    Call<ResponseBody> updateItem(@Path("id") long id,
                                  @Body ItemDto item);

    /*Call<ResponseBody> saveItem(@Field("id") long id, @Field("name") String name,
                                @Field("description") String descr);*/



}
