package lsteamer.elmexicano.com.docsearch.list.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface DoctorRequestData {

    @Headers({"Accept: application/json"})

    @GET
    Call<DoctorData> getData(@Url String url, @Header("Authorization") String bearer);
}
