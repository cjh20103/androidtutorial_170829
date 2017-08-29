package com.example.a.p02_weatherlist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData {
        String hour;
        String day;
        String temp;
        String wfKor;
    }

    ArrayList<MyData> list = new ArrayList<>();

    enum DataType{ none, hourType, dayType, tempType, wfKorType};

    class MyTask extends AsyncTask<String, Void, String> {

        DataType type = DataType.none;

        @Override
        protected void onPostExecute(String s) {
            Log.d("count", "count:"+list.size());
        }

        @Override
        protected String doInBackground(String... params) {
            String res = "";
            try {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
                URL url = new URL(params[0]);
                xpp.setInput(url.openStream(), "utf-8");
                int eventType = xpp.getEventType();

                MyData data = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_TAG){
                        if(xpp.getName().equals("hour")) {
                            type = DataType.hourType;
                        }else if( xpp.getName().equals("day")){
                            type = DataType.dayType;
                        }else if(xpp.getName().equals("temp")){
                            type = DataType.tempType;
                        }else if(xpp.getName().equals("wfKor")){
                            type = DataType.wfKorType;
                        }
                    }else if(eventType == XmlPullParser.TEXT){
                       switch (type){
                           case hourType:
                               data = new MyData();
                               list.add(data);
                               data.hour = xpp.getText();
                               break;
                           case dayType:
                               data.day = xpp.getText();
                               break;
                           case tempType:
                               data.temp = xpp.getText();
                               break;
                           case wfKorType:
                               data.wfKor = xpp.getText();
                               break;
                       }
                       type = DataType.none;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    class MyAdapter extends BaseAdapter {


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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.activity_main, null);
            }
            TextView hour = (TextView) convertView.findViewById(R.id.hour);
            TextView day = (TextView) convertView.findViewById(R.id.day);
            TextView temp = (TextView) convertView.findViewById(R.id.temp);
            TextView wfKor = (TextView) convertView.findViewById(R.id.wfKor);

            MyData data = list.get(position);

            hour.setText(data.hour +"시간");
            day.setText(data.day+"일");
            temp.setText(data.temp+"도");
            wfKor.setText(data.wfKor);

            return convertView;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        MyAdapter adapter = new MyAdapter();
        ListView customListView = (ListView) findViewById(R.id.customListView);
        customListView.setAdapter(adapter);

        MyTask task = new MyTask();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");

    }
}
