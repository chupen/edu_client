package teteruk_vorobiov.edu.com.twitterclient;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class MyTwitterApiClient extends TwitterApiClient {

    public MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public CustomService getCustomService() {
        return getService(CustomService.class);
    }
}

interface CustomService {
    @POST("/1.1/account/update_profile_image.json")
    void sendPhoto(@Query("user_id") long id, @Query("image") String image, Callback<Response> cb);
}
