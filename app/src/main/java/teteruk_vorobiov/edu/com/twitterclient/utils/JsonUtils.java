package teteruk_vorobiov.edu.com.twitterclient.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import teteruk_vorobiov.edu.com.twitterclient.beans.UserBean;

public final class JsonUtils {
    private JsonUtils() {
    }

    public static List<UserBean> parseUsers(String json) throws JSONException {
        List<UserBean> users = new ArrayList<>();
        JSONObject response = new JSONObject(json);
        JSONArray usersArray = response.getJSONArray("users");
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.optJSONObject(i);
            UserBean userBean = new UserBean();
            userBean.setName(user.getString("name"));
            userBean.setScreenName(user.getString("screen_name"));
            userBean.setProfileImgUrl(user.getString("profile_image_url").replaceAll("_normal", ""));
            userBean.setFollowersCount(user.getInt("followers_count"));
            users.add(userBean);
        }
        return users;
    }
}
