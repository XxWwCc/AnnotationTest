package com.xwc.annotationtest.annotation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class IntentUtil {

    public static void bind(Activity activity) {
        Class cls = activity.getClass();

        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();     // 使用 bundle 不直接用 intent 是因为 bundle 父类有 get() 可以直接获取值不需要知道类型

        Field[] declaredFiles = cls.getDeclaredFields();

        for (Field field : declaredFiles) {
            if (field.isAnnotationPresent(IntentAnnotation.class)) {
                IntentAnnotation intentAnnotation = field.getAnnotation(IntentAnnotation.class);

                String key = TextUtils.isEmpty(intentAnnotation.value()) ? field.getName() : intentAnnotation.value();
                // 判断 bundle 中是否有 key 值
                if (extras.containsKey(key)) {
                    // 获取值
                    Object obj = extras.get(key);
                    // Parcelable 数据类型不能直接设置
                    // 获取数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();
                    // 当前属性是数组并且是 Parcelable 子类
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) obj;
                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                        obj = objects;
                    }

                    field.setAccessible(true);

                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
