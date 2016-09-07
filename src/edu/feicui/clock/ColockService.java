package edu.feicui.clock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ColockService extends Service {
	/**
	 * 时间值
	 */
	long time;
	/**
	 * 循环开关
	 */
	boolean flag;
	/**
	 * 广播意图
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
		Log.e("tag", "计时开始");
		new Thread() {
			public void run() {

				// 在循环中， time加1 休眠一秒；
				while (flag) {
					time++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 循环发送广播信息
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
		Log.e("tag", "计时结束");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
