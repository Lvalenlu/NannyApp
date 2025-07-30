package co.edu.ue.nanniapp.api;
import co.edu.ue.nanniapp.model.RegisterParents;
import co.edu.ue.nanniapp.model.ResponseParents;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceParents {
    @POST("parents")
    Call<ResponseParents> createParent(@Body RegisterParents parentRequest);

    //@GET("parents/{id}")
    //Call<Parents.ParentResponse> getParent(@Path("id") int parentId);

    //@POST("parents/{id}")
    //Call<Void> updateParent(@Path("id") int parentId, @Body Parents.ParentUpdateRequest parentUpdateRequest);

}
