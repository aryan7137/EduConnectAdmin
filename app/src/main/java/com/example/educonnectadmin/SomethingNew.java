package com.example.educonnectadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SomethingNew extends AppCompatActivity {
    private TextInputEditText title, url;
    private Button uploadbtn;
    private DatabaseReference reference;
    private String datatitle, dataurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_something_new);
        title = findViewById(R.id.Title);
        url = findViewById(R.id.Url);
        uploadbtn = findViewById(R.id.Exploresubmit);

        reference = FirebaseDatabase.getInstance().getReference().child("Something New");

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datatitle = title.getText().toString();
                dataurl = url.getText().toString();
               if (datatitle.isEmpty()) {
                    title.setError("Empty");
                    title.requestFocus();
                } else if (dataurl.isEmpty()) {
                    url.setError("Empty");
                    url.requestFocus();
                } else {
                    uploadata();

                }

            }
        });
    }

    private void uploadata() {
        String unique_key = reference.child("Something New").push().getKey();

        somethingNewData data = new somethingNewData();


        data.setTitle(title.getText().toString());
        data.setUrl(url.getText().toString());
        data.setKey(unique_key);
        reference.child("Something New").child(unique_key).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SomethingNew.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SomethingNew.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}