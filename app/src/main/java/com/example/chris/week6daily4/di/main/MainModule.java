package com.example.chris.week6daily4.di.main;

import com.example.chris.week6daily4.view.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 11/29/2017.
 */

@Module
public class MainModule
{
    @Provides
    MainPresenter providerMainPresenter()
    {
        return new MainPresenter();
    }
}
