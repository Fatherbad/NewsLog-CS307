package com.example.paul.demo;

/**
 * Created by steevie on 12/8/2015.
 */
public class themeHandler {
    private static int theme = 0;

    public void changeTheme(){
        if(theme == 0) {
            theme = 1;
        }else{
            theme = 0;
        }

    }

    public int getTheme(){
        return theme;
    }
}
