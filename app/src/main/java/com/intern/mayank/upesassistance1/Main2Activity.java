package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    Button student, faculty;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        student=(Button)findViewById(R.id.student);
        faculty=(Button)findViewById(R.id.faculty);
        auth=FirebaseAuth.getInstance();

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studentactivity = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(studentactivity);
            }
        });
     /*   faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facultyactivity = new Intent(getApplicationContext(), FacultyLoginActivity.class);
            }
        });
*/


    }

   @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = auth.getCurrentUser();
        if (currentuser != null) {
            Intent cust = new Intent(getApplicationContext(), StudentDetailsActivity.class);
            startActivity(cust);
        }}


    }

