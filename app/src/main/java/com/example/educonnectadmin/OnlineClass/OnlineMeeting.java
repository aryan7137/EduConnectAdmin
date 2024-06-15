package com.example.educonnectadmin.OnlineClass;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.educonnectadmin.Notification.NotificationData;
import com.example.educonnectadmin.Notification.PushNotification;
import com.example.educonnectadmin.R;
import com.example.educonnectadmin.api.ApiUtilities;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineMeeting extends AppCompatActivity {
    private EditText classTitle, meetingCode;
    Button generate, join;
    private DatabaseReference reference, dbRef;
    private StorageReference storageReference;
    String selectedClass;
    private ProgressDialog pd;
    AutoCompleteTextView classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_meeting);
        getSupportActionBar().setTitle("Join Class");
        pd = new ProgressDialog(this);
        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        classList = findViewById(R.id.customerTextView);


        String[] type = new String[]{"For All", "6th", "7th", "8th", "9th", "10th", "11th", "12th"};
        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(OnlineMeeting.this, R.layout.dropdown_spinner_custom, type);

        //Set adapter
        classList.setAdapter(adapter);


        classTitle = findViewById(R.id.classTitle);
        meetingCode = findViewById(R.id.meetingCode);
        generate = findViewById(R.id.generate);
        join = findViewById(R.id.btnJoin);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = RandomString.getAlphaNumericString(8);
                meetingCode.setText(code);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();

            }
        });


    }

    private void checkValidation() {
        selectedClass = classList.getText().toString();
        if (classTitle.getText().toString().isEmpty()) {
            classTitle.setError("Empty");
            classTitle.requestFocus();
        } else if (selectedClass.isEmpty()) {
            classList.setError("Empty");
            classList.requestFocus();
        } else {
            uploadData();
        }
    }

    private void uploadData() {
        dbRef = reference.child("Classes").child(selectedClass);
        final String uniqueKey = dbRef.push().getKey();

        String title = classTitle.getText().toString();
        String code = meetingCode.getText().toString();

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calForTime.getTime());

        HashMap hashMap = new HashMap();
        hashMap.put("title", title);
        hashMap.put("date", date);
        hashMap.put("time", time);
        hashMap.put("key", uniqueKey);
        hashMap.put("code", code);


        dbRef.child(uniqueKey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                PushNotification notification = new PushNotification(new NotificationData("Online Class: " + title, title), "/topics/" + selectedClass);
                sendNotification(notification);
                createClass();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(OnlineMeeting.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(OnlineMeeting.this, "Notification Sent", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {
                Toast.makeText(OnlineMeeting.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createClass() {
        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setRoom(meetingCode.getText().toString())
                    .setAudioMuted(false)
                    .setVideoMuted(false)
                    .setAudioOnly(false)
                    .setConfigOverride("requireDisplayName", true)
                    .build();

            JitsiMeetActivity.launch(OnlineMeeting.this, options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}