package com.stacktrace.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
	private ViewPager viewPager;
	private TextView tv_intro;
	private LinearLayout dot_layout;
	
	private ArrayList<com.stacktrace.viewpager.Ad> list = new ArrayList<com.stacktrace.viewpager.Ad>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		
		initData();
		initListener();
	}


	private void initView() {
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		tv_intro = (TextView) findViewById(R.id.tv_intro);
		dot_layout = (LinearLayout) findViewById(R.id.dot_layout);
	}
	
	private void initListener() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				updateIntroAndDot();
			}
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}
	
	private void initData() {
		list.add(new com.stacktrace.viewpager.Ad(R.drawable.a, "巩俐不低俗，我就不能低俗"));
		list.add(new com.stacktrace.viewpager.Ad(R.drawable.b, "朴树又回来了，再唱经典老歌引百万人同唱啊"));
		list.add(new com.stacktrace.viewpager.Ad(R.drawable.c, "揭秘北京电影如何升级"));
		list.add(new com.stacktrace.viewpager.Ad(R.drawable.d, "乐视网TV版大放送"));
		list.add(new com.stacktrace.viewpager.Ad(R.drawable.e, "热血屌丝的反杀"));
		
		initDots();
		
		viewPager.setAdapter(new MyPagerAdapter());
		
		updateIntroAndDot();
	}
	
	/**
	 * 初始化dot
	 */
	private void initDots(){
		for (int i = 0; i < list.size(); i++) {
			View view = new View(this);
			LayoutParams params = new LayoutParams(20, 20);
			if(i != 0){
				params.leftMargin = 10;
			}
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selector_dot);
			dot_layout.addView(view);
		}
	}

	/**
	 * 更新文本
	 */
	private void updateIntroAndDot(){
		int currentPage = viewPager.getCurrentItem()%list.size();
		tv_intro.setText(list.get(currentPage).getIntro());
		
		//获取焦点
		for (int i = 0; i < dot_layout.getChildCount(); i++) {
			dot_layout.getChildAt(i).setEnabled(i==currentPage);
		}
	}
	
	class MyPagerAdapter extends PagerAdapter{
		
		
		@Override
		public int getCount() {
			return list.size();
		}

		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(MainActivity.this, R.layout.adapter_ad, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			
			com.stacktrace.viewpager.Ad ad = list.get(position%list.size());
			imageView.setImageResource(ad.getIconResId());
			
			container.addView(view);//一定不能少，将view加入到viewPager中
			
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
		
		
	}

}
