package com.example.hw04;

//      ListActivity
//      Group Number: groups1 3
//      Akshay Popli and Neel Solanki

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListActivity extends AppCompatActivity {

    TextView year_title;
    TextView movietitle2;
    EditText moviedescription2;
    TextView moviegenre2;
    TextView movierating2;
    TextView movieyear2;
    TextView movieimdb2;
    Button btn_finish;
    ImageView iv_first;
    ImageView iv_prev;
    ImageView iv_next;
    ImageView iv_last;
    int movie;

    ArrayList<Movie> aListMovies;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_year);

        year_title = findViewById(R.id.year_title);
        movietitle2 = findViewById(R.id.movietitle2);
        moviedescription2 = findViewById(R.id.moviedescription2);
        moviegenre2 = findViewById(R.id.moviegenre2);
        movierating2 = findViewById(R.id.movierating2);
        movieyear2 = findViewById(R.id.movieyear2);
        movieimdb2 = findViewById(R.id.movieimdb2);
        btn_finish = findViewById(R.id.btn_finish);
        iv_first = findViewById(R.id.iv_first);
        iv_prev = findViewById(R.id.iv_prev);
        iv_next = findViewById(R.id.iv_next);
        iv_last = findViewById(R.id.iv_last);

        iv_prev.setAlpha((float) 0.2);
        iv_first.setAlpha((float) 0.2);

        movie = getIntent().getExtras().getInt(MainActivity.MOVIE_KEY);
        if (movie == 400) {
            aListMovies = (ArrayList<Movie>) getIntent().getSerializableExtra("allmovies");
            setTitle("Movies by Year");
            year_title.setText("Movies by Year");

            Collections.sort(aListMovies, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getYear() - o2.getYear();
                }
            });
        } else if (movie == 500){
            aListMovies = (ArrayList<Movie>) getIntent().getSerializableExtra("allmovies");
            setTitle("Movies by Rating");
            year_title.setText("Movies by Rating");
            Collections.sort(aListMovies, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getRating() - o1.getRating();
                }
            });
        }

        movietitle2.setText(aListMovies.get(counter).name);
        moviedescription2.setText(aListMovies.get(counter).description);
        moviegenre2.setText(aListMovies.get(counter).genre);
        movierating2.setText(String.valueOf(aListMovies.get(counter).rating) + "/5");
        movieyear2.setText(String.valueOf(aListMovies.get(counter).year));
        movieimdb2.setText(aListMovies.get(counter).imdb);

        Log.d("Size", String.valueOf(aListMovies.size()));

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_first.setAlpha((float) 1.0);
                iv_prev.setAlpha((float) 1.0);

                counter += 1;
                if (counter < aListMovies.size()) {
                    movietitle2.setText(aListMovies.get(counter).name);
                    moviedescription2.setText(aListMovies.get(counter).description);
                    moviegenre2.setText(aListMovies.get(counter).genre);
                    movierating2.setText(String.valueOf(aListMovies.get(counter).rating) + "/5");
                    movieyear2.setText(String.valueOf(aListMovies.get(counter).year));
                    movieimdb2.setText(aListMovies.get(counter).imdb);
                }

                if (counter == aListMovies.size() - 1) {
                    iv_next.setAlpha((float) 0.2);
                    iv_last.setAlpha((float) 0.2);
                }
            }
        });

        iv_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iv_first.setAlpha((float) 1.0);
                iv_prev.setAlpha((float) 1.0);

                counter = aListMovies.size() - 1;
                if (counter < aListMovies.size()) {
                    movietitle2.setText(aListMovies.get(counter).name);
                    moviedescription2.setText(aListMovies.get(counter).description);
                    moviegenre2.setText(aListMovies.get(counter).genre);
                    movierating2.setText(String.valueOf(aListMovies.get(counter).rating) + "/5");
                    movieyear2.setText(String.valueOf(aListMovies.get(counter).year));
                    movieimdb2.setText(aListMovies.get(counter).imdb);
                    iv_next.setAlpha((float) 0.2);
                    iv_last.setAlpha((float) 0.2);
                }
            }
        });

        iv_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iv_next.setAlpha((float) 1.0);
                iv_last.setAlpha((float) 1.0);

                counter -= 1;
                if (counter >= 0) {
                    movietitle2.setText(aListMovies.get(counter).name);
                    moviedescription2.setText(aListMovies.get(counter).description);
                    moviegenre2.setText(aListMovies.get(counter).genre);
                    movierating2.setText(String.valueOf(aListMovies.get(counter).rating) + "/5");
                    movieyear2.setText(String.valueOf(aListMovies.get(counter).year));
                    movieimdb2.setText(aListMovies.get(counter).imdb);
                }
                if (counter == 0) {
                    iv_prev.setAlpha((float) 0.2);
                    iv_first.setAlpha((float) 0.2);
                }
            }
        });

        iv_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iv_next.setAlpha((float) 1.0);
                iv_last.setAlpha((float) 1.0);

                counter = 0;
                if (counter < aListMovies.size()) {
                    movietitle2.setText(aListMovies.get(counter).name);
                    moviedescription2.setText(aListMovies.get(counter).description);
                    moviegenre2.setText(aListMovies.get(counter).genre);
                    movierating2.setText(String.valueOf(aListMovies.get(counter).rating) + "/5");
                    movieyear2.setText(String.valueOf(aListMovies.get(counter).year));
                    movieimdb2.setText(aListMovies.get(counter).imdb);
                    iv_prev.setAlpha((float) 0.2);
                    iv_first.setAlpha((float) 0.2);
                }
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}