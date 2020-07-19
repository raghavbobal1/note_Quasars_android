package com.aby.note_quasars_android.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import butterknife.OnClick;

import static androidx.core.view.ViewCompat.getTransitionName;

public class FolderListerActivity extends AppCompatActivity implements FolderListerInterface {


    final String FOLDER_OBJ_NAME = "folderObj";
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

    @OnClick(R.id.fabAddFolder)
    public void addFolder(){
        // Creating alert Dialog with one Button
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FolderListerActivity.this);

        //AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Create New Folder");

        // Setting Dialog Message
        alertDialog.setMessage("Enter Folder Name");
        final EditText input = new EditText(FolderListerActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        //alertDialog.setView(input);



        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Create",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        // Write your code here to execute after dialog
                        LocalCacheManager.getInstance(FolderListerActivity.this).addFolders(FolderListerActivity.this
                                ,input.getText().toString());

                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        // closed

        // Showing Alert Message
        alertDialog.show();

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
                    intent.putExtra(FOLDER_OBJ_NAME,folder);
                    intent.putExtra("transition_name", getTransitionName(view));

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

        Toast.makeText(getApplicationContext(),"Folder added", Toast.LENGTH_SHORT).show();
        loadFolders();

    }

    @Override
    public void onDataNotAvailable() {

    }
}