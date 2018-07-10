package lsteamer.elmexicano.com.docsearch.login.model;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LoginRequestData {

    @Headers({"Content-Type: application/x-www-form-urlencoded","Accept: application/json","Authorization: Basic aXBob25lOmlwaG9uZXdpbGxub3RiZXRoZXJlYW55bW9yZQ=="})

    @POST
    Call<LoginData> getData(@Url String url);
}
