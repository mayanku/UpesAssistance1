package com.intern.mayank.upesassistance1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MentorListActivity extends AppCompatActivity {
    DatabaseReference proa;
    ListView mentorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list);

        mentorView=(ListView)findViewById(R.id.list_mentor1);
        proa= FirebaseDatabase.getInstance().getReference().child("Faculty");


        mentorView.setAdapter(new FirebaseListAdapter<mentor>(this,mentor.class,R.layout.mentor_info,proa) {
            @Override
            protected void populateView(View v, mentor model, int position) {
                ((TextView) v.findViewById(R.id.menname)).setText(model.getNames());
                ((TextView) v.findViewById(R.id.departmentname)).setText(model.getDepartment());
                ((TextView) v.findViewById(R.id.emaill)).setText(model.getEmail());
                ((TextView) v.findViewById(R.id.menskill1)).setText(model.getSkill1());
                ((TextView) v.findViewById(R.id.menskill2)).setText(model.getSkill2());
                ((TextView) v.findViewById(R.id.menskill3)).setText(model.getSkill3());
                ((TextView) v.findViewById(R.id.menskill4)).setText(model.getSkill4());

            }
        });
    }
}
