package sg.edu.rp.c346.id22024905.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView tvID, tvSongTitle, tvSinger, tvYear, tvStars;
    EditText etID, etTitle, etSinger, etYear;
    RadioGroup rgRating;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvID = findViewById(R.id.tvID);
        tvSongTitle = findViewById(R.id.tvSongtitle);
        tvSinger = findViewById(R.id.tvSinger);
        tvYear = findViewById(R.id.tvYear);
        tvStars = findViewById(R.id.tvStars);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgRating = findViewById(R.id.radioGroupRating);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");


        etID.setText(String.valueOf(data.get_id()));
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        int rating = data.getStars();
        RadioButton radioButton = findViewById(getRadioButtonIdForRating(rating));
        radioButton.setChecked(true);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);

                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

                int selectedRadioButtonId = rgRating.getCheckedRadioButtonId();

                int star;
                if (selectedRadioButtonId == R.id.rbtn1) {
                    star = 1;
                } else if (selectedRadioButtonId == R.id.rbtn2) {
                    star = 2;
                }  else if (selectedRadioButtonId == R.id.rbtn3) {
                    star = 3;
                }  else if (selectedRadioButtonId == R.id.rbtn4) {
                    star = 4;
                } else{
                    star = 5;
                }

                data.setStars(star);


                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.get_id());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private int getRadioButtonIdForRating(int rating) {
        switch (rating) {
            case 1:
                return R.id.rbtn1;
            case 2:
                return R.id.rbtn2;
            case 3:
                return R.id.rbtn3;
            case 4:
                return R.id.rbtn4;
            case 5:
                return R.id.rbtn5;
            default:
                return -1;
        }
    }
}