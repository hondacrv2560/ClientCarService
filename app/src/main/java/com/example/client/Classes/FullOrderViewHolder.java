package com.example.client.Classes;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Interface.IItemClickListener;
import com.example.client.R;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

public class FullOrderViewHolder extends RecyclerView.ViewHolder {

//    public TextView txt_item_text;
//    public TextView txt_child_text;
//    public TextView txt_price_child;

    public TextView txt_idService;
    public TextView txt_titleService;
    public TextView txt_price_BigSUV;
    public TextView txt_price_SUV;
    public TextView txt_price_business;
    public TextView txt_price_premium;
    public TextView txt_price_sedan;
    public TextView txt_cat_BigSUV;
    public TextView txt_cat_SUV;
    public TextView txt_cat_business;
    public TextView txt_cat_premium;
    public TextView txt_cat_sedan;
    public CheckBox checkBoxSedan;
    public CheckBox checkBoxBigSUV;
    public CheckBox checkBoxSUV;
    public CheckBox checkBoxBusiness;
    public CheckBox checkBoxPremium;
    public RelativeLayout relativeLayout;
    public ExpandableLinearLayout expandableLinearLayout;

    IItemClickListener iItemClickListener;

    public void setiItemClickListener(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;
    }

    public FullOrderViewHolder(@NonNull View itemView, boolean isExpandable) {
        super(itemView);

        if(isExpandable){
            txt_titleService = itemView.findViewById(R.id.titleService);
            txt_idService = itemView.findViewById(R.id.idService);

            txt_cat_BigSUV = itemView.findViewById(R.id.cat_BigSUV);
            txt_cat_SUV = itemView.findViewById(R.id.cat_SUV);
            txt_cat_business = itemView.findViewById(R.id.cat_business);
            txt_cat_premium = itemView.findViewById(R.id.cat_premium);
            txt_cat_sedan = itemView.findViewById(R.id.cat_sedan);

            txt_price_BigSUV = itemView.findViewById(R.id.price_BigSUV);
            txt_price_SUV = itemView.findViewById(R.id.price_SUV);
            txt_price_business = itemView.findViewById(R.id.price_business);
            txt_price_premium = itemView.findViewById(R.id.price_premium);
            txt_price_sedan = itemView.findViewById(R.id.price_sedan);

            checkBoxBigSUV = itemView.findViewById(R.id.checkboxOrdersBigSuv);
            checkBoxSUV = itemView.findViewById(R.id.checkboxOrdersSuv);
            checkBoxBusiness = itemView.findViewById(R.id.checkboxOrdersBusiness);
            checkBoxPremium = itemView.findViewById(R.id.checkboxOrdersPremium);
            checkBoxSedan = itemView.findViewById(R.id.checkboxOrdersSedan);

            relativeLayout = itemView.findViewById(R.id.rowUp);
            expandableLinearLayout = itemView.findViewById(R.id.expandableLayout);
        }else{
            txt_titleService = itemView.findViewById(R.id.titleService);
        }

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iItemClickListener.onClick(v, getAdapterPosition());
//            }
//        });

    }
}
