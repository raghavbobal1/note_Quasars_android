package com.aby.note_quasars_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aby.note_quasars_android.R;
import com.aby.note_quasars_android.database.Folder;
import com.aby.note_quasars_android.database.Note;

import java.util.ArrayList;
import java.util.List;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.FoldesViewHolder> {

    Context context;
    List<Folder> folderList = new ArrayList<>();
    private OnItemClickListner listner;


    public FoldersAdapter(Context context, List<Folder> folderList) {
        this.context = context;
        this.folderList = folderList;
    }

    @Override
    public FoldesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_folder,parent,false);
        FoldesViewHolder nvh = new FoldesViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(FoldesViewHolder holder, int position) {
        holder.tvFolderName.setText(folderList.get(position).getName());
        holder.tvNotesCount.setText("test count(5)");
        ViewCompat.setTransitionName(holder.tvFolderName, Integer.toString(folderList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class FoldesViewHolder extends RecyclerView.ViewHolder{

        TextView tvFolderName,tvNotesCount;
        public FoldesViewHolder(View itemView) {
            super(itemView);

            tvFolderName = itemView.findViewById(R.id.tvFolderName);
            tvNotesCount = itemView.findViewById(R.id.tvNotesCount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position  =  getAdapterPosition();

                    if(listner != null && position != RecyclerView.NO_POSITION){
                        listner.onItemClick(folderList.get(position),tvFolderName);
                    }

                }
            });

        }
    }

    public  interface  OnItemClickListner {
        void onItemClick(Folder folder, View view);
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.listner = onItemClickListner;
    }

}

