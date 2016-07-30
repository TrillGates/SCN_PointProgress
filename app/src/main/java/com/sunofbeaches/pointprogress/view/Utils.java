
package com.sunofbeaches.pointprogress.view;

import android.app.Activity;

/**
 * 类名：Utils
 * 包名: com.wearinsoft.wismenu.customview
 * 作者: TrillGates
 * 日期: 2016/7/6 16:56
 * 描述: 这个是工具类，具体功能看注释
 */
public class Utils {

    //找出是最小的整数
    public static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    //找出最小的小数
    public static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    //异或运算（相同为false,不同为true）
    public static boolean sameSign(int x, int y) {
        return (x >= 0) ^ (y < 0);
    }

    /**
     * dip转化为px
     *
     * @param dpValue dp
     * @return
     */
    public static int Dip2px(float dpValue,Activity activity) {
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



}
