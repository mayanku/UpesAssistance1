package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Add_project_Activity extends AppCompatActivity {
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
        RootRef=FirebaseDatabase.getInstance().getReference();
        Rootref= FirebaseDatabase.getInstance().getReference().child("Users");
        getUserInfo();
        InitializeMethods();
        buttonsubmit.setText("choose your mentor");
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


        if (TextUtils.isEmpty(projecttitles) || TextUtils.isEmpty(projectabstract) || TextUtils.isEmpty(sk_2) || TextUtils.isEmpty(sk_1) || TextUtils.isEmpty(sk_3) || TextUtils.isEmpty(sk_4) ) {
            Toast.makeText(getApplicationContext(), "Required Fields cannot bee left blank", Toast.LENGTH_SHORT).show();
        }else{
            createNewGroup(projecttitles);
        post(projecttitles,projectabstract,sk_1,sk_2,sk_3,sk_4,mentor,personrequired,n);}




    }

    private void post(final String projecttitles,final String projectabstract, final String sk_1,final String sk_2,final String sk_3,final String sk_4,final String mentor,final String personrequired,final String n){
        reference = FirebaseDatabase.getInstance().getReference("Projects").child("Academic").child(projecttitles);
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
                    Intent projectss=new Intent(getApplicationContext(),ChooseMentorActivity.class);
                    projectss.putExtra("skill1name",sk_1);
                    projectss.putExtra("title",projecttitles);
                    projectss.putExtra("abstract",projectabstract);
                    projectss.putExtra("creator",currentUserName);


                    Toast.makeText(getApplicationContext(),"Your project is inserted",Toast.LENGTH_SHORT).show();
                    startActivity(projectss);

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
        HashMap<String,String> asa=new HashMap<>();
        asa.put("Date","");
        asa.put("Time","");
        asa.put("name","admin");
        asa.put("message","Welcome To this group. You can discuss about your project in this group. Mentors will be there for guiding you. ");
        RootRef.child("Groups").child(projecttitles).push().setValue(asa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Add_project_Activity.this,groupName + "group is created successful",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
