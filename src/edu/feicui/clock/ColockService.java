package edu.feicui.clock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ColockService extends Service {
	/**
	 * ʱ��ֵ
	 */
	long time;
	/**
	 * ѭ������
	 */
	boolean flag;
	/**
	 * �㲥��ͼ
	 */
	Intent mIntent;

	@Override
	public void onCreate() {
		super.onCreate();
		mIntent = new Intent("edu.feicui.clock.MyReceiver");
		flag = true;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("tag", "��ʱ��ʼ");
		new Thread() {
			public void run() {

				// ��ѭ���У� time��1 ����һ�룻
				while (flag) {
					time++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// ѭ�����͹㲥��Ϣ
					mIntent.putExtra("time", time);
					sendBroadcast(mIntent);
				}
			};
		}.start();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		flag = false;
		Log.e("tag", "��ʱ����");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
