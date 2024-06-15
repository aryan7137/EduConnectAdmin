package com.example.educonnectadmin.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.educonnectadmin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView PricipalDepartment,ViceprincipalDepartment ,CsDepartment,SocialDepartment,MathematicsDepartment,PhysicsDepartment,ChemistryDepartment,BiologyDepartment,ScienceDepartment,EnglishDepartment,HindiDepartment,MalyalamDepartment,MusicDepartment,ArtsDepartment,PhysicalDepartment,LibraryDepartment;
    private LinearLayout principalNoData,viceprincipalNoData,csNoData,socialNoData,mathematicsNoData,physicsNoData,chemistryNoData,biologyNoData,scienceNoData,englishNoData,hindiNoData,malyalamNoData,musicNoData,artsNoData,physicalNoData,libraryNoData;
    private List<TeacherData> list1, list2, list3, list4, list5, list6, list7, list8, list9, list10, list11, list12, list13, list14 ,list15,list16;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        PricipalDepartment = findViewById(R.id.PrincipalDepartment);
        ViceprincipalDepartment = findViewById(R.id.ViceprincipalDepartment);
        CsDepartment = findViewById(R.id.CsDepartment);
        SocialDepartment  = findViewById(R.id.SocialDepartment);
        MathematicsDepartment = findViewById(R.id.MathematicsDepartment);
        PhysicsDepartment = findViewById(R.id.PhysicsDepartment);
        ChemistryDepartment = findViewById(R.id.ChemistryDepartment);
        BiologyDepartment = findViewById(R.id.BiologyDepartment);
        ScienceDepartment = findViewById(R.id.ScienceDepartment);
        EnglishDepartment = findViewById(R.id.EnglishDepartment);
        HindiDepartment = findViewById(R.id.HindiDepartment);
        MalyalamDepartment = findViewById(R.id.MalyalamDepartment);
        MusicDepartment = findViewById(R.id.MusicDepartment);
        ArtsDepartment = findViewById(R.id.ArtsDepartment);
        PhysicalDepartment = findViewById(R.id.PhysicalDepartment);
        LibraryDepartment = findViewById(R.id.LibraryDepartment);

        principalNoData = findViewById(R.id.principalNoData);
        viceprincipalNoData = findViewById(R.id.viceprincipalNoData);
        csNoData = findViewById(R.id.csNoData);
        socialNoData = findViewById(R.id.socialNoData);
        mathematicsNoData = findViewById(R.id.mathematicsNoData);
        physicsNoData = findViewById(R.id.physicsNoData);
        chemistryNoData = findViewById(R.id.chemistryNoData);
        biologyNoData = findViewById(R.id.biologyNoData);
        scienceNoData = findViewById(R.id.scienceNoData);
        englishNoData = findViewById(R.id.englishNoData);
        hindiNoData = findViewById(R.id.hindiNoData);
        malyalamNoData = findViewById(R.id.malyalamNoData);
        musicNoData = findViewById(R.id.musicNoData);
        artsNoData = findViewById(R.id.artsNoData);
        physicalNoData = findViewById(R.id.physicalNoData);
        libraryNoData = findViewById(R.id.libraryNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

       PricipalDepartment();
        ViceprincipalDepartment();

        CsDepartment();
        SocialDepartment();
        MathematicsDepartment();
        PhysicsDepartment();
        ChemistryDepartment();
        BiologyDepartment();
        ScienceDepartment();
        EnglishDepartment();
        HindiDepartment();
        MalyalamDepartment();
        MusicDepartment();
        ArtsDepartment();
        PhysicalDepartment();
        LibraryDepartment();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateFaculty.this,AddTeachers.class));
            }
        });
    }

    private void PricipalDepartment() {
        dbRef = reference.child("Principal");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list15 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    principalNoData.setVisibility(View.VISIBLE);
                    PricipalDepartment.setVisibility(View.GONE);
                }else {
                    principalNoData.setVisibility(View.GONE);
                    PricipalDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list15.add(data);
                    }
                    PricipalDepartment.setHasFixedSize(true);
                    PricipalDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list15, UpdateFaculty.this, "Principal");
                    PricipalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ViceprincipalDepartment() {
        dbRef = reference.child("Vice-Principal");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list16 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    viceprincipalNoData.setVisibility(View.VISIBLE);
                    ViceprincipalDepartment.setVisibility(View.GONE);
                }else {
                    viceprincipalNoData.setVisibility(View.GONE);
                    ViceprincipalDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list16.add(data);
                    }
                    ViceprincipalDepartment.setHasFixedSize(true);
                    ViceprincipalDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list16, UpdateFaculty.this, "Vice-Principal");
                    ViceprincipalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CsDepartment() {
        dbRef = reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list1 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    CsDepartment.setVisibility(View.GONE);
                }else {
                    csNoData.setVisibility(View.GONE);
                    CsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    CsDepartment.setHasFixedSize(true);
                    CsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list1, UpdateFaculty.this, "Computer Science");
                    CsDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void SocialDepartment() {
        dbRef = reference.child("Social Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list2 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    socialNoData.setVisibility(View.VISIBLE);
                    SocialDepartment.setVisibility(View.GONE);
                }else {
                    socialNoData.setVisibility(View.GONE);
                    SocialDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    SocialDepartment.setHasFixedSize(true);
                    SocialDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2, UpdateFaculty.this, "Social Science");
                    SocialDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void MathematicsDepartment() {
        dbRef = reference.child("Mathematics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list3 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    mathematicsNoData.setVisibility(View.VISIBLE);
                    MathematicsDepartment.setVisibility(View.GONE);
                }else {
                    mathematicsNoData.setVisibility(View.GONE);
                    MathematicsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    MathematicsDepartment.setHasFixedSize(true);
                    MathematicsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3, UpdateFaculty.this,"Mathematics");
                    MathematicsDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void PhysicsDepartment() {
        dbRef = reference.child("Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list4 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    physicsNoData.setVisibility(View.VISIBLE);
                    PhysicsDepartment.setVisibility(View.GONE);
                }else {
                    physicsNoData.setVisibility(View.GONE);
                    PhysicsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    PhysicsDepartment.setHasFixedSize(true);
                    PhysicsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4, UpdateFaculty.this, "Physics");
                    PhysicsDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ChemistryDepartment() {
        dbRef = reference.child("Chemistry");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list5 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    chemistryNoData.setVisibility(View.VISIBLE);
                    ChemistryDepartment.setVisibility(View.GONE);
                }else {
                    chemistryNoData.setVisibility(View.GONE);
                    ChemistryDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    ChemistryDepartment.setHasFixedSize(true);
                    ChemistryDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list5, UpdateFaculty.this, "Chemistry");
                    ChemistryDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void BiologyDepartment() {
        dbRef = reference.child("Biology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list6 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    biologyNoData.setVisibility(View.VISIBLE);
                    BiologyDepartment.setVisibility(View.GONE);
                }else {
                    biologyNoData.setVisibility(View.GONE);
                    BiologyDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    BiologyDepartment.setHasFixedSize(true);
                    BiologyDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list6, UpdateFaculty.this, "Biology");
                    BiologyDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ScienceDepartment() {
        dbRef = reference.child("Junior Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list7 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    scienceNoData.setVisibility(View.VISIBLE);
                    ScienceDepartment.setVisibility(View.GONE);
                }else {
                    scienceNoData.setVisibility(View.GONE);
                    ScienceDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    ScienceDepartment.setHasFixedSize(true);
                    ScienceDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list7, UpdateFaculty.this, "Junior Science");
                    ScienceDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void EnglishDepartment() {
        dbRef = reference.child("English");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list8 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    englishNoData.setVisibility(View.VISIBLE);
                    EnglishDepartment.setVisibility(View.GONE);
                }else {
                    englishNoData.setVisibility(View.GONE);
                    EnglishDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    EnglishDepartment.setHasFixedSize(true);
                    EnglishDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list8, UpdateFaculty.this, "English");
                    EnglishDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void HindiDepartment() {
        dbRef = reference.child("Hindi");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list9 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    hindiNoData.setVisibility(View.VISIBLE);
                    HindiDepartment.setVisibility(View.GONE);
                }else {
                    hindiNoData.setVisibility(View.GONE);
                    HindiDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list9.add(data);
                    }
                    HindiDepartment.setHasFixedSize(true);
                    HindiDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list9, UpdateFaculty.this,"Hindi");
                    HindiDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void MalyalamDepartment() {
        dbRef = reference.child("Regional Language");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list10 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    malyalamNoData.setVisibility(View.VISIBLE);
                    MalyalamDepartment.setVisibility(View.GONE);
                }else {
                    malyalamNoData.setVisibility(View.GONE);
                    MalyalamDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list10.add(data);
                    }
                    MalyalamDepartment.setHasFixedSize(true);
                    MalyalamDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list10, UpdateFaculty.this, "Regional Language");
                    MalyalamDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void MusicDepartment() {
        dbRef = reference.child("Music");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list11 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    musicNoData.setVisibility(View.VISIBLE);
                    MusicDepartment.setVisibility(View.GONE);
                }else {
                    musicNoData.setVisibility(View.GONE);
                    MusicDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list11.add(data);
                    }
                    MusicDepartment.setHasFixedSize(true);
                    MusicDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list11, UpdateFaculty.this, "Music");
                    MusicDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ArtsDepartment() {
        dbRef = reference.child("Arts");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list12 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    artsNoData.setVisibility(View.VISIBLE);
                    ArtsDepartment.setVisibility(View.GONE);
                }else {
                    artsNoData.setVisibility(View.GONE);
                    ArtsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list12.add(data);
                    }
                    ArtsDepartment.setHasFixedSize(true);
                    ArtsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list12, UpdateFaculty.this, "Arts");
                    ArtsDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void PhysicalDepartment() {
        dbRef = reference.child("Physical education");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list13 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    physicalNoData.setVisibility(View.VISIBLE);
                    PhysicalDepartment.setVisibility(View.GONE);
                }else {
                    physicalNoData.setVisibility(View.GONE);
                    PhysicalDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list13.add(data);
                    }
                    PhysicalDepartment.setHasFixedSize(true);
                    PhysicalDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list13, UpdateFaculty.this, "Physical education");
                    PhysicalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void LibraryDepartment() {
        dbRef = reference.child("Librarian");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list14 = new ArrayList<>();
                if (!datasnapshot.exists()){
                    libraryNoData.setVisibility(View.VISIBLE);
                    LibraryDepartment.setVisibility(View.GONE);
                }else {
                    libraryNoData.setVisibility(View.GONE);
                    LibraryDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list14.add(data);
                    }
                    LibraryDepartment.setHasFixedSize(true);
                    LibraryDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list14, UpdateFaculty.this, "Librarian");
                    LibraryDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError ) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
