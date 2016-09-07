package edu.feicui.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * ������ʵ�ֹ㲥����
 * 
 */
public class TimeReceiver extends BroadcastReceiver {
	OnTimeListener mListener;

	@Override
	public void onReceive(Context context, Intent intent) {
		long time = intent.getLongExtra("time", 0);
		// Log.e("time", "" + time);
		// ÿ����һ��ʱ��ֵ��ˢ��һ�ν���
		mListener.onFlush(time);
	}

	public void setOnTimeListener(OnTimeListener listener) {
		mListener = listener;
	}

	public interface OnTimeListener {
		// �÷�������ˢ�½�����ʾ
		void onFlush(long time);
	}
}
