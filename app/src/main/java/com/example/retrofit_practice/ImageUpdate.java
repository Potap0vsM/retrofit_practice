package com.example.retrofit_practice;

public class ImageUpdate{
    public int setImage(MethodResult methodResult){
        switch (methodResult.getIcon()){
            case "01d":
                return R.drawable.clear;
            case "01n":
                return R.drawable.clearn;
            case "02d":
                return R.drawable.few;
            case "02n":
                return R.drawable.fewn;
            case "03d":
            case "03n":
                return R.drawable.scattered;
            case "04d":
            case "04n":
                return R.drawable.broken;
            case "09d":
            case "09n":
                return R.drawable.shower;
            case "10d":
                return R.drawable.rain;
            case "10n":
                return R.drawable.rainn;
            case "11d":
            case "11n":
                return R.drawable.thunder;
            case "13d":
            case "13n":
                return R.drawable.snow;
            case "50d":
            case "50n":
                return R.drawable.mist;
        }
        return 0;
    }
}
