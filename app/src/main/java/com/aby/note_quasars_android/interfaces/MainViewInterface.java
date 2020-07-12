package com.aby.note_quasars_android.interfaces;

import com.aby.note_quasars_android.model.Note;

import java.util.List;

public interface MainViewInterface {

    void onNotesLoaded(List<Note> notes);

    void onNoteAdded();

    void onDataNotAvailable();

}
