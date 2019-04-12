package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseProjectActivity extends AppCompatActivity {
Button academicProject,SelflearningProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_project);
        academicProject=(Button)findViewById(R.id.academicproject);
        SelflearningProject=(Button)findViewById(R.id.learningproject);
        academicProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToProjectActivity();

            }
        });
        SelflearningProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToLearningProjectActivity();


            }
        });
    }

    private void sendToLearningProjectActivity() {
        Intent pe=new Intent(getApplicationContext(),Project_learning_Activity.class);
        startActivity(pe);
    }
    private void sendToProjectActivity() {
        Intent pee=new Intent(getApplicationContext(),ProjectActivity.class);
        startActivity(pee);
    }
}
