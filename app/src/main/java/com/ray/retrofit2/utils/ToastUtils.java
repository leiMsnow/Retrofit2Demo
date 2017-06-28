package com.ray.retrofit2.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ray.retrofit2.MyApplication;
import com.ray.retrofit2.R;

/**
 * 单例tost
 *
 * @author zhangleilei
 */
public class ToastUtils {

    private Context context;
    private Toast toast;
    private TextView tv;
    private static ToastUtils instance;

    private ToastUtils(Context context) {
        this.context = context.getApplicationContext();
        createToast();
    }

    public static ToastUtils getInstance() {
        if (instance == null) {
            synchronized (ToastUtils.class) {
                if (instance == null) {
                    instance = new ToastUtils(MyApplication.getApplication());
                }
            }
        }
        return instance;
    }

    private void createToast() {
        toast = new Toast(context);
        View v = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
        tv = ((TextView) v.findViewById(R.id.tv_toast));
        toast.setView(v);
    }

    private void show(final String message, final int duration) {
        tv.setText(message);
        toast.setDuration(duration);
        toast.show();
    }

    public void showToast(final String message, final int duration) {
        show(message, duration);
    }

    public void showToast(final String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    public void showToast(final int stringId) {
        show(context.getString(stringId), Toast.LENGTH_SHORT);
    }

    public void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
