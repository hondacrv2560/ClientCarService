package com.example.client.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Models.Staff;
import com.example.client.R;

import java.util.List;

public class StaffInfoAdapter extends RecyclerView.Adapter<StaffInfoAdapter.StaffInfoHolder> {

    public Context context;
    public List<Staff> staffInfoList;

    public StaffInfoAdapter(Context context, List<Staff> staffInfoList) {
        this.context = context;
        this.staffInfoList = staffInfoList;
    }

    @NonNull
    @Override
    public StaffInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.staff_list, parent, false);
        return new StaffInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffInfoHolder holder, int position) {
        Staff staff = staffInfoList.get(position);
        holder.functionStaff.setText(staff.functionStaff);
        holder.nameStaff.setText(staff.nameStaff);
        holder.surnameStaff.setText(staff.surnameStaff);
        holder.numPhoneStaff.setText(staff.numPhoneStaff);
        holder.seriesPassportStaff.setText(staff.seriesPassportStaff);
        holder.numberPassportStaff.setText((staff.numberPassportStaff)+"");
        holder.passportIssuingAuthorityStaff.setText(staff.passportIssuingAuthorityStaff);
        holder.dateIssuePassportStaff.setText(staff.dateIssuePassportStaff);
        holder.dateBirthStaff.setText((staff.dateBirthStaff));
    }

    @Override
    public int getItemCount() {
        return staffInfoList.size();
    }

    class StaffInfoHolder extends RecyclerView.ViewHolder{

        TextView functionStaff;
        TextView nameStaff;
        TextView surnameStaff;
        TextView numPhoneStaff;
        TextView seriesPassportStaff;
        TextView numberPassportStaff;
        TextView passportIssuingAuthorityStaff;
        TextView dateIssuePassportStaff;
        TextView dateBirthStaff;

        public StaffInfoHolder(@NonNull View itemView) {
            super(itemView);
            functionStaff = itemView.findViewById(R.id.functionStaff);
            nameStaff = itemView.findViewById(R.id.nameStaff);
            surnameStaff = itemView.findViewById(R.id.surnameStaff);
            numPhoneStaff = itemView.findViewById(R.id.numPhoneStaff);
            seriesPassportStaff = itemView.findViewById(R.id.seriesPassportStaff);
            numberPassportStaff = itemView.findViewById(R.id.numberPassportStaff);
            passportIssuingAuthorityStaff = itemView.findViewById(R.id.passportIssuingAuthorityStaff);
            dateIssuePassportStaff = itemView.findViewById(R.id.dateIssuePassportStaff);
            dateBirthStaff = itemView.findViewById(R.id.dateBirthStaff);
        }
    }
}
