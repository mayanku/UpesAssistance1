package com.intern.mayank.upesassistance1;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BooksRecommendationActivity extends AppCompatActivity {
    private AutoCompleteTextView editText78;
ListView list_books;
Button choose_books;
DatabaseReference BookRef;
private ArrayList<String> liss=new ArrayList<>();
private ArrayAdapter adapter;
    private ProgressDialog loadingbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_recommendation);
        list_books=(ListView)findViewById(R.id.list_books);
        loadingbar=new ProgressDialog(BooksRecommendationActivity.this);

        String[] book = {"C","Data Structures","C++", "Java","Artificial Intelligence","Go","JavaScript","Machine Learning","Scala"};
        editText78 =(AutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextVie);
        final ArrayAdapter<String> adapterm = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,book);
        editText78.setAdapter(adapterm);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,liss);
        list_books.setAdapter(adapter);


        editText78.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String selection = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(BooksRecommendationActivity.this, selection, Toast.LENGTH_SHORT).show();
                BookRef= FirebaseDatabase.getInstance().getReference().child("Books").child(selection);
               BookRef.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      liss.clear();
                       loadingbar.setTitle("Loading Books");
                       loadingbar.setMessage("Please wait");
                       loadingbar.setCanceledOnTouchOutside(true);
                       loadingbar.show();

                       for(DataSnapshot d : dataSnapshot.getChildren()) {

                           String me  = d.getKey();
                           liss.add(me);
                           adapter.notifyDataSetChanged();
                           loadingbar.dismiss();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
                /*  BookRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        liss.clear();
                        String boo=dataSnapshot.getChildren().toString();
                        liss.add(boo);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

            }
        });








    }


}
