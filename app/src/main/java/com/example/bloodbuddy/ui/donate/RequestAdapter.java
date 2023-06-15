package com.example.bloodbuddy.ui.donate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbuddy.R;
import com.example.bloodbuddy.ui.request.RequestData;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{

    private RequestData requestData;
    //1 data
    private List<RequestData> list;
    private Context context;


    public RequestAdapter(List<RequestData> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.request_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        requestData = list.get(holder.getAdapterPosition());
        String name="Name : "+requestData.getPatient_first_name()+" "+requestData.getPatient_last_name();
        String unit="Unit : "+requestData.getUnits();
     String address="Address : "+requestData.getState()+" , "+requestData.getDistrict()+" , "+requestData.getLocal_address();
        String date="Date : "+requestData.getDonate_date();
        holder.name.setText(name);
        holder.units.setText(unit);
        holder.address.setText(address);
        holder.date.setText(date);

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AcceptActivity.class);
                intent.putExtra("requestData",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

        String bg=requestData.getSelect_blood_grp();

        try{
            String a_pos = "A+";
            String a_neg = "A-";
            String b_pos = "B+";
            String b_neg = "B-";
            String ab_pos = "AB+";
            String ab_neg = "AB-";
            String o_pos = "O+";
            String o_neg = "O-";
            if(bg.trim().equals(o_pos)){
                Glide.
                        with(context)
                        .load(R.drawable.bg_o_pos)
                        .into(holder.bg_image);
            }else  if(bg.trim().equals(o_neg)){
                Glide.
                        with(context)
                        .load(R.drawable.bg_o_neg)
                        .into(holder.bg_image);
            }else  if(bg.trim().equals(a_pos)){
                Glide.
                        with(context)
                        .load(R.drawable.bg_a_pos)
                        .into(holder.bg_image);
            }else  if(bg.trim().equals(a_neg)){
                Glide.
                        with(context)
                        .load(R.drawable.bg_a_neg)
                        .into(holder.bg_image);
            }else  if(bg.trim().equals(b_pos)){
                Glide.
                        with(context)
                        .load(R.drawable.bg_b_pos)
                        .into(holder.bg_image);
            }else  if(bg.trim().equals(b_neg)){
                Glide.
                        with(context)
                        .load(R.drawable.bg_b_neg)
                        .into(holder.bg_image);
            }else  if(bg.trim().equals(ab_pos)){

                Glide.
                        with(context)
                        .load(R.drawable.bg_ab_pos)
                        .into(holder.bg_image);
            }else  if(bg.trim().equals(ab_neg)){
                Glide.
                        with(context)
                        .load(R.drawable.bg_ab_neg)
                        .into(holder.bg_image);
            }
            else{
                Glide.
                        with(context)
                        .load(R.drawable.bg_img)
                        .into(holder.bg_image);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, units, address,date;
        private Button accept;
        private ImageView bg_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            units = itemView.findViewById(R.id.units);
            address = itemView.findViewById(R.id.address);
            date = itemView.findViewById(R.id.date);
            bg_image=itemView.findViewById(R.id.bg_image);
              accept=itemView.findViewById(R.id.accept);
        }
    }



}
