package com.aby.note_quasars_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.aby.note_quasars_android.R;
import com.aby.note_quasars_android.database.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditableDetailViewActivity extends AppCompatActivity {

    private String NOTE_OBJECT_NAME = "NoteOBJECT";

    @BindView(R.id.tvNoteTitleDetail)
    EditText tvNoteTitleDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_detail_view);
        ButterKnife.bind(this);

        // setup all views
        setupViewsForDetail();
    }

    private void setupViewsForDetail(){

        Intent intent = getIntent();
        if(intent.getSerializableExtra(NOTE_OBJECT_NAME) != null){
            Note note = (Note) intent.getSerializableExtra(NOTE_OBJECT_NAME);
            String titleTransitionName = intent.getStringExtra("transition_name");
            tvNoteTitleDetail.setTransitionName(titleTransitionName);
            tvNoteTitleDetail.setText(note.getTitle());
            tvNoteTitleDetail.setEnabled(false);
            tvNoteTitleDetail.setBackground(null);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_note,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.edit_note){
            setupForEdit();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupForEdit(){
        tvNoteTitleDetail.setEnabled(true);
    }
}