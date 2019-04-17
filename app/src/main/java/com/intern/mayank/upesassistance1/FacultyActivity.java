package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class FacultyActivity extends AppCompatActivity {
Button projectciew,ment,even,chat,buttonlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        projectciew=(Button)findViewById(R.id.projectciew);
        ment=(Button)findViewById(R.id.mento);
        even=(Button)findViewById(R.id.events);
        chat=(Button)findViewById(R.id.groupchats);
        buttonlogout=(Button)findViewById(R.id.buttonlogout);
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), FacultyLoginActivity.class));
                finish();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lll=new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(lll);
            }
        });
        even.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aa=new Intent(getApplicationContext(),EventActivity.class);
                startActivity(aa);
            }
        });
        ment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loo=new Intent(getApplicationContext(),MentorListActivity.class);
                startActivity(loo);
            }
        });
        projectciew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent llll=new Intent(getApplicationContext(),Project_list_viewActivity.class);
                startActivity(llll);
            }
        });

    }
}
