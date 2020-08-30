package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.dtos.NombreGenerico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NombresGenericosService {

    String API_ROUTE = "/app_datacenter_log/nombresgenericos.php";

    @GET(API_ROUTE)
    Call< List<NombreGenerico> > getNombresGenericos(@Query("tokenIplan") long tokenIplan);


}
