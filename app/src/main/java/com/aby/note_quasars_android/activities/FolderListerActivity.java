package com.aby.note_quasars_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aby.note_quasars_android.R;
import com.aby.note_quasars_android.adapters.FoldersAdapter;
import com.aby.note_quasars_android.adapters.NotesAdapter;
import com.aby.note_quasars_android.database.Folder;
import com.aby.note_quasars_android.database.LocalCacheManager;
import com.aby.note_quasars_android.database.Note;
import com.aby.note_quasars_android.interfaces.FolderListerInterface;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.core.view.ViewCompat.getTransitionName;

public class FolderListerActivity extends AppCompatActivity implements FolderListerInterface {


    @BindView(R.id.rvFolders)
    RecyclerView rvFolders;

    FoldersAdapter adapter;
    List<Folder> folderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_lister);
        ButterKnife.bind(this);

        initViews();
        loadFolders();

    }

    private void initViews() {

        rvFolders.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadFolders(){

        //Call Method to get Notes
        LocalCacheManager.getInstance(this).getFolders(this);


    }

    @Override
    public void onFoldersLoaded(List<Folder> folders) {

        folderList = folders;

        if(folderList.size() == 0){
            onDataNotAvailable();
        }else {
            adapter = new FoldersAdapter(this, folders);
            adapter.setOnItemClickListner(new FoldersAdapter.OnItemClickListner() {
                @Override
                public void onItemClick(Folder folder, View view) {
                    Intent intent = new Intent(FolderListerActivity.this,MainActivity.class);
//                    intent.putExtra(NOTE_OBJECT_NAME,note);
//                    intent.putExtra("transition_name", getTransitionName(view));

                    ActivityOptionsCompat options;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            FolderListerActivity.this,
                            view,
                            Objects.requireNonNull(getTransitionName(view)));

                    startActivity(intent, options.toBundle());

                }
            });

            rvFolders.setAdapter(adapter);
        }

    }

    @Override
    public void onFolderAdded() {

    }

    @Override
    public void onDataNotAvailable() {

    }
}