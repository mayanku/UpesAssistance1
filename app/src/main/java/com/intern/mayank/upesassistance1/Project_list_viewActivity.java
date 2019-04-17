package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Project_list_viewActivity extends AppCompatActivity {
Button academicproject,learningproject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_project);
        academicproject=(Button)findViewById(R.id.academicproject);
        learningproject=(Button)findViewById(R.id.learningproject);
        academicproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aca=new Intent(getApplicationContext(),Join_project_activity.class);
                startActivity(aca);
            }
        });
       learningproject.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent aa=new Intent(getApplicationContext(),JoinLearningProject.class);
               startActivity(aa);
           }
       });
    }
}
