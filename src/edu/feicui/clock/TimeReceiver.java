package edu.feicui.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 单独类实现广播接收
 * 
 */
public class TimeReceiver extends BroadcastReceiver {
	OnTimeListener mListener;

	@Override
	public void onReceive(Context context, Intent intent) {
		long time = intent.getLongExtra("time", 0);
		// Log.e("time", "" + time);
		// 每接收一次时间值，刷新一次界面
		mListener.onFlush(time);
	}

	public void setOnTimeListener(OnTimeListener listener) {
		mListener = listener;
	}

	public interface OnTimeListener {
		// 该方法用来刷新界面显示
		void onFlush(long time);
	}
}
