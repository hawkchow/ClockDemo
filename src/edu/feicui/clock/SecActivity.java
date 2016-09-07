package edu.feicui.clock;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import edu.feicui.clock.TimeReceiver.OnTimeListener;

public class SecActivity extends Activity implements OnTimeListener {
	TextView mTxtTime;
	TimeReceiver mReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerMyReceiver();
		setContentView(R.layout.activity_sec);
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
		mTxtTime = (TextView) findViewById(R.id.txt_time_sec);
		mReceiver.setOnTimeListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 销毁广播
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
