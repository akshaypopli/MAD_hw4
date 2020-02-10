package com.example.hw04;

//      EditMovieActivity
//      Group Number: groups1 3
//      Akshay Popli and Neel Solanki

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditMovieActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText et_name;
    private EditText et_description;
    private Spinner genre_drop;
    private SeekBar seekBar;
    private TextView tv_rating1;
    private EditText et_year;
    private EditText et_imdb;
    private Button btn_save;

    String movie_name;
    String movie_description;
    String selected_genre;
    int rating;
    int movie_year;
    String movie_imdb;
    Movie movie0;
    int stringPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        setTitle("Edit Movie");

        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        seekBar = findViewById(R.id.seekBar);
        tv_rating1 = findViewById(R.id.tv_rating1);
        et_year = findViewById(R.id.et_year);
        et_imdb = findViewById(R.id.et_imdb);
        btn_save = findViewById(R.id.btn_save);
        genre_drop = findViewById(R.id.genre_drop);

        movie0 = (Movie) getIntent().getExtras().getSerializable("editmovie");

        et_name.setText(movie0.name);
        et_description.setText(movie0.description);
        et_year.setText(String.valueOf(movie0.year));
        et_imdb.setText(movie0.imdb);

        rating = movie0.rating;
        seekBar.setProgress(rating);
        tv_rating1.setText(String.valueOf(rating));
        selected_genre = movie0.genre;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                tv_rating1.setText(String.valueOf(i));
                rating = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genre_drop.setAdapter(adapter);
        String[] stringArray = getResources().getStringArray(R.array.genre_array);

        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].contains(selected_genre)) {
                stringPos = i;
                genre_drop.setSelection(stringPos);
            }
        }

        genre_drop.setOnItemSelectedListener(this);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_name = et_name.getText().toString();
                movie_description = et_description.getText().toString();

                final String tempyear = et_year.getText().toString();
                if (tempyear != null && !tempyear.equals("")) {
                    movie_year = Integer.parseInt(tempyear);
                }

                movie_imdb = et_imdb.getText().toString();

                if (movie_name.trim().equalsIgnoreCase("")) {
                    et_name.setError("Invalid Input");
                } else if (movie_description.trim().equalsIgnoreCase("")) {
                    et_description.setError("Invalid Input");
                } else if (selected_genre.equals("Select")) {
                    Toast.makeText(getApplicationContext(), "Please Select a Genre", Toast.LENGTH_SHORT).show();
                } else if (et_year.getText().toString().trim().equalsIgnoreCase("")) {
                    et_year.setError("Invalid Input");
                } else if (movie_imdb.trim().equalsIgnoreCase("")) {
                    et_imdb.setError("Invalid Input");
                } else {
                    Movie movie1 = new Movie(movie_name, movie_description, selected_genre, rating, movie_year, movie_imdb);
                    Toast.makeText(getApplicationContext(), "Movie Updated: " + movie_name, Toast.LENGTH_SHORT).show();

                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("movie1", movie1);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_genre = parent.getItemAtPosition(position).toString();
        Log.d("demo", selected_genre);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}