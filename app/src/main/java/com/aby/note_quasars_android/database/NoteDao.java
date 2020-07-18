package com.aby.note_quasars_android.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    Maybe<List<Note>> getAll();

    @Insert
    void insertAll(Note... notes);

}
