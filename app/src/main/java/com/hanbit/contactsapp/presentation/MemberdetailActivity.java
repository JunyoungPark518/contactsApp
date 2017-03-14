package com.hanbit.contactsapp.presentation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.dao.DetailQuery;
import com.hanbit.contactsapp.domain.MemberBean;
import com.hanbit.contactsapp.service.DetailService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

public class MemberdetailActivity extends AppCompatActivity {
    TextView name, phone, age, address, salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdetail);
        Context context = MemberdetailActivity.this;
        Map<String, String> map = new HashMap<>();
        Intent it = this.getIntent();
        map.put("id", it.getExtras().getString("id"));

        List<Button> buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.btDial));
        buttons.add((Button) findViewById(R.id.btCall));
        buttons.add((Button) findViewById(R.id.btBack));
        buttons.add((Button) findViewById(R.id.btUpdate));
        DetailService service = new DetailService() {
            @Override
            public Object detail(Map<?, ?> map) {
                String sql = String.format("SELECT _id AS id, name, phone, age, address, salary FROM Member WHERE _id = '%s';", map.get("id"));
                return new MemberDetail(MemberdetailActivity.this).findOne(sql);
            }
        };

        final MemberBean member = (MemberBean) service.detail(map);
        name = (TextView) findViewById(R.id.tvName);
        phone = (TextView) findViewById(R.id.tvPhone);
        age = (TextView) findViewById(R.id.tvAge);
        address = (TextView) findViewById(R.id.tvAddress);
        salary = (TextView) findViewById(R.id.tvSalary);
        name.setText("이름:  " + member.getName());
        phone.setText("전화번호:  " + member.getPhone());
        age.setText("나이:  " + member.getAge());
        address.setText("주소:  " + member.getAddress());
        salary.setText("연봉:  " + member.getSalary());
        map.put("phoneNum",member.getPhone());
        map.put("name", member.getName());
        map.put("address",member.getAddress());
        map.put("age",member.getAge());
        map.put("salary",member.getSalary());
        new ButtonObserver(context, map, buttons).onClick(this.findViewById(android.R.id.content));
    }
    class MemberDetail extends DetailQuery {
        public MemberDetail(Context context) {
            super(context);
        }
        @Override
        public Object findOne(String sql) {
            SQLiteDatabase db = this.getDatabase();
            Cursor c = db.rawQuery(sql,null);
            MemberBean bean = null;
            if(c!=null) {
                if(c.moveToFirst()) {
                    do{
                        bean = new MemberBean();
                        bean.setId(c.getString(c.getColumnIndex("id")));
                        bean.setName(c.getString(c.getColumnIndex("name")));
                        bean.setPhone(c.getString(c.getColumnIndex("phone")));
                        bean.setAge(c.getString(c.getColumnIndex("age")));
                        bean.setAddress(c.getString(c.getColumnIndex("address")));
                        bean.setSalary(c.getString(c.getColumnIndex("salary")));
                    }while(c.moveToNext());
                }
            }
            return bean;
        }
    }
    class ButtonObserver implements View.OnClickListener {
        Context context;
        Map<String, String> map;
        List<Button> buttons;

        public ButtonObserver(Context context, Map<String, String> map, List<Button> buttons) {
            this.context = context;
            this.map = map;
            this.buttons = buttons;
            for(Button b : buttons) {
                b.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            Intent it;
            switch(v.getId()) {
                case R.id.btDial:
                    it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + map.get("phoneNum")));
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);
                    break;
                case R.id.btCall:
                    it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + map.get("phoneNum")));
                    if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MemberdetailActivity.this, new String[]{
                                Manifest.permission.CALL_PHONE
                        }, 2);
                    }
                    break;
                case R.id.btBack:
                    startActivity(new Intent(context, MemberlistActivity.class));
                    break;
                case R.id.btUpdate:
                    Map<String, MemberBean> update = new HashMap<>();
                    MemberBean member = new MemberBean();
                    member.setId(map.get("id"));
                    it = new Intent(context, MemberupdateActivity.class);
                    it.putExtra("id", map.get("id"));
                    it.putExtra("phoneNum", map.get("phoneNum"));
                    it.putExtra("name", map.get("name"));
                    it.putExtra("address", map.get("address"));
                    it.putExtra("age", map.get("age"));
                    it.putExtra("salary", map.get("salary"));
                    startActivity(it);
                    break;
            }
        }
    }

}
