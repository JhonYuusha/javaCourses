package com.joao.listacursos.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.joao.listacursos.view.MainActivity;

public class UiUtils {

    public static void showToast(Context context, String message) {
        showToast(context, message, false);
    }

    public static void showToast(Context context, String message, boolean isLong) {
        Toast.makeText(context, message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public static void showDialog(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    public static void finishWithDelay(Context context, long delayMillis) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (context instanceof MainActivity) {
                ((MainActivity) context).finish();
                System.exit(0);
            }
        }, delayMillis);
    }
};