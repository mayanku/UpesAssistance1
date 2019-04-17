package com.intern.mayank.upesassistance1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SkillsActivity extends AppCompatActivity {
    private MultiAutoCompleteTextView editText;
    private Button addSkillsButton;
    private RecyclerView listOfSelectedSkills;
    private DatabaseReference mdatabase;
    private String currentUserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();;
        String[] skills_list = getResources().getStringArray(R.array.skills);
        editText = findViewById(R.id.multiAutoCompleteTextView);
        ArrayAdapter<String> adapterm = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, skills_list);
        editText.setAdapter(adapterm);
        editText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
    public void showAndAddInput(View v){

        mdatabase = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID).child("Skills");

        String[] arr = editText.getText().toString().split(", ");
        HashMap<String, String> stringHashMap = new HashMap<>();
        for(int i=0; i<arr.length;i++){
            stringHashMap.put(Integer.toString(i), arr[i]);
        }
        mdatabase.setValue(stringHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Your Skills are inserted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Please Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
