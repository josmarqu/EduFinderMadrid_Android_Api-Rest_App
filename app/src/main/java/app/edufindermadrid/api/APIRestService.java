package app.edufindermadrid.api;

import app.edufindermadrid.entities.EduCenter;
import app.edufindermadrid.entities.EduCenterList;
import retrofit2.Call;

import java.util.List;

import retrofit2.http.GET;

public interface APIRestService {
     static final String BASE_URL = "https://datos.madrid.es/egob/catalogo/";

    @GET("203166-0-universidades-educacion.json")
    Call<EduCenterList> getEduCenters();
}
