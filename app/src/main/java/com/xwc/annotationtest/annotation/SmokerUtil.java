package com.xwc.annotationtest.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class SmokerUtil {

    public static void bind(Activity activity) {
        // 获取class
        Class cls = activity.getClass();

        // 反射获取 activity 中的成员变量
        Field[] declaredFields = cls.getDeclaredFields();

        // 获取成员
        for (Field field : declaredFields) {
            // 判断成员上是否有注解
            if (field.isAnnotationPresent(Smoker.class)) {
                // 获取注解
                Smoker smoker = field.getAnnotation(Smoker.class);
                // 获取注解的值
                int id = smoker.value();

                // 获取id对应的控件
                View view = activity.findViewById(id);

                // 设置访问权限
                field.setAccessible(true);

                // 赋值
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
