package com.example.adrija.todo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrija.todo.MainActivity;
import com.example.adrija.todo.Model.ToDo;
import com.example.adrija.todo.R;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by Adrija on 28-01-2018.
 */

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener
{

    ItemClickListener itemClickListener;
    TextView item_title,item_description;

    public ListItemViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnClickListener(this);

        item_title=(TextView)itemView.findViewById(R.id.item_title);
        item_description=(TextView)itemView.findViewById(R.id.item_description);
        itemView.setOnCreateContextMenuListener(this);

    }

   public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
   }


    @Override
    public void onClick(View view) {

       itemClickListener.onClick(view,getAdapterPosition(),false);

    }


    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
contextMenu.setHeaderTitle("Select the action");
contextMenu.add(0,getAdapterPosition(),0,"DELETE");

    }


}

public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder>{

    MainActivity mainActivity;

    public ListItemAdapter(MainActivity mainActivity, List<ToDo> todoList) {
        this.mainActivity = mainActivity;
        this.todoList = todoList;
    }

    List<ToDo> todoList;



    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mainActivity.getBaseContext());
        View view=inflater.inflate(R.layout.list_item,parent,false);

        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, final int position) {

        holder.item_title.setText(todoList.get(position).getTitle());
        holder.item_description.setText(todoList.get(position).getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mainActivity.title.setText(todoList.get(position).getTitle());
                mainActivity.description.setText(todoList.get(position).getDescription());

                mainActivity.isUpdate=true;
                mainActivity.idUpdate=todoList.get(position).getId();

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
    }



    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
