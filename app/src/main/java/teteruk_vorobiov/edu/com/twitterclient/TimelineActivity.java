package teteruk_vorobiov.edu.com.twitterclient;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TimelineActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        final SwipeRefreshLayout swipeLayout = findViewById(R.id.refresh);
        long userId = TwitterCore.getInstance().getSessionManager().getActiveSession().getUserId();
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .userId(userId)
                .includeReplies(true)
                .includeRetweets(true)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);

        swipeLayout.setOnRefreshListener(() -> {
            swipeLayout.setRefreshing(true);
            adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                @Override
                public void success(Result<TimelineResult<Tweet>> result) {
                    swipeLayout.setRefreshing(false);
                }

                @Override
                public void failure(TwitterException exception) {
                    Toast.makeText(TimelineActivity.this,
                            "Update failed", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}