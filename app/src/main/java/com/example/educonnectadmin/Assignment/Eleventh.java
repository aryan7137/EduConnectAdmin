package com.example.educonnectadmin.Assignment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educonnectadmin.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Eleventh extends Fragment {
    private RecyclerView ebookrecycler;
    private DatabaseReference reference;
    private List<AssignmentData> list;
    private AssignmentAdapter adapter;
    EditText search;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eleventh, container, false);

        ebookrecycler = view.findViewById(R.id.ebookRecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("Assignment");

        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        shimmerLayout = view.findViewById(R.id.shimmer_layout);
        search = view.findViewById(R.id.searchtext);

        getData();
        return view;
    }

    private void getData() {
        reference.child("11th").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AssignmentData data = snapshot.getValue(AssignmentData.class);
                    list.add(data);
                }
                adapter = new AssignmentAdapter(getActivity(), list, "11th");
                ebookrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                ebookrecycler.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLayout.setVisibility(View.GONE);
                search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
        ArrayList<AssignmentData> filterlist = new ArrayList<>();
        try {
            for (AssignmentData item : list) {
                if (item.getPdfTitle().toLowerCase().contains(text.toLowerCase())) {
                    filterlist.add(item);
                }
            }
            adapter.filteredList(filterlist);
        } catch (Exception e) {

        }

    }


}