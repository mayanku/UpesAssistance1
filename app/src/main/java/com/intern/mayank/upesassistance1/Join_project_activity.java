package com.intern.mayank.upesassistance1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Join_project_activity extends AppCompatActivity {
    ListView projectView;
    FirebaseDatabase dat;
    DatabaseReference pro;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    projec projec,pp;
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_project_activity);
        projec=new projec();
        pp=new projec();
        projectView=(ListView)findViewById(R.id.listview);
        dat=FirebaseDatabase.getInstance();
        pro=FirebaseDatabase.getInstance().getReference().child("Projects").child("Academic");
        //list=new ArrayList<>();
        //adapter=new ArrayAdapter<String>(this,R.layout.project_info,R.id.projectinfo,list);
        projectView.setAdapter(new FirebaseListAdapter<projec>(this, projec.class,
                R.layout.project_info,pro) {
            @Override
            protected void populateView(View v, projec model, int position) {
                ((TextView) v.findViewById(R.id.projectinfo)).setText(model.getTitle());
                ((TextView) v.findViewById(R.id.sk_1)).setText(model.getSkill_1());
                ((TextView) v.findViewById(R.id.sk_2)).setText(model.getSkill_2());
                ((TextView) v.findViewById(R.id.sk_3)).setText(model.getSkill_3());
                ((TextView) v.findViewById(R.id.sk_4)).setText(model.getSkill_4());
            }
        });
        projectView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.info_project);
                dialog.getWindow().setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
                String selected = ((TextView) view.findViewById(R.id.projectinfo)).getText().toString();
                pro=FirebaseDatabase.getInstance().getReference().child("Projects").child("Academic").child(selected);
                pro.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String abs= dataSnapshot.child("Abstract").getValue().toString();
                        String tii=dataSnapshot.child("Title").getValue().toString();
                        String abss= dataSnapshot.child("mentor").getValue().toString();
                        String tiis=dataSnapshot.child("skill_1").getValue().toString();
                        String absss= dataSnapshot.child("skill_2").getValue().toString();
                        String tiiss=dataSnapshot.child("skill_3").getValue().toString();
                        String abssssss= dataSnapshot.child("skill_4").getValue().toString();
                        String absssss= dataSnapshot.child("person_required").getValue().toString();
                        String tiissssss=dataSnapshot.child("Creator").getValue().toString();

                        TextView text = (TextView) dialog.findViewById(R.id.tit);
                        TextView tt=(TextView) dialog.findViewById(R.id.abstracta);
                        TextView sk_1 = (TextView) dialog.findViewById(R.id.skk_1);
                        TextView sk_2=(TextView) dialog.findViewById(R.id.skk_2);
                        TextView sk_3 = (TextView) dialog.findViewById(R.id.skk_3);
                        TextView sk_4=(TextView) dialog.findViewById(R.id.skk_4);
                        TextView men = (TextView) dialog.findViewById(R.id.mentor);
                        TextView require=(TextView) dialog.findViewById(R.id.personrequired);
                        TextView owne=(TextView) dialog.findViewById(R.id.creator);
                        Button but=(Button) dialog.findViewById(R.id.button2);
                        tt.setText(abs);
                        text.setText(tii);
                        sk_1.setText(tiis);
                        sk_2.setText(absss);
                        sk_3.setText(tiiss);
                        sk_4.setText(abssssss);
                        men.setText(abss);
                        require.setText(absssss);
                        owne.setText(tiissssss);
                        but.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent lk=new Intent(getApplicationContext(),ChatActivity.class);
                                startActivity(lk);

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                dialog.show();

            }

        });



    }
}
