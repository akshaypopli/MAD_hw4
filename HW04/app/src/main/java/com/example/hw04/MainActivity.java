package com.example.hw04;

//      MainActivity
//      Group Number: groups1 3
//      Akshay Popli and Neel Solanki

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button add_movie;
    private Button edit_movie;
    private Button delete_movie;
    private Button show_year;
    private Button show_rating;
    public ArrayList<Movie> movielist = new ArrayList<>();

    int movieindex;


    static String MOVIE_KEY = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favorite Movies");

        add_movie = findViewById(R.id.add_movie);
        edit_movie = findViewById(R.id.edit_movie);
        delete_movie = findViewById(R.id.delete_movie);
        show_year = findViewById(R.id.show_year);
        show_rating = findViewById(R.id.show_rating);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Movie");

        add_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
                intent.putExtra(MOVIE_KEY, 100);
                startActivityForResult(intent, 1);
            }
        });

        edit_movie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ArrayList<String> names = new ArrayList<>();
                for (Movie moviename : movielist) {
                    names.add(moviename.name);
                }
                final String[] movieArr = names.toArray(new String[names.size()]);

                builder.setItems(movieArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("demo", "Selected" + movielist.get(which));
                        movieindex = which;
                        Intent intent = new Intent(MainActivity.this, EditMovieActivity.class);
                        intent.putExtra("editmovie", movielist.get(which));
                        intent.putExtra(MOVIE_KEY, 200);
                        startActivityForResult(intent, 2);
                    }
                });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });

        delete_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> names = new ArrayList<>();
                for (Movie moviename : movielist) {
                    names.add(moviename.name);
                }
                final String[] movieArr = names.toArray(new String[names.size()]);

                builder.setItems(movieArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("demo", "Selected" + movielist.get(which));
                        movielist.remove(which);
                        Toast.makeText(getApplicationContext(),"Delete Movie: " + movieArr[which],Toast.LENGTH_SHORT).show();
                    }
                });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });

        show_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.hw04.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("allmovies", movielist);
                intent.putExtra(MOVIE_KEY, 400);
                startActivity(intent);

            }
        });

        show_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.hw04.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("allmovies", movielist);
                intent.putExtra(MOVIE_KEY, 500);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Movie movie0 = (Movie) data.getExtras().getSerializable("movie1");
                Log.d("demo2", movie0.toString());
                movielist.add(movie0);
            } else if (resultCode == AddMovieActivity.RESULT_CANCELED) {
                Log.d("demo2", "no data");
            }
        }

        else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Movie movie0 = (Movie) data.getExtras().getSerializable("movie1");
                Log.d("demo2", movie0.toString());
                movielist.set(movieindex, movie0);
            } else if (resultCode == AddMovieActivity.RESULT_CANCELED) {
                Log.d("demo2", "no data");
            }
        }
    }
}
