package com.hanbit.contactsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.dao.DatabaseHelper;
import com.hanbit.contactsapp.domain.MemberBean;
import com.hanbit.contactsapp.service.UpdateService;

import java.util.Map;

import static com.hanbit.contactsapp.R.id.btList;

public class MemberupdateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberupdate);
        DatabaseHelper dao = DatabaseHelper.getInstance();
        final MemberBean member = new MemberBean();
        UpdateService service = new UpdateService() {
            @Override
            public void update(Map<?,?> map) {
                if(map.get("").equals(member.getSeq())) {
                    member.setName("");
                    member.setAddr("");
                    member.setPhone("");
                }
            }
        };
        findViewById(btList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberupdateActivity.this, MemberlistActivity.class));
            }
        });
    }
}
