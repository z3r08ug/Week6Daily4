package com.example.chris.week6daily4.di.main;


import com.example.chris.week6daily4.view.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Admin on 11/29/2017.
 */

@Subcomponent(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
}
