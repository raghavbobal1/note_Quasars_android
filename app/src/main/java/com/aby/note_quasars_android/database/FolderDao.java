package com.aby.note_quasars_android.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface FolderDao {

    @Query("SELECT * FROM folder")
    Maybe<List<Folder>> getAll();

    @Insert
    void insertAll(Folder... folders);

    @Update
    void update(Folder folder);

    @Delete
    void delete(Folder folder);

}
