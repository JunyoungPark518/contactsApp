package com.hanbit.contactsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.dao.SQLExecution;
import com.hanbit.contactsapp.domain.MemberBean;
import com.hanbit.contactsapp.service.UpdateService;

import java.util.HashMap;
import java.util.Map;

public class MemberupdateActivity extends AppCompatActivity {
    EditText etSalary, etAddress, etAge, etPhone, etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberupdate);
        final Context thispage = MemberupdateActivity.this;
        final Map<String, String> map = new HashMap<>();
        Intent it = this.getIntent();
        final MemberBean member = new MemberBean();
        member.setId(it.getExtras().getString("id"));
        member.setName(it.getExtras().getString("name"));
        member.setPhone(it.getExtras().getString("phoneNum"));
        member.setAddress(it.getExtras().getString("address"));
        member.setAge(it.getExtras().getString("age"));
        member.setSalary(it.getExtras().getString("salary"));
        final UpdateService service = new UpdateService() {
            @Override
            public void update(Map<?, ?> map) {
                String name = member.getName().equals("") ? member.getName() : (String) map.get("name");
                String phone = member.getPhone().equals("") ? member.getPhone() : (String) map.get("phone");
                String age = member.getAge().equals("") ? member.getAge() : (String) map.get("age");
                String address = member.getAddress().equals("") ? member.getAddress() : (String) map.get("address");
                String salary = member.getSalary().equals("") ? member.getSalary() : (String) map.get("salary");
                String sql = String.format("UPDATE Member SET name='%s', phone='%s', age='%s', address='%s', salary='%s' WHERE _id='%s';",name, phone, age, address, salary, member.getId());
                new MemberUpdate(thispage).execute(sql);
            }
        };
        etName = (EditText) findViewById(R.id.etName);
        etName.setText(member.getName());
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPhone.setText(member.getPhone());
        etAge = (EditText) findViewById(R.id.etAge);
        etAge.setText(member.getAge());
        etAddress = (EditText) findViewById(R.id.etAddress);
        etAddress.setText(member.getAddress());
        etSalary = (EditText) findViewById(R.id.etSalary);
        etSalary.setText(member.getSalary());

        findViewById(R.id.btConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("salary", etSalary.getText().toString());
                map.put("address",etAddress.getText().toString());
                map.put("age",etAge.getText().toString());
                map.put("phone",etPhone.getText().toString());
                map.put("name",etName.getText().toString());
                service.update(map);
                startActivity(new Intent(thispage, MemberlistActivity.class));
            }
        });
        findViewById(R.id.btCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(thispage, MemberlistActivity.class));
            }
        });
    }

    class MemberUpdate extends SQLExecution {
        public MemberUpdate(Context context) {
            super(context);
        }

        @Override
        public void execute(String sql) {
            this.getDatabase().execSQL(sql);
        }
    }

}
