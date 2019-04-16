package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddLearningProject extends AppCompatActivity {
    EditText projecttitle,abstractproject,skill1,skill2,skill3,skill4,mentorname,github;
    RadioButton radioButton0,radioButton1,radioButton2,radioButton3;
    Button buttonsubmit;
    DatabaseReference reference,Rootref,RootRef;
    FirebaseAuth auth;
    String projectabstract,projecttitles,sk_1,sk_2,sk_3,sk_4,mentor,personrequired,n;
    private String currentUserID,currentUserName;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_);
        auth= FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();
        Rootref= FirebaseDatabase.getInstance().getReference().child("Users");
        RootRef=FirebaseDatabase.getInstance().getReference();
        getUserInfo();
        InitializeMethods();
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterProjectData();

            }
        });


    }

    private void EnterProjectData() {
        projecttitles=projecttitle.getText().toString();
        projectabstract=abstractproject.getText().toString();
        sk_1=skill1.getText().toString();
        sk_2=skill2.getText().toString();
        sk_3=skill3.getText().toString();
        sk_4=skill4.getText().toString();
        n=github.getText().toString();
        createNewGroup(projecttitles);
        mentor=mentorname.getText().toString();
        if (radioButton0.isChecked()){
            personrequired="0";
        }
        else if (radioButton1.isChecked()){
            personrequired="1";
        }
        else if (radioButton2.isChecked()){
            personrequired="2";
        }
        else if (radioButton3.isChecked()){
            personrequired="3";
        }
        else {personrequired="0";}

        post(projecttitles,projectabstract,sk_1,sk_2,sk_3,sk_4,mentor,personrequired,n);

    }

    private void post(final String projecttitles,final String projectabstract, final String sk_1,final String sk_2,final String sk_3,final String sk_4,final String mentor,final String personrequired,final String n){
        reference = FirebaseDatabase.getInstance().getReference("Projects").child("Learning").child(projecttitles);
        auth= FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();
        final HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Title", projecttitles);
        hashMap.put("Abstract",projectabstract);
        hashMap.put("skill_1", sk_1);
        hashMap.put("skill_2", sk_2);
        hashMap.put("skill_3", sk_3);
        hashMap.put("skill_4", sk_4);
        hashMap.put("mentor",mentor);
        hashMap.put("person_required",personrequired);
        hashMap.put("GitHub",n);
        hashMap.put("uid",currentUserID);
        hashMap.put("Creator",currentUserName);

        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent projectss=new Intent(getApplicationContext(),ChatActivity.class);
                    Toast.makeText(getApplicationContext(),"Your project is inserted",Toast.LENGTH_SHORT).show();
                    startActivity(projectss);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Please Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void InitializeMethods() {
        buttonsubmit=(Button)findViewById(R.id.buttonsubmit);
        projecttitle=(EditText)findViewById(R.id.projecttitle);
        abstractproject=(EditText)findViewById(R.id.abstractproject);
        skill1=(EditText)findViewById(R.id.skill1);
        skill2=(EditText)findViewById(R.id.skill2);
        skill3=(EditText)findViewById(R.id.skill3);
        skill4=(EditText)findViewById(R.id.skill4);
        mentorname=(EditText)findViewById(R.id.mentorname);
        github=(EditText)findViewById(R.id.github);
        radioButton0=(RadioButton)findViewById(R.id.radioButton0);
        radioButton1=(RadioButton)findViewById(R.id.radioButton1);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
    }
    private void getUserInfo() {
        Rootref.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentUserName = dataSnapshot.child("username").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void createNewGroup(final String groupName) {
        RootRef.child("Groups").child(projecttitles).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddLearningProject.this,groupName + "group is created successfull",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    }

