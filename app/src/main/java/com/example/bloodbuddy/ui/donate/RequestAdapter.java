package com.example.bloodbuddy.ui.donate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.name.setText(item.getPatient_first_name()+" "+item.getPatient_last_name());
        holder.units.setText(item.getUnits());
        holder.address.setText(item.getLocal_address());
//          holder.date.setText(item.getDate());// date wala box nhi banaya abhi//
//
          // image bad me banayenge//
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
