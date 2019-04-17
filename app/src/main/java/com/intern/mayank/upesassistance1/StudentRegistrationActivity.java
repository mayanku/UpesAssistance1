package com.intern.mayank.upesassistance1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class StudentRegistrationActivity extends AppCompatActivity {
EditText register_name,register_email,register_password,SAP,branch;
Spinner year_spinner;
Button register_button;
TextView alreadyhavelink;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingbar;
    DatabaseReference RootRef;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        RootRef= FirebaseDatabase.getInstance().getReference();

        InitializeMethods();
        String[] year = {"1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, year);
        year_spinner.setAdapter(arrayAdapter);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestNewAccount();
            }
        });


    }

    private void requestNewAccount() {
         String username=register_name.getText().toString();
         String password=register_password.getText().toString();
         String email=register_email.getText().toString();
         String sap=SAP.getText().toString();

         String brancha= branch.getText().toString();
        String years= year_spinner.getSelectedItem().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(brancha) || TextUtils.isEmpty(sap)) {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be atleast 8 chars long", Toast.LENGTH_SHORT).show();
        } else {
            register(username, email, password, sap, brancha,years);
        }

    }

    private void register(final String username,final String email, final String password, final String sap,final String brancha, final String years ){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loadingbar.setTitle("Creating New Account");
                    loadingbar.setMessage("Please wait");
                    loadingbar.setCanceledOnTouchOutside(true);
                    loadingbar.show();
                   currentUserID=mAuth.getCurrentUser().getUid();
                   HashMap<String,String> hashMap=new HashMap<>();
                   hashMap.put("username",username);
                   hashMap.put("email",email);
                   hashMap.put("sap",sap);
                   hashMap.put("branch",brancha);
                   hashMap.put("Year of Study",years);
                   hashMap.put("uid",currentUserID);
                   RootRef.child("Users").child(currentUserID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Toast.makeText(getApplicationContext(), "Registration Successful, Welcome", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getApplicationContext(), StudentDetailsActivity.class);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);
                           finish();
                       }});
                }else{
                    Toast.makeText(getApplicationContext(), "Email ID already registered", Toast.LENGTH_SHORT).show();
                } }});
    }

    private void InitializeMethods() {
        register_name=(EditText)findViewById(R.id.register_name);
        register_email=(EditText)findViewById(R.id.register_email);
        register_password=(EditText)findViewById(R.id.register_password);
        SAP=(EditText)findViewById(R.id.SAP);
        branch=(EditText)findViewById(R.id.branch);
        register_button=(Button) findViewById(R.id.register_button);
        alreadyhavelink=(TextView) findViewById(R.id.already_have_account_link);
        year_spinner=(Spinner)findViewById(R.id.year_spinner);
        loadingbar=new ProgressDialog(StudentRegistrationActivity.this);
    }

}
