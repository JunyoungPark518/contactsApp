package com.hanbit.contactsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.domain.MemberBean;

import static com.hanbit.contactsapp.R.id.btUpdate;

public class MemberdetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdetail);
        Intent it = this.getIntent();
        final String id = it.getExtras().getString("id");
        final MemberBean member = new MemberBean();

        findViewById(btUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberdetailActivity.this, "id:"+id, Toast.LENGTH_LONG).show();
                startActivity(new Intent(MemberdetailActivity.this, MemberupdateActivity.class));
            }
        });
    }
}
