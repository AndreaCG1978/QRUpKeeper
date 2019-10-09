package com.boxico.android.kn.qrlocationtracker.util;
import com.boxico.android.kn.qrlocationtracker.ItemDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemService {

    String API_ROUTE = "/items";

    @GET(API_ROUTE)
    Call< List<ItemDto> > getItems();



}
