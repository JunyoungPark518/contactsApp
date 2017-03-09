package com.hanbit.contactsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.domain.MemberBean;
import com.hanbit.contactsapp.service.ListService;

import java.util.ArrayList;

import static com.hanbit.contactsapp.R.id.btAdd;
import static com.hanbit.contactsapp.R.id.btDetail;

public class MemberlistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlist);
        final MemberBean member = new MemberBean();
        final ArrayList<MemberBean> list = new ArrayList<>();
        ListService service = new ListService() {
            @Override
            public ArrayList<MemberBean> list() {
                member.setName("hong");
                list.add(member);
                return list;
            }
        };
        service.list();
        findViewById(btDetail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberlistActivity.this, "GO DETAIL", Toast.LENGTH_LONG).show();
                Intent it = new Intent(MemberlistActivity.this, MemberdetailActivity.class);
                it.putExtra("id","hong");
                startActivity(it);
            }
        });
        findViewById(btAdd).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberlistActivity.this, "Add", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MemberlistActivity.this, MemberaddActivity.class));
            }
        });
    }
}
