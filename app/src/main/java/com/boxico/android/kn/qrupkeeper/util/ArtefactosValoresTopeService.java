package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.dtos.ArtefactoValorTope;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArtefactosValoresTopeService {

    String API_ROUTE = "/app_datacenter_log/topes.php";

    @GET(API_ROUTE)
    Call< List<ArtefactoValorTope> > getValoresTopes(@Query("tokenIplan") long tokenIplan);


}