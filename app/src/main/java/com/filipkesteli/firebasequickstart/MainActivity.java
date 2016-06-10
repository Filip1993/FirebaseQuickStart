package com.filipkesteli.firebasequickstart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView tvCondition;
    private Button btnSunny;
    private Button btnFoggy;

    //Firebase reference:
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        initFirebase();
        setupListeners();
    }

    private void initWidgets() {
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        btnSunny = (Button) findViewById(R.id.btnSunny);
        btnFoggy = (Button) findViewById(R.id.btnFoggy);
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://glowing-fire-9031.firebaseio.com/");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newCondition = dataSnapshot.getValue().toString();
                tvCondition.setText(newCondition);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void setupListeners() {
        btnSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.setValue("Sunny");
            }
        });
        btnFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.setValue("Foggy");
            }
        });
    }
}
