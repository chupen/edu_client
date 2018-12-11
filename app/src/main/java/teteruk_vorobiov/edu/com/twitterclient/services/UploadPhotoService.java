package teteruk_vorobiov.edu.com.twitterclient.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UploadPhotoService {
    @FormUrlEncoded
    @POST("/1.1/account/update_profile_image.json")
    Call<ResponseBody> updateProfileImage(@Field("image") String image);
}
