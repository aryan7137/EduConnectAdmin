package com.example.educonnectadmin.Assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educonnectadmin.MainActivity;
import com.example.educonnectadmin.Notification.NotificationData;
import com.example.educonnectadmin.Notification.PushNotification;
import com.example.educonnectadmin.OnlineClass.RandomString;
import com.example.educonnectadmin.R;
import com.example.educonnectadmin.UploadPdfActivity;
import com.example.educonnectadmin.api.ApiUtilities;
import com.example.educonnectadmin.notice.UploadNotice;
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
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadAssignment extends AppCompatActivity {

    private CardView addPdf;
    private EditText PdfTitle;
    private Button uploadPdfBtn;
    private TextView pdfTextView;
    private String pdfName, title,selectedClass;
    private final int REQ = 1;
    private Uri pdfData;
    private DatabaseReference databasereference;
    private StorageReference storageReference;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assignment);
        databasereference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        pd = new ProgressDialog(this);
        addPdf = findViewById(R.id.addPdf);
        AutoCompleteTextView classList = findViewById(R.id.customerTextView);




        String[] type = new String[]{"6th", "7th", "8th", "9th", "10th", "11th", "12th"};
        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(UploadAssignment.this, R.layout.dropdown_spinner_custom, type);

        //Set adapter
        classList.setAdapter(adapter);

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
                selectedClass = classList.getText().toString();
                if (title.isEmpty()){
                    PdfTitle.setError("Empty");
                    PdfTitle.requestFocus();
                }else if (selectedClass.isEmpty()) {
                    classList.setError("Empty");
                    classList.requestFocus();
                }else if (pdfData == null){
                    Toast.makeText(UploadAssignment.this, "Please Select Pdf", Toast.LENGTH_SHORT).show();
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
        StorageReference reference = storageReference.child("Assignment/"+ pdfName+"-"+System.currentTimeMillis()+".pdf");
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
                        Toast.makeText(UploadAssignment.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String downloadurl) {
        String uniqueKey = databasereference.child("pdf").push().getKey();

        HashMap data = new HashMap();
        data.put("PdfTitle",title);
        data.put("PdfUrl",downloadurl);
        data.put("selectedClass",selectedClass);
        data.put("key",uniqueKey);


        databasereference.child("Assignment").child(selectedClass).child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadAssignment.this, "Pdf Uploaded Successfully", Toast.LENGTH_SHORT).show();
                PushNotification notification = new PushNotification(new NotificationData("Assignment Added: "+title,title), "/topics/"+selectedClass);
                sendNotification(notification);
                PdfTitle.setText("");
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadAssignment.this, "Failed to Upload Pdf", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UploadAssignment.this, "Notification Sent", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {
                Toast.makeText(UploadAssignment.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    cursor = UploadAssignment.this.getContentResolver().query(pdfData, null, null, null,null);
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