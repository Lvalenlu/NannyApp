package co.edu.ue.nanniapp.api;

import co.edu.ue.nanniapp.model.Loger;
import co.edu.ue.nanniapp.model.ResponseCredentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ServiceLoginParents {
    @POST("login")
    public Call<ResponseCredentials> accessLogin(@Body Loger login);

}
