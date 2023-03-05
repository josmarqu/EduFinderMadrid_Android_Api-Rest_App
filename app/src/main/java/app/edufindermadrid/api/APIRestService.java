package app.edufindermadrid.api;

import app.edufindermadrid.entities.EduCenter;
import app.edufindermadrid.entities.EduCenterList;
import retrofit2.Call;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRestService {
     static final String BASE_URL = "https://datos.madrid.es/egob/catalogo/";

    @GET("203166-0-universidades-educacion.json")
    Call<EduCenterList> getEduCenterFilter(@Query("latitud") double lat,
                                   @Query("longitud") double lon,
                                   @Query("distancia") int dist);


    @GET("tipo/entidadesyorganismos/{centro}")
    Call<EduCenter> getDataEduCenter(@Path("center") String url);

    @GET("203166-0-universidades-educacion.json")
    Call<EduCenterList> getEduCenters();
}
