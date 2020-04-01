package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.SurfaceControl;
import android.view.View;

import com.example.notesapp.adapters.NotesAdapter;
import com.example.notesapp.models.Notes;
import com.example.notesapp.models.Rv_List;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnItemNotesListener {

    public static final String NOTES_KEY = "NOTES";
    public static final String INDEX_KEY = "INDEX";
    public static final int INSERT_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;
    private RecyclerView notesView;
    private NotesAdapter adapter;
    private Rv_List rv_list;

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
        rv_list = Application.getRv_List();

        adapter = new NotesAdapter(rv_list.getNotes(), this);
        notesView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        notesView.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(notesView);
    }

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
            new ItemTouchHelper.SimpleCallback(
                    0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(
                        @NonNull RecyclerView recyclerView,
                        @NonNull RecyclerView.ViewHolder viewHolder,
                        @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int index = viewHolder.getAdapterPosition();
                    rv_list.removeNotes(index);
                    adapter.notifyDataSetChanged();
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Notes note = data.getParcelableExtra(NOTES_KEY);
            if (requestCode == INSERT_REQUEST) {
                rv_list.addNotes(note);
            }
            else if (requestCode == UPDATE_REQUEST) {
                int index = data.getIntExtra(INDEX_KEY, 0);
                rv_list.updateNotes(index, note);
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onTransactionClicked(int index, Notes item) {
        //update
        Intent intent = new Intent(this, SaveActivity.class);
        intent.putExtra(NOTES_KEY, item);
        intent.putExtra(INDEX_KEY, index);
        startActivityForResult(intent, UPDATE_REQUEST);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
