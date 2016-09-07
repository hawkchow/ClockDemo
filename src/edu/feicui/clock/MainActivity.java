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
	 * ������ͼ
	 */
	Intent mService;
	/**
	 * �㲥������
	 */
	TimeReceiver mReceiver;
	/**
	 * ��ʾʱ��ؼ�
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
	 * ��ʼ��������ͼ�����������ע��
	 */
	void init() {
		registerMyReceiver();
		mService = new Intent(this, ColockService.class);
		startService(mService);

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
		mTxtTime = (TextView) findViewById(R.id.txt_time);
		mBtn = (Button) findViewById(R.id.btn);
		mBtn.setOnClickListener(this);
		mReceiver.setOnTimeListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.btn:// ��ת���ڶ�����
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
		// ���ٷ���
		stopService(mService);
		// ע���㲥
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
