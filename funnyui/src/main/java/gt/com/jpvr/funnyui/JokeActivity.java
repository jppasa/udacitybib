package gt.com.jpvr.funnyui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import gt.com.jpvr.joker.Joke;

public class JokeActivity extends AppCompatActivity {

    public static final String ARG_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView txtSetup = findViewById(R.id.txtJokeSetup);
        TextView txtPunchline = findViewById(R.id.txtJokePunchline);

        Bundle extras = getIntent().getExtras();

        String jokeStr = "No joke...\nSorry";
        Joke joke = Joke.fromString(jokeStr);
        if (extras != null && extras.containsKey(ARG_JOKE)) {
            jokeStr = extras.getString(ARG_JOKE);

            if (jokeStr != null) {
                joke = Joke.fromString(jokeStr);
            }
        }

        txtSetup.setText(joke.getSetup());

        if (joke.getPunchline() != null) {
            txtPunchline.setText(joke.getPunchline());
        }
    }
}
