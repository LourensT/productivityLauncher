package com.example.productivitylauncher;

import java.io.Serializable;

public class appInfo implements Serializable, Comparable<appInfo> {

    CharSequence label;
    CharSequence packageName;
    boolean important;

    public appInfo(CharSequence label, CharSequence packageName, boolean important){
        this.label = label;
        this.packageName = packageName;
        this.important = important;
    }

    public appInfo(){

    }

    @Override
    public int compareTo(appInfo other){
        //return
        return this.label.toString().compareTo(other.label.toString());
    }

}