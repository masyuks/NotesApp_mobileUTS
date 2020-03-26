package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;

import com.example.notesapp.adapters.NotesAdapter;
import com.example.notesapp.models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnItemNotesListener {

    public static final String NOTES_KEY = "NOTES";
    public static final String INDEX_KEY = "INDEX";
    public static final int INSERT_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;
    private RecyclerView notesView;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesView = findViewById(R.id.rv_notes);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Tambahkan event click fab di sini
                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                intent.putExtra(NOTES_KEY, new Notes());
                startActivityForResult(intent, INSERT_REQUEST);
            }
        });
    }


    public void handleAdd(View view) {
        Intent intent = new Intent(MainActivity.this, SaveActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTransactionClicked(int index, Notes item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
