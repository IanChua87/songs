package sg.edu.rp.c346.id22024905.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnShow5Stars;
    ListView lvSong;
    ArrayList<String> songTitles;
    ArrayList<Song> songLv;
//    ArrayList<String> items = new ArrayList();

    ArrayAdapter<Song> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShow5Stars = findViewById(R.id.buttomShow5Star);
        lvSong = findViewById(R.id.listViewSong);

        songLv = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songLv);
        lvSong.setAdapter(adapter);

        DBHelper db = new DBHelper(SecondActivity.this);
//        songTitles = db.getSongContent();
        songLv = db.getSongs();
//        db.close();

        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = songLv.get(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        btnShow5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < songLv.size(); i++){
                    if(songLv.get(i).getStars() == 5){
                        adapter.clear();
                        adapter.addAll(songLv.get(i));
                        adapter.notifyDataSetChanged();
                    } else{
                        adapter.clear();
                    }
                }
            }
        });
        adapter.clear();
        adapter.addAll(songLv);
        adapter.notifyDataSetChanged();
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