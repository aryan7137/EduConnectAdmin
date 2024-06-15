package com.example.educonnectadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educonnectadmin.MainActivity;
import com.example.educonnectadmin.R;
import com.hanks.passcodeview.PasscodeView;

public class passcode extends AppCompatActivity {
    PasscodeView passcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        passcodeView = findViewById(R.id.passCodeView);
        passcodeView.setPasscodeLength(5)
                .setLocalPasscode("88764")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(passcode.this, "Passcode is Wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        Intent intent = new Intent(passcode.this, MainActivity.class);
                        startActivity(intent);

                    }
                });
    }
}