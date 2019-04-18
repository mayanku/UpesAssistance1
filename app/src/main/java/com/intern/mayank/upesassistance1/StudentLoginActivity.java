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

public class StudentLoginActivity extends AppCompatActivity {
    Button login1,register1;
    private FirebaseAuth auth;
    EditText username1,password1;
    private FirebaseUser currentUser;
    private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        InitializeMethods();
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowUserToLogin();
            }
        });
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToStudentRegistrationActivity();
            }
        });


    }

    private void sendUserToStudentRegistrationActivity() {
        Intent registration=new Intent(StudentLoginActivity.this,StudentRegistrationActivity.class);
        startActivity(registration);

    }

    private void InitializeMethods() {
        login1=(Button)findViewById(R.id.login1);
        register1=(Button)findViewById(R.id.register1);
        username1=(EditText)findViewById(R.id.username1);
        password1=(EditText)findViewById(R.id.password1);
        loadingbar=new ProgressDialog(StudentLoginActivity.this);
    }
    private void AllowUserToLogin(){
        String email = username1.getText().toString();
        String password = password1.getText().toString();

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
                        sendUserToStudentDetailsActivity();
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
        if (currentuser!=null){
          sendUserToStudentDetailsActivity();
        }
    }

    private void sendUserToStudentDetailsActivity(){
        Intent logl= new Intent(StudentLoginActivity.this,StudentDetailsActivity.class);
        logl.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logl);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ne=new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(ne);
    }
}
