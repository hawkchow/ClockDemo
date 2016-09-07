package edu.feicui.clock;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import edu.feicui.clock.TimeReceiver.OnTimeListener;

public class MainActivity extends Activity implements OnClickListener,
		OnTimeListener {
	/**
	 * 服务意图
	 */
	Intent mService;
	/**
	 * 广播接收器
	 */
	TimeReceiver mReceiver;
	/**
	 * 显示时间控件
	 */
	TextView mTxtTime;
	Button mBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.activity_main);
	}

	/**
	 * 初始化服务意图，开启服务和注册
	 */
	void init() {
		registerMyReceiver();
		mService = new Intent(this, ColockService.class);
		startService(mService);

	}

	/**
	 * 动态注册广播
	 */
	void registerMyReceiver() {
		mReceiver = new TimeReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("edu.feicui.clock.MyReceiver");
		registerReceiver(mReceiver, filter);
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();
		mTxtTime = (TextView) findViewById(R.id.txt_time);
		mBtn = (Button) findViewById(R.id.btn);
		mBtn.setOnClickListener(this);
		mReceiver.setOnTimeListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.btn:// 跳转至第二界面
			Intent intent = new Intent(this, SecActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 销毁服务
		stopService(mService);
		// 注销广播
		unregisterReceiver(mReceiver);
	}

	/*
	 * 该方法用来刷新界面显示
	 */
	@Override
	public void onFlush(long time) {
		long sec = time % 60;
		long min = time / 60 % 60;
		long hour = time / 60 / 60 % 24;
		String str = String.format("%1$d时%2$d分%3$d秒", hour, min, sec);
		mTxtTime.setText(str);
	}

}
