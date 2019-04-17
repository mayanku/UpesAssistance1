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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FacultyRegistrationActivity extends AppCompatActivity {
    EditText register_namefaculty,register_emailfaculty,register_passwordfaculty;
    Button register_button;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingbar;
    DatabaseReference RootRef;
    private String currentUserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_registration);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        RootRef= FirebaseDatabase.getInstance().getReference();
        InitializeMethods();
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestNewAccount();
            }
        });
    }

    private void requestNewAccount() {
        String username=register_namefaculty.getText().toString();
        String password=register_passwordfaculty.getText().toString();
        String email=register_emailfaculty.getText().toString();



        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be atleast 8 chars long", Toast.LENGTH_SHORT).show();
        } else {
            register(username, email, password);
        }

    }

    private void register(final String username,final String email, final String password ){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loadingbar.setTitle("Creating New Account");
                    loadingbar.setMessage("Please wait");
                    loadingbar.setCanceledOnTouchOutside(true);
                    loadingbar.show();
                    currentUserID=mAuth.getCurrentUser().getUid();
                    RootRef= FirebaseDatabase.getInstance().getReference();
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("username",username);
                    hashMap.put("email",email);
                    hashMap.put("uid",currentUserID);
                    RootRef.child("Users").child(currentUserID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Registration Successful, Welcome", Toast.LENGTH_SHORT).show();
                            Intent intents = new Intent(getApplicationContext(), FacultyLoginActivity.class);
                            intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intents);
                            finish();
                        }});
                }else{
                    Toast.makeText(getApplicationContext(), "Email ID already registered", Toast.LENGTH_SHORT).show();
                } }});
    }


    private void InitializeMethods() {

        register_namefaculty=(EditText)findViewById(R.id.register_namefaculty);
        register_emailfaculty=(EditText)findViewById(R.id.register_emailfaculty);
        register_passwordfaculty=(EditText)findViewById(R.id.register_passwordfaculty);
        register_button=(Button)findViewById(R.id.facultyregistration);
        loadingbar=new ProgressDialog(FacultyRegistrationActivity.this);
    }
}
