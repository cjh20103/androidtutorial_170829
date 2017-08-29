package com.example.a.t04_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    class MyClass{

        public MyClass(String title, String desc, int length) {
            this.title = title;
            this.desc = desc;
            this.length = length;
        }

        String title;
        String desc;
        int imglcon;
        int length;
    }

    ArrayList<MyClass> list = new ArrayList<>();

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 스크롤 할때 나오는 View
        // 페이지 네이션 개념
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
            }
            TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            TextView descTextView = (TextView) convertView.findViewById(R.id.descTextView);
            TextView lengthTextView = (TextView) convertView.findViewById(R.id.lengthTextView);

            MyClass data = list.get(position);
            titleTextView.setText(data.title);
            descTextView.setText(data.desc);
            lengthTextView.setText(data.length/60+":"+data.length%60);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        setData();
        MyAdapter adapter = new MyAdapter();
        ListView customListView = (ListView) findViewById(R.id.customListView);
        customListView.setAdapter(adapter);


    }

    private void setData(){
        list.add(new MyClass("Someone Like You","Adele", 287));
        list.add(new MyClass("Space Bound","Eminem", 278));
        list.add(new MyClass("Stranger In Moscow","Michael Jackson", 278));
        list.add(new MyClass("Love Love","juni", 277));
        list.add(new MyClass("last Love","ju-co", 277));
        list.add(new MyClass("first Love","juni-ka", 277));
        list.add(new MyClass("first Love2","juni-ka", 277));
    }


}
