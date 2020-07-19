package com.aby.note_quasars_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aby.note_quasars_android.database.LocalCacheManager;
import com.aby.note_quasars_android.interfaces.MainViewInterface;
import com.aby.note_quasars_android.database.Note;
import com.aby.note_quasars_android.adapters.NotesAdapter;
import com.aby.note_quasars_android.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static androidx.core.view.ViewCompat.*;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private String NOTE_OBJECT_NAME = "NoteOBJECT";
    @BindView(R.id.rvNotes)
    RecyclerView rvNotes;

    NotesAdapter adapter;
    List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        loadNotes();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void initViews() {

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadNotes(){

        //Call Method to get Notes
        LocalCacheManager.getInstance(this).getNotes(this);


    }

    @OnClick(R.id.fabAddNote)
    public void addNote(){
        Intent i = new Intent(MainActivity.this,AddNoteActivity.class);
        startActivity(i);
    }

    @Override
    public void onNotesLoaded(List<Note> notes) {
        notesList = notes;

        if(notesList.size() == 0){
            onDataNotAvailable();
        }else {
            adapter = new NotesAdapter(this, notes);
            adapter.setOnItemClickListner(new NotesAdapter.OnItemClickListner() {
                @Override
                public void onItemClick(Note note, View view) {
                    Intent intent = new Intent(MainActivity.this,EditableDetailViewActivity.class);
                    intent.putExtra(NOTE_OBJECT_NAME,note);
                    intent.putExtra("transition_name", getTransitionName(view));

                    ActivityOptionsCompat options;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            MainActivity.this,
                            view,
                            Objects.requireNonNull(getTransitionName(view)));

                    startActivity(intent, options.toBundle());
                }
            });
            rvNotes.setAdapter(adapter);
        }
    }

    @Override
    public void onNoteAdded() {
        Toast.makeText(this,"Note Added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataNotAvailable() {
        Toast.makeText(this,"No Notes Yet",Toast.LENGTH_SHORT).show();
    }
}