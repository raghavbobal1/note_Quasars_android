package com.aby.note_quasars_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.aby.note_quasars_android.R;
import com.aby.note_quasars_android.database.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditableDetailViewActivity extends AppCompatActivity {

    private String NOTE_OBJECT_NAME = "NoteOBJECT";

    @BindView(R.id.tvNoteTitleDetail)
    TextView tvNoteTitleDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_detail_view);
        ButterKnife.bind(this);

        // setup all views
        setupViews();
    }

    private void setupViews(){

        Intent intent = getIntent();
        if(intent.getSerializableExtra(NOTE_OBJECT_NAME) != null){
            Note note = (Note) intent.getSerializableExtra(NOTE_OBJECT_NAME);
            tvNoteTitleDetail.setText(note.getTitle());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_note,menu);
        return true;
    }
}