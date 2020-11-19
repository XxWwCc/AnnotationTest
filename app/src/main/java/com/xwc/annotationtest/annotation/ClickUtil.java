package com.xwc.annotationtest.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClickUtil {

    public static void bind(Activity activity) {
        // 获取class
        Class clazz = activity.getClass();

        // 获取activity所有方法
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            // 判断方法上是否有 Click 注解
            if (method.isAnnotationPresent(Click.class)) {
                // 获取Click注解
                Click click = method.getAnnotation(Click.class);
                // 获取注解的值
                int[] ids = click.value();
                // 设置权限
                method.setAccessible(true);

                // 创建一个方法回调
                ListenerInvocationHandler<Activity> handler = new ListenerInvocationHandler<>(method, activity);
                // 设置动态代理 代理 View.OnClickListener 的方法
                Object listenerProxy = Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{View.OnClickListener.class}, handler);

                for (int id : ids) {
                    // 找到对应的view
                    View view = activity.findViewById(id);
                    try {
                        // 获取 setOnClickListener 方法
                        Method setter = view.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                        // 发射给view设置方法
                        setter.invoke(view, listenerProxy);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ListenerInvocationHandler<T> implements InvocationHandler {

        private Method method;
        private T target;

        public ListenerInvocationHandler(Method method, T target) {
            this.method = method;
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }
}
