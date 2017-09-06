package zhao.siqi.mvptestdemo.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import zhao.siqi.mvptestdemo.MyApplication;

/**
 * SharedPreferences辅助类，线程安全
 *@作者：chao.Cheng
 *@创建时间 2012 下午3:27:05
 */
public class SPHelper {
	//========================== SharedPreferences key =========================
	public final static String SHAREPREFERENCES_NAME = "com.newborntown.android.solo.fitness.SharedPreferences";
	
	private static final SPHelper mSPHelper = new SPHelper();
	private SharedPreferences mSP;
	private Editor mEditor;
	
	public static SPHelper getInstance() {
		return mSPHelper;
	}
	
	private void open() {
		if(mSP == null) {
			mSP= MyApplication.getInstance().getSharedPreferences(SHAREPREFERENCES_NAME, 0);
			mEditor = mSP.edit();
		}
	}
	
	public synchronized String getString(String key, String defValue) {
		open();
		return mSP.getString(key, defValue);
	}
	
	public synchronized void setString(String key, String value) {
		open();
		mEditor.putString(key, value);
		mEditor.commit();
	}
	
	public synchronized int getInt(String key, int defValue) {
		open();
		return mSP.getInt(key, defValue);
	}
	public synchronized void setInt(String key, int value) {
		open();
		mEditor.putInt(key, value);
		mEditor.commit();
	}
	
	public synchronized long getLong(String key, long defValue) {
		open();
		return mSP.getLong(key, defValue);
	}
	public synchronized void setLong(String key, long value) {
		open();
		mEditor.putLong(key, value);
		mEditor.commit();
	}
	
	public synchronized float getFloat(String key, float defValue) {
		open();
		return mSP.getFloat(key, defValue);
	}
	public synchronized void seFloat(String key, float value) {
		open();
		mEditor.putFloat(key, value);
		mEditor.commit();
	}
	
	public synchronized boolean getBoolean(String key, boolean defValue) {
		open();
		return mSP.getBoolean(key, defValue);
	}
	public synchronized void setBoolean(String key, boolean value) {
		open();
		mEditor.putBoolean(key, value);
		mEditor.commit();
	}
	
}
