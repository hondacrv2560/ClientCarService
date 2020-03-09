package com.example.client.Classes;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Interface.IItemClickListener;
import com.example.client.R;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

public class FullOrderViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_item_text;
    public TextView txt_child_text;
    public RelativeLayout relativeLayout;
    public ExpandableLinearLayout expandableLinearLayout;

    IItemClickListener iItemClickListener;

    public void setiItemClickListener(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;
    }

    public FullOrderViewHolder(@NonNull View itemView, boolean isExpandable) {
        super(itemView);

        if(isExpandable){
            txt_item_text = itemView.findViewById(R.id.txt_item_view);
            txt_child_text = itemView.findViewById(R.id.txt_child);
            relativeLayout = itemView.findViewById(R.id.rowUp);
            expandableLinearLayout = itemView.findViewById(R.id.expandableLayout);
        }else{
            txt_item_text = itemView.findViewById(R.id.txt_item_view);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClickListener.onClick(v, getAdapterPosition());
            }
        });

    }
}
