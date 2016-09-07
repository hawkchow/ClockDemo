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
	 * ��̬ע��㲥
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
		// ���ٹ㲥
		unregisterReceiver(mReceiver);
	}

	/*
	 * �÷�������ˢ�½�����ʾ
	 */
	@Override
	public void onFlush(long time) {
		long sec = time % 60;
		long min = time / 60 % 60;
		long hour = time / 60 / 60 % 24;
		String str = String.format("%1$dʱ%2$d��%3$d��", hour, min, sec);
		mTxtTime.setText(str);
	}

}
