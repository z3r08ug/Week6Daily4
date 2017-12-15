package com.example.chris.week6daily4.util;

import android.content.Context;

/**
 * Created by chris on 12/14/2017.
 */

public class SingletonSavesContext
{
    private Context context;
    private static SingletonSavesContext instance;
    
    public Context getContext()
    {
        return context;
    }
    
    public void setContext(Context context)
    {
        this.context = context;
    }
    
    public static SingletonSavesContext getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonSavesContext();
        }
        return instance;
    }
}
