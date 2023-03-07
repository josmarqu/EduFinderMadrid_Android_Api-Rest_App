package app.edufindermadrid.api;

import app.edufindermadrid.entities.EduCenterList;
import app.edufindermadrid.entities.EduDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRestService {
    String BASE_URL = "https://datos.madrid.es/egob/catalogo/";

    @GET("203166-0-universidades-educacion.json")
    Call<EduCenterList> getEduCenterFilter(@Query("latitud") double lat,
                                           @Query("longitud") double lon,
                                           @Query("distancia") int dist);

    @GET("tipo/entidadesyorganismos/{id_url}")
    Call<EduDetails> getEduOrganization(@Path("id_url") String id_url);

    @GET("203166-0-universidades-educacion.json")
    Call<EduCenterList> getEduCenters();
}
