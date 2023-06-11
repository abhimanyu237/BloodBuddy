package com.example.bloodbuddy.ui.donate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bloodbuddy.R;
import com.example.bloodbuddy.UserData;
import com.example.bloodbuddy.ui.request.RequestData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class OtherBloodGroupFragment extends Fragment {
    private DatabaseReference databaseRef;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private RecyclerView recyclerView;
    private LinearLayout linear_layout;
    private String phone=null;
    private String state=null;
    private String district=null;
    private String bg=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_other_blood_group, container, false);

        recyclerView=view.findViewById(R.id.recyclerView);


        db=FirebaseDatabase.getInstance();
        databaseRef = db.getReference();
        auth=FirebaseAuth.getInstance();

        phone=auth.getCurrentUser().getPhoneNumber();


        getUserData();





        return view;
    }

    void getUserData(){
        FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists())
                        {
                            UserData userData=dataSnapshot.getValue(UserData.class);

                            state=userData.getState();
                            district=userData.getDistrict();
                            bg=userData.getBlood_grp();


                            Toast.makeText(getContext(), phone+" "+state+" "+district+" "+bg, Toast.LENGTH_SHORT).show();
                            loadRequest();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }

    void loadRequest(){
        DatabaseReference myRef= databaseRef.child("requests").child(state).child(district);
        //      Toast.makeText(getContext(), state, Toast.LENGTH_SHORT).show();
        //

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
                    Toast.makeText(getContext(), "No Request Available", Toast.LENGTH_SHORT).show();
                }
                else
                {



                    List<RequestData> list=new ArrayList();

                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        RequestData data=snaps.getValue(RequestData.class);
                        String s="+91"+data.getAttendee_mobile_number();
                        if(!s.equals(phone) && !data.getSelect_blood_grp().equals(bg))
                            list.add(data);
                    }

//                    if(list.size()==0)
//                        Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
//                    else  if(list.size()==1)
//                        Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
//                    else  if(list.size()==2)
//                        Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
//                    else  if(list.size()==3)
//                        Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();

                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



                    RequestAdapter ad=new RequestAdapter(list, getContext());

                    recyclerView.setAdapter(ad);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}