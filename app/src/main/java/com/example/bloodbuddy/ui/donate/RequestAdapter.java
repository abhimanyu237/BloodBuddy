package com.example.bloodbuddy.ui.donate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbuddy.R;
import com.example.bloodbuddy.ui.request.RequestData;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{


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

        RequestData item = list.get(position);
        String a=item.getPatient_first_name()+" "+item.getPatient_last_name();
        holder.name.setText(a);
        holder.units.setText(item.getUnits());
        String b=item.getState()+" , "+item.getCity()+" , "+item.getLocal_address();
        holder.address.setText(b);
        holder.date.setText(item.getDonate_date());

        String bg=item.getSelect_blood_grp();

        try{

            if(bg.equals("O+")){
                Glide.
                        with(context)
                        .load(R.drawable.bg_o_pos)
                        .into(holder.bg_image);
            }else  if(bg.equals("O-")){
                Glide.
                        with(context)
                        .load(R.drawable.bg_o_neg)
                        .into(holder.bg_image);
            }else  if(bg.equals("A+")){
                Glide.
                        with(context)
                        .load(R.drawable.bg_a_pos)
                        .into(holder.bg_image);
            }else  if(bg.equals("A-")){
                Glide.
                        with(context)
                        .load(R.drawable.bg_a_neg)
                        .into(holder.bg_image);
            }else  if(bg.equals("B+")){
                Glide.
                        with(context)
                        .load(R.drawable.bg_b_pos)
                        .into(holder.bg_image);
            }else  if(bg.equals("B-")){
                Glide.
                        with(context)
                        .load(R.drawable.bg_b_neg)
                        .into(holder.bg_image);
            }else  if(bg.equals("AB+")){
                Glide.
                        with(context)
                        .load(R.drawable.bg_ab_pos)
                        .into(holder.bg_image);
            }else  if(bg.equals("AB-")){
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

        private ImageView bg_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            units = itemView.findViewById(R.id.units);
            address = itemView.findViewById(R.id.address);
            date = itemView.findViewById(R.id.date);
            bg_image=itemView.findViewById(R.id.bg_image);

        }
    }


}
