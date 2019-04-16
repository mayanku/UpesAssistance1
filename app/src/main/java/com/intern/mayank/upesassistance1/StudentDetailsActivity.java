package com.intern.mayank.upesassistance1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Button projectcollab,events,bookrecommendation,chats;
DatabaseReference Rootref;
FirebaseAuth auth;
private String currentUserID,currentUserNamee,currentUseremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth=FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();
        InitializaMethos();
        Rootref= FirebaseDatabase.getInstance().getReference().child("Users");
        getUserInfo();

        bookrecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent collabi=new Intent(StudentDetailsActivity.this,BooksRecommendationActivity.class);
                startActivity(collabi);
            }
        });

        projectcollab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent collab=new Intent(StudentDetailsActivity.this,ChooseProjectActivity.class);
                startActivity(collab);
            }
        });
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cha=new Intent(StudentDetailsActivity.this,ChatActivity.class);
                startActivity(cha);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }
    private void getUserInfo() {
        Rootref.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentUserNamee = dataSnapshot.child("username").getValue().toString();
                    currentUseremail = dataSnapshot.child("email").getValue().toString();

                    Toast.makeText(getApplicationContext(),currentUserNamee,Toast.LENGTH_LONG).show();
                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    navigationView.setNavigationItemSelectedListener(StudentDetailsActivity.this);
                    View hView =  navigationView.getHeaderView(0);
                    TextView nameUser = (TextView)hView.findViewById(R.id.nameuser);
                    TextView emailUser=(TextView)hView.findViewById(R.id.emailuser);
                    nameUser.setText(currentUserNamee);
                    emailUser.setText(currentUseremail);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"err",Toast.LENGTH_LONG).show();

            }
        });
    }


    private void InitializaMethos() {
        projectcollab=(Button)findViewById(R.id.projectcollab);
        events=(Button)findViewById(R.id.events);
        bookrecommendation=(Button)findViewById(R.id.booksrecommendation);
        chats=(Button)findViewById(R.id.chat);
        auth=FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), StudentLoginActivity.class));
            finish();
         /*   Intent se=new Intent(getApplicationContext(),SkillsActivity.class);
            startActivity(se);
            return true;*/
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent ss=new Intent(getApplicationContext(),SkillsActivity.class);
            startActivity(ss);
        } else if (id == R.id.nav_gallery) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), StudentLoginActivity.class));
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent me=new Intent(getApplicationContext(),MentorListActivity.class);
            startActivity(me);

        } else if (id == R.id.nav_manage) {
            Intent mes=new Intent(getApplicationContext(),JoinLearningProject.class);
            startActivity(mes);

        }
        else if (id == R.id.nav_recommendation) {
            Intent mes=new Intent(getApplicationContext(),ProjectRecommendationActivity.class);
            startActivity(mes);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
