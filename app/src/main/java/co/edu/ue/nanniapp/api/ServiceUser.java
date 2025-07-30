package co.edu.ue.nanniapp.api;

import co.edu.ue.nanniapp.model.ResponseParentsRegister;
import co.edu.ue.nanniapp.model.UserRegistrationRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceUser {
    @POST("register")
    Call<ResponseParentsRegister> registerUser(@Body UserRegistrationRequest registerUser);
}
