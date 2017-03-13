package com.hanbit.contactsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.dao.DetailQuery;
import com.hanbit.contactsapp.domain.MemberBean;
import com.hanbit.contactsapp.service.DetailService;

import java.util.HashMap;
import java.util.Map;

import static com.hanbit.contactsapp.R.id.btBack;
import static com.hanbit.contactsapp.R.id.btUpdate;

public class MemberdetailActivity extends AppCompatActivity {
    TextView name, phone, age, address, salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdetail);
        Intent it = this.getIntent();
        DetailService service = new DetailService() {
            @Override
            public Object detail(Map<?, ?> map) {
                String sql = String.format("SELECT _id AS id, name, phone, age, address, salary FROM Member WHERE _id = '%s';",map.get("id"));
                return new MemberDetail(MemberdetailActivity.this).findOne(sql);
            }
        };
        Map<String,String> map = new HashMap<>();
        map.put("id",it.getExtras().getString("id"));
        MemberBean member = (MemberBean) service.detail(map);
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
        findViewById(btUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberdetailActivity.this, MemberupdateActivity.class));
            }
        });
        findViewById(btBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberdetailActivity.this, MemberlistActivity.class));
            }
        });
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
}
