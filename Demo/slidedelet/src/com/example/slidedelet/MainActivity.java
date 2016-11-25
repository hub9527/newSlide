package com.example.slidedelet;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;


public class MainActivity extends Activity {

    private SwipeMenuListView sml;
    private MyCreate create;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sml=(SwipeMenuListView)findViewById(R.id.del);
        
        sml.setMenuCreator(new MyCreate());
        sml.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
				
				Log.d("main^^^", menu.getMenuItem(index).getTitle());
			}
		});
        
       
        
        String[]ss=new String[]{"item1","item2","items"};
        sml.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ss));
        
    }
   class MyCreate implements SwipeMenuCreator{

	@Override
	public void create(SwipeMenu menu) {
		SwipeMenuItem smiOpen=new SwipeMenuItem(MainActivity.this);
		smiOpen.setWidth(100);
		smiOpen.setTitle("ÖÃ¶¥");
		smiOpen.setTitleSize(18);
		smiOpen.setTitleColor(Color.RED);
		smiOpen.setBackground(new ColorDrawable(Color.LTGRAY));
		menu.addMenuItem(smiOpen);
		SwipeMenuItem smiDel=new SwipeMenuItem(MainActivity.this);
		smiDel.setWidth(100);
		smiDel.setTitle("É¾³ý");
		smiDel.setBackground(new ColorDrawable(Color.RED));
		smiDel.setTitleSize(18);
		smiDel.setTitleColor(Color.WHITE);
		menu.addMenuItem(smiDel);
		
		
	}}

}
