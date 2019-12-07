package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.dtos.DataCenter;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DatacenterService {

    String API_ROUTE = "/datacenters.php";

    @GET(API_ROUTE)
    Call< List<DataCenter> > getDatacenters(@Query("code") String code);


    @GET(API_ROUTE)
    Call< List<DataCenter> > getAllDatacenters();





}
