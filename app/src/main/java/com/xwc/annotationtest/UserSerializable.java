package com.xwc.annotationtest;

import java.io.Serializable;
import java.util.Arrays;

public class UserSerializable implements Serializable {
    private String id = "";
    private String[] imgUrls;
    private int age = 0;

    public UserSerializable(String id, String[] imgUrls, int age) {
        this.id = id;
        this.imgUrls = imgUrls;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserSerializable{" +
                "id='" + id + '\'' +
                ", imgUrls=" + Arrays.toString(imgUrls) +
                ", age=" + age +
                '}';
    }
}
