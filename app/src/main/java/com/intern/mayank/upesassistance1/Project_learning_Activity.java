package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Project_learning_Activity extends AppCompatActivity {
Button addproject,joinproject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        joinproject=(Button)findViewById(R.id.joinproject);
        addproject=(Button)findViewById(R.id.newproject);
        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToAddLearningProject();
            }
        });
        joinproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToJoinLearningProject();
            }
        });
    }

    private void sendUserToAddLearningProject() {
        Intent joinproject=new Intent(Project_learning_Activity.this,AddLearningProject.class);
        startActivity(joinproject);
    }
    private void sendUserToJoinLearningProject() {
        Intent addproject=new Intent(Project_learning_Activity.this,JoinLearningProject.class);
        startActivity(addproject);
    }
    }

