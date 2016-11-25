package com.example.leftdelet;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MySwipeView extends FrameLayout {
   private View contentView,deleteView;
   private int contentWidth,contentHeight,deleteWidth;
   private SwipeState swipe=SwipeState.Closed;
   private ViewDragHelper vdh=ViewDragHelper.create(MySwipeView.this,1.0f, new MyCallBack());
	public MySwipeView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public MySwipeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MySwipeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	//拦截事件,触摸交给拖拽去处理
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return vdh.shouldInterceptTouchEvent(ev);
	}
	
	@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
		    vdh.processTouchEvent(event);
			return true;
		}

	//---加载完成
	@Override
		protected void onFinishInflate() {
			// TODO Auto-generated method stub
			super.onFinishInflate();
			contentView=getChildAt(0);
			deleteView=getChildAt(1);
		}
	  @Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			// TODO Auto-generated method stub
			super.onSizeChanged(w, h, oldw, oldh);
			contentWidth=contentView.getMeasuredWidth();
			contentHeight=contentView.getMeasuredHeight();
			deleteWidth=deleteView.getMeasuredWidth();
		}
	  //布局
	  @Override
		protected void onLayout(boolean changed, int left, int top, int right,
				int bottom) {
			// TODO Auto-generated method stub
			super.onLayout(changed, left, top, right, bottom);
			contentView.layout(0, 0, contentWidth, contentHeight);
			deleteView.layout(contentWidth, 0, contentWidth+deleteWidth, contentHeight);
		}
	  //触摸事件
	  
	//拖拽监听接口
     class MyCallBack extends ViewDragHelper.Callback{

		@Override
		public boolean tryCaptureView(View arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}
		
		
   
		@Override//控件位置改变
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			// TODO Auto-generated method stub
			super.onViewPositionChanged(changedView, left, top, dx, dy);
			if(changedView==contentView)
				deleteView.layout(deleteView.getLeft()+dx,
						deleteView.getTop(), deleteView.getRight()+dx, deleteView.getBottom());
			else if(changedView==deleteView)
				contentView.layout(contentView.getLeft()+dx,
						contentView.getTop(), contentView.getRight()+dx, contentView.getBottom());
			
			if(contentWidth==0&&swipe==SwipeState.Closed){
				swipe=SwipeState.Closed;
			    unClosedSwipe.remove(MySwipeView.this);	
			}
			else if(contentWidth==-deleteWidth&&swipe==SwipeState.OPEN){
				swipe=SwipeState.OPEN;
				for (int i = 0; i <unClosedSwipe.size(); i++) {
					if(unClosedSwipe.get(i)!=MySwipeView.this)
						unClosedSwipe.get(i).close();
					
				}
				if(!unClosedSwipe.contains(MySwipeView.this))
				    unClosedSwipe.add(MySwipeView.this);}
			else {
				swipe=SwipeState.SWipe;
				for (int i = 0; i <unClosedSwipe.size(); i++) {
					if(unClosedSwipe.get(i)!=MySwipeView.this&&dx<0)
						unClosedSwipe.get(i).close();
					
				}
				if(!unClosedSwipe.contains(MySwipeView.this))
				    unClosedSwipe.add(MySwipeView.this);
			}
		}    



		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			// TODO Auto-generated method stub
			super.onViewReleased(releasedChild, xvel, yvel);
			if(contentView.getLeft()<-deleteWidth/2){
				open();
			}
			else {
				close();
			}
		}



		



		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			// TODO Auto-generated method stub
		 if(child==contentView){
			if(left>0)left=0;
			if(left<-deleteWidth)left=-deleteWidth;}
		 else {
			if(left>contentWidth)left=contentWidth;
			if(left<contentWidth-deleteWidth)left=contentWidth-deleteWidth;
		}
			return left;
		}

		@Override
		public int getViewHorizontalDragRange(View child) {
			// TODO Auto-generated method stub
			return deleteWidth;
		}
		
    	 
     }
     
     @Override
    	public void computeScroll() {
    		// TODO Auto-generated method stub
    		super.computeScroll();
    		if(vdh.continueSettling(true))
    			ViewCompat.postInvalidateOnAnimation(MySwipeView.this);
    	}
     //定义枚举控制开头
     enum SwipeState{
    	 OPEN,Closed,SWipe;
     }
     
     public  void open() {
			vdh.smoothSlideViewTo(contentView, -deleteWidth, 0);
			ViewCompat.postInvalidateOnAnimation(MySwipeView.this);
		}

	public void close() {
		vdh.smoothSlideViewTo(contentView, 0, 0);
		ViewCompat.postInvalidateOnAnimation(MySwipeView.this);
	}
	
	public static List<MySwipeView>unClosedSwipe=new ArrayList<MySwipeView>();
}
