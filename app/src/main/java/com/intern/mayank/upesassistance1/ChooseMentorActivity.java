package com.intern.mayank.upesassistance1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseMentorActivity extends AppCompatActivity {
    Toolbar mtoolbar;
    ListView mentorView;
    FirebaseDatabase dat;
    DatabaseReference proa,proo;
    final Context context=this;
    mentor mm;
    String currentskill1,currentskill2,currentskill3,currentskill4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mentor);
        mm=new mentor();
        mentorView=(ListView)findViewById(R.id.list_mentor);
        mtoolbar=(Toolbar)findViewById(R.id.group_chat_bar_layout_button);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Choose Your Mentor");


        currentskill1 = getIntent().getExtras().get("skill1name").toString();
        currentskill2 = getIntent().getExtras().get("title").toString();
        currentskill3 = getIntent().getExtras().get("abstract").toString();
        currentskill4 = getIntent().getExtras().get("creator").toString();
        proa=FirebaseDatabase.getInstance().getReference().child("Faculty");
        //Query query= FirebaseDatabase.getInstance().getReference("Faculty").orderByChild("skill1").equalTo(currentskill1);

        mentorView.setAdapter(new FirebaseListAdapter<mentor>(this,mentor.class,R.layout.mentor_info,proa) {
            @Override
            protected void populateView(View v, mentor model, int position) {
                    ((TextView) v.findViewById(R.id.menname)).setText(model.getNames());
                    ((TextView) v.findViewById(R.id.departmentname)).setText(model.getDepartment());
                    ((TextView) v.findViewById(R.id.emaill)).setText(model.getEmail());
                    ((TextView) v.findViewById(R.id.menskill1)).setText(model.getSkill1());
                    ((TextView) v.findViewById(R.id.menskill2)).setText(model.getSkill2());
                    ((TextView) v.findViewById(R.id.menskill3)).setText(model.getSkill3());
                    ((TextView) v.findViewById(R.id.menskill4)).setText(model.getSkill4());

            }
        });

       mentorView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String selected = ((TextView) view.findViewById(R.id.menname)).getText().toString();
               proo=FirebaseDatabase.getInstance().getReference().child("Faculty").child(selected);
              proo.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      String mai= dataSnapshot.child("email").getValue().toString();
                      String[] TO=new String[]{mai};

                      String Text=("Please checkout my Project and I request you to mentor us for this project"+ "\n"+ "Title:- "+currentskill2+"\n"+ "Abstract:- "+currentskill3+"\n"+"Creator:- "+currentskill4);
                      Intent emailIntent = new Intent(Intent.ACTION_SEND);

                      emailIntent.setData(Uri.parse("mailto:"));
                      emailIntent.setType("text/plain");
                      emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
                      //emailIntent.putExtra(Intent.EXTRA_CC, CC);
                      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Project Mentor Request");
                      emailIntent.putExtra(Intent.EXTRA_TEXT, Text);

                      try {
                          startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                          finish();
                          // Log.i("Finished sending email...", "");
                      } catch (android.content.ActivityNotFoundException ex) {
                          Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });



           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.button_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Skip) {


            Intent se=new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(se);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



}

