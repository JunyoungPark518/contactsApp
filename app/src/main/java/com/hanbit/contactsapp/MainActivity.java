package com.hanbit.contactsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contactsapp.presentation.MemberlistActivity;

import static com.hanbit.contactsapp.R.id.btGoList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(btGoList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"GO Click!",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, MemberlistActivity.class);
                i.putExtra("id","hong");
                startActivity(i);
            }
        });
    }

}
