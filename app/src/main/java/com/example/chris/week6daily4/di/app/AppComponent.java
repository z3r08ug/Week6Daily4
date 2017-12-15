package com.example.chris.week6daily4.di.app;


import com.example.chris.week6daily4.di.barcode.BarcodeComponent;
import com.example.chris.week6daily4.di.barcode.BarcodeModule;
import com.example.chris.week6daily4.di.main.MainComponent;
import com.example.chris.week6daily4.di.main.MainModule;

import dagger.Component;

/**
 * Created by chris on 12/7/2017.
 */

@Component(modules = AppModule.class)
public interface AppComponent
{
    MainComponent add(MainModule mainModule);
    BarcodeComponent add(BarcodeModule barcodeModule);
}
