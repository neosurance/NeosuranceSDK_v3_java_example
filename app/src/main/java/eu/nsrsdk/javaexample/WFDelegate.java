package eu.nsrsdk.javaexample;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import eu.nsrsdk.v3java.NSRWorkflowDelegate;

public class WFDelegate implements NSRWorkflowDelegate {

	public final static String TAG = "NSRSDKJavaExample - NSRWorkflowDelegate";

	@Override
	public boolean executeLogin(final Context ctx, final String url) {
		setData(ctx, "login_url", url);
		return true;
	}

	@Override
	public JSONObject executePayment(final Context ctx, final JSONObject payment, final String url) {
		setData(ctx, "payment_url", url);
		return null;
	}

	@Override
	public void confirmTransaction(Context ctx, JSONObject paymentInfo) {

	}

	@Override
	public void keepAlive() {
		Log.d(TAG, "keepAlive");
	}

	public static String getData(Context ctx, String key) {
		SharedPreferences sp = ctx.getSharedPreferences("NSRSample", Application.MODE_PRIVATE);
		if (sp.contains(key)) {
			return sp.getString(key, "");
		} else {
			return null;
		}
	}

	@Override
	public void goTo(final Context ctx, final String area) {
		Log.d(TAG, "goTo: " + area);
	}

	public static void setData(Context ctx, String key, String value) {
		SharedPreferences sp = ctx.getSharedPreferences("NSRSample", Application.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		if (value != null) {
			editor.putString(key, value);
		} else {
			editor.remove(key);
		}
		editor.commit();
	}
}
