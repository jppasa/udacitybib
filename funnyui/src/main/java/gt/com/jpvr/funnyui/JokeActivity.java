package gt.com.jpvr.funnyui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String ARG_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView txtSetup = findViewById(R.id.txtJokeSetup);
        TextView txtPunchline = findViewById(R.id.txtJokePunchline);

        Bundle extras = getIntent().getExtras();

        String jokeStr = "No joke...";
        if (extras != null && extras.containsKey(ARG_JOKE)) {
            jokeStr = extras.getString(ARG_JOKE, jokeStr);
        }

        txtSetup.setText(jokeStr);
    }
}
