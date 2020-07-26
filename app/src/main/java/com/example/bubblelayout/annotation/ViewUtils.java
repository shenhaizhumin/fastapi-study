package com.example.bubblelayout.annotation;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {

    private static ViewUtils viewUtils = new ViewUtils();

    public static ViewUtils getInstance() {
        return viewUtils;
    }

    public <T extends AppCompatActivity> void inject(T activity) {
        bindView(activity);
        bindClick(activity);
    }

    public <T extends AppCompatActivity> void bindView(T activity) {
        Class<? extends AppCompatActivity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                BindView annotation = field.getAnnotation(BindView.class);
                int viewId = annotation.id();
                field.setAccessible(true);
                try {
                    field.set(activity, activity.findViewById(viewId));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }
            }
        }
    }

    public <T extends AppCompatActivity> void bindClick(T activity) {
        Class<? extends AppCompatActivity> aClass = activity.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(BindClick.class)) {
                BindClick annotation = declaredMethod.getAnnotation(BindClick.class);
                int viewID = annotation.id();
                View view = activity.findViewById(viewID);
                view.setOnClickListener(v -> {
                    declaredMethod.setAccessible(true);
                    try {
                        declaredMethod.invoke(activity, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        declaredMethod.setAccessible(false);
                    }
                });
            }
        }
    }
}
