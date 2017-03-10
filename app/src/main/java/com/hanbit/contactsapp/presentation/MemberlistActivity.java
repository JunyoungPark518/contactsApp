package com.hanbit.contactsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.dao.ListQuery;
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

        findViewById(btDetail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MemberList mlist = new MemberList(MemberlistActivity.this);
                ListService service = new ListService() {
                    @Override
                    public ArrayList<?> list() {
                        return mlist.list("SELECT _id AS id, name, phone, age, address, salary FROM Member;");
                    }
                };
                ArrayList<MemberBean> list = (ArrayList<MemberBean>)service.list();
                Toast.makeText(MemberlistActivity.this, list.get(0).toString(), Toast.LENGTH_LONG).show();
                Intent it = new Intent(MemberlistActivity.this, MemberdetailActivity.class);
                it.putExtra("id",list.get(0).getId());
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
    class MemberList extends ListQuery {
        public MemberList(Context context) {
            super(context);
        }

        @Override
        public ArrayList<?> list(String sql) {
            ArrayList<MemberBean> list = new ArrayList<>();
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
                        list.add(bean);
                    }while(c.moveToNext());
                }
            }
            return list;
        }
    }
}
