package com.intern.mayank.upesassistance1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacultyLoginActivity extends AppCompatActivity {
    Button loginfaculty,registerfaculty;
    private FirebaseAuth auth;
    EditText usernamefaculty,passwordfaculty;
    private FirebaseUser currentUser;
    private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        InitializeMethods();
        loginfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowUserToLogin();
            }
        });
        registerfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToFacultyRegistrationActivity();
            }
        });


    }

    private void sendUserToFacultyRegistrationActivity() {
        Intent registration=new Intent(FacultyLoginActivity.this,FacultyRegistrationActivity.class);
        startActivity(registration);

    }

    private void InitializeMethods() {
        loginfaculty=(Button)findViewById(R.id.loginfaculty);
        registerfaculty=(Button)findViewById(R.id.registerfaculty);
        usernamefaculty=(EditText)findViewById(R.id.usernamefaculty);
        passwordfaculty=(EditText)findViewById(R.id.passwordfaculty);
        loadingbar=new ProgressDialog(FacultyLoginActivity.this);
    }
    private void AllowUserToLogin(){
        String email = usernamefaculty.getText().toString();
        String password = passwordfaculty.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Pleaseenter email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Pleaseenter ail", Toast.LENGTH_SHORT).show();
        } else {
            loadingbar.setTitle("Signin");
            loadingbar.setMessage("Please wait");
            loadingbar.setCanceledOnTouchOutside(true);
            loadingbar.show();

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        sendUserToFacultyActivity();
                        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                        loadingbar.dismiss();
                    }
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser=auth.getCurrentUser();
        if (currentuser!=null) {
            sendUserToFacultyActivity();
        }}

    private void sendUserToFacultyActivity(){
        Intent logl= new Intent(FacultyLoginActivity.this,FacultyActivity.class);
        logl.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logl);

    }



    }

