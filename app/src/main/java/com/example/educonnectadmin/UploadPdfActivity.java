package com.example.educonnectadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class UploadPdfActivity extends AppCompatActivity {

    private CardView addPdf;

    private EditText PdfTitle;
    private Button uploadPdfBtn;
    private TextView pdfTextView;
    private String pdfName, title;

    private final int REQ = 1;
    private Uri pdfData;
    private DatabaseReference databasereference;
    private StorageReference storageReference;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        databasereference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        pd = new ProgressDialog(this);

       addPdf = findViewById(R.id.addPdf);

        PdfTitle = findViewById(R.id.pdfTitle);
        uploadPdfBtn = findViewById(R.id.uploadPdfBtn);
        pdfTextView = findViewById(R.id.pdfTextView);

        addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        uploadPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               title = PdfTitle.getText().toString();
               if (title.isEmpty()){
                   PdfTitle.setError("Empty");
                   PdfTitle.requestFocus();
               }else if (pdfData == null){
                   Toast.makeText(UploadPdfActivity.this, "Please Select Pdf", Toast.LENGTH_SHORT).show();
               }else {
                   uploadPdf();
               }
            }
        });
    }

    private void uploadPdf() {
        pd.setTitle("Please Wait....");
        pd.setMessage("Uploading Pdf");
        pd.show();
        StorageReference reference = storageReference.child("pdf/"+ pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri = uriTask.getResult();
                        uploadData(String.valueOf(uri));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String downloadurl) {
       String uniqueKey = databasereference.child("pdf").push().getKey();

        HashMap data = new HashMap();
        data.put("PdfTitle",title);
        data.put("PdfUrl",downloadurl);


        databasereference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Pdf Uploaded Successfully", Toast.LENGTH_SHORT).show();
                PdfTitle.setText("");
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Failed to Upload Pdf", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf File") ,REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK){
            pdfData = data.getData();

            if (pdfData.toString().startsWith("content://")){
                Cursor cursor = null;
                try {
                    cursor = UploadPdfActivity.this.getContentResolver().query(pdfData, null, null, null,null);
                    if (cursor != null && cursor.moveToFirst()){
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (pdfData.toString().startsWith("file://")){
                pdfName= new File(pdfData.toString()).getName();
            }
            pdfTextView.setText(pdfName);
        }
    }
}