package teteruk_vorobiov.edu.com.twitterclient.services;

import com.twitter.sdk.android.core.Callback;

import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UploadPhotoService {
    @POST("/1.1/account/update_profile_image.json")
    void sendPhoto(@Query("user_id") long id, @Query("image") String image, Callback<Response> cb);
}
