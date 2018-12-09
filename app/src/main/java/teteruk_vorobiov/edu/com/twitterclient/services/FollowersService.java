package teteruk_vorobiov.edu.com.twitterclient.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FollowersService {
    @GET("/1.1/followers/list.json")
    Call<ResponseBody> list(@Query("user_id") long id);
}
