package teteruk_vorobiov.edu.com.twitterclient;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import teteruk_vorobiov.edu.com.twitterclient.adapters.UserAdapter;
import teteruk_vorobiov.edu.com.twitterclient.beans.UserBean;
import teteruk_vorobiov.edu.com.twitterclient.utils.JsonUtils;

public class FollowersActivity extends ListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                .getActiveSession();
        UserAdapter adapter = new UserAdapter(FollowersActivity.this);
        setListAdapter(adapter);
        MyTwitterApiClient apiClient = new MyTwitterApiClient(session);
        apiClient.getFollowersService().listFollowers(session.getUserId(), 200).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    List<UserBean> users = JsonUtils.parseUsers(response.body().string());
                    adapter.clear();
                    adapter.addAll(users);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Toast.makeText(FollowersActivity.this,
                            e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FollowersActivity.this,
                        t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
