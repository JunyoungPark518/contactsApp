package com.hanbit.contactsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.domain.MemberBean;

import static com.hanbit.contactsapp.R.id.btList;

public class MemberupdateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberupdate);
        final MemberBean member = new MemberBean();
        findViewById(btList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberupdateActivity.this, MemberlistActivity.class));
            }
        });
    }
}
