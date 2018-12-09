package teteruk_vorobiov.edu.com.twitterclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;

public class HomePage extends Activity {
    private TextView textViewUsername;
    private TextView textViewUserInfo;
    private ImageView imageViewUserAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        initView();
        loadApiClient();
    }

    private void loadApiClient() {
        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        apiClient.getAccountService()
                .verifyCredentials(true, false, true)
                .enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        User user = result.data;

                        String stringBuilder = "Email: " + user.email + System.lineSeparator() +
                                "Description:" + user.description + System.lineSeparator() +
                                "Location: " + user.location + System.lineSeparator() +
                                "Followers count: " + user.followersCount + System.lineSeparator() +
                                "Friends count: " + user.friendsCount + System.lineSeparator();
                        textViewUserInfo.setText(stringBuilder);

                        textViewUsername.setText(user.name);

                        String imgUrl = user.profileImageUrl;
                        Picasso.with(getApplicationContext())
                                .load(imgUrl.replace("_normal", ""))
                                .into(imageViewUserAvatar);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(HomePage.this,
                                exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void initView() {
        textViewUserInfo = findViewById(R.id.user_profile_info);
        textViewUsername = findViewById(R.id.user_profile_username);

        imageViewUserAvatar = findViewById(R.id.user_profile_avatar);
        imageViewUserAvatar.setClipToOutline(true);

        Button showTweetsButton = findViewById(R.id.button_to_tweets);
        showTweetsButton.setOnClickListener((view) -> startActivity(new Intent(this, TimelineActivity.class)));
        Button makeTweetButton = findViewById(R.id.make_tweet);

        final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                .getActiveSession();
        final Intent intent = new ComposerActivity.Builder(this)
                .session(session)
                .createIntent();
        makeTweetButton.setOnClickListener((view) -> startActivity(intent));
    }
}