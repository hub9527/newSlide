package com.example.leftdelet;

import java.util.ArrayList;
import java.util.List;

import com.example.leftdelet.MySwipeView.SwipeState;
import com.example.leftdelet.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity{

    private String url="www.it165.com/pro/html/201505/40127.html";
    private ListView lv;
    private List<String>sList=new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.lv);
        for (int i = 0; i <50; i++) {
			sList.add("this is a item......×ó»¬¿É ÒÔÉ¾³ýÅ¶Â·Ò×.....");
		}
        
        lv.setAdapter(new MyAdapter());
    }
    class MyAdapter extends BaseAdapter implements OnItemClickListener,OnClickListener{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return sList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHodler vh;
			if(convertView==null){
				vh=new ViewHodler();
				convertView=View.inflate(MainActivity.this, R.layout.item_main, null);
				vh.content=(TextView)convertView.findViewById(R.id.content);
				vh.delete=(TextView)convertView.findViewById(R.id.dele);
				convertView.setTag(vh);
			}
			else 
				vh=(ViewHodler)convertView.getTag();
			vh.content.setText("this is item .....+"+position);
			vh.delete.setText("É¾³ý");
			vh.delete.setTag(""+position);
			vh.delete.setOnClickListener(this);
			
			return convertView;
		}
		
		class ViewHodler {
			TextView content,delete;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onClick(View arg0) {
			if(arg0.getId()==id.dele){
				String pos=(String)arg0.getTag();
				((MySwipeView)arg0.getParent()).close();
				sList.remove(Integer.parseInt(pos));
				notifyDataSetChanged();
			}
				
			
		}
    	
    }
	
	class MyListener implements OnScrollListener{

		@Override
		public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			for (int i = 0; i <MySwipeView.unClosedSwipe.size(); i++) {
				MySwipeView.unClosedSwipe.get(i).close();
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	}

   
