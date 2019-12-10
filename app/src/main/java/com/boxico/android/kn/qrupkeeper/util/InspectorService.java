package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.ItemDto;
import com.boxico.android.kn.qrupkeeper.dtos.Inspector;

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

public interface InspectorService {

    String API_ROUTE = "/app_datacenter_log/inspectors.php";

    @GET(API_ROUTE)
    Call< List<Inspector> > getInspectors(@Query("usr") String usr);

    @GET(API_ROUTE)
    Call< List<Inspector> > getInspectorsUsrPsw(@Query("user") String usr);

    @GET(API_ROUTE)
    Call< List<Inspector> > getAllInspectors();





}
