package com.example.retrofit_practice;

import androidx.annotation.NonNull;

public class MethodResult {
    private float temp;
    private int pres;
    private int hum;
    private String main;
    private String description;

    private String icon;

    public void setTemp(float temp) {this.temp = temp;}

    public void setPres(int pres) {this.pres = pres;}

    public void setHum(int hum) {this.hum = hum;}

    public float getTemp() {return temp;}

    public int getPres() {return pres;}

    public int getHum() {return hum;}

    public String getMain() {return main;}

    public void setMain(String main) {this.main = main;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @NonNull
    @Override
    public String toString() {
        return "MethodResult{" +
                "temp=" + temp +
                ", pres=" + pres +
                ", hum=" + hum +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
