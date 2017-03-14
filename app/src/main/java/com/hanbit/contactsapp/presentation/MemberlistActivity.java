package com.hanbit.contactsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.contactsapp.R;
import com.hanbit.contactsapp.dao.ListQuery;
import com.hanbit.contactsapp.dao.SQLExecution;
import com.hanbit.contactsapp.domain.MemberBean;
import com.hanbit.contactsapp.service.DeleteService;
import com.hanbit.contactsapp.service.ListService;

import java.util.ArrayList;
import java.util.Map;

import static com.hanbit.contactsapp.R.id.btAdd;

public class MemberlistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlist);
        final ListView mList = (ListView) findViewById(R.id.mList);
        final MemberBean member = new MemberBean();
        ListService service = new ListService() {
            @Override
            public ArrayList<?> list() {
                return new MemberList(MemberlistActivity.this).list("SELECT _id AS id, name, phone, age, address, salary FROM Member;");
            }
        };
        mList.setAdapter(new MemberAdapter(service.list(), this)); // Adapter Pattern
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                Intent it = new Intent(MemberlistActivity.this, MemberdetailActivity.class);
                it.putExtra("id", ((MemberBean) mList.getItemAtPosition(i)).getId());
                startActivity(it);
            }
        });
        DeleteService delservice = new DeleteService() {
            @Override
            public void delete(Map<?, ?> map) {

            }
        };
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String sql = "";
                new MemberDelete(MemberlistActivity.this).execute(sql);
                return true;
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
    class MemberAdapter extends BaseAdapter {
        ArrayList<?> list;
        LayoutInflater inflater;
        private int[] photos = {
          R.drawable.cupcake,
          R.drawable.donut,
          R.drawable.eclair,
          R.drawable.froyo,
          R.drawable.gingerbread,
          R.drawable.honeycomb,
          R.drawable.icecream,
          R.drawable.jellybean,
          R.drawable.kitkat,
          R.drawable.lollipop
        };

        public MemberAdapter(ArrayList<?> list, Context context) {
            this.list = list;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup g) {
            ViewHolder holder;
            if(v==null) {
                v = inflater.inflate(R.layout.member_item, null); // item 1개에 1개의 data, 1:1 대응
                holder = new ViewHolder();
                holder.profileImg = (ImageView) v.findViewById(R.id.profileImg);
                holder.tvName = (TextView) v.findViewById(R.id.tvName);
                holder.tvPhone = (TextView) v.findViewById(R.id.tvPhone);
                v.setTag(holder); // Basic Syntax
            } else {
                holder = (ViewHolder) v.getTag(); // 이미 있는 것 가져옴
            }
            holder.profileImg.setImageResource(photos[i]);
            holder.tvName.setText(((MemberBean)list.get(i)).getName());
            holder.tvPhone.setText(((MemberBean)list.get(i)).getPhone());
            return v; // 기존에 받은 파라미터에 변화를 주어 반환
        }
    }
    // getter, setter를 안쓰기 위해 static
    static class ViewHolder{
        ImageView profileImg;
        TextView tvName, tvPhone;
    }
    class MemberDelete extends SQLExecution {
        public MemberDelete(Context context) {
            super(context);
        }

        @Override
        public void execute(String sql) {
            this.getDatabase().execSQL(sql);
        }
    }
}
