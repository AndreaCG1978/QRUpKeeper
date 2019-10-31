package com.boxico.android.kn.qrlocationtracker.util;
import com.boxico.android.kn.qrlocationtracker.ItemDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CasoPolicialService {

    String API_ROUTE = "/casopolicial.php";

    @GET(API_ROUTE)
    Call< List<CasoPolicial> > getCasosPoliciales();



}
