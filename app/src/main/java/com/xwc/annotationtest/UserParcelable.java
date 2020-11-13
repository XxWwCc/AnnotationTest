package com.xwc.annotationtest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class UserParcelable implements Parcelable {

    private String id = "";
    private String[] imgUrls;
    private int age = 0;

    public UserParcelable(String id, String[] imgUrls, int age) {
        this.id = id;
        this.imgUrls = imgUrls;
        this.age = age;
    }

    protected UserParcelable(Parcel in) {
        id = in.readString();
        imgUrls = in.createStringArray();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeStringArray(imgUrls);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel in) {
            return new UserParcelable(in);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };

    @Override
    public String toString() {
        return "UserParcelable{" +
                "id='" + id + '\'' +
                ", imgUrls=" + Arrays.toString(imgUrls) +
                ", age=" + age +
                '}';
    }
}
