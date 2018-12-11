package teteruk_vorobiov.edu.com.twitterclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import teteruk_vorobiov.edu.com.twitterclient.R;
import teteruk_vorobiov.edu.com.twitterclient.beans.UserBean;

public class UserAdapter extends ArrayAdapter<UserBean> {

    private Context context;

    public UserAdapter(Context context) {
        super(context, R.layout.list);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list, null, true);
        }
        UserBean user = getItem(position);
        ImageView imageView = convertView.findViewById(R.id.profile_img);
        TextView userName = convertView.findViewById(R.id.user_name);
        String userInfo = user.getName() + System.lineSeparator() +
                "@" + user.getScreenName() + System.lineSeparator() +
                "Followers: " + user.getFollowersCount();
        userName.setText(userInfo);
        Picasso.with(context)
                .load(user.getProfileImgUrl())
                .fit().centerInside()
                .into(imageView);
        return convertView;
    }
}
