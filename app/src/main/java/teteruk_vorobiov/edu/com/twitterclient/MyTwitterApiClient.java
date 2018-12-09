package teteruk_vorobiov.edu.com.twitterclient;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import teteruk_vorobiov.edu.com.twitterclient.services.FollowersService;
import teteruk_vorobiov.edu.com.twitterclient.services.UploadPhotoService;

public class MyTwitterApiClient extends TwitterApiClient {

    public MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public UploadPhotoService getUploadService() {
        return getService(UploadPhotoService.class);
    }

    public FollowersService getFollowersService() {
        return getService(FollowersService.class);
    }

}


