package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProjectActivity extends AppCompatActivity {
Button joinproject,addproject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        joinproject=(Button)findViewById(R.id.joinproject);
        addproject=(Button)findViewById(R.id.newproject);
        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToAddProject();
            }
        });
        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToAddProject();
            }
        });
        joinproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToJoinProject();
            }
        });
    }

    private void sendUserToAddProject() {
        Intent joinproject=new Intent(ProjectActivity.this,Add_project_Activity.class);
        startActivity(joinproject);
    }
    private void sendUserToJoinProject() {
        Intent addproject=new Intent(ProjectActivity.this,Join_project_activity.class);
        startActivity(addproject);
    }
}
