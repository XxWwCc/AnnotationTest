package com.xwc.annotationtest.view;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * describe:
 * author: xuweichao
 * date: 2020/9/16 15:27
 */
public class Utils {

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    // 适配分辨率不同的手机
    public static float getZForCamera() {
        return -6 * Resources.getSystem().getDisplayMetrics().density;
    }
}
