package com.aby.note_quasars_android.interfaces;

import com.aby.note_quasars_android.database.Folder;
import com.aby.note_quasars_android.database.Note;

import java.util.List;

public interface FolderListerInterface {

    void onFoldersLoaded(List<Folder> folders);

    void onFolderAdded();

    void onDataNotAvailable();

}
