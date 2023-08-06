package com.example.bloodbuddy;

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

import com.example.bloodbuddy.ui.donate.RequestAdapter;
import com.example.bloodbuddy.ui.request.RequestData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OpenRequestsFragment extends Fragment {

    private DatabaseReference databaseRef;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private RecyclerView recyclerView;
    private LinearLayout linear_layout;
    private String phone=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_open_requests, container, false);


        recyclerView=view.findViewById(R.id.recyclerView);


        db=FirebaseDatabase.getInstance();
        databaseRef = db.getReference();
        auth=FirebaseAuth.getInstance();

        phone=auth.getCurrentUser().getPhoneNumber();


        loadRequest();
        return view;
    }

    void loadRequest(){
        DatabaseReference myRef= databaseRef.child("userRequests").child(phone).child("openRequests");
        //      Toast.makeText(getContext(), state, Toast.LENGTH_SHORT).show();
        //

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
//                    Toast.makeText(getContext(), "No Request Available", Toast.LENGTH_SHORT).show();
                }
                else
                {



                    List<RequestData> list=new ArrayList();

                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        RequestData data=snaps.getValue(RequestData.class);
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



                    OpenCloseRequestAdapter ad=new OpenCloseRequestAdapter(list, getContext(),1);

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