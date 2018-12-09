package teteruk_vorobiov.edu.com.twitterclient.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FollowersService {
    @GET("/1.1/followers/list.json")
    Call<ResponseBody> listFollowers(@Query("user_id") long id);

    @GET("/1.1/friends/list.json")
    Call<ResponseBody> listFollowings(@Query("user_id") long id);
}
