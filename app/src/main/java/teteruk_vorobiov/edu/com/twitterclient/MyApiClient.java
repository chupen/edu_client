package teteruk_vorobiov.edu.com.twitterclient;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import teteruk_vorobiov.edu.com.twitterclient.services.FollowersService;

public class MyApiClient extends TwitterApiClient {

    public MyApiClient(TwitterSession session) {
        super(session);
    }

    public FollowersService getFollowersService() {
        return getService(FollowersService.class);
    }
}
