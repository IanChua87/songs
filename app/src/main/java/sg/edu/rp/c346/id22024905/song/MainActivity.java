package sg.edu.rp.c346.id22024905.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etSong;
    EditText etSinger;
    EditText etYear;
    RadioGroup rgStars;
    Button btnInsert;
    Button btnShow;
    TextView tvShow;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;

    ArrayList songList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSong = findViewById(R.id.editTextSong);
        etSinger = findViewById(R.id.editTextSinger);
        etYear = findViewById(R.id.editTextYear);
        rgStars = findViewById(R.id.radioGroupStars);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShowList);
        tvShow = findViewById(R.id.textViewShow);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                String song = etSong.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int star;
                int checkedRadioId = rgStars.getCheckedRadioButtonId();

                if (checkedRadioId == R.id.radioButton) {
                    star = 1;
                } else if (checkedRadioId == R.id.radioButton2) {
                    star = 2;
                }  else if (checkedRadioId == R.id.radioButton3) {
                    star = 3;
                }  else if (checkedRadioId == R.id.radioButton4) {
                    star = 4;
                } else{
                    star = 5;
                }
                db.insertSong(song, singer, year, star);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}