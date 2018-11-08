package teteruk_vorobiov.edu.com.twitterclient;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class HomePage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        String username = getIntent().getStringExtra("username");

        TextView uname = findViewById(R.id.TV_username);
        uname.setText(username);
    }
}
