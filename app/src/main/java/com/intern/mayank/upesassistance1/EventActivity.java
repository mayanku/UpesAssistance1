package com.intern.mayank.upesassistance1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    ListView eventView;
    FirebaseDatabase dat;
    DatabaseReference pross;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    final Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        eventView=(ListView)findViewById(R.id.eventview);
        pross=FirebaseDatabase.getInstance().getReference().child("Events");

        eventView.setAdapter(new FirebaseListAdapter<eventsss>(this,eventsss.class,R.layout.event_info,pross) {

            @Override
            protected void populateView(View v, eventsss model, int position) {
                ((TextView) v.findViewById(R.id.eventname)).setText(model.getEventname());
                ((TextView) v.findViewById(R.id.contactinfo)).setText(model.getContactemail());
                ((TextView) v.findViewById(R.id.contactperson)).setText(model.getContactperson());
                ((TextView) v.findViewById(R.id.chaptername)).setText(model.getChaptername());
                String message=model.getUrl();
                Picasso.get()
                        .load(message)
                        .into((ImageView)v.findViewById(R.id.img));

            }
        });
    }



 /*   @Override
    protected void onStart() {
        super.onStart();
        childrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue().toString();
                url.setText(message);
                Picasso.get()
                        .load(message)
                        .into(img);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

