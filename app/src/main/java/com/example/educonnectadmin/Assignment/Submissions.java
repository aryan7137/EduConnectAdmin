package com.example.educonnectadmin.Assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.educonnectadmin.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Submissions extends AppCompatActivity {
    private RecyclerView ebookrecycler;
    private DatabaseReference reference;
    private List<SubmitData> list;
    private SubmitAdapter adapter;
    EditText search;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;
    String selectedClass,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submissions);
        selectedClass = getIntent().getStringExtra("class");
        name = getIntent().getStringExtra("name");

        ebookrecycler = findViewById(R.id.ebookRecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("Submission").child(selectedClass).child(name);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLayout = findViewById(R.id.shimmer_layout);
        search = findViewById(R.id.searchtext);
        getData();
    }
    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SubmitData data = snapshot.getValue(SubmitData.class);
                    list.add(data);
                }
                adapter = new SubmitAdapter(getApplicationContext(), list);
                ebookrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                ebookrecycler.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLayout.setVisibility(View.GONE);
                search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {
        ArrayList<SubmitData> filterlist = new ArrayList<>();
        try {
            for (SubmitData item : list) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filterlist.add(item);
                }
            }
            adapter.filteredList(filterlist);
        } catch (Exception e) {

        }

    }


}