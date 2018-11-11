package teteruk_vorobiov.edu.com.twitterclient;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

public class HomePage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        String username = getIntent().getStringExtra("username");
        final TextView profileInfo = findViewById(R.id.TV_userinfo);
        final ImageView profileImage = findViewById(R.id.TV_userimage);
        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        apiClient.getAccountService().verifyCredentials(true, false, true)
                .enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        User user = result.data;
                        setUserInfo(user, profileInfo);
                        String imgUrl = user.profileImageUrl;
                        Picasso.with(getApplicationContext())
                                .load(imgUrl.replace("_normal", ""))
                                .into(profileImage);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(HomePage.this,
                                exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setUserInfo(User user, TextView profileInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ").append(user.name).append(System.lineSeparator())
                .append("Email: ").append(user.email).append(System.lineSeparator())
                .append("Description:").append(user.description).append(System.lineSeparator())
                .append("Location: ").append(user.location).append(System.lineSeparator())
                .append("Followers count: ").append(user.followersCount).append(System.lineSeparator())
                .append("Friends count: ").append(user.friendsCount).append(System.lineSeparator());
        profileInfo.setText(stringBuilder.toString());
    }
}
