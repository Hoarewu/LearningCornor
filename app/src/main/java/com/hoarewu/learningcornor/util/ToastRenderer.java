package com.hoarewu.learningcornor.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by danielrosowski on 17.07.16.
 */
public class ToastRenderer {

    public void show(Context ctx, String msg) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(ctx, msg, duration);
        toast.show();
    }
}
