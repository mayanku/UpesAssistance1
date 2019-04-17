package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FacultyLoginActivity extends AppCompatActivity {
EditText numpass;
Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        numpass=(EditText)findViewById(R.id.numberpass);
        log=(Button)findViewById(R.id.loginfsculty);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String li=numpass.getText().toString();
                if (li.equalsIgnoreCase("upes")){
                    Intent fac=new Intent(getApplicationContext(),FacultyActivity.class);
                    startActivity(fac);

                    Toast.makeText(getApplicationContext(),"ss",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong credentials",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
