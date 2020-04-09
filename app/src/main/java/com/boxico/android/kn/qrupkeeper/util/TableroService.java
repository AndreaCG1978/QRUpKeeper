package com.boxico.android.kn.qrupkeeper.util;
import com.boxico.android.kn.qrupkeeper.dtos.AbstractArtefactDto;
import com.boxico.android.kn.qrupkeeper.dtos.TableroTGBT;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
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
/*
    @GET(API_ROUTE)
    Call<List<TableroTGBT>> getTableros(@Query("name") String name);*/
/*
    @GET(API_ROUTE)
    Call<List<AbstractArtefactDto>> getTablero(@Query("name") String name, @Query("codigo") String codigo, @Query("idForm") int idForm);

*/
  /*  @GET(API_ROUTE)
    Call<List<TableroTGBT>> getAllTableros();*/


    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveTablero(@Field("name") String name,
                                          @Field("codigo") int code,
                                          @Field("description") String description,
                                          @Field("kwr") String kwr,
                                          @Field("kws") String kws,
                                          @Field("kwt") String kwt,
                                          @Field("par") String ar,
                                          @Field("pas") String as,
                                          @Field("pat") String at,
                                          @Field("idForm") Integer idForm,
                                          @Field("alarma") String alarm,
                                          @Field("tokenIplan") long tokenIplan);

    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveGrupoElectrogeno(@Field("name") String name,
                                                   @Field("codigo") int code,
                                                   @Field("description") String description,
                                                   @Field("percent_comb") String percent_comb,
                                                   @Field("temperatura") String temperatura,
                                                   @Field("auto") String auto,
                                                   @Field("precalent") String precalent,
                                                   @Field("cargadorbat") String cargadorbat,
                                                   @Field("alarma") String alarm,
                                                   @Field("idForm") Integer idForm,
                                                   @Field("tokenIplan") long tokenIplan);


    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveAireCrac(@Field("name") String name,
                                           @Field("codigo") int code,
                                           @Field("description") String description,
                                           @Field("funciona_ok") String funcionaOk,
                                           @Field("temperatura") String temperatura,
                                           @Field("idForm") Integer idForm,
                                           @Field("tokenIplan") long tokenIplan);


    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveAireChiller(@Field("name") String name,
                                              @Field("codigo") int code,
                                              @Field("description") String description,
                                              @Field("comp1_ok") String comp1Ok,
                                              @Field("comp1_load") String comp1Load,
                                              @Field("comp2_ok") String comp2Ok,
                                              @Field("comp2_load") String comp2Load,
                                              @Field("atr_out") String out,
                                              @Field("pprim") String pprim,
                                              @Field("psec") String psec,
                                              @Field("idForm") Integer idForm,
                                              @Field("tokenIplan") long tokenIplan);

    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveIncendio(@Field("name") String name,
                                           @Field("codigo") int code,
                                           @Field("description") String description,
                                           @Field("energiaA_ok") String energAOk,
                                           @Field("energiaB_ok") String energBOk,
                                           @Field("funciona_ok") String funcionaOk,
                                           @Field("presion") String presion,
                                           @Field("idForm") Integer idForm,
                                           @Field("tokenIplan") long tokenIplan);

    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveIncendio2(@Field("name") String name,
                                           @Field("codigo") int code,
                                           @Field("description") String description,
                                           @Field("energiaA_ok") String energAOk,
                                           @Field("fm200_ok") String energBOk,
                                           @Field("funciona_ok") String funcionaOk,
                                           @Field("idForm") Integer idForm,
                                           @Field("tokenIplan") long tokenIplan);


    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> savePresostato(@Field("name") String name,
                                             @Field("codigo") int code,
                                             @Field("description") String description,
                                             @Field("agua_ok") String aguaOk,
                                             @Field("aire_ok") String aireOk,
                                             @Field("agua_presion") String aguaPresion,
                                             @Field("aire_presion") String airePresion,
                                             @Field("idForm") Integer idForm,
                                             @Field("tokenIplan") long tokenIplan);


    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveAireAcond(@Field("name") String name,
                                            @Field("codigo") int code,
                                            @Field("description") String description,
                                            @Field("funciona_ok") String funcionaOk,
                                            @Field("temperatura") String temperatura,
                                            @Field("idForm") Integer idForm,
                                            @Field("tokenIplan") long tokenIplan);


    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveTableroPDR(@Field("name") String name,
                                             @Field("codigo") int code,
                                             @Field("description") String description,
                                             @Field("pottotRA") String pottotRA,
                                             @Field("pottotRB") String pottotRB,
                                             @Field("idForm") Integer idForm,
                                             @Field("tokenIplan") long tokenIplan);

    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveTableroPDR2(@Field("name") String name,
                                             @Field("codigo") int code,
                                             @Field("description") String description,
                                             @Field("pottotRA") String pottotRA,
                                             @Field("idForm") Integer idForm,
                                             @Field("tokenIplan") long tokenIplan);

    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> savePresurizacionEscalera(@Field("name") String name,
                                                        @Field("codigo") int code,
                                                        @Field("description") String description,
                                                        @Field("arranque") String arranque,
                                                        @Field("correas") String correas,
                                                        @Field("engrase") String engrase,
                                                        @Field("funcionamiento") String funcionamiento,
                                                        @Field("limpieza") String limpieza,
                                                        @Field("tiemp") String tiemp,
                                                        @Field("idForm") Integer idForm,
                                                        @Field("tokenIplan") long tokenIplan);

    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> saveEstractorAire(@Field("name") String name,
                                                @Field("codigo") int code,
                                                @Field("description") String description,
                                                @Field("arranque") String arranque,
                                                @Field("correas") String correas,
                                                @Field("engrase") String engrase,
                                                @Field("funcionamiento") String funcionamiento,
                                                @Field("limpieza") String limpieza,
                                                @Field("idForm") Integer idForm,
                                                @Field("tokenIplan") long tokenIplan);


    @POST(API_ROUTE)
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<AbstractArtefactDto> savePresurizacionCanieria(@Field("name") String name,
                                                        @Field("codigo") int code,
                                                        @Field("description") String description,
                                                        @Field("alarma") String alarma,
                                                        @Field("encendido") String encendido,
                                                        @Field("idForm") Integer idForm,
                                                        @Field("tokenIplan") long tokenIplan);

    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateTablero(@Field("id") int id,
                                     @Field("name") String name,
                                     @Field("codigo") int code,
                                     @Field("description") String description,
                                     @Field("kwr") String kwr,
                                     @Field("kws") String kws,
                                     @Field("kwt") String kwt,
                                     @Field("par") String ar,
                                     @Field("pas") String as,
                                     @Field("pat") String at,
                                     @Field("tokenIplan") long tokenIplan);

    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateGrupoElectrogeno(@Field("id") int id,
                                              @Field("name") String name,
                                              @Field("codigo") int code,
                                              @Field("description") String description,
                                              @Field("percent_comb") String percent_comb,
                                              @Field("temperatura") String temperatura,
                                              @Field("auto") String auto,
                                              @Field("precalent") String precalent,
                                              @Field("cargadorbat") String cargadorbat,
                                              @Field("alarma") String alarm,
                                              @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateAireCrac(@Field("id") int id,
                                      @Field("name") String name,
                                      @Field("codigo") int code,
                                      @Field("description") String description,
                                      @Field("funciona_ok") String funcionaOk,
                                      @Field("temperatura") String temperatura,
                                      @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateAireChiller(@Field("id") int id,
                                         @Field("name") String name,
                                         @Field("codigo") int code,
                                         @Field("description") String description,
                                         @Field("comp1_ok") String comp1Ok,
                                         @Field("comp1_load") String comp1Load,
                                         @Field("comp2_ok") String comp2Ok,
                                         @Field("comp2_load") String comp2Load,
                                         @Field("atr_out") String out,
                                         @Field("pprim") String pprim,
                                         @Field("psec") String psec,
                                         @Field("tokenIplan") long tokenIplan);

    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateIncendio(@Field("id") int id,
                                      @Field("name") String name,
                                      @Field("codigo") int code,
                                      @Field("description") String description,
                                      @Field("energiaA_ok") String energAOk,
                                      @Field("energiaB_ok") String energBOk,
                                      @Field("funciona_ok") String funcionaOk,
                                      @Field("presion") String presion,
                                      @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateIncendio2(@Field("id") int id,
                                      @Field("name") String name,
                                      @Field("codigo") int code,
                                      @Field("description") String description,
                                      @Field("energiaA_ok") String energAOk,
                                      @Field("fm200_ok") String energBOk,
                                      @Field("funciona_ok") String funcionaOk,
                                      @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updatePresostato(@Field("id") int id,
                                        @Field("name") String name,
                                        @Field("codigo") int code,
                                        @Field("description") String description,
                                        @Field("agua_ok") String aguaOk,
                                        @Field("aire_ok") String aireOk,
                                        @Field("agua_presion") String aguaPresion,
                                        @Field("aire_presion") String airePresion,
                                        @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateAireAcond(@Field("id") int id,
                                       @Field("name") String name,
                                       @Field("codigo") int code,
                                       @Field("description") String description,
                                       @Field("funciona_ok") String funcionaOk,
                                       @Field("temperatura") String temperatura,
                                       @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateTableroPDR(@Field("id") int id,
                                        @Field("name") String name,
                                        @Field("codigo") int code,
                                        @Field("description") String description,
                                        @Field("pottotRA") String pottotRA,
                                        @Field("pottotRB") String pottotRB,
                                        @Field("tokenIplan") long tokenIplan);

    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateTableroPDR2(@Field("id") int id,
                                        @Field("name") String name,
                                        @Field("codigo") int code,
                                        @Field("description") String description,
                                        @Field("pottotRA") String pottotRA,
                                        @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updatePresurizacionEscalera(@Field("id") int id,
                                                   @Field("name") String name,
                                                   @Field("codigo") int code,
                                                   @Field("description") String description,
                                                   @Field("arranque") String arranque,
                                                   @Field("correas") String correas,
                                                   @Field("engrase") String engrase,
                                                   @Field("funcionamiento") String funcionamiento,
                                                   @Field("limpieza") String limpieza,
                                                   @Field("tiemp") String tiemp,
                                                   @Field("tokenIplan") long tokenIplan);

    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateEstractorAire(@Field("id") int id,
                                           @Field("name") String name,
                                           @Field("codigo") int code,
                                           @Field("description") String description,
                                           @Field("arranque") String arranque,
                                           @Field("correas") String correas,
                                           @Field("engrase") String engrase,
                                           @Field("funcionamiento") String funcionamiento,
                                           @Field("limpieza") String limpieza,
                                           @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updatePresurizacionCanieria(@Field("id") int id,
                                                   @Field("name") String name,
                                                   @Field("codigo") int code,
                                                   @Field("description") String description,
                                                   @Field("alarma") String alarma,
                                                   @Field("encendido") String encendido,
                                                   @Field("tokenIplan") long tokenIplan);


    @PUT(API_ROUTE + "/{id}/")
    @FormUrlEncoded
        //  @Headers("Content-Type: application/json")
    Call<ResponseBody> updateLoadUps(@Field("id") int id,
                                     @Field("name") String name,
                                     @Field("codigo") int code,
                                     @Field("description") String description,
                                     @Field("percent_r") String ar,
                                     @Field("percent_s") String as,
                                     @Field("percent_t") String at,
                                     @Field("alarma") String alarm,
                                     @Field("tokenIplan") long tokenIplan);


    @DELETE(API_ROUTE + "/{id}/")
    Call<ResponseBody> deleteTablero(@Path("id") int itemId,@Query("id") int id, @Query("codigo") String code, @Query("tokenIplan") long tokenIplan);

}