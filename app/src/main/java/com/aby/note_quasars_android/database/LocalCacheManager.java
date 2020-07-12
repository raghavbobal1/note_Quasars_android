package com.aby.note_quasars_android.database;

import android.content.Context;

import com.aby.note_quasars_android.interfaces.AddNoteViewInterface;
import com.aby.note_quasars_android.interfaces.MainViewInterface;
import com.aby.note_quasars_android.model.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LocalCacheManager {
    private Context context;
    private static LocalCacheManager _instance;
    private AppDatabase db;

    public static LocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new LocalCacheManager(context);
        }
        return _instance;
    }

    public LocalCacheManager(Context context) {
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }

    public void getNotes(final MainViewInterface mainViewInterface) {
        db.noteDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Note>>() {
            @Override
            public void accept(List<Note> notes) throws Exception {
                mainViewInterface.onNotesLoaded(notes);
            }
        });
    }

    public void addNotes(final AddNoteViewInterface addNoteViewInterface, final String title, final String note_text) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Note note = new Note(title, note_text);
                db.noteDao().insertAll(note);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                addNoteViewInterface.onNoteAdded();
            }

            @Override
            public void onError(Throwable e) {
                addNoteViewInterface.onDataNotAvailable();
            }
        });
    }


}
