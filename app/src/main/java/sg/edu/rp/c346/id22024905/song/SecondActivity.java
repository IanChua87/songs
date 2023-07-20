package sg.edu.rp.c346.id22024905.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Button btnShow5Stars;
    ListView lvSong;
    //    ArrayList<String> songTitles;
    ArrayList<Song> items = new ArrayList<>();
    //    ArrayList<String> items = new ArrayList();
//    ArrayAdapter<Song> adapter;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShow5Stars = findViewById(R.id.buttomShow5Star);
        lvSong = findViewById(R.id.listViewSong);

        DBHelper db = new DBHelper(SecondActivity.this);
        items = db.getSongs();
//        db.close();

//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        adapter = new CustomAdapter(this, R.layout.row, items);
        lvSong.setAdapter(adapter);


        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = items.get(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        btnShow5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getStars() == 5) {
                        lvSong.setAdapter(adapter);
                    }
//                    } else{
//                        adapter.clear();
//                    }
                }
            }
        });
//        adapter.clear();
//        adapter.addAll(items);
//        adapter.notifyDataSetChanged();
    }

    protected void onResume() {
        super.onResume();

        if (getIntent().getBooleanExtra("dataModified", false)) {
            // Refresh the data in the ListView
            loadSongData(); // Replace this with the actual method you use to load the data
        }
    }

    private void loadSongData() {
        // Retrieve the song data from the database or any other data source
        DBHelper dbHelper = new DBHelper(SecondActivity.this);
        ArrayList<Song> songList = dbHelper.getSongs();

        // Create an ArrayAdapter or a custom adapter for the ListView
        ArrayAdapter<Song> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);

        // Set the adapter to the ListView
        lvSong.setAdapter(adapter);
    }
}