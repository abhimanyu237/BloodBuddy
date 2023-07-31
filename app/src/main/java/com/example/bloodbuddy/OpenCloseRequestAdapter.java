package com.example.bloodbuddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.bloodbuddy.ui.donate.AcceptActivity;
import com.example.bloodbuddy.ui.donate.RequestAdapter;
import com.example.bloodbuddy.ui.request.RequestData;
import com.example.bloodbuddy.ui.request.RequestFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class OpenCloseRequestAdapter extends RecyclerView.Adapter<OpenCloseRequestAdapter.ViewHolder>{

    private RequestData requestData;
    //1 data
    private List<RequestData> list;
    private Context context;

    private int check;

    public OpenCloseRequestAdapter(List<RequestData> list, Context context,int check) {
        this.list = list;
        this.context = context;
        this.check=check;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.open_close_request_item_layout, parent, false);
        return new OpenCloseRequestAdapter.ViewHolder(view);
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
         String phone=requestData.getAttendee_mobile_number().trim();
        String code=requestData.getRequestId().trim();

         if(check==2){
             holder.close.setText("Request Closed");
         }




             holder.close.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                    if(check==1){




                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference openRequestRef = databaseRef.child("userRequests").child(phone).child("openRequests");
                        openRequestRef.child(code).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                           // @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {

                                    storeForCloseRequests(phone,code);
                                    deleteFromRequestsTable(requestData.getState(),requestData.getDistrict(),code);
                                    notifyDataSetChanged();
                                     context.startActivity(new Intent(context,Navigation_Request_History.class));
                                     notifyDataSetChanged();
//
                                }
                                else
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });



                    }



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
        private Button close;
        private ImageView bg_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            units = itemView.findViewById(R.id.units);
            address = itemView.findViewById(R.id.address);
            date = itemView.findViewById(R.id.date);
            bg_image=itemView.findViewById(R.id.bg_image);
            close=itemView.findViewById(R.id.close);
        }
    }

    private void storeForCloseRequests(String phone ,String code){


        FirebaseDatabase.getInstance().getReference().child("userRequests").child(phone).child("closeRequests").child(code).setValue(requestData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(!task.isSuccessful())
                {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void deleteFromRequestsTable(String state,String district, String code){

        DatabaseReference DRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = DRef.child("requests").child(state).child(district);

        ref.child(code).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(!task.isSuccessful())
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

                }


            }
        });




    }
}
